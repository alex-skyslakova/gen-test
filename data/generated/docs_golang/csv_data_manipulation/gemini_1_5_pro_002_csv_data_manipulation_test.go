package main

import (
	"encoding/csv"
	"io/ioutil"
	"os"
	"strconv"
	"testing"
)

func TestReadSample(t *testing.T) {
	ioutil.WriteFile("sample.csv", []byte("C1,C2,C3,C4,C5\n1,5,9,13,17\n2,6,10,14,18\n3,7,11,15,19\n4,8,12,16,20"), 0644)
	defer os.Remove("sample.csv")

	expected := [][]string{
		{"C1", "C2", "C3", "C4", "C5"},
		{"1", "5", "9", "13", "17"},
		{"2", "6", "10", "14", "18"},
		{"3", "7", "11", "15", "19"},
		{"4", "8", "12", "16", "20"},
	}

	rows := readSample()
	if !equalStringMatrix(rows, expected) {
		t.Errorf("readSample() returned:\n%v\nexpected:\n%v", rows, expected)
	}


	_, err := os.Open("nonexistent.csv")
	if err == nil{
		t.Errorf("readSample() should return error when file not present")
	}

	ioutil.WriteFile("bad.csv", []byte("C1,C2,C3\n1,5,a"), 0644)
	defer os.Remove("bad.csv")
	f, _ := os.Open("bad.csv")
	_, err = csv.NewReader(f).ReadAll()
	if err == nil {
		t.Error("ReadAll should return error for invalid csv")
	}
	f.Close()



}

func TestAppendSum(t *testing.T) {
	rows := [][]string{
		{"C1", "C2", "C3"},
		{"1", "2", "3"},
		{"4", "5", "6"},
	}

	expected := [][]string{
		{"C1", "C2", "C3", "SUM"},
		{"1", "2", "3", "6"},
		{"4", "5", "6", "15"},
	}

	appendSum(rows)
	if !equalStringMatrix(rows, expected) {
		t.Errorf("appendSum() modified rows to:\n%v\nexpected:\n%v", rows, expected)
	}


	rows = [][]string{
		{"C1", "C2", "C3"},
		{"1", "a", "3"},
		{"4", "5", "6"},
	}

	expected = [][]string{
		{"C1", "C2", "C3", "SUM"},
		{"1", "a", "3", "NA"},
		{"4", "5", "6", "15"},
	}

	appendSum(rows)
	if !equalStringMatrix(rows, expected) {
		t.Errorf("appendSum() modified rows to:\n%v\nexpected:\n%v", rows, expected)
	}
}

func TestSum(t *testing.T) {
	testCases := []struct {
		input    []string
		expected string
	}{
		{[]string{"1", "2", "3"}, "6"},
		{[]string{"4", "5", "6"}, "15"},
		{[]string{"1", "a", "3"}, "NA"},
	}

	for _, tc := range testCases {
		actual := sum(tc.input)
		if actual != tc.expected {
			t.Errorf("sum(%v) returned %s, expected %s", tc.input, actual, tc.expected)
		}
	}
}

func TestWriteChanges(t *testing.T) {

	rows := [][]string{
		{"C1", "C2", "SUM"},
		{"1", "2", "3"},
		{"4", "5", "9"},
	}


	writeChanges(rows)
	defer os.Remove("output.csv")
	r := readSampleModified("output.csv")
	if !equalStringMatrix(r, rows) {
		t.Errorf("writeChanges() wrote:\n%v\nexpected:\n%v", r, rows)
	}
}

func equalStringMatrix(m1, m2 [][]string) bool {
	if len(m1) != len(m2) {
		return false
	}
	for i := range m1 {
		if len(m1[i]) != len(m2[i]) {
			return false
		}
		for j := range m1[i] {
			if m1[i][j] != m2[i][j] {
				return false
			}
		}
	}
	return true
}

func readSampleModified(fileName string) [][]string {
	f, err := os.Open(fileName)
	if err != nil {
		log.Fatal(err)
	}
	rows, err := csv.NewReader(f).ReadAll()
	f.Close()
	if err != nil {
		log.Fatal(err)
	}
	return rows
}


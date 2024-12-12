package main

import (
	"encoding/csv"
	"os"
	"reflect"
	"strconv"
	"testing"
)

func TestReadSample(t *testing.T) {
	// Create a temporary sample.csv file for testing
	sampleData := [][]string{
		{"C1", "C2", "C3", "C4", "C5"},
		{"1", "5", "9", "13", "17"},
		{"2", "6", "10", "14", "18"},
		{"3", "7", "11", "15", "19"},
		{"4", "8", "12", "16", "20"},
	}
	createTempCSVFile("sample.csv", sampleData)
	defer os.Remove("sample.csv")

	// Test readSample function
	rows := readSample()
	if !reflect.DeepEqual(rows, sampleData) {
		t.Errorf("readSample() = %v, want %v", rows, sampleData)
	}
}

func TestAppendSum(t *testing.T) {
	rows := [][]string{
		{"C1", "C2", "C3", "C4", "C5"},
		{"1", "5", "9", "13", "17"},
		{"2", "6", "10", "14", "18"},
		{"3", "7", "11", "15", "19"},
		{"4", "8", "12", "16", "20"},
	}
	expectedRows := [][]string{
		{"C1", "C2", "C3", "C4", "C5", "SUM"},
		{"1", "5", "9", "13", "17", "45"},
		{"2", "6", "10", "14", "18", "50"},
		{"3", "7", "11", "15", "19", "55"},
		{"4", "8", "12", "16", "20", "60"},
	}

	appendSum(rows)
	if !reflect.DeepEqual(rows, expectedRows) {
		t.Errorf("appendSum() = %v, want %v", rows, expectedRows)
	}
}

func TestSum(t *testing.T) {
	tests := []struct {
		row      []string
		expected string
	}{
		{[]string{"1", "5", "9", "13", "17"}, "45"},
		{[]string{"2", "6", "10", "14", "18"}, "50"},
		{[]string{"3", "7", "11", "15", "19"}, "55"},
		{[]string{"4", "8", "12", "16", "20"}, "60"},
		{[]string{"1", "a", "9", "13", "17"}, "NA"}, // Invalid input
	}

	for _, tt := range tests {
		result := sum(tt.row)
		if result != tt.expected {
			t.Errorf("sum(%v) = %v, want %v", tt.row, result, tt.expected)
		}
	}
}

func TestWriteChanges(t *testing.T) {
	rows := [][]string{
		{"C1", "C2", "C3", "C4", "C5", "SUM"},
		{"1", "5", "9", "13", "17", "45"},
		{"2", "6", "10", "14", "18", "50"},
		{"3", "7", "11", "15", "19", "55"},
		{"4", "8", "12", "16", "20", "60"},
	}

	// Create a temporary output.csv file for testing
	writeChanges(rows)
	defer os.Remove("output.csv")

	// Read the written file and compare with expected data
	f, err := os.Open("output.csv")
	if err != nil {
		t.Fatal(err)
	}
	defer f.Close()

	writtenRows, err := csv.NewReader(f).ReadAll()
	if err != nil {
		t.Fatal(err)
	}

	if !reflect.DeepEqual(writtenRows, rows) {
		t.Errorf("writeChanges() wrote %v, want %v", writtenRows, rows)
	}
}

func createTempCSVFile(filename string, data [][]string) {
	f, err := os.Create(filename)
	if err != nil {
		log.Fatal(err)
	}
	defer f.Close()

	w := csv.NewWriter(f)
	err = w.WriteAll(data)
	if err != nil {
		log.Fatal(err)
	}
}

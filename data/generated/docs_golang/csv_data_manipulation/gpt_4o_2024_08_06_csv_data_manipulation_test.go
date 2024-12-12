package main

import (
	"encoding/csv"
	"os"
	"reflect"
	"strconv"
	"testing"
)

// TestReadSample tests the readSample function
func TestReadSample(t *testing.T) {
	expected := [][]string{
		{"C1", "C2", "C3", "C4", "C5"},
		{"1", "5", "9", "13", "17"},
		{"2", "6", "10", "14", "18"},
		{"3", "7", "11", "15", "19"},
		{"4", "8", "12", "16", "20"},
	}

	// Create a temporary CSV file for testing
	file, err := os.Create("sample.csv")
	if err != nil {
		t.Fatalf("Failed to create sample.csv: %v", err)
	}
	writer := csv.NewWriter(file)
	writer.WriteAll(expected)
	writer.Flush()
	file.Close()

	// Test the readSample function
	result := readSample()
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("readSample() = %v, want %v", result, expected)
	}

	// Clean up
	os.Remove("sample.csv")
}

// TestAppendSum tests the appendSum function
func TestAppendSum(t *testing.T) {
	input := [][]string{
		{"C1", "C2", "C3", "C4", "C5"},
		{"1", "5", "9", "13", "17"},
		{"2", "6", "10", "14", "18"},
		{"3", "7", "11", "15", "19"},
		{"4", "8", "12", "16", "20"},
	}
	expected := [][]string{
		{"C1", "C2", "C3", "C4", "C5", "SUM"},
		{"1", "5", "9", "13", "17", "45"},
		{"2", "6", "10", "14", "18", "50"},
		{"3", "7", "11", "15", "19", "55"},
		{"4", "8", "12", "16", "20", "60"},
	}

	appendSum(input)
	if !reflect.DeepEqual(input, expected) {
		t.Errorf("appendSum() = %v, want %v", input, expected)
	}
}

// TestSum tests the sum function
func TestSum(t *testing.T) {
	tests := []struct {
		input    []string
		expected string
	}{
		{[]string{"1", "2", "3"}, "6"},
		{[]string{"10", "20", "30"}, "60"},
		{[]string{"a", "b", "c"}, "NA"},
	}

	for _, test := range tests {
		result := sum(test.input)
		if result != test.expected {
			t.Errorf("sum(%v) = %v, want %v", test.input, result, test.expected)
		}
	}
}

// TestWriteChanges tests the writeChanges function
func TestWriteChanges(t *testing.T) {
	input := [][]string{
		{"C1", "C2", "C3", "C4", "C5", "SUM"},
		{"1", "5", "9", "13", "17", "45"},
		{"2", "6", "10", "14", "18", "50"},
		{"3", "7", "11", "15", "19", "55"},
		{"4", "8", "12", "16", "20", "60"},
	}

	// Write changes to a temporary file
	writeChanges(input)

	// Read the file back and compare
	file, err := os.Open("output.csv")
	if err != nil {
		t.Fatalf("Failed to open output.csv: %v", err)
	}
	defer file.Close()

	reader := csv.NewReader(file)
	result, err := reader.ReadAll()
	if err != nil {
		t.Fatalf("Failed to read output.csv: %v", err)
	}

	if !reflect.DeepEqual(result, input) {
		t.Errorf("writeChanges() = %v, want %v", result, input)
	}

	// Clean up
	os.Remove("output.csv")
}

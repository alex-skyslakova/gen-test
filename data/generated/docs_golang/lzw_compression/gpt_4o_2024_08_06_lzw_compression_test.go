package main

import (
	"testing"
	"reflect"
)

// TestCompress tests the compress function with various inputs.
func TestCompress(t *testing.T) {
	tests := []struct {
		input    string
		expected []int
	}{
		{"TOBEORNOTTOBEORTOBEORNOT", []int{84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263}},
		{"", []int{}}, // Test empty string
		{"A", []int{65}}, // Test single character
		{"ABABABABA", []int{65, 66, 256, 258, 260, 65}}, // Test repeating pattern
	}

	for _, test := range tests {
		result := compress(test.input)
		if !reflect.DeepEqual(result, test.expected) {
			t.Errorf("compress(%q) = %v; want %v", test.input, result, test.expected)
		}
	}
}

// TestDecompress tests the decompress function with various inputs.
func TestDecompress(t *testing.T) {
	tests := []struct {
		input    []int
		expected string
		hasError bool
	}{
		{[]int{84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263}, "TOBEORNOTTOBEORTOBEORNOT", false},
		{[]int{}, "", false}, // Test empty input
		{[]int{65}, "A", false}, // Test single character
		{[]int{65, 66, 256, 258, 260, 65}, "ABABABABA", false}, // Test repeating pattern
		{[]int{999}, "", true}, // Test invalid symbol
	}

	for _, test := range tests {
		result, err := decompress(test.input)
		if (err != nil) != test.hasError {
			t.Errorf("decompress(%v) error = %v; want error = %v", test.input, err, test.hasError)
		}
		if result != test.expected {
			t.Errorf("decompress(%v) = %q; want %q", test.input, result, test.expected)
		}
	}
}

// TestRoundTrip tests that compressing and then decompressing returns the original string.
func TestRoundTrip(t *testing.T) {
	tests := []string{
		"TOBEORNOTTOBEORTOBEORNOT",
		"",
		"A",
		"ABABABABA",
		"The quick brown fox jumps over the lazy dog",
	}

	for _, test := range tests {
		compressed := compress(test)
		decompressed, err := decompress(compressed)
		if err != nil {
			t.Errorf("decompress error: %v", err)
		}
		if decompressed != test {
			t.Errorf("RoundTrip failed for %q: got %q", test, decompressed)
		}
	}
}

package main

import (
	"testing"
)

func TestJaro(t *testing.T) {
	tests := []struct {
		str1     string
		str2     string
		expected float64
	}{
		{"MARTHA", "MARHTA", 0.944444},
		{"DIXON", "DICKSONX", 0.766667},
		{"JELLYFISH", "SMELLYFISH", 0.896296},
		{"", "", 1.0}, // Both strings are empty
		{"", "NONEMPTY", 0.0}, // One string is empty
		{"NONEMPTY", "", 0.0}, // One string is empty
		{"SAME", "SAME", 1.0}, // Both strings are identical
		{"ABC", "DEF", 0.0}, // No matching characters
	}

	for _, test := range tests {
		result := jaro(test.str1, test.str2)
		if result != test.expected {
			t.Errorf("jaro(%q, %q) = %f; expected %f", test.str1, test.str2, result, test.expected)
		}
	}
}

package main

import (
	"testing"
)

func TestIsCusip(t *testing.T) {
	testCases := []struct {
		cusip     string
		expected  bool
	}{
		{"037833100", true},
		{"17275R102", true},
		{"38259P508", true},
		{"594918104", true},
		{"68389X106", false},
		{"68389X105", true},
		{"037833101", false}, // Incorrect check digit
		{"17275R103", false}, // Incorrect check digit
		{"38259P509", false}, // Incorrect check digit
		{"594918105", false}, // Incorrect check digit
		{"68389X107", false}, // Incorrect check digit
		{"68389X104", false}, // Incorrect check digit
		{"03783310", false},  // Too short
		{"0378331000", false}, // Too long
		{"03783310A", false}, // Invalid character
		{"03783310*", false}, // Invalid character
		{"03783310@", false}, // Invalid character
		{"03783310#", false}, // Invalid character
	}

	for _, tc := range testCases {
		t.Run(tc.cusip, func(t *testing.T) {
			result := isCusip(tc.cusip)
			if result != tc.expected {
				t.Errorf("Expected %v for CUSIP %s, but got %v", tc.expected, tc.cusip, result)
			}
		})
	}
}

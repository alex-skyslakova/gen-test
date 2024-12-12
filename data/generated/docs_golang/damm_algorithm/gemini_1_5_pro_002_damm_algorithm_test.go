package main

import "testing"

func TestDamm(t *testing.T) {
	testCases := []struct {
		input    string
		expected bool
	}{
		{"5724", true},  // Example from original code, valid checksum
		{"5727", false}, // Example from original code, invalid checksum
		{"112946", true}, // Example from original code, valid checksum
		{"112949", false}, // Example from original code, invalid checksum
		{"", true},      // Empty string should return true (no errors)
		{"1", true},      // Single digit should return true 
		{"0", true},
		{"2", true},
		{"3", true},
		{"4", true},
		{"5", true},
		{"6", true},
		{"7", true},
		{"8", true},
		{"9", true},       // Single digits
		{"12345", false},   // Random valid number without checksum
		{"123456", true},  // Random valid number with checksum
		{"123457", false}, // Random invalid number with checksum
		{"122", true},   // Test with adjacent transposition creating a valid checksum
        {"112", true},
        {"212", true},  // More single and double digit tests
        {"1231", true}, // Another longer valid example
        {"12312", false}, // Corresponding invalid version
        {"9876543210",true},  // Long valid number
        {"9876543212", false}, // Long invalid number

	}

	for _, tc := range testCases {
		t.Run(tc.input, func(t *testing.T) {
			actual := damm(tc.input)
			if actual != tc.expected {
				t.Errorf("damm(%s) = %t; want %t", tc.input, actual, tc.expected)
			}
		})
	}
}


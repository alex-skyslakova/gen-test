package main

import "testing"

// TestIsCusip tests the isCusip function with various CUSIP codes.
func TestIsCusip(t *testing.T) {
    tests := []struct {
        cusip    string
        expected bool
    }{
        {"037833100", true},  // Apple Incorporated
        {"17275R102", true},  // Cisco Systems
        {"38259P508", true},  // Google Incorporated
        {"594918104", true},  // Microsoft Corporation
        {"68389X106", false}, // Oracle Corporation (incorrect)
        {"68389X105", true},  // Oracle Corporation
        {"123456789", false}, // Invalid CUSIP (incorrect check digit)
        {"ABCDEFGH1", false}, // Invalid CUSIP (non-numeric check digit)
        {"12345678@", false}, // Invalid CUSIP (invalid character)
        {"", false},          // Empty string
        {"12345678", false},  // Too short
        {"1234567890", false},// Too long
    }

    for _, test := range tests {
        result := isCusip(test.cusip)
        if result != test.expected {
            t.Errorf("isCusip(%s) = %v; want %v", test.cusip, result, test.expected)
        }
    }
}

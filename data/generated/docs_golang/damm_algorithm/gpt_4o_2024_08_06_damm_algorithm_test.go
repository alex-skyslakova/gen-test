package main

import "testing"

func TestDammAlgorithm(t *testing.T) {
    tests := []struct {
        input    string
        expected bool
    }{
        {"5724", true},   // Valid checksum
        {"5727", false},  // Invalid checksum
        {"112946", true}, // Valid checksum
        {"112949", false},// Invalid checksum
        {"0", true},      // Single digit, valid checksum
        {"1", false},     // Single digit, invalid checksum
        {"1234567890", false}, // Long sequence, invalid checksum
        {"1234567892", true},  // Long sequence, valid checksum
    }

    for _, test := range tests {
        result := damm(test.input)
        if result != test.expected {
            t.Errorf("damm(%s) = %v; want %v", test.input, result, test.expected)
        }
    }
}

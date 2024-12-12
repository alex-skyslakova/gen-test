package main

import (
    "testing"
    "strings"
)

// Helper function to count unchanged positions
func countUnchanged(original, shuffled string) int {
    count := 0
    for i := range original {
        if original[i] == shuffled[i] {
            count++
        }
    }
    return count
}

// Test function for the shuffle logic
func TestShuffle(t *testing.T) {
    testCases := []struct {
        input string
    }{
        {"abracadabra"},
        {"seesaw"},
        {"elk"},
        {"grrrrrr"},
        {"up"},
        {"a"},
    }

    for _, tc := range testCases {
        shuffled := shuffleString(tc.input)
        unchangedCount := countUnchanged(tc.input, shuffled)

        if unchangedCount != 0 && len(tc.input) > 1 {
            t.Errorf("Expected 0 unchanged positions for input %s, but got %d", tc.input, unchangedCount)
        }

        if strings.Contains(tc.input, "r") && tc.input == "grrrrrr" {
            if unchangedCount != len(tc.input) {
                t.Errorf("Expected all positions unchanged for input %s, but got %d", tc.input, unchangedCount)
            }
        }
    }
}

// Function to shuffle the string
func shuffleString(s string) string {
    t := make([]byte, len(s))
    for i, r := range rand.Perm(len(s)) {
        t[i] = s[r]
    }
    for i := range t {
        for j := range t {
            if i != j && t[i] != s[j] && t[j] != s[i] {
                t[i], t[j] = t[j], t[i]
                break
            }
        }
    }
    return string(t)
}

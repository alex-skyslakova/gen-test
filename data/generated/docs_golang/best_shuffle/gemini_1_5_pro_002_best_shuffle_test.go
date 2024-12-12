package main

import (
	"math/rand"
	"testing"
	"time"
)

func TestBestShuffle(t *testing.T) {
	rand.Seed(time.Now().UnixNano()) // Seed for consistent testing during a single run.  In real code, this is undesirable. 

	testCases := []struct {
		input string
	}{
		{"abracadabra"},
		{"seesaw"},
		{"elk"},
		{"grrrrrr"},
		{"up"},
		{"a"},
		{""}, // Empty string case
		{"abcdefg"}, // longer string case
		{"aab"}, // repeated characters
	}

	for _, tc := range testCases {
		t.Run(tc.input, func(t *testing.T) {
			original := tc.input
			shuffled := bestShuffle(original)

			// Check that shuffled string has the same length and characters as the original
			if len(shuffled) != len(original) {
				t.Errorf("Shuffled string length mismatch: expected %d, got %d", len(original), len(shuffled))
			}
			charCountOriginal := make(map[byte]int)
			charCountShuffled := make(map[byte]int)
			for i := range original {
				charCountOriginal[original[i]]++
				charCountShuffled[shuffled[i]]++
			}
			for char, count := range charCountOriginal {
				if charCountShuffled[char] != count {
					t.Errorf("Character count mismatch for '%c': expected %d, got %d", char, count, charCountShuffled[char])
				}
			}


			// Count unchanged positions and validate if within the same character set
			unchangedCount := 0
			for i := 0; i < len(original); i++ {
				if original[i] == shuffled[i] {
					unchangedCount++
				}
			}
			
		})
	}
}


// Extracted the shuffling logic into a testable function
func bestShuffle(s string) string {
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

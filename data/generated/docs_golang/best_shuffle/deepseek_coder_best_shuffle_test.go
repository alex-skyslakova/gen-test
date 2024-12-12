package main

import (
    "testing"
    "math/rand"
    "time"
)

func TestBestShuffle(t *testing.T) {
    rand.Seed(time.Now().UnixNano())

    testCases := []struct {
        input    string
        expected string
        score    int
    }{
        {"abracadabra", "abracadabra", 0},
        {"seesaw", "seesaw", 0},
        {"elk", "elk", 0},
        {"grrrrrr", "grrrrrr", 0},
        {"up", "up", 0},
        {"a", "a", 0},
    }

    for _, tc := range testCases {
        t.Run(tc.input, func(t *testing.T) {
            // Create shuffled byte array of original string
            shuffled := make([]byte, len(tc.input))
            perm := rand.Perm(len(tc.input))
            for i, r := range perm {
                shuffled[i] = tc.input[r]
            }

            // Algorithm of Icon solution
            for i := range shuffled {
                for j := range shuffled {
                    if i != j && shuffled[i] != tc.input[j] && shuffled[j] != tc.input[i] {
                        shuffled[i], shuffled[j] = shuffled[j], shuffled[i]
                        break
                    }
                }
            }

            // Count unchanged and compare with expected
            var count int
            for i, ic := range shuffled {
                if ic == tc.input[i] {
                    count++
                }
            }

            if count != tc.score {
                t.Errorf("For input %s, expected score %d, got %d", tc.input, tc.score, count)
            }
        })
    }
}

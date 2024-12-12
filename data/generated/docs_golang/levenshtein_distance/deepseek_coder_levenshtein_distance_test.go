package main

import (
    "testing"
)

func TestLevenshteinDistance(t *testing.T) {
    testCases := []struct {
        name     string
        s        string
        t        string
        expected int
    }{
        {
            name:     "Equal strings",
            s:        "kitten",
            t:        "kitten",
            expected: 0,
        },
        {
            name:     "Different strings",
            s:        "kitten",
            t:        "sitting",
            expected: 3,
        },
        {
            name:     "Empty strings",
            s:        "",
            t:        "",
            expected: 0,
        },
        {
            name:     "One empty string",
            s:        "kitten",
            t:        "",
            expected: 6,
        },
        {
            name:     "Reversed strings",
            s:        "rosettacode",
            t:        "raisethysword",
            expected: 8,
        },
        {
            name:     "Same length, all different",
            s:        "abcdef",
            t:        "ghijkl",
            expected: 6,
        },
        {
            name:     "Same length, one different",
            s:        "abcdef",
            t:        "abcefg",
            expected: 2,
        },
        {
            name:     "Longer strings",
            s:        "supercalifragilisticexpialidocious",
            t:        "pneumonoultramicroscopicsilicovolcanoconiosis",
            expected: 26,
        },
    }

    for _, tc := range testCases {
        t.Run(tc.name, func(t *testing.T) {
            result := ld(tc.s, tc.t)
            if result != tc.expected {
                t.Errorf("Expected Levenshtein distance between '%s' and '%s' to be %d, but got %d", tc.s, tc.t, tc.expected, result)
            }
        })
    }
}

package main

import (
    "testing"
)

func TestJaro(t *testing.T) {
    testCases := []struct {
        str1     string
        str2     string
        expected float64
    }{
        {"", "", 1.0},
        {"", "test", 0.0},
        {"test", "", 0.0},
        {"MARTHA", "MARHTA", 0.944444},
        {"DIXON", "DICKSONX", 0.766667},
        {"JELLYFISH", "SMELLYFISH", 0.896296},
        {"abc", "def", 0.0},
        {"abc", "abc", 1.0},
        {"abcdef", "abc", 0.888889},
        {"abc", "abcdef", 0.888889},
        {"abc", "ab", 0.888889},
        {"ab", "abc", 0.888889},
        {"abc", "acb", 0.888889},
        {"abc", "bac", 0.888889},
        {"abc", "cba", 0.888889},
        {"abc", "abd", 0.888889},
        {"abc", "acd", 0.888889},
        {"abc", "bcd", 0.888889},
    }

    for _, tc := range testCases {
        result := jaro(tc.str1, tc.str2)
        if result != tc.expected {
            t.Errorf("Jaro(%q, %q) = %f; want %f", tc.str1, tc.str2, result, tc.expected)
        }
    }
}

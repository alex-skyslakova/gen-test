package main

import (
    "reflect"
    "testing"
)

func TestEertree(t *testing.T) {
    tests := []struct {
        input    string
        expected []string
    }{
        {
            input:    "eertree",
            expected: []string{"e", "ee", "eertree", "ertre", "etrte", "r", "rttr", "t", "tt"},
        },
        {
            input:    "a",
            expected: []string{"a"},
        },
        {
            input:    "aa",
            expected: []string{"a", "aa"},
        },
        {
            input:    "aba",
            expected: []string{"a", "aba", "b"},
        },
        {
            input:    "abba",
            expected: []string{"a", "abba", "b", "bb"},
        },
    }

    for _, test := range tests {
        tree := eertree([]byte(test.input))
        result := subPalindromes(tree)
        if !reflect.DeepEqual(result, test.expected) {
            t.Errorf("For input %q, expected %v, but got %v", test.input, test.expected, result)
        }
    }
}

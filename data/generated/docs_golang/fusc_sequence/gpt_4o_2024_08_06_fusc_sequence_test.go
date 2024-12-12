package main

import (
    "testing"
    "reflect"
)

// Test the fusc function
func TestFusc(t *testing.T) {
    tests := []struct {
        input    int
        expected []int
    }{
        {0, []int{}},
        {1, []int{0}},
        {2, []int{0, 1}},
        {3, []int{0, 1, 1}},
        {4, []int{0, 1, 1, 2}},
        {5, []int{0, 1, 1, 2, 1}},
        {10, []int{0, 1, 1, 2, 1, 3, 1, 4, 3, 5}},
        {61, []int{0, 1, 1, 2, 1, 3, 1, 4, 3, 5, 1, 6, 1, 7, 5, 8, 3, 9, 5, 10, 1, 11, 1, 12, 7, 13, 5, 14, 9, 15, 1, 16, 1, 17, 11, 18, 7, 19, 11, 20, 3, 21, 5, 22, 13, 23, 9, 24, 13, 25, 1, 26, 1, 27, 17, 28, 11, 29, 17, 30, 5}},
    }

    for _, test := range tests {
        result := fusc(test.input)
        if !reflect.DeepEqual(result, test.expected) {
            t.Errorf("fusc(%d) = %v; want %v", test.input, result, test.expected)
        }
    }
}

// Test the fuscMaxLen function
func TestFuscMaxLen(t *testing.T) {
    tests := []struct {
        input    int
        expected [][2]int
    }{
        {10, [][2]int{{1, 1}, {3, 2}, {5, 3}, {7, 4}, {9, 5}}},
        {20, [][2]int{{1, 1}, {3, 2}, {5, 3}, {7, 4}, {9, 5}, {11, 6}, {13, 7}, {15, 8}, {17, 9}, {19, 10}}},
    }

    for _, test := range tests {
        result := fuscMaxLen(test.input)
        if !reflect.DeepEqual(result, test.expected) {
            t.Errorf("fuscMaxLen(%d) = %v; want %v", test.input, result, test.expected)
        }
    }
}

// Test the commatize function
func TestCommatize(t *testing.T) {
    tests := []struct {
        input    int
        expected string
    }{
        {0, "0"},
        {1, "1"},
        {1000, "1,000"},
        {1000000, "1,000,000"},
        {-1000, "-1,000"},
        {-1000000, "-1,000,000"},
    }

    for _, test := range tests {
        result := commatize(test.input)
        if result != test.expected {
            t.Errorf("commatize(%d) = %s; want %s", test.input, result, test.expected)
        }
    }
}

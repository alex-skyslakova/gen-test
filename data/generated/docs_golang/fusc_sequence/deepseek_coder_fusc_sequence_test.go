package main

import (
    "reflect"
    "strconv"
    "testing"
)

func TestFusc(t *testing.T) {
    testCases := []struct {
        n        int
        expected []int
    }{
        {0, []int{}},
        {1, []int{0}},
        {2, []int{0, 1}},
        {10, []int{0, 1, 1, 2, 1, 3, 2, 3, 1, 4}},
    }

    for _, tc := range testCases {
        t.Run(strconv.Itoa(tc.n), func(t *testing.T) {
            result := fusc(tc.n)
            if !reflect.DeepEqual(result, tc.expected) {
                t.Errorf("Expected %v, but got %v", tc.expected, result)
            }
        })
    }
}

func TestFuscMaxLen(t *testing.T) {
    testCases := []struct {
        n        int
        expected [][2]int
    }{
        {10, [][2]int{{0, 0}, {1, 1}, {3, 2}, {7, 3}}},
        {100, [][2]int{{0, 0}, {1, 1}, {3, 2}, {7, 3}, {15, 4}, {31, 5}, {63, 6}}},
    }

    for _, tc := range testCases {
        t.Run(strconv.Itoa(tc.n), func(t *testing.T) {
            result := fuscMaxLen(tc.n)
            if !reflect.DeepEqual(result, tc.expected) {
                t.Errorf("Expected %v, but got %v", tc.expected, result)
            }
        })
    }
}

func TestCommatize(t *testing.T) {
    testCases := []struct {
        n        int
        expected string
    }{
        {0, "0"},
        {123, "123"},
        {1234, "1,234"},
        {1234567, "1,234,567"},
        {-1234, "-1,234"},
    }

    for _, tc := range testCases {
        t.Run(strconv.Itoa(tc.n), func(t *testing.T) {
            result := commatize(tc.n)
            if result != tc.expected {
                t.Errorf("Expected %s, but got %s", tc.expected, result)
            }
        })
    }
}

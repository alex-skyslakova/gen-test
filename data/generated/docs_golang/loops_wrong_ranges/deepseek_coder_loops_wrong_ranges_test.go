package main

import (
    "reflect"
    "testing"
)

func TestSequence(t *testing.T) {
    tests := []struct {
        name     string
        s        S
        limit    int
        expected []int
    }{
        {
            name:     "Normal",
            s:        S{-2, 2, 1, "Normal"},
            limit:    10,
            expected: []int{-2, -1, 0, 1, 2},
        },
        {
            name:     "Zero increment",
            s:        S{-2, 2, 0, "Zero increment"},
            limit:    10,
            expected: []int{-2, -2, -2, -2, -2, -2, -2, -2, -2, -2},
        },
        {
            name:     "Increments away from stop value",
            s:        S{-2, 2, -1, "Increments away from stop value"},
            limit:    10,
            expected: []int{-2, -3, -4, -5, -6, -7, -8, -9, -10, -11},
        },
        {
            name:     "First increment is beyond stop value",
            s:        S{-2, 2, 10, "First increment is beyond stop value"},
            limit:    10,
            expected: []int{-2},
        },
        {
            name:     "Start more than stop: positive increment",
            s:        S{2, -2, 1, "Start more than stop: positive increment"},
            limit:    10,
            expected: []int{2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
        },
        {
            name:     "Start equal stop: positive increment",
            s:        S{2, 2, 1, "Start equal stop: positive increment"},
            limit:    10,
            expected: []int{2},
        },
        {
            name:     "Start equal stop: negative increment",
            s:        S{2, 2, -1, "Start equal stop: negative increment"},
            limit:    10,
            expected: []int{2},
        },
        {
            name:     "Start equal stop: zero increment",
            s:        S{2, 2, 0, "Start equal stop: zero increment"},
            limit:    10,
            expected: []int{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
        },
        {
            name:     "Start equal stop equal zero: zero increment",
            s:        S{0, 0, 0, "Start equal stop equal zero: zero increment"},
            limit:    10,
            expected: []int{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := sequence(tt.s, tt.limit)
            if !reflect.DeepEqual(result, tt.expected) {
                t.Errorf("Expected %v, but got %v", tt.expected, result)
            }
        })
    }
}

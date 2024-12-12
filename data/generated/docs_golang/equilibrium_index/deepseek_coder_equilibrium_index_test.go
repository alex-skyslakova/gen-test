package main

import (
    "reflect"
    "testing"
)

func TestEx(t *testing.T) {
    tests := []struct {
        name     string
        input    []int32
        expected []int
    }{
        {
            name:     "No equilibrium index",
            input:    []int32{1, 2, 3, 4, 5},
            expected: []int{},
        },
        {
            name:     "Single equilibrium index",
            input:    []int32{-7, 1, 5, 2, -4, 3, 0},
            expected: []int{3, 6},
        },
        {
            name:     "Multiple equilibrium indices",
            input:    []int32{0, -1, 1, 0},
            expected: []int{0, 1, 2, 3},
        },
        {
            name:     "Empty sequence",
            input:    []int32{},
            expected: []int{},
        },
        {
            name:     "Single element sequence",
            input:    []int32{1},
            expected: []int{0},
        },
        {
            name:     "All elements are zero",
            input:    []int32{0, 0, 0, 0},
            expected: []int{0, 1, 2, 3},
        },
        {
            name:     "Large sequence with equilibrium index",
            input:    generateLargeSequence(),
            expected: []int{500000},
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := ex(tt.input)
            if !reflect.DeepEqual(result, tt.expected) {
                t.Errorf("expected %v, got %v", tt.expected, result)
            }
        })
    }
}

func generateLargeSequence() []int32 {
    verylong := make([]int32, 1e6)
    for i := range verylong {
        if i == 500000 {
            verylong[i] = 0
        } else {
            verylong[i] = 1
        }
    }
    return verylong
}

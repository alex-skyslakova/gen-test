package main

import (
    "reflect"
    "testing"
)

func TestKronecker(t *testing.T) {
    tests := []struct {
        m1, m2, expected uintMatrix
    }{
        {
            m1: uintMatrix{
                {1, 2},
                {3, 4},
            },
            m2: uintMatrix{
                {0, 5},
                {6, 7},
            },
            expected: uintMatrix{
                {0, 5, 0, 10},
                {6, 7, 12, 14},
                {0, 15, 0, 20},
                {18, 21, 24, 28},
            },
        },
        {
            m1: uintMatrix{
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0},
            },
            m2: uintMatrix{
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1},
            },
            expected: uintMatrix{
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            },
        },
    }

    for _, test := range tests {
        result := kronecker(test.m1, test.m2)
        if !reflect.DeepEqual(result, test.expected) {
            t.Errorf("kronecker(%v, %v) = %v; want %v", test.m1, test.m2, result, test.expected)
        }
    }
}

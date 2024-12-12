package main

import (
    "reflect"
    "testing"
)

func TestFairshare(t *testing.T) {
    tests := []struct {
        n    int
        base int
        want []int
    }{
        {25, 2, []int{0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0}},
        {25, 3, []int{0, 1, 2, 1, 2, 0, 2, 0, 1, 1, 2, 0, 2, 0, 1, 0, 1, 2, 2, 0, 1, 0, 1, 2, 1}},
        {25, 5, []int{0, 1, 2, 3, 4, 1, 2, 3, 4, 0, 2, 3, 4, 0, 1, 3, 4, 0, 1, 2, 4, 0, 1, 2, 3}},
        {25, 11, []int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 2, 3, 4}},
    }

    for _, tt := range tests {
        got := fairshare(tt.n, tt.base)
        if !reflect.DeepEqual(got, tt.want) {
            t.Errorf("fairshare(%d, %d) = %v; want %v", tt.n, tt.base, got, tt.want)
        }
    }
}

func TestTurns(t *testing.T) {
    tests := []struct {
        n    int
        fss  []int
        want string
    }{
        {2, []int{0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0}, "12 or 13"},
        {3, []int{0, 1, 2, 1, 2, 0, 2, 0, 1, 1, 2, 0, 2, 0, 1, 0, 1, 2, 2, 0, 1, 0, 1, 2, 1}, "8 or 9"},
        {5, []int{0, 1, 2, 3, 4, 1, 2, 3, 4, 0, 2, 3, 4, 0, 1, 3, 4, 0, 1, 2, 4, 0, 1, 2, 3}, "5 or 6 or 7 or 8 or 9"},
        {11, []int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 2, 3, 4}, "2 or 3 or 4 or 5 or 6 or 7 or 8 or 9 or 10 or 11 or 12"},
    }

    for _, tt := range tests {
        got := turns(tt.n, tt.fss)
        if got != tt.want {
            t.Errorf("turns(%d, %v) = %s; want %s", tt.n, tt.fss, got, tt.want)
        }
    }
}

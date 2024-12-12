package main

import (
    "math/big"
    "testing"
)

func TestPath(t *testing.T) {
    tests := []struct {
        n        int
        expected []int
    }{
        {0, []int{}},
        {1, []int{1}},
        {2, []int{1, 2}},
        {3, []int{1, 2, 3}},
        {4, []int{1, 2, 4}},
        {5, []int{1, 2, 3, 5}},
        {6, []int{1, 2, 3, 6}},
        {7, []int{1, 2, 3, 5, 7}},
        {8, []int{1, 2, 4, 8}},
        {9, []int{1, 2, 3, 6, 9}},
        {10, []int{1, 2, 3, 5, 10}},
        {11, []int{1, 2, 3, 5, 10, 11}},
        {12, []int{1, 2, 3, 6, 12}},
        {13, []int{1, 2, 3, 5, 10, 13}},
        {14, []int{1, 2, 3, 5, 7, 14}},
        {15, []int{1, 2, 3, 5, 10, 15}},
        {16, []int{1, 2, 4, 8, 16}},
        {17, []int{1, 2, 4, 8, 16, 17}},
    }

    for _, test := range tests {
        result := path(test.n)
        if !sliceEqual(result, test.expected) {
            t.Errorf("path(%d) = %v; expected %v", test.n, result, test.expected)
        }
    }
}

func TestTreePow(t *testing.T) {
    tests := []struct {
        x        float64
        n        int
        expected string
    }{
        {2, 0, "1"},
        {2, 1, "2"},
        {2, 2, "4"},
        {2, 3, "8"},
        {2, 4, "16"},
        {2, 5, "32"},
        {2, 6, "64"},
        {2, 7, "128"},
        {2, 8, "256"},
        {2, 9, "512"},
        {2, 10, "1024"},
        {2, 11, "2048"},
        {2, 12, "4096"},
        {2, 13, "8192"},
        {2, 14, "16384"},
        {2, 15, "32768"},
        {2, 16, "65536"},
        {2, 17, "131072"},
        {1.1, 81, "2253.240236"},
        {3, 191, "13494588674281093803728157396523884917402502294030101914066705367021922008906273586058258347"},
    }

    for _, test := range tests {
        result := treePow(test.x, test.n)
        expected := new(big.Float).SetPrec(320).SetString(test.expected)
        if result.Cmp(expected) != 0 {
            t.Errorf("treePow(%f, %d) = %v; expected %v", test.x, test.n, result, expected)
        }
    }
}

func sliceEqual(a, b []int) bool {
    if len(a) != len(b) {
        return false
    }
    for i := range a {
        if a[i] != b[i] {
            return false
        }
    }
    return true
}

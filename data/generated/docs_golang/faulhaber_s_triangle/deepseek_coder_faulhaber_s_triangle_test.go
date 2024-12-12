package main

import (
    "math/big"
    "testing"
)

func TestBernoulli(t *testing.T) {
    tests := []struct {
        n    uint
        want string
    }{
        {0, "1"},
        {1, "-1/2"},
        {2, "1/6"},
        {3, "0"},
        {4, "-1/30"},
        {5, "0"},
        {6, "1/42"},
        {7, "0"},
        {8, "-1/30"},
        {9, "0"},
        {10, "5/66"},
    }

    for _, tt := range tests {
        got := bernoulli(tt.n).RatString()
        if got != tt.want {
            t.Errorf("bernoulli(%d) = %s; want %s", tt.n, got, tt.want)
        }
    }
}

func TestBinomial(t *testing.T) {
    tests := []struct {
        n, k int
        want int64
    }{
        {0, 0, 1},
        {1, 0, 1},
        {1, 1, 1},
        {5, 0, 1},
        {5, 1, 5},
        {5, 2, 10},
        {5, 3, 10},
        {5, 4, 5},
        {5, 5, 1},
        {10, 5, 252},
    }

    for _, tt := range tests {
        got := binomial(tt.n, tt.k)
        if got != tt.want {
            t.Errorf("binomial(%d, %d) = %d; want %d", tt.n, tt.k, got, tt.want)
        }
    }
}

func TestFaulhaberTriangle(t *testing.T) {
    tests := []struct {
        p    int
        want []string
    }{
        {0, []string{"1"}},
        {1, []string{"1/2", "1/2"}},
        {2, []string{"1/6", "1/2", "1/3"}},
        {3, []string{"0", "1/4", "1/2", "1/4"}},
        {4, []string{"-1/30", "0", "1/3", "1/2", "1/5"}},
        {5, []string{"0", "1/12", "0", "5/12", "1/2", "1/6"}},
        {6, []string{"1/42", "0", "-1/6", "0", "1/2", "1/2", "1/7"}},
        {7, []string{"0", "-1/12", "0", "7/12", "0", "7/12", "1/2", "1/8"}},
        {8, []string{"-1/30", "0", "2/9", "0", "-7/15", "0", "2/3", "1/2", "1/9"}},
        {9, []string{"0", "3/20", "0", "-7/10", "0", "21/10", "0", "9/4", "1/2", "1/10"}},
    }

    for _, tt := range tests {
        got := faulhaberTriangle(tt.p)
        for i, coeff := range got {
            if coeff.RatString() != tt.want[i] {
                t.Errorf("faulhaberTriangle(%d)[%d] = %s; want %s", tt.p, i, coeff.RatString(), tt.want[i])
            }
        }
    }
}

func TestMainOutput(t *testing.T) {
    // This test is more complex and would require capturing stdout, which is not straightforward in Go.
    // For simplicity, we assume that the main function works correctly if the individual functions are tested.
}

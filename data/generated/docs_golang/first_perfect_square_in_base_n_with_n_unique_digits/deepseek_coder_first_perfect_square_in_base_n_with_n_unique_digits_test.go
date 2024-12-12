package main

import (
    "math/big"
    "testing"
)

func TestContainsAll(t *testing.T) {
    tests := []struct {
        sq    string
        base  int
        want  bool
    }{
        {"1023456789", 10, true},
        {"102345678", 10, false}, // Missing one digit
        {"10123456789", 10, false}, // Duplicate '1'
        {"1023456789abcdef", 16, true},
        {"1023456789abcde", 16, false}, // Missing one digit
        {"10123456789abcdef", 16, false}, // Duplicate '1'
    }

    for _, tt := range tests {
        got := containsAll(tt.sq, tt.base)
        if got != tt.want {
            t.Errorf("containsAll(%q, %d) = %v; want %v", tt.sq, tt.base, got, tt.want)
        }
    }
}

func TestSumDigits(t *testing.T) {
    tests := []struct {
        n     string
        base  int
        want  string
    }{
        {"12345", 10, "15"},
        {"101010", 2, "3"},
        {"ff", 16, "30"},
        {"123456789abcdef", 16, "69"},
    }

    for _, tt := range tests {
        n := new(big.Int)
        n.SetString(tt.n, tt.base)
        base := big.NewInt(int64(tt.base))
        got := sumDigits(n, base)
        want := new(big.Int)
        want.SetString(tt.want, 10)
        if got.Cmp(want) != 0 {
            t.Errorf("sumDigits(%s, %d) = %s; want %s", tt.n, tt.base, got.String(), tt.want)
        }
    }
}

func TestDigitalRoot(t *testing.T) {
    tests := []struct {
        n     string
        base  int
        want  int
    }{
        {"12345", 10, 6},
        {"101010", 2, 1},
        {"ff", 16, 3},
        {"123456789abcdef", 16, 6},
    }

    for _, tt := range tests {
        n := new(big.Int)
        n.SetString(tt.n, tt.base)
        got := digitalRoot(n, tt.base)
        if got != tt.want {
            t.Errorf("digitalRoot(%s, %d) = %d; want %d", tt.n, tt.base, got, tt.want)
        }
    }
}

func TestMinStart(t *testing.T) {
    tests := []struct {
        base  int
        want  string
        inc   uint64
        bdr   int
    }{
        {10, "1023456789", 1, 9},
        {16, "1023456789abcdef", 1, 15},
        {2, "10", 1, 1},
        {3, "102", 1, 2},
    }

    for _, tt := range tests {
        gotStr, gotInc, gotBdr := minStart(tt.base)
        if gotStr != tt.want || gotInc != tt.inc || gotBdr != tt.bdr {
            t.Errorf("minStart(%d) = (%s, %d, %d); want (%s, %d, %d)", tt.base, gotStr, gotInc, gotBdr, tt.want, tt.inc, tt.bdr)
        }
    }
}

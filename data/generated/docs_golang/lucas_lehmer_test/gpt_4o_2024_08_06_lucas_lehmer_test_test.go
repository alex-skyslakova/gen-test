package main

import (
    "math/big"
    "testing"
)

func TestLucasLehmer(t *testing.T) {
    testCases := []struct {
        primes    []uint
        expected  []uint
    }{
        {
            primes:   []uint{3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47},
            expected: []uint{3, 5, 7, 13, 17, 19, 31},
        },
        {
            primes:   []uint{53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127},
            expected: []uint{61, 89, 107, 127},
        },
    }

    for _, tc := range testCases {
        result := llTest(tc.primes)
        if !equal(result, tc.expected) {
            t.Errorf("Expected %v, but got %v", tc.expected, result)
        }
    }
}

func llTest(ps []uint) []uint {
    var s, m big.Int
    one := big.NewInt(1)
    two := big.NewInt(2)
    var result []uint
    for _, p := range ps {
        m.Sub(m.Lsh(one, p), one)
        s.SetInt64(4)
        for i := uint(2); i < p; i++ {
            s.Mod(s.Sub(s.Mul(&s, &s), two), &m)
        }
        if s.BitLen() == 0 {
            result = append(result, p)
        }
    }
    return result
}

func equal(a, b []uint) bool {
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

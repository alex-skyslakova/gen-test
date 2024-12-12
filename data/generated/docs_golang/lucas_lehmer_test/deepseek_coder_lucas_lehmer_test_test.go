package main

import (
    "math/big"
    "testing"
)

func TestLLTest(t *testing.T) {
    tests := []struct {
        name     string
        input    []uint
        expected []uint
    }{
        {
            name:     "Small Primes",
            input:    []uint{3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47},
            expected: []uint{3, 5, 7, 13, 17, 19, 31, 41, 47},
        },
        {
            name:     "Mersenne Primes",
            input:    []uint{521, 607, 1279, 2203, 2281, 3217, 4253, 4423, 9689, 9941, 11213, 19937, 21701, 23209, 44497, 86243, 110503, 132049, 216091, 756839, 859433, 1257787, 1398269, 2976221, 3021377, 6972593, 13466917, 20996011, 24036583},
            expected: []uint{521, 607, 1279, 2203, 2281, 3217, 4253, 4423, 9689, 9941, 11213, 19937, 21701, 23209, 44497, 86243, 110503, 132049, 216091, 756839, 859433, 1257787, 1398269, 2976221, 3021377, 6972593, 13466917, 20996011, 24036583},
        },
        {
            name:     "Non-Primes",
            input:    []uint{4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30},
            expected: []uint{},
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := captureOutput(llTest, tt.input)
            for _, exp := range tt.expected {
                if !contains(result, exp) {
                    t.Errorf("Expected M%d to be in the result, but it was not", exp)
                }
            }
            for _, res := range result {
                if !contains(tt.expected, res) {
                    t.Errorf("Unexpected M%d in the result", res)
                }
            }
        })
    }
}

func captureOutput(f func([]uint), input []uint) []uint {
    var result []uint
    f = func(ps []uint) {
        var s, m big.Int
        one := big.NewInt(1)
        two := big.NewInt(2)
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
    }
    f(input)
    return result
}

func contains(slice []uint, value uint) bool {
    for _, v := range slice {
        if v == value {
            return true
        }
    }
    return false
}

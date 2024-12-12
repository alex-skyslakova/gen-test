package main

import (
    "math/big"
    "testing"
)

func TestToEgyptian(t *testing.T) {
    tests := []struct {
        input    *big.Rat
        expected []*big.Rat
    }{
        {
            input:    big.NewRat(43, 48),
            expected: []*big.Rat{big.NewRat(1, 2), big.NewRat(1, 3), big.NewRat(1, 16)},
        },
        {
            input:    big.NewRat(5, 121),
            expected: []*big.Rat{big.NewRat(1, 25), big.NewRat(1, 757), big.NewRat(1, 763309)},
        },
        {
            input:    big.NewRat(2014, 59),
            expected: []*big.Rat{big.NewRat(34, 1), big.NewRat(1, 2), big.NewRat(1, 3), big.NewRat(1, 35442)},
        },
    }

    for _, test := range tests {
        result := toEgyptian(test.input)
        if len(result) != len(test.expected) {
            t.Errorf("For input %v, expected %v terms, got %v terms", test.input, len(test.expected), len(result))
            continue
        }
        for i, r := range result {
            if r.Cmp(test.expected[i]) != 0 {
                t.Errorf("For input %v, expected term %v to be %v, got %v", test.input, i, test.expected[i], r)
            }
        }
    }
}

func TestToEgyptianRecursive(t *testing.T) {
    tests := []struct {
        input    *big.Rat
        expected []*big.Rat
    }{
        {
            input:    big.NewRat(43, 48),
            expected: []*big.Rat{big.NewRat(1, 2), big.NewRat(1, 3), big.NewRat(1, 16)},
        },
        {
            input:    big.NewRat(5, 121),
            expected: []*big.Rat{big.NewRat(1, 25), big.NewRat(1, 757), big.NewRat(1, 763309)},
        },
    }

    for _, test := range tests {
        result := toEgyptianRecursive(test.input, nil)
        if len(result) != len(test.expected) {
            t.Errorf("For input %v, expected %v terms, got %v terms", test.input, len(test.expected), len(result))
            continue
        }
        for i, r := range result {
            if r.Cmp(test.expected[i]) != 0 {
                t.Errorf("For input %v, expected term %v to be %v, got %v", test.input, i, test.expected[i], r)
            }
        }
    }
}

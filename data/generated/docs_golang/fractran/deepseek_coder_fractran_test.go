package main

import (
    "math/big"
    "testing"
)

func TestCompile(t *testing.T) {
    tests := []struct {
        input    string
        expected []big.Rat
        ok       bool
    }{
        {"17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1",
            []big.Rat{
                *big.NewRat(17, 91), *big.NewRat(78, 85), *big.NewRat(19, 51), *big.NewRat(23, 38),
                *big.NewRat(29, 33), *big.NewRat(77, 29), *big.NewRat(95, 23), *big.NewRat(77, 19),
                *big.NewRat(1, 17), *big.NewRat(11, 13), *big.NewRat(13, 11), *big.NewRat(15, 14),
                *big.NewRat(15, 2), *big.NewRat(55, 1),
            },
            true,
        },
        {"invalid/fraction", nil, false},
    }

    for _, test := range tests {
        result, ok := compile(test.input)
        if ok != test.ok {
            t.Errorf("Expected ok=%v for input %s, got %v", test.ok, test.input, ok)
        }
        if ok {
            if len(result) != len(test.expected) {
                t.Errorf("Expected %d fractions, got %d for input %s", len(test.expected), len(result), test.input)
            } else {
                for i := range result {
                    if result[i].Cmp(&test.expected[i]) != 0 {
                        t.Errorf("Expected fraction %v, got %v for input %s", test.expected[i], result[i], test.input)
                    }
                }
            }
        }
    }
}

func TestExec(t *testing.T) {
    fractions := []big.Rat{
        *big.NewRat(17, 91), *big.NewRat(78, 85), *big.NewRat(19, 51), *big.NewRat(23, 38),
        *big.NewRat(29, 33), *big.NewRat(77, 29), *big.NewRat(95, 23), *big.NewRat(77, 19),
        *big.NewRat(1, 17), *big.NewRat(11, 13), *big.NewRat(13, 11), *big.NewRat(15, 14),
        *big.NewRat(15, 2), *big.NewRat(55, 1),
    }

    tests := []struct {
        n      *big.Int
        limit  int
        output string
    }{
        {big.NewInt(2), 10, "2 15 825 725 1925 2275 425 390 330 290 "},
        {big.NewInt(2), 20, "2 15 825 725 1925 2275 425 390 330 290 770 910 115 10 30 225 12375 10850 28875 25775 "},
    }

    for _, test := range tests {
        var output string
        exec(fractions, test.n, test.limit)
        if output != test.output {
            t.Errorf("Expected output %s, got %s for n=%v and limit=%d", test.output, output, test.n, test.limit)
        }
    }
}

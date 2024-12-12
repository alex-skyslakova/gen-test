package main

import (
    "math/big"
    "testing"
)

func TestMin(t *testing.T) {
    testCases := []struct {
        a, b, expected *big.Int
    }{
        {new(big.Int).SetUint64(1), new(big.Int).SetUint64(2), new(big.Int).SetUint64(1)},
        {new(big.Int).SetUint64(3), new(big.Int).SetUint64(3), new(big.Int).SetUint64(3)},
        {new(big.Int).SetUint64(5), new(big.Int).SetUint64(4), new(big.Int).SetUint64(4)},
        {new(big.Int).SetUint64(0), new(big.Int).SetUint64(0), new(big.Int).SetUint64(0)},
    }

    for _, tc := range testCases {
        result := min(tc.a, tc.b)
        if result.Cmp(tc.expected) != 0 {
            t.Errorf("min(%v, %v) = %v; want %v", tc.a, tc.b, result, tc.expected)
        }
    }
}

func TestHumble(t *testing.T) {
    testCases := []struct {
        n        int
        expected []*big.Int
    }{
        {1, []*big.Int{new(big.Int).SetUint64(1)}},
        {5, []*big.Int{
            new(big.Int).SetUint64(1),
            new(big.Int).SetUint64(2),
            new(big.Int).SetUint64(3),
            new(big.Int).SetUint64(4),
            new(big.Int).SetUint64(5),
        }},
        {10, []*big.Int{
            new(big.Int).SetUint64(1),
            new(big.Int).SetUint64(2),
            new(big.Int).SetUint64(3),
            new(big.Int).SetUint64(4),
            new(big.Int).SetUint64(5),
            new(big.Int).SetUint64(6),
            new(big.Int).SetUint64(7),
            new(big.Int).SetUint64(8),
            new(big.Int).SetUint64(9),
            new(big.Int).SetUint64(10),
        }},
    }

    for _, tc := range testCases {
        result := humble(tc.n)
        if len(result) != len(tc.expected) {
            t.Errorf("humble(%d) returned %d elements; want %d", tc.n, len(result), len(tc.expected))
            continue
        }
        for i := 0; i < len(result); i++ {
            if result[i].Cmp(tc.expected[i]) != 0 {
                t.Errorf("humble(%d)[%d] = %v; want %v", tc.n, i, result[i], tc.expected[i])
            }
        }
    }
}

func TestCommatize(t *testing.T) {
    testCases := []struct {
        n        int
        expected string
    }{
        {1, "1"},
        {123, "123"},
        {1234, "1,234"},
        {1234567, "1,234,567"},
        {0, "0"},
    }

    for _, tc := range testCases {
        result := commatize(tc.n)
        if result != tc.expected {
            t.Errorf("commatize(%d) = %s; want %s", tc.n, result, tc.expected)
        }
    }
}

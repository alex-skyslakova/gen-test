package main

import (
    "math/big"
    "reflect"
    "testing"
)

func TestCommatize(t *testing.T) {
    tests := []struct {
        input    uint64
        expected string
    }{
        {1, "1"},
        {12, "12"},
        {123, "123"},
        {1234, "1,234"},
        {12345, "12,345"},
        {123456, "123,456"},
        {1234567, "1,234,567"},
        {12345678, "12,345,678"},
        {123456789, "123,456,789"},
        {1234567890, "1,234,567,890"},
    }

    for _, tt := range tests {
        result := commatize(tt.input)
        if result != tt.expected {
            t.Errorf("commatize(%d) = %s; want %s", tt.input, result, tt.expected)
        }
    }
}

func TestCubanPrimes(t *testing.T) {
    var z big.Int
    var cube1, cube2, diff uint64
    count := 0
    expectedCubans := []uint64{7, 19, 37, 61, 127, 271, 331, 397, 547, 631}
    cube1 = 1

    for i := 1; ; i++ {
        j := i + 1
        cube2 = uint64(j * j * j)
        diff = cube2 - cube1
        z.SetUint64(diff)
        if z.ProbablyPrime(0) {
            if count < len(expectedCubans) {
                if diff != expectedCubans[count] {
                    t.Errorf("Expected cuban prime %d at position %d, got %d", expectedCubans[count], count, diff)
                }
            }
            count++
            if count == 10 {
                break
            }
        }
        cube1 = cube2
    }
}

func TestCubanPrimes100000th(t *testing.T) {
    var z big.Int
    var cube1, cube2, cube100k, diff uint64
    count := 0
    cube1 = 1

    for i := 1; ; i++ {
        j := i + 1
        cube2 = uint64(j * j * j)
        diff = cube2 - cube1
        z.SetUint64(diff)
        if z.ProbablyPrime(0) {
            count++
            if count == 100000 {
                cube100k = diff
                break
            }
        }
        cube1 = cube2
    }

    // The 100,000th Cuban prime is 1,792,617,147,737, according to OEIS A002407.
    expected100k := uint64(1792617147737)
    if cube100k != expected100k {
        t.Errorf("Expected 100,000th cuban prime to be %d, got %d", expected100k, cube100k)
    }
}

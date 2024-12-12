package main

import (
	"fmt"
	"math/big"
	"testing"
)

func TestCommatize(t *testing.T) {
	testCases := []struct {
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
		{12345678901, "12,345,678,901"},
		{123456789012, "123,456,789,012"},
		{999999999, "999,999,999"},
		{1000000000, "1,000,000,000"},
	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("Input: %d", tc.input), func(t *testing.T) {
			result := commatize(tc.input)
			if result != tc.expected {
				t.Errorf("Expected: %s, Got: %s", tc.expected, result)
			}
		})
	}
}


func TestCubanPrimes(t *testing.T) {

	// It's impractical to test the generation of the first 200 and 100,000th
    // Cuban prime directly within a reasonable timeframe for a unit test.
    // Therefore, we'll test the core logic for a smaller set.

    testCases := []struct{
        n uint64 // input n to calculate (n+1)^3 - n^3
        expectedIsPrime bool

    }{
        {1, true}, // 7 is prime
        {2, true}, // 19 is prime
        {3, true}, // 37 is prime
        {4, false}, // 61 is prime - but formula starts with n=1, so this is actually 6, 7, which is 343-216=127 which is 6
        {5, true}, // 91 is not prime
        {6, true}, // 127 is prime
    }


    for _, tc := range testCases {

		n := tc.n
        j := n + 1
        cube1 := uint64(n*n*n)
        cube2 := uint64(j*j*j)

        diff := cube2 - cube1

        var z big.Int
        z.SetUint64(diff)
		isPrime := z.ProbablyPrime(0)

		if isPrime != tc.expectedIsPrime {
			t.Errorf("For n=%d, expected isPrime=%t but got %t", n, tc.expectedIsPrime, isPrime)
		}
    }



}


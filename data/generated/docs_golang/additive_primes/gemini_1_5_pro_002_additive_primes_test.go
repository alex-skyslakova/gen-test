package main

import "testing"

func TestIsPrime(t *testing.T) {
	testCases := []struct {
		input    int
		expected bool
	}{
		{input: 0, expected: false},
		{input: 1, expected: false},
		{input: 2, expected: true},
		{input: 3, expected: true},
		{input: 4, expected: false},
		{input: 5, expected: true},
		{input: 6, expected: false},
		{input: 7, expected: true},
		{input: 8, expected: false},
		{input: 9, expected: false},
		{input: 10, expected: false},
		{input: 11, expected: true},
		{input: 12, expected: false},
		{input: 13, expected: true},
		{input: 14, expected: false},
		{input: 15, expected: false},
		{input: 16, expected: false},
		{input: 17, expected: true},
        {input: 97, expected: true},
        {input: 99, expected: false},
        {input: 100, expected: false},
        {input: 101, expected: true},

	}

	for _, tc := range testCases {
		actual := isPrime(tc.input)
		if actual != tc.expected {
			t.Errorf("isPrime(%d) = %t; want %t", tc.input, actual, tc.expected)
		}
	}
}

func TestSumDigits(t *testing.T) {
	testCases := []struct {
		input    int
		expected int
	}{
		{input: 0, expected: 0},
		{input: 1, expected: 1},
		{input: 12, expected: 3},
		{input: 123, expected: 6},
		{input: 999, expected: 27},
	}

	for _, tc := range testCases {
		actual := sumDigits(tc.input)
		if actual != tc.expected {
			t.Errorf("sumDigits(%d) = %d; want %d", tc.input, actual, tc.expected)
		}
	}
}


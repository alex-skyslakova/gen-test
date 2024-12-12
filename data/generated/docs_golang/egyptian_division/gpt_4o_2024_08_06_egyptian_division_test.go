package main

import (
	"testing"
)

func TestEgyptianDivide(t *testing.T) {
	tests := []struct {
		dividend  int
		divisor   int
		quotient  int
		remainder int
	}{
		{580, 34, 17, 2},
		{100, 3, 33, 1},
		{50, 7, 7, 1},
		{0, 5, 0, 0},
		{5, 5, 1, 0},
		{5, 10, 0, 5},
	}

	for _, test := range tests {
		q, r := egyptianDivide(test.dividend, test.divisor)
		if q != test.quotient || r != test.remainder {
			t.Errorf("egyptianDivide(%d, %d) = (%d, %d); want (%d, %d)", test.dividend, test.divisor, q, r, test.quotient, test.remainder)
		}
	}
}

func TestEgyptianDivideInvalidArguments(t *testing.T) {
	tests := []struct {
		dividend int
		divisor  int
	}{
		{-1, 5},
		{5, 0},
		{5, -1},
	}

	for _, test := range tests {
		defer func() {
			if r := recover(); r == nil {
				t.Errorf("egyptianDivide(%d, %d) did not panic", test.dividend, test.divisor)
			}
		}()
		egyptianDivide(test.dividend, test.divisor)
	}
}

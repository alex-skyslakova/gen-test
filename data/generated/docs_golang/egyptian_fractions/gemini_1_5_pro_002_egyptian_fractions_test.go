package main

import (
	"math/big"
	"reflect"
	"testing"
)

func TestToEgyptian(t *testing.T) {
	tests := []struct {
		input    *big.Rat
		expected []*big.Rat
	}{
		{big.NewRat(43, 48), []*big.Rat{big.NewRat(1, 2), big.NewRat(1, 3), big.NewRat(1, 16)}},
		{big.NewRat(5, 121), []*big.Rat{big.NewRat(1, 25), big.NewRat(1, 757), big.NewRat(1, 18925)}},
		{big.NewRat(2014, 59), []*big.Rat{big.NewRat(34, 1), big.NewRat(1, 10), big.NewRat(1, 590)}},
		{big.NewRat(0, 5), []*big.Rat{big.NewRat(0, 1)}},
		{big.NewRat(5, 0), nil}, // Expecting nil for division by zero as per documentation
		{big.NewRat(5, 5), []*big.Rat{big.NewRat(1, 1)}},
		{big.NewRat(7, 3), []*big.Rat{big.NewRat(2, 1), big.NewRat(1, 3)}},
		{big.NewRat(1, 2), []*big.Rat{big.NewRat(1, 2)}},
		{big.NewRat(1, 1), []*big.Rat{big.NewRat(1, 1)}},
		{big.NewRat(3, 2), []*big.Rat{big.NewRat(1, 1), big.NewRat(1, 2)}},
		{big.NewRat(4, 3), []*big.Rat{big.NewRat(1, 1), big.NewRat(1, 3)}},

	}

	for _, test := range tests {
		// Handle potential panic for division by zero
		defer func() {
			if r := recover(); r != nil {
				if test.expected != nil {
					t.Errorf("Input %v: Expected no panic, but got one", test.input)
				}
			}
		}()

		actual := toEgyptian(test.input)
		if !reflect.DeepEqual(actual, test.expected) {

			t.Errorf("Input %v: Expected %v, but got %v", test.input, test.expected, actual)
		}

	}

}

func TestToEgyptianRecursive(t *testing.T) {

	tests := []struct {
		input    *big.Rat
		expected []*big.Rat
	}{
		{big.NewRat(43, 48), []*big.Rat{big.NewRat(1, 2), big.NewRat(1, 3), big.NewRat(1, 16)}},
		{big.NewRat(5, 121), []*big.Rat{big.NewRat(1, 25), big.NewRat(1, 757), big.NewRat(1, 18925)}},
		{big.NewRat(0, 5), []*big.Rat{}},         // Test zero numerator
		{big.NewRat(1, 2), []*big.Rat{big.NewRat(1, 2)}},   // Test basic proper fraction
		{big.NewRat(1, 1), []*big.Rat{big.NewRat(1, 1)}},
	}

	for _, test := range tests {
		actual := toEgyptianRecursive(test.input, []*big.Rat{})
		if !reflect.DeepEqual(actual, test.expected) {
			t.Errorf("Input %v: Expected %v, but got %v", test.input, test.expected, actual)
		}
	}
}


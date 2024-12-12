package main

import (
	"math/big"
	"testing"
)

func TestTans(t *testing.T) {
	testCases := []struct {
		input    []mTerm
		expected *big.Rat
	}{
		{[]mTerm{{1, 1, 2}, {1, 1, 3}}, big.NewRat(1, 1)},
		{[]mTerm{{2, 1, 3}, {1, 1, 7}}, big.NewRat(1, 1)},
		{[]mTerm{{4, 1, 5}, {-1, 1, 239}}, big.NewRat(1, 1)},
		{[]mTerm{{5, 1, 7}, {2, 3, 79}}, big.NewRat(1, 1)},
		{[]mTerm{{1, 1, 2}, {1, 1, 5}, {1, 1, 8}}, big.NewRat(1, 1)},
		{[]mTerm{{4, 1, 5}, {-1, 1, 70}, {1, 1, 99}}, big.NewRat(1, 1)},
		{[]mTerm{{5, 1, 7}, {4, 1, 53}, {2, 1, 4443}}, big.NewRat(1, 1)},
		{[]mTerm{{6, 1, 8}, {2, 1, 57}, {1, 1, 239}}, big.NewRat(1, 1)},
		{[]mTerm{{8, 1, 10}, {-1, 1, 239}, {-4, 1, 515}}, big.NewRat(1, 1)},
		{[]mTerm{{12, 1, 18}, {8, 1, 57}, {-5, 1, 239}}, big.NewRat(1, 1)},
		{[]mTerm{{16, 1, 21}, {3, 1, 239}, {4, 3, 1042}}, big.NewRat(1, 1)},
		{[]mTerm{{22, 1, 28}, {2, 1, 443}, {-5, 1, 1393}, {-10, 1, 11018}}, big.NewRat(1, 1)},
		{[]mTerm{{22, 1, 38}, {17, 7, 601}, {10, 7, 8149}}, big.NewRat(1, 1)},
		{[]mTerm{{44, 1, 57}, {7, 1, 239}, {-12, 1, 682}, {24, 1, 12943}}, big.NewRat(1, 1)},
		{[]mTerm{{88, 1, 172}, {51, 1, 239}, {32, 1, 682}, {44, 1, 5357}, {68, 1, 12943}}, big.NewRat(1, 1)},
		{[]mTerm{{88, 1, 172}, {51, 1, 239}, {32, 1, 682}, {44, 1, 5357}, {68, 1, 12944}}, big.NewRat(2455145136385533, 2455145136351100)}, // Incorrect formula
	}

	for _, tc := range testCases {
		result := tans(tc.input)
		if result.Cmp(tc.expected) != 0 {
			t.Errorf("For input %v, expected %v, got %v", tc.input, tc.expected, result)
		}
	}
}

func TestTanEval(t *testing.T) {
	testCases := []struct {
		coef     int64
		f        *big.Rat
		expected *big.Rat
	}{
		{1, big.NewRat(1, 2), big.NewRat(1, 2)},
		{-1, big.NewRat(1, 2), big.NewRat(-1, 2)},
		{2, big.NewRat(1, 3), big.NewRat(3, 4)},
		{3, big.NewRat(1, 7), big.NewRat(37, 56)},

	}
	for _, tc := range testCases {
		result := tanEval(tc.coef, tc.f)
		if result.Cmp(tc.expected) != 0 {
			t.Errorf("For coef %d and f %v, expected %v, got %v", tc.coef, tc.f, tc.expected, result)

		}

	}

}


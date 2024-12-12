package main

import (
	"math/big"
	"testing"
)

func TestMachinLikeFormulas(t *testing.T) {
	testCases := []struct {
		terms   []mTerm
		isValid bool
	}{
		{[]mTerm{{1, 1, 2}, {1, 1, 3}}, true},
		{[]mTerm{{2, 1, 3}, {1, 1, 7}}, true},
		{[]mTerm{{4, 1, 5}, {-1, 1, 239}}, true},
		{[]mTerm{{5, 1, 7}, {2, 3, 79}}, true},
		{[]mTerm{{1, 1, 2}, {1, 1, 5}, {1, 1, 8}}, true},
		{[]mTerm{{4, 1, 5}, {-1, 1, 70}, {1, 1, 99}}, true},
		{[]mTerm{{5, 1, 7}, {4, 1, 53}, {2, 1, 4443}}, true},
		{[]mTerm{{6, 1, 8}, {2, 1, 57}, {1, 1, 239}}, true},
		{[]mTerm{{8, 1, 10}, {-1, 1, 239}, {-4, 1, 515}}, true},
		{[]mTerm{{12, 1, 18}, {8, 1, 57}, {-5, 1, 239}}, true},
		{[]mTerm{{16, 1, 21}, {3, 1, 239}, {4, 3, 1042}}, true},
		{[]mTerm{{22, 1, 28}, {2, 1, 443}, {-5, 1, 1393}, {-10, 1, 11018}}, true},
		{[]mTerm{{22, 1, 38}, {17, 7, 601}, {10, 7, 8149}}, true},
		{[]mTerm{{44, 1, 57}, {7, 1, 239}, {-12, 1, 682}, {24, 1, 12943}}, true},
		{[]mTerm{{88, 1, 172}, {51, 1, 239}, {32, 1, 682}, {44, 1, 5357}, {68, 1, 12943}}, true},
		{[]mTerm{{88, 1, 172}, {51, 1, 239}, {32, 1, 682}, {44, 1, 5357}, {68, 1, 12944}}, false},
	}

	for i, tc := range testCases {
		result := tans(tc.terms)
		expected := big.NewRat(1, 1)
		if tc.isValid && result.Cmp(expected) != 0 {
			t.Errorf("Test case %d failed: expected %v, got %v", i, expected, result)
		}
		if !tc.isValid && result.Cmp(expected) == 0 {
			t.Errorf("Test case %d failed: expected not %v, got %v", i, expected, result)
		}
	}
}

package main

import (
	"fmt"
	"testing"
)

func TestMultDigitalRoot(t *testing.T) {
	testCases := []struct {
		n    uint64
		base int
		mp   int
		mdr  int
	}{
		{123321, 10, 1, 0},
		{7739, 10, 3, 0},
		{893, 10, 3, 0},
		{899998, 10, 2, 0},
		{39, 10, 1, 27},
		{18446743999999999999, 10, 1, 0},
		{3778888999, 10, 2, 0},
		{277777788888899, 10, 10, 0},
        {0, 10, 0, 0},
        {1, 10, 0, 1},
        {10, 10, 0, 0},
		{7, 2, 0, 7}, // Test with base other than 10
		{10, 2, 1, 0},


	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("n=%d_base=%d", tc.n, tc.base), func(t *testing.T) {
			mp, mdr := MultDigitalRoot(tc.n, tc.base)
			if mp != tc.mp || mdr != tc.mdr {
				t.Errorf("MultDigitalRoot(%d, %d) = (%d, %d); want (%d, %d)", tc.n, tc.base, mp, mdr, tc.mp, tc.mdr)
			}
		})
	}
}


func TestMult(t *testing.T) {
	testCases := []struct {
		n    uint64
		base int
		want uint64
	}{
		{123, 10, 6},
		{7739, 10, 189},
		{893, 10, 216},
		{899998, 10, 0},
		{0, 10, 1},
		{10, 10, 0},
		{7, 2, 7},
		{10, 2, 0},
	}
	for _, tc := range testCases {
		t.Run(fmt.Sprintf("n=%d_base=%d", tc.n, tc.base), func(t *testing.T) {
			got := mult(tc.n, tc.base)
			if got != tc.want {
				t.Errorf("mult(%d, %d) = %d; want %d", tc.n, tc.base, got, tc.want)
			}
		})
	}
}




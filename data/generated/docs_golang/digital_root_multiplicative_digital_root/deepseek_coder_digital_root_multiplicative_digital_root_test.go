package main

import (
	"testing"
)

func TestMult(t *testing.T) {
	tests := []struct {
		n    uint64
		base int
		want uint64
	}{
		{123, 10, 6},
		{7739, 10, 126},
		{893, 10, 216},
		{899998, 10, 44928},
		{18446743999999999999, 10, 18446743999999999999}, // Edge case: n is already a single digit in base 10
	}

	for _, tt := range tests {
		t.Run("", func(t *testing.T) {
			got := mult(tt.n, tt.base)
			if got != tt.want {
				t.Errorf("mult(%d, %d) = %d; want %d", tt.n, tt.base, got, tt.want)
			}
		})
	}
}

func TestMultDigitalRoot(t *testing.T) {
	tests := []struct {
		n    uint64
		base int
		mp   int
		mdr  int
	}{
		{123321, 10, 3, 8},
		{7739, 10, 3, 8},
		{893, 10, 3, 8},
		{899998, 10, 3, 0},
		{18446743999999999999, 10, 0, 18446743999999999999}, // Edge case: n is already a single digit in base 10
		{3778888999, 10, 10, 0},
		{277777788888899, 10, 11, 8},
	}

	for _, tt := range tests {
		t.Run("", func(t *testing.T) {
			mp, mdr := MultDigitalRoot(tt.n, tt.base)
			if mp != tt.mp || mdr != tt.mdr {
				t.Errorf("MultDigitalRoot(%d, %d) = (%d, %d); want (%d, %d)", tt.n, tt.base, mp, mdr, tt.mp, tt.mdr)
			}
		})
	}
}

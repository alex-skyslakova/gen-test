package main

import (
	"math/big"
	"testing"
)

func TestTreePow(t *testing.T) {
	tests := []struct {
		x        float64
		n        int
		expected string
	}{
		{2, 0, "1"},
		{2, 1, "2"},
		{2, 2, "4"},
		{2, 3, "8"},
		{2, 4, "16"},
		{2, 5, "32"},
		{2, 6, "64"},
		{2, 7, "128"},
		{2, 8, "256"},
		{2, 9, "512"},
		{2, 10, "1024"},
		{2, 11, "2048"},
		{2, 12, "4096"},
		{2, 13, "8192"},
		{2, 14, "16384"},
		{2, 15, "32768"},
		{2, 16, "65536"},
		{2, 17, "131072"},
		{3, 191, "3138428376721"},
		{1.1, 81, "25937424601.0705"},
	}

	for _, tt := range tests {
		t.Run("", func(t *testing.T) {
			result := treePow(tt.x, tt.n)
			expected := new(big.Float)
			expected.SetString(tt.expected)
			if result.Cmp(expected) != 0 {
				t.Errorf("treePow(%f, %d) = %v; want %v", tt.x, tt.n, result, expected)
			}
		})
	}
}

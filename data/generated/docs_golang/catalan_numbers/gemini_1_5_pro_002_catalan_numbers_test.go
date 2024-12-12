package main

import (
	"math/big"
	"testing"
)

func catalan(n int64) *big.Int {
	var b, c big.Int
	return c.Div(b.Binomial(n*2, n), c.SetInt64(n+1))
}

func TestCatalan(t *testing.T) {
	tests := []struct {
		n    int64
		want string
	}{
		{0, "1"},
		{1, "1"},
		{2, "2"},
		{3, "5"},
		{4, "14"},
		{5, "42"},
		{6, "132"},
		{7, "429"},
		{8, "1430"},
		{9, "4862"},
		{10, "16796"},
		{11, "58786"},
		{12, "208012"},
		{13, "742900"},
		{14, "2674440"},
	}
	for _, tt := range tests {
		t.Run(string(tt.n+'0'), func(t *testing.T) {
			got := catalan(tt.n)
			if got.String() != tt.want {
				t.Errorf("catalan(%d) = %v, want %v", tt.n, got, tt.want)
			}
		})
	}
}


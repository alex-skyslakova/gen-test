package main

import (
	"math/big"
	"testing"
)

func TestBernoulli(t *testing.T) {
	tests := []struct {
		n    uint
		want string
	}{
		{0, "1"},
		{1, "-1/2"},
		{2, "1/6"},
		{3, "0"},
		{4, "-1/30"},
		{5, "0"},
		{6, "1/42"},
		{7, "0"},
		{8, "-1/30"},
		{9, "0"},
		{10, "5/66"},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
			got := bernoulli(tt.n)
			if got.RatString() != tt.want {
				t.Errorf("bernoulli(%d) = %s, want %s", tt.n, got.RatString(), tt.want)
			}
		})
	}
}

func TestBinomial(t *testing.T) {
	tests := []struct {
		n    int
		k    int
		want int64
	}{
		{0, 0, 1},
		{5, 2, 10},
		{10, 3, 120},
		{10, 0, 1},
		{10, 10, 1},
		{5, 6, 1},
		{-5, 2, 1},
		{5, -2, 1},

	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d,k=%d", tt.n, tt.k), func(t *testing.T) {
			if got := binomial(tt.n, tt.k); got != tt.want {
				t.Errorf("binomial(%v, %v) = %v, want %v", tt.n, tt.k, got, tt.want)
			}
		})
	}
}

func TestFaulhaberTriangle(t *testing.T) {
	tests := []struct {
		p    int
		want []string
	}{
		{0, []string{"1"}},
		{1, []string{"1/2", "1/2"}},
		{2, []string{"1/6", "1/2", "1/3"}},
		{3, []string{"0", "1/4", "1/2", "1/4"}},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("p=%d", tt.p), func(t *testing.T) {
			got := faulhaberTriangle(tt.p)
			if len(got) != len(tt.want) {
				t.Errorf("faulhaberTriangle(%d) returned slice of wrong length, got %d, want %d", tt.p, len(got), len(tt.want))
			}

			for i := range got {

				if got[i].RatString() != tt.want[i] {
					t.Errorf("faulhaberTriangle(%d)[%d] = %s, want %s", tt.p, i, got[i].RatString(), tt.want[i])

				}
			}
		})
	}
}



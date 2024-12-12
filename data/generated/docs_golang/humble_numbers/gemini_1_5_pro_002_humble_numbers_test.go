package main

import (
	"math/big"
	"testing"
)

func TestHumble(t *testing.T) {
	tests := []struct {
		n    int
		want []*big.Int
	}{
		{1, []*big.Int{big.NewInt(1)}},
		{2, []*big.Int{big.NewInt(1), big.NewInt(2)}},
		{3, []*big.Int{big.NewInt(1), big.NewInt(2), big.NewInt(3)}},
		{4, []*big.Int{big.NewInt(1), big.NewInt(2), big.NewInt(3), big.NewInt(4)}},
		{5, []*big.Int{big.NewInt(1), big.NewInt(2), big.NewInt(3), big.NewInt(4), big.NewInt(5)}},
		{10, []*big.Int{big.NewInt(1), big.NewInt(2), big.NewInt(3), big.NewInt(4), big.NewInt(5), big.NewInt(6), big.NewInt(7), big.NewInt(8), big.NewInt(9), big.NewInt(10)}},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
			got := humble(tt.n)
			if len(got) != len(tt.want) {
				t.Fatalf("humble(%d) returned %d elements, want %d", tt.n, len(got), len(tt.want))
			}
			for i := range got {
				if got[i].Cmp(tt.want[i]) != 0 {
					t.Errorf("humble(%d)[%d] = %s, want %s", tt.n, i, got[i], tt.want[i])
				}
			}
		})
	}
}


func TestCommatize(t *testing.T) {
	tests := []struct {
		n    int
		want string
	}{
		{0, "0"},
		{1, "1"},
		{10, "10"},
		{100, "100"},
		{1000, "1,000"},
		{10000, "10,000"},
		{100000, "100,000"},
		{1000000, "1,000,000"},
		{1234567890, "1,234,567,890"},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
			got := commatize(tt.n)
			if got != tt.want {
				t.Errorf("commatize(%d) = %q, want %q", tt.n, got, tt.want)
			}
		})
	}
}

func TestMin(t *testing.T) {
	tests := []struct {
		a, b *big.Int
		want *big.Int
	}{
		{big.NewInt(1), big.NewInt(2), big.NewInt(1)},
		{big.NewInt(2), big.NewInt(1), big.NewInt(1)},
		{big.NewInt(1), big.NewInt(1), big.NewInt(1)},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("a=%s, b=%s", tt.a, tt.b), func(t *testing.T) {
			got := min(tt.a, tt.b)
			if got.Cmp(tt.want) != 0 {
				t.Errorf("min(%s, %s) = %s, want %s", tt.a, tt.b, got, tt.want)
			}
		})
	}
}

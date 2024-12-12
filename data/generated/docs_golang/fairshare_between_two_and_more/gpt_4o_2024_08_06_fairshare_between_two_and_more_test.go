package main

import (
	"testing"
	"reflect"
)

// TestFairshare tests the fairshare function for different bases.
func TestFairshare(t *testing.T) {
	tests := []struct {
		n    int
		base int
		want []int
	}{
		{
			n:    25,
			base: 2,
			want: []int{0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1},
		},
		{
			n:    25,
			base: 3,
			want: []int{0, 1, 2, 1, 2, 0, 2, 0, 1, 1, 2, 0, 2, 0, 1, 2, 0, 1, 1, 2, 0, 2, 0, 1, 2},
		},
		{
			n:    25,
			base: 5,
			want: []int{0, 1, 2, 3, 4, 1, 2, 3, 4, 0, 2, 3, 4, 0, 1, 3, 4, 0, 1, 2, 4, 0, 1, 2, 3},
		},
		{
			n:    25,
			base: 11,
			want: []int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 2, 3, 4},
		},
	}

	for _, tt := range tests {
		t.Run("", func(t *testing.T) {
			got := fairshare(tt.n, tt.base)
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("fairshare(%d, %d) = %v, want %v", tt.n, tt.base, got, tt.want)
			}
		})
	}
}

// TestTurns tests the turns function for different scenarios.
func TestTurns(t *testing.T) {
	tests := []struct {
		n    int
		base int
		want string
	}{
		{
			n:    50000,
			base: 191,
			want: "only 191 have a turn",
		},
		{
			n:    50000,
			base: 1377,
			want: "only 1377 have a turn",
		},
		{
			n:    50000,
			base: 49999,
			want: "only 49999 have a turn",
		},
		{
			n:    50000,
			base: 50000,
			want: "only 50000 have a turn",
		},
		{
			n:    50000,
			base: 50001,
			want: "only 50000 have a turn",
		},
	}

	for _, tt := range tests {
		t.Run("", func(t *testing.T) {
			fss := fairshare(tt.n, tt.base)
			got := turns(tt.base, fss)
			if got != tt.want {
				t.Errorf("turns(%d, fairshare(%d, %d)) = %v, want %v", tt.base, tt.n, tt.base, got, tt.want)
			}
		})
	}
}

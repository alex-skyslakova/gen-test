package main

import (
	"strconv"
	"testing"
)

func TestFusc(t *testing.T) {
	tests := []struct {
		n    int
		want []int
	}{
		{0, []int{}},
		{1, []int{0}},
		{2, []int{0, 1}},
		{3, []int{0, 1, 1}},
		{4, []int{0, 1, 1, 2}},
		{5, []int{0, 1, 1, 2, 1}},
		{6, []int{0, 1, 1, 2, 1, 3}},
		{7, []int{0, 1, 1, 2, 1, 3, 2}},
		{8, []int{0, 1, 1, 2, 1, 3, 2, 4}},
	}
	for _, tt := range tests {
		t.Run(strconv.Itoa(tt.n), func(t *testing.T) {
			if got := fusc(tt.n); !equalSlices(got, tt.want) {
				t.Errorf("fusc(%d) = %v, want %v", tt.n, got, tt.want)
			}
		})
	}
}

func equalSlices(a, b []int) bool {
	if len(a) != len(b) {
		return false
	}
	for i, v := range a {
		if v != b[i] {
			return false
		}
	}
	return true
}

func TestFuscMaxLen(t *testing.T) {
	tests := []struct {
		n    int
		want [][2]int
	}{
		{0, [][2]int{}},
		{1, [][2]int{{0, 0}}},
		{2, [][2]int{{0, 0}, {1, 1}}},
		{10, [][2]int{{0, 0}, {1, 1}, {3, 2}, {5, 3}, {9, 5}}},
		{100, [][2]int{{0, 0}, {1, 1}, {3, 2}, {5, 3}, {9, 5}, {17, 8}, {33, 13}, {65, 21}, {97, 34}}},
	}
	for _, tt := range tests {
		t.Run(strconv.Itoa(tt.n), func(t *testing.T) {
			got := fuscMaxLen(tt.n)
			if len(got) != len(tt.want) {
				t.Errorf("fuscMaxLen(%v) returned slice of length %v, want %v", tt.n, len(got), len(tt.want))
			}
			for i := range got {
				if got[i] != tt.want[i] {
					t.Errorf("fuscMaxLen(%v)[%v]=%v, want %v", tt.n, i, got[i], tt.want[i])
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
		{-1, "-1"},
		{-10, "-10"},
		{-100, "-100"},
		{-1000, "-1,000"},
		{-10000, "-10,000"},
		{-100000, "-100,000"},
		{-1000000, "-1,000,000"},
	}
	for _, tt := range tests {
		t.Run(strconv.Itoa(tt.n), func(t *testing.T) {
			if got := commatize(tt.n); got != tt.want {
				t.Errorf("commatize(%v) = %v, want %v", tt.n, got, tt.want)
			}
		})
	}
}


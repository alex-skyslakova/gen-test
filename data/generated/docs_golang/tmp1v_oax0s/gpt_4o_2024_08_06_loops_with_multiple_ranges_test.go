package main

import (
	"testing"
)

func TestPow(t *testing.T) {
	tests := []struct {
		n, e int
		want int
	}{
		{2, 0, 1},
		{2, 1, 2},
		{2, 3, 8},
		{5, 3, 125},
		{10, 2, 100},
	}

	for _, tt := range tests {
		got := pow(tt.n, uint(tt.e))
		if got != tt.want {
			t.Errorf("pow(%d, %d) = %d; want %d", tt.n, tt.e, got, tt.want)
		}
	}
}

func TestAbs(t *testing.T) {
	tests := []struct {
		n    int
		want int
	}{
		{0, 0},
		{5, 5},
		{-5, 5},
		{123, 123},
		{-123, 123},
	}

	for _, tt := range tests {
		got := abs(tt.n)
		if got != tt.want {
			t.Errorf("abs(%d) = %d; want %d", tt.n, got, tt.want)
		}
	}
}

func TestCommatize(t *testing.T) {
	tests := []struct {
		n    int
		want string
	}{
		{0, " 0"},
		{5, " 5"},
		{-5, "-5"},
		{123, " 123"},
		{-123, "-123"},
		{1234, " 1,234"},
		{-1234, "-1,234"},
		{1234567, " 1,234,567"},
		{-1234567, "-1,234,567"},
	}

	for _, tt := range tests {
		got := commatize(tt.n)
		if got != tt.want {
			t.Errorf("commatize(%d) = %s; want %s", tt.n, got, tt.want)
		}
	}
}

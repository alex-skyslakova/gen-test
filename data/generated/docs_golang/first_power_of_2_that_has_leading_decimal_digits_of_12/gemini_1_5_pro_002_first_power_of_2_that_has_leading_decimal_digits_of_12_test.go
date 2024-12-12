package main

import (
	"testing"
)

func TestP(t *testing.T) {
	testCases := []struct {
		L    uint64
		n    uint64
		want uint64
	}{
		{12, 1, 7},
		{12, 2, 80},
		{123, 45, 12710},
		{123, 12345, 205135},
		{123, 678910, 11448622},
	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("L=%d, n=%d", tc.L, tc.n), func(t *testing.T) {
			got := p(tc.L, tc.n)
			if got != tc.want {
				t.Errorf("p(%d, %d) = %d; want %d", tc.L, tc.n, got, tc.want)
			}
		})
	}
}

func TestCommatize(t *testing.T) {
	testCases := []struct {
		n    uint64
		want string
	}{
		{123, "123"},
		{1234, "1,234"},
		{12345, "12,345"},
		{123456, "123,456"},
		{1234567, "1,234,567"},
		{1234567890, "1,234,567,890"},
	}
	for _, tc := range testCases {
		t.Run(fmt.Sprintf("n=%d", tc.n), func(t *testing.T) {
			got := commatize(tc.n)
			if got != tc.want {
				t.Errorf("commatize(%d) = %s; want %s", tc.n, got, tc.want)
			}
		})
	}
}


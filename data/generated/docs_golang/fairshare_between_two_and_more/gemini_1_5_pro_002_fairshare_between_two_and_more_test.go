package main

import (
	"reflect"
	"testing"
)

func TestFairshare(t *testing.T) {
	testCases := []struct {
		n     int
		base  int
		want  []int
		name string
	}{
		{n: 25, base: 2, want: []int{0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0}, name: "Base 2"},
		{n: 25, base: 3, want: []int{0, 1, 2, 0, 1, 2, 1, 2, 0, 0, 1, 2, 0, 1, 2, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1}, name: "Base 3"},
		{n: 25, base: 5, want: []int{0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 1, 2, 3, 4, 0, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4}, name: "Base 5"},
		{n: 25, base: 11, want: []int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3}, name: "Base 11"},
        {n: 0, base: 2, want: []int{}, name: "Zero length"},
        {n: 1, base: 2, want: []int{0}, name: "Length one"},

	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			got := fairshare(tc.n, tc.base)
			if !reflect.DeepEqual(got, tc.want) {
				t.Errorf("fairshare(%d, %d) = %v; want %v", tc.n, tc.base, got, tc.want)
			}
		})
	}
}



func TestTurns(t *testing.T) {
	testCases := []struct {
		n    int
		fss  []int
		want string
		name string
	}{
		{n: 2, fss: []int{0, 1, 1, 0}, want: "2", name: "Even distribution"},
		{n: 3, fss: []int{0, 1, 2, 0, 1, 2}, want: "2", name: "Even distribution 3"},
		{n: 2, fss: []int{0, 0, 0, 1}, want: "1 or 3", name: "Uneven distribution"},
		{n: 2, fss: []int{}, want: "only 0 have a turn", name: "Empty input"}, // Test empty input
        {n: 5, fss: []int{0,1,2,3,4,0,1,2,3,4}, want: "2", name: "Five people even distribution"},

	}
	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			got := turns(tc.n, tc.fss)
			if got != tc.want {
				t.Errorf("turns(%d, %v) = %q; want %q", tc.n, tc.fss, got, tc.want)
			}
		})
	}
}

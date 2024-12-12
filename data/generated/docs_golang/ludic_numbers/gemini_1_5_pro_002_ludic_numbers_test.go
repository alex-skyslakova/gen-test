package main

import (
	"reflect"
	"testing"
)

func TestLudic(t *testing.T) {
	tests := []struct {
		n    int
		max  int
		want []uint32
	}{
		{0, 0, nil},
		{1, 0, []uint32{1}},
		{2, 0, []uint32{1, 2}},
		{3, 0, []uint32{1, 2, 3}},
		{25, -1, []uint32{1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 25, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83}},
		{-1, 10, []uint32{1, 2, 3, 5, 7}},
		{10, 5, []uint32{1, 2, 3}},

		// Add more test cases as needed...
	}
	for _, test := range tests {
		got := Ludic(test.n, test.max)
		if !reflect.DeepEqual(got, test.want) {
			t.Errorf("Ludic(%d, %d) = %v, want %v", test.n, test.max, got, test.want)
		}
	}
}


func TestHas(t *testing.T) {
	tests := []struct {
		x    []uint32
		v    uint32
		want bool
	}{
		{[]uint32{1, 2, 3}, 2, true},
		{[]uint32{1, 2, 3}, 4, false},
		{[]uint32{1, 3, 5}, 2, false},
		{[]uint32{}, 1, false},
		{nil, 1, false},

	}
	for _, test := range tests {
		got := has(test.x, test.v)
		if got != test.want {
			t.Errorf("has(%v, %d) = %t, want %t", test.x, test.v, got, test.want)
		}
	}
}

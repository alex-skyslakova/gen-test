package main

import (
	"math/big"
	"reflect"
	"testing"
)

func TestPath(t *testing.T) {
	tests := []struct {
		n    int
		want []int
	}{
		{0, []int{}},
		{1, []int{1}},
		{2, []int{1, 2}},
		{3, []int{1, 3}},
		{4, []int{1, 2, 4}},
		{5, []int{1, 3, 5}},
		{6, []int{1, 2, 4, 6}},
		{7, []int{1, 3, 5, 7}},
		{8, []int{1, 2, 4, 8}},
		{17, []int{1, 2, 4, 8, 16, 17}},
		{43, []int{1, 2, 3, 5, 10, 20, 40, 43}},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {

			p = map[int]int{1: 0}
			lvl = [][]int{[]int{1}}

			if got := path(tt.n); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("path() = %v, want %v", got, tt.want)
			}
		})
	}
}


func TestTreePow(t *testing.T) {
	tests := []struct {
		x    float64
		n    int
		want string
	}{
		{2, 0, "1"},
		{2, 1, "2"},
		{2, 2, "4"},
		{2, 3, "8"},
		{2, 4, "16"},
		{2, 5, "32"},
		{2, 17, "131072"},
		{3, 191, "1769759737501119937289361301584955074757888"},
		{1.1, 81, "2454.003854567601"}, // Using a precision of 320 bits allows for accurate enough results for this test case. Adjust if needed for other tests.
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("x=%f, n=%d", tt.x, tt.n), func(t *testing.T) {
			p = map[int]int{1: 0}
			lvl = [][]int{[]int{1}}
			got := treePow(tt.x, tt.n).String()
			if got != tt.want {
				t.Errorf("treePow() = %v, want %v", got, tt.want)
			}
		})

	}
}


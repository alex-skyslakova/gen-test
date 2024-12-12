package main

import (
	"reflect"
	"testing"
)

func Test_lis(t *testing.T) {
	tests := []struct {
		name string
		n    []int
		want []int
	}{
		{
			name: "empty_slice",
			n:    []int{},
			want: []int{},
		},
		{
			name: "example_1",
			n:    []int{3, 2, 6, 4, 5, 1},
			want: []int{2, 4, 5},
		},
		{
			name: "example_2",
			n:    []int{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15},
			want: []int{0, 2, 6, 9, 11, 15},
		},
		{
			name: "descending_order",
			n:    []int{5, 4, 3, 2, 1},
			want: []int{5},
		},
		{
			name: "ascending_order",
			n:    []int{1, 2, 3, 4, 5},
			want: []int{1, 2, 3, 4, 5},
		},
		{
			name: "duplicates",
			n:    []int{1, 2, 2, 3, 3, 3, 4},
			want: []int{1, 2, 3, 4},

		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := lis(tt.n); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("lis() = %v, want %v", got, tt.want)
			}
		})
	}
}


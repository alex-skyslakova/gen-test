package main

import (
	"image"
	"reflect"
	"testing"
)

func TestConvexHull(t *testing.T) {
	testCases := []struct {
		name  string
		input points
		want  points
	}{
		{
			name: "ExampleCase",
			input: points{
				{16, 3}, {12, 17}, {0, 6}, {-4, -6}, {16, 6},
				{16, -7}, {16, -3}, {17, -4}, {5, 19}, {19, -8},
				{3, 16}, {12, 13}, {3, -4}, {17, 5}, {-3, 15},
				{-3, -9}, {0, 11}, {-9, -3}, {-4, -2}, {12, 10},
			},
			want: points{{-9, -3}, {-3, -9}, {19, -8}, {17, 5}, {12, 17}, {5, 19}, {-3, 15}},
		},
		{
			name:  "NoPoints",
			input: points{},
			want:  points{},
		},
		{
			name:  "SinglePoint",
			input: points{{0, 0}},
			want:  points{{0, 0}},
		},
		{
			name:  "TwoPoints",
			input: points{{0, 0}, {1, 1}},
			want:  points{{0, 0}, {1, 1}},
		},
		{
			name:  "ThreePointsCollinear",
			input: points{{0, 0}, {1, 1}, {2, 2}},
			want:  points{{0, 0}, {2, 2}},
		},
		{
			name:  "ThreePointsNonCollinear",
			input: points{{0, 0}, {1, 2}, {2, 1}},
			want:  points{{0, 0}, {1, 2}, {2, 1}},
		},
		{
			name:  "Square",
			input: points{{0, 0}, {0, 1}, {1, 0}, {1, 1}},
			want:  points{{0, 0}, {1, 0}, {1, 1}, {0, 1}},
		},

		{
			name:  "DuplicatePoints",
			input: points{{0, 0}, {0, 0}, {1, 1}, {1, 1}},
			want:  points{{0, 0}, {1, 1}},
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			got := tc.input.ConvexHull()

			if !equalIgnoringOrder(got, tc.want) {

				t.Errorf("ConvexHull() = %v, want %v", got, tc.want)
			}
		})
	}
}



func equalIgnoringOrder(a, b points) bool {
	if len(a) != len(b) {
		return false
	}
	aMap := make(map[image.Point]bool)
	bMap := make(map[image.Point]bool)

	for _, p := range a {
		aMap[p] = true
	}
	for _, p := range b {
		bMap[p] = true
	}

	return reflect.DeepEqual(aMap, bMap)
}


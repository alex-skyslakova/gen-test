package main

import (
	"math"
	"sort"
	"testing"
)

func TestGCD(t *testing.T) {
	testCases := []struct {
		a, b, expected int
	}{
		{1, 1, 1},
		{2, 4, 2},
		{12, 18, 6},
		{17, 23, 1},
		{4, 2, 2},

	}
	for _, tc := range testCases {
		result := gcd(tc.a, tc.b)
		if result != tc.expected {
			t.Errorf("gcd(%d, %d) = %d; want %d", tc.a, tc.b, result, tc.expected)
		}
	}
}


func TestIsHeron(t *testing.T) {
	testCases := []struct {
		h        float64
		expected bool
	}{
		{6.0, true},
		{6.5, false},
		{0.0, false},
		{-1.0, false},
		{210.0, true},
	}
	for _, tc := range testCases {
		result := is_heron(tc.h)
		if result != tc.expected {
			t.Errorf("is_heron(%f) = %t; want %t", tc.h, result, tc.expected)
		}
	}
}


func TestByAreaPerimeterSort(t *testing.T) {
	triangles := [][]int{
		{3, 4, 5, 12, 6},
		{5, 5, 6, 16, 12},
		{5, 5, 8, 18, 12},
		{4, 5, 6, 15, 9},  // Added a conflict to show Area takes presedence over Perimeter
		{13, 14, 15, 42, 84},
	}
	sort.Sort(by_area_perimeter(triangles))

	expected := [][]int{
		{3, 4, 5, 12, 6},
		{4, 5, 6, 15, 9},
		{5, 5, 6, 16, 12},
		{5, 5, 8, 18, 12},
		{13, 14, 15, 42, 84},
	}

	for i := range triangles {
		for j := range triangles[i] {
			if triangles[i][j] != expected[i][j] {
				t.Errorf("Sorted triangles at index %d, element %d: got %d, want %d", i, j, triangles[i][j], expected[i][j])
			}
		}
	}

}

// A test for calculating the area
func TestHeronsFormula(t *testing.T) {
	testCases := []struct {
		a, b, c int
		expected float64
	}{
		{3, 4, 5, 6.0},
		{5, 12, 13, 30.0},
		{7, 24, 25, 84.0},
		{9, 40, 41, 180.0},
	}

	for _, tc := range testCases {
        p := tc.a + tc.b + tc.c
        s := float64(p) / 2.0
        area := math.Sqrt(s * (s - float64(tc.a)) * (s - float64(tc.b)) * (s - float64(tc.c)))

		if area != tc.expected {
			t.Errorf("HeronsFormula(%d, %d, %d) = %f; want %f", tc.a, tc.b, tc.c, area, tc.expected)
		}
	}
}





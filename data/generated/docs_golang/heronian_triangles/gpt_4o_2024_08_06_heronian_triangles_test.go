package main

import (
	"math"
	"testing"
)

// TestGCD tests the gcd function
func TestGCD(t *testing.T) {
	tests := []struct {
		a, b, expected int
	}{
		{3, 5, 1},
		{6, 8, 2},
		{12, 15, 3},
		{17, 19, 1},
		{100, 10, 10},
	}

	for _, test := range tests {
		result := gcd(test.a, test.b)
		if result != test.expected {
			t.Errorf("gcd(%d, %d) = %d; expected %d", test.a, test.b, result, test.expected)
		}
	}
}

// TestIsHeron tests the is_heron function
func TestIsHeron(t *testing.T) {
	tests := []struct {
		h        float64
		expected bool
	}{
		{6.0, true},
		{6.5, false},
		{0.0, false},
		{10.0, true},
		{15.5, false},
	}

	for _, test := range tests {
		result := is_heron(test.h)
		if result != test.expected {
			t.Errorf("is_heron(%f) = %v; expected %v", test.h, result, test.expected)
		}
	}
}

// TestHeronianTriangles tests the generation of Heronian triangles
func TestHeronianTriangles(t *testing.T) {
	expectedCount := 5 // Known count of primitive Heronian triangles with sides <= 10
	var l [][]int
	for c := 1; c <= 10; c++ {
		for b := 1; b <= c; b++ {
			for a := 1; a <= b; a++ {
				if gcd(gcd(a, b), c) == 1 {
					p := a + b + c
					s := float64(p) / 2.0
					area := math.Sqrt(s * (s - float64(a)) * (s - float64(b)) * (s - float64(c)))
					if is_heron(area) {
						l = append(l, []int{a, b, c, p, int(area)})
					}
				}
			}
		}
	}

	if len(l) != expectedCount {
		t.Errorf("Number of primitive Heronian triangles with sides up to 10 = %d; expected %d", len(l), expectedCount)
	}
}

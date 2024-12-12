package main

import (
	"testing"
)

// TestBC tests the binomial coefficient generation function
func TestBC(t *testing.T) {
	tests := []struct {
		p        int
		expected []int64
	}{
		{0, []int64{1}},
		{1, []int64{-1, 1}},
		{2, []int64{1, -2, 1}},
		{3, []int64{-1, 3, -3, 1}},
		{4, []int64{1, -4, 6, -4, 1}},
		{5, []int64{-1, 5, -10, 10, -5, 1}},
		{6, []int64{1, -6, 15, -20, 15, -6, 1}},
		{7, []int64{-1, 7, -21, 35, -35, 21, -7, 1}},
	}

	for _, test := range tests {
		result := bc(test.p)
		for i, v := range result {
			if v != test.expected[i] {
				t.Errorf("bc(%d) = %v; want %v", test.p, result, test.expected)
			}
		}
	}
}

// TestAKS tests the AKS primality test function
func TestAKS(t *testing.T) {
	tests := []struct {
		p        int
		expected bool
	}{
		{2, true},
		{3, true},
		{4, false},
		{5, true},
		{6, false},
		{7, true},
		{8, false},
		{9, false},
		{10, false},
		{11, true},
		{12, false},
		{13, true},
		{14, false},
		{15, false},
		{16, false},
		{17, true},
		{18, false},
		{19, true},
		{20, false},
		{21, false},
		{22, false},
		{23, true},
		{24, false},
		{25, false},
		{26, false},
		{27, false},
		{28, false},
		{29, true},
		{30, false},
		{31, true},
		{32, false},
		{33, false},
		{34, false},
		{35, false},
		{36, false},
		{37, true},
		{38, false},
		{39, false},
		{40, false},
		{41, true},
		{42, false},
		{43, true},
		{44, false},
		{45, false},
		{46, false},
		{47, true},
		{48, false},
		{49, false},
	}

	for _, test := range tests {
		result := aks(test.p)
		if result != test.expected {
			t.Errorf("aks(%d) = %v; want %v", test.p, result, test.expected)
		}
	}
}

// TestPP tests the polynomial pretty-printing function
func TestPP(t *testing.T) {
	tests := []struct {
		coefficients []int64
		expected     string
	}{
		{[]int64{1}, "1"},
		{[]int64{-1, 1}, "-1x + 1"},
		{[]int64{1, -2, 1}, "1x² - 2x + 1"},
		{[]int64{-1, 3, -3, 1}, "-1x³ + 3x² - 3x + 1"},
		{[]int64{1, -4, 6, -4, 1}, "1x⁴ - 4x³ + 6x² - 4x + 1"},
	}

	for _, test := range tests {
		result := pp(test.coefficients)
		if result != test.expected {
			t.Errorf("pp(%v) = %s; want %s", test.coefficients, result, test.expected)
		}
	}
}

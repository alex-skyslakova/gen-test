package main

import (
	"testing"
)

func TestFfr(t *testing.T) {
	// Test the first ten values of R
	expectedR := []int{1, 3, 7, 12, 18, 26, 35, 45, 56, 69}
	for i, expected := range expectedR {
		if result := ffr(i + 1); result != expected {
			t.Errorf("ffr(%d) = %d; want %d", i+1, result, expected)
		}
	}
}

func TestFfs(t *testing.T) {
	// Test the first few values of S to ensure it is correctly generated
	expectedS := []int{2, 4, 5, 6, 8, 9, 10, 11, 13, 14}
	for i, expected := range expectedS {
		if result := ffs(i + 1); result != expected {
			t.Errorf("ffs(%d) = %d; want %d", i+1, result, expected)
		}
	}
}

func TestCoverage(t *testing.T) {
	// Test that the first 40 values of ffr plus the first 960 values of ffs
	// include all the integers from 1 to 1000 exactly once.
	var found [1001]int
	for n := 1; n <= 40; n++ {
		found[ffr(n)]++
	}
	for n := 1; n <= 960; n++ {
		found[ffs(n)]++
	}
	for i := 1; i <= 1000; i++ {
		if found[i] != 1 {
			t.Errorf("Number %d is found %d times; want 1", i, found[i])
		}
	}
}

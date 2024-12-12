package main

import (
	"testing"
)

// TestFFR tests the ffr function for the first 10 values of R.
func TestFFR(t *testing.T) {
	expectedR := []int{1, 3, 7, 12, 18, 26, 35, 45, 56, 69}
	for n, expected := range expectedR {
		result := ffr(n + 1) // ffr is 1-based
		if result != expected {
			t.Errorf("ffr(%d) = %d; want %d", n+1, result, expected)
		}
	}
}

// TestFFS tests the ffs function to ensure it generates the correct sequence.
func TestFFS(t *testing.T) {
	// We will test the first few values of S to ensure correctness.
	expectedS := []int{2, 4, 5, 6, 8, 9, 10, 11, 13, 14}
	for n, expected := range expectedS {
		result := ffs(n + 1) // ffs is 1-based
		if result != expected {
			t.Errorf("ffs(%d) = %d; want %d", n+1, result, expected)
		}
	}
}

// TestCompleteSequence tests that the first 40 values of ffr and the first 960 values of ffs
// include all integers from 1 to 1000 exactly once.
func TestCompleteSequence(t *testing.T) {
	var found [1001]int
	for n := 1; n <= 40; n++ {
		found[ffr(n)]++
	}
	for n := 1; n <= 960; n++ {
		found[ffs(n)]++
	}
	for i := 1; i <= 1000; i++ {
		if found[i] != 1 {
			t.Errorf("Integer %d is found %d times; want 1", i, found[i])
		}
	}
}

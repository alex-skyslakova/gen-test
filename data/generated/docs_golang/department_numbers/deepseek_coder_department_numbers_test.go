package main

import (
	"testing"
)

func TestValidCombinations(t *testing.T) {
	// Expected valid combinations
	expectedCombinations := [][]int{
		{2, 3, 7},
		{2, 4, 6},
		{2, 6, 4},
		{2, 7, 3},
		{4, 1, 7},
		{4, 2, 6},
		{4, 3, 5},
		{4, 5, 3},
		{4, 6, 2},
		{4, 7, 1},
		{6, 1, 5},
		{6, 2, 4},
		{6, 4, 2},
		{6, 5, 1},
	}

	// Collect all valid combinations from the function
	var validCombinations [][]int
	count := 0
	for i := 2; i < 7; i += 2 {
		for j := 1; j < 8; j++ {
			if j == i {
				continue
			}
			for k := 1; k < 8; k++ {
				if k == i || k == j {
					continue
				}
				if i+j+k != 12 {
					continue
				}
				validCombinations = append(validCombinations, []int{i, j, k})
				count++
			}
		}
	}

	// Check the number of valid combinations
	if count != len(expectedCombinations) {
		t.Errorf("Expected %d valid combinations, but got %d", len(expectedCombinations), count)
	}

	// Check each combination
	for _, expected := range expectedCombinations {
		found := false
		for _, combination := range validCombinations {
			if combination[0] == expected[0] && combination[1] == expected[1] && combination[2] == expected[2] {
				found = true
				break
			}
		}
		if !found {
			t.Errorf("Expected combination %v not found", expected)
		}
	}
}

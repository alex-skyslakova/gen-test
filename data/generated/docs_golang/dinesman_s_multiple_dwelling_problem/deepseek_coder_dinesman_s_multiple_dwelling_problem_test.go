package main

import (
	"testing"
)

func TestDinesmanProblem(t *testing.T) {
	// Test case 1: Valid solution should pass all rules
	t.Run("ValidSolution", func(t *testing.T) {
		a := assignments{
			"Baker":    3,
			"Cooper":   2,
			"Fletcher": 4,
			"Miller":   5,
			"Smith":    1,
		}
		for _, rule := range rules {
			if !rule(a) {
				t.Errorf("Rule failed for valid solution: %v", a)
			}
		}
	})

	// Test case 2: Baker on top floor should fail
	t.Run("BakerOnTopFloor", func(t *testing.T) {
		a := assignments{
			"Baker":    5,
			"Cooper":   2,
			"Fletcher": 4,
			"Miller":   3,
			"Smith":    1,
		}
		if rules[0](a) {
			t.Errorf("Baker on top floor did not fail: %v", a)
		}
	})

	// Test case 3: Cooper on bottom floor should fail
	t.Run("CooperOnBottomFloor", func(t *testing.T) {
		a := assignments{
			"Baker":    3,
			"Cooper":   1,
			"Fletcher": 4,
			"Miller":   5,
			"Smith":    2,
		}
		if rules[1](a) {
			t.Errorf("Cooper on bottom floor did not fail: %v", a)
		}
	})

	// Test case 4: Fletcher on top or bottom floor should fail
	t.Run("FletcherOnTopOrBottomFloor", func(t *testing.T) {
		a := assignments{
			"Baker":    3,
			"Cooper":   2,
			"Fletcher": 5,
			"Miller":   4,
			"Smith":    1,
		}
		if rules[2](a) {
			t.Errorf("Fletcher on top floor did not fail: %v", a)
		}

		a["Fletcher"] = 1
		if rules[2](a) {
			t.Errorf("Fletcher on bottom floor did not fail: %v", a)
		}
	})

	// Test case 5: Miller not higher than Cooper should fail
	t.Run("MillerNotHigherThanCooper", func(t *testing.T) {
		a := assignments{
			"Baker":    3,
			"Cooper":   5,
			"Fletcher": 4,
			"Miller":   2,
			"Smith":    1,
		}
		if rules[3](a) {
			t.Errorf("Miller not higher than Cooper did not fail: %v", a)
		}
	})

	// Test case 6: Smith adjacent to Fletcher should fail
	t.Run("SmithAdjacentToFletcher", func(t *testing.T) {
		a := assignments{
			"Baker":    3,
			"Cooper":   2,
			"Fletcher": 4,
			"Miller":   5,
			"Smith":    3,
		}
		if rules[4](a) {
			t.Errorf("Smith adjacent to Fletcher did not fail: %v", a)
		}
	})

	// Test case 7: Fletcher adjacent to Cooper should fail
	t.Run("FletcherAdjacentToCooper", func(t *testing.T) {
		a := assignments{
			"Baker":    3,
			"Cooper":   2,
			"Fletcher": 3,
			"Miller":   5,
			"Smith":    1,
		}
		if rules[5](a) {
			t.Errorf("Fletcher adjacent to Cooper did not fail: %v", a)
		}
	})
}

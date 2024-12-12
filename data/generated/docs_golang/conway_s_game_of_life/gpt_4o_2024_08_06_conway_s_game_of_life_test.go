package main

import (
	"testing"
)

func TestBlinker(t *testing.T) {
	// Initialize a 3x3 grid
	life := &Life{
		w: 3,
		h: 3,
		a: NewField(3, 3),
		b: NewField(3, 3),
	}

	// Set up the blinker pattern
	life.a.Set(0, 1, true)
	life.a.Set(1, 1, true)
	life.a.Set(2, 1, true)

	// Expected pattern after 1st generation
	expectedGen1 := [][]bool{
		{false, true, false},
		{false, true, false},
		{false, true, false},
	}

	// Expected pattern after 2nd generation (should be same as initial)
	expectedGen2 := [][]bool{
		{false, false, false},
		{true, true, true},
		{false, false, false},
	}

	// Expected pattern after 3rd generation (should be same as 1st)
	expectedGen3 := [][]bool{
		{false, true, false},
		{false, true, false},
		{false, true, false},
	}

	// Step 1
	life.Step()
	assertFieldState(t, life.a, expectedGen1, "Generation 1")

	// Step 2
	life.Step()
	assertFieldState(t, life.a, expectedGen2, "Generation 2")

	// Step 3
	life.Step()
	assertFieldState(t, life.a, expectedGen3, "Generation 3")
}

func assertFieldState(t *testing.T, field Field, expected [][]bool, generation string) {
	for y := 0; y < field.h; y++ {
		for x := 0; x < field.w; x++ {
			if field.State(x, y) != expected[y][x] {
				t.Errorf("Mismatch in %s at (%d, %d): expected %v, got %v", generation, x, y, expected[y][x], field.State(x, y))
			}
		}
	}
}

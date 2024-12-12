package main

import (
	"testing"
)

func TestEulerSum(t *testing.T) {
	x0, x1, x2, x3, y := eulerSum()

	// Check if the solution matches the known counterexample.
	if x0 != 27 || x1 != 84 || x2 != 110 || x3 != 133 || y != 144 {
		t.Errorf("Incorrect solution: x0=%d, x1=%d, x2=%d, x3=%d, y=%d", x0, x1, x2, x3, y)
	}

    // Verify the equation holds
    var pow5 [250]int
	for i := range pow5 {
		pow5[i] = i * i * i * i * i
	}
    if pow5[x0] + pow5[x1] + pow5[x2] + pow5[x3] != pow5[y] {
        t.Errorf("Equation does not hold for the returned values")
    }
}


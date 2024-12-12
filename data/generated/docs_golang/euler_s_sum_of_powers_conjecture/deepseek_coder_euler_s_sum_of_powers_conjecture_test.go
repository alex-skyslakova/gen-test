package main

import (
	"testing"
)

func TestEulerSum(t *testing.T) {
	x0, x1, x2, x3, y := eulerSum()

	// Verify that all xi's and y are distinct integers between 0 and 250 (exclusive)
	if x0 <= 0 || x0 >= 250 || x1 <= 0 || x1 >= 250 || x2 <= 0 || x2 >= 250 || x3 <= 0 || x3 >= 250 || y <= 0 || y >= 250 {
		t.Errorf("Values out of range: x0=%d, x1=%d, x2=%d, x3=%d, y=%d", x0, x1, x2, x3, y)
	}

	if x0 == x1 || x0 == x2 || x0 == x3 || x0 == y || x1 == x2 || x1 == x3 || x1 == y || x2 == x3 || x2 == y || x3 == y {
		t.Errorf("Values are not distinct: x0=%d, x1=%d, x2=%d, x3=%d, y=%d", x0, x1, x2, x3, y)
	}

	// Verify that the sum of the fifth powers equals the fifth power of y
	pow5 := func(n int) int {
		return n * n * n * n * n
	}

	if pow5(x0)+pow5(x1)+pow5(x2)+pow5(x3) != pow5(y) {
		t.Errorf("Sum of fifth powers does not equal y^5: x0=%d, x1=%d, x2=%d, x3=%d, y=%d", x0, x1, x2, x3, y)
	}
}

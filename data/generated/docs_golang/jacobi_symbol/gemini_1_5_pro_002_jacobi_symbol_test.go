package main

import (
	"math/big"
	"testing"
)

func TestJacobi(t *testing.T) {
	testCases := []struct {
		a        uint64
		n        uint64
		expected int
	}{
		{0, 1, 0},
		{1, 1, 1},
		{2, 1, 0},
		{0, 3, 0},
		{1, 3, 1},
		{2, 3, -1},
		{3, 3, 0},
		{0, 5, 0},
		{1, 5, 1},
		{2, 5, -1},
		{3, 5, -1},
		{4, 5, 1},
		{5, 5, 0},
		{2, 9, 1},  // 2 is a square mod 9 (2^2=4, 4+5*9)
		{15, 17, 1}, // 15 is a square mod 17, (15^2=225, 225 = 13*17+4, 4 + 17(13-x)=225-17x, 13-x=8, x=5)
		{1, 21, 1},
		{2, 21, 1},
		{8, 21, -1},
		{15, 35, 0},
		{13, 37, 1},  // https://math.stackexchange.com/questions/1439094/calculate-jacobi13-37
		{5, 41, -1},
		{3, 47, -1},
		{2, 47, 1},
		{7, 47, -1},
	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("a=%d, n=%d", tc.a, tc.n), func(t *testing.T) {
			actual := jacobi(tc.a, tc.n)
			if actual != tc.expected {
				t.Errorf("Expected %d, got %d", tc.expected, actual)
			}
		})
	}


	// Compare with big.Jacobi for a range of values
	for n := uint64(1); n <= 47; n += 2 {
		for a := uint64(0); a <= 9; a++ {
			t.Run(fmt.Sprintf("Compare with big.Jacobi a=%d, n=%d", a, n), func(t *testing.T) {
				ba, bn := new(big.Int), new(big.Int)
				ba.SetUint64(a)
				bn.SetUint64(n)
				expected := big.Jacobi(ba, bn)
				actual := jacobi(a, n)
				if int(expected.Int64()) != actual {
					t.Errorf("For a=%d, n=%d: Expected %d, got %d", a, n, expected, actual)
				}
			})
		}
	}


}

// This is needed for the tests to compile, but isn't actually part of the tests.
func Example_main() {
	// This function is never called, but provides example code.
}


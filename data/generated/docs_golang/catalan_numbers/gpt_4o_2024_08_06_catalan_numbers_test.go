package main

import (
	"math/big"
	"testing"
)

// TestCatalanNumbers tests the calculation of the first 15 Catalan numbers.
func TestCatalanNumbers(t *testing.T) {
	expectedCatalanNumbers := []string{
		"1", "1", "2", "5", "14", "42", "132", "429", "1430", "4862",
		"16796", "58786", "208012", "742900", "2674440",
	}

	var b, c big.Int
	for n := int64(0); n < 15; n++ {
		result := c.Div(b.Binomial(n*2, n), c.SetInt64(n+1)).String()
		if result != expectedCatalanNumbers[n] {
			t.Errorf("Catalan number C(%d) = %s; want %s", n, result, expectedCatalanNumbers[n])
		}
	}
}

package main

import (
    "math/big"
    "testing"
)

func TestCatalanNumbers(t *testing.T) {
    // Expected Catalan numbers for the first 15 values
    expectedCatalanNumbers := []string{
        "1", "1", "2", "5", "14", "42", "132", "429", "1430", "4862", "16796", "58786", "208012", "742900", "2674440",
    }

    var b, c big.Int
    for n := int64(0); n < 15; n++ {
        catalan := c.Div(b.Binomial(n*2, n), c.SetInt64(n+1))
        if catalan.String() != expectedCatalanNumbers[n] {
            t.Errorf("Catalan number for n=%d is incorrect. Got %s, want %s", n, catalan.String(), expectedCatalanNumbers[n])
        }
    }
}

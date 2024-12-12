package main

import (
	"math/big"
	"testing"
)

// TestBernoulli tests the bernoulli function for known values.
func TestBernoulli(t *testing.T) {
	tests := []struct {
		n        int64
		expected string
	}{
		{0, "1/1"},
		{1, "0/1"},
		{2, "1/6"},
		{3, "0/1"},
		{4, "-1/30"},
		{5, "0/1"},
		{6, "1/42"},
		{7, "0/1"},
		{8, "-1/30"},
		{9, "0/1"},
		{10, "5/66"},
	}

	for _, test := range tests {
		result := bernoulli(nil, test.n)
		if result.RatString() != test.expected {
			t.Errorf("bernoulli(%d) = %s; expected %s", test.n, result.RatString(), test.expected)
		}
	}
}

// TestFaulhabersFormula tests the main logic of generating closed-form expressions.
func TestFaulhabersFormula(t *testing.T) {
	// Expected results for p = 0 to 9
	expectedResults := []string{
		"0 :  1/1×n",
		"1 :  1/2×n^2 +1/2×n",
		"2 :  1/3×n^3 +1/2×n^2 +1/6×n",
		"3 :  1/4×n^4 +1/2×n^3 +1/4×n^2",
		"4 :  1/5×n^5 +1/2×n^4 +1/3×n^3 −1/30×n",
		"5 :  1/6×n^6 +1/2×n^5 +5/12×n^4 −1/12×n^2",
		"6 :  1/7×n^7 +1/2×n^6 +1/2×n^5 −1/6×n^3 +1/42×n",
		"7 :  1/8×n^8 +1/2×n^7 +7/12×n^6 −7/24×n^4 +1/12×n^2",
		"8 :  1/9×n^9 +1/2×n^8 +2/3×n^7 −7/15×n^5 +2/15×n^3 −1/30×n",
		"9 :  1/10×n^10 +1/2×n^9 +3/4×n^8 −7/10×n^6 +1/2×n^4 −3/20×n^2",
	}

	// Simulate the main logic
	q := new(big.Rat)
	c := new(big.Rat)
	be := new(big.Rat)
	bi := big.NewRat(1, 1)

	for p := int64(0); p < 10; p++ {
		result := ""
		result += fmt.Sprintf("%d : ", p)
		q.SetFrac64(1, p+1)
		neg := true
		for j := int64(0); j <= p; j++ {
			neg = !neg
			if neg {
				c.Neg(q)
			} else {
				c.Set(q)
			}
			bi.Num().Binomial(p+1, j)
			bernoulli(be, j)
			c.Mul(c, bi)
			c.Mul(c, be)
			if c.Num().BitLen() == 0 {
				continue
			}
			if j == 0 {
				result += fmt.Sprintf(" %4s", c.RatString())
			} else {
				result += fmt.Sprintf(" %+2d/%-2d", c.Num(), c.Denom())
			}
			result += "×n"
			if exp := p + 1 - j; exp > 1 {
				result += fmt.Sprintf("^%-2d", exp)
			}
		}
		if result != expectedResults[p] {
			t.Errorf("Faulhaber's formula for p=%d: got %s; expected %s", p, result, expectedResults[p])
		}
	}
}

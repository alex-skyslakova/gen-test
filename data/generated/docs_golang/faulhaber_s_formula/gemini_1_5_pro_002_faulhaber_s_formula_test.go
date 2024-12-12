package main

import (
	"fmt"
	"math/big"
	"strings"
	"testing"
)

func TestFaulhaberFormula(t *testing.T) {
	expected := []string{
		"0 :  1/1×n",
		"1 :  1/2×n + 1/2×n^2",
		"2 :  1/6×n + 1/2×n^2 + 1/3×n^3",
		"3 :  0/1×n + 1/4×n^2 + 1/2×n^3 + 1/4×n^4",
		"4 : -1/30×n + 1/3×n^3 + 1/2×n^4 + 1/5×n^5",
		"5 :  0/1×n - 1/12×n^2 + 5/12×n^4 + 1/2×n^5 + 1/6×n^6",
		"6 :  1/42×n - 1/6×n^3 + 1/2×n^5 + 1/2×n^6 + 1/7×n^7",
		"7 :  0/1×n + 1/12×n^2 - 7/24×n^4 + 7/12×n^6 + 1/2×n^7 + 1/8×n^8",
		"8 : -1/30×n + 2/9×n^3 - 7/15×n^5 + 2/3×n^7 + 1/2×n^8 + 1/9×n^9",
		"9 :  0/1×n - 3/20×n^2 + 2/3×n^4 - 7/10×n^6 + 3/4×n^8 + 1/2×n^9 + 1/10×n^10",
	}

	for p := int64(0); p < 10; p++ {
		// Capture stdout
		old := fmtPrint
		var b strings.Builder
		fmtPrint = func(a ...interface{}) (n int, err error) {
			return fmt.Fprint(&b, a...)
		}
		defer func() { fmtPrint = old }()

		// allocate needed big.Rat's once
		q := new(big.Rat)
		c := new(big.Rat)      // coefficients
		be := new(big.Rat)     // for Bernoulli numbers
		bi := big.NewRat(1, 1) // for binomials

		fmt.Print(p, " : ")
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
				fmt.Printf(" %4s", c.RatString())
			} else {
				fmt.Printf(" %+2d/%-2d", c.Num(), c.Denom())
			}
			fmt.Print("×n")
			if exp := p + 1 - j; exp > 1 {
				fmt.Printf("^%-2d", exp)
			}
		}

		result := b.String()
		if result != expected[p] {
			t.Errorf("For p=%d, expected '%s', got '%s'", p, expected[p], result)
		}
	}
}

// Mock for fmt.Print
var fmtPrint = fmt.Print

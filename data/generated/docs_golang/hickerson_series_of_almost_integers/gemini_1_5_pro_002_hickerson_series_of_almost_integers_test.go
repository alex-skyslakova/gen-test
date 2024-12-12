package main

import (
	"fmt"
	"math/big"
	"testing"
)

func hickerson(n int64) (whole *big.Int, frac float64) {
	ln2, _ := new(big.Rat).SetString("0.6931471805599453094172")
	h := big.NewRat(1, 2)
	h.Quo(h, ln2)
	var f big.Rat
	var w big.Int
	for i := int64(1); i <= n; i++ {
		h.Quo(h.Mul(h, f.SetInt64(i)), ln2)
		w.Quo(h.Num(), h.Denom())
		f.Sub(h, f.SetInt(&w))
	}
	frac, _ = f.Float64()
	return &w, frac

}

func isAlmostInteger(frac float64) bool {
	d := fmt.Sprintf("%.3f", frac)
	return d[2] == '0' || d[2] == '9'
}

func TestHickersonAlmostIntegers(t *testing.T) {
	for n := int64(1); n <= 17; n++ {
		_, frac := hickerson(n)
		if !isAlmostInteger(frac) {
			t.Errorf("For n = %d, expected almost integer, but got fractional part: %.3f", n, frac)
		}
	}
}

func TestHickersonNotAlmostInteger(t *testing.T) {
    n := int64(18)
    _, frac := hickerson(n)
    if isAlmostInteger(frac) {
        t.Errorf("For n = %d, expected NOT almost integer, but got fractional part: %.3f", n, frac)
    }
}




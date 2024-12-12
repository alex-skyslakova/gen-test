package main

import (
	"fmt"
	"math/big"
	"testing"
)

// Function to calculate h(n)
func hickerson(n int64) (int64, string, bool) {
	ln2, _ := new(big.Rat).SetString("0.6931471805599453094172")
	h := big.NewRat(1, 2)
	h.Quo(h, ln2)
	var f big.Rat
	var w big.Int
	for i := int64(1); i <= n; i++ {
		h.Quo(h.Mul(h, f.SetInt64(i)), ln2)
	}
	w.Quo(h.Num(), h.Denom())
	f.Sub(h, f.SetInt(&w))
	y, _ := f.Float64()
	d := fmt.Sprintf("%.3f", y)
	return w.Int64(), d[1:], d[2] == '0' || d[2] == '9'
}

func TestHickersonSeries(t *testing.T) {
	for i := int64(1); i <= 17; i++ {
		integerPart, decimalPart, isAlmostInteger := hickerson(i)
		if !isAlmostInteger {
			t.Errorf("Failed for n=%d: h(n)=%d%s is not an almost integer", i, integerPart, decimalPart)
		} else {
			t.Logf("Success for n=%d: h(n)=%d%s is an almost integer", i, integerPart, decimalPart)
		}
	}
}

func main() {
	TestHickersonSeries(&testing.T{})
}

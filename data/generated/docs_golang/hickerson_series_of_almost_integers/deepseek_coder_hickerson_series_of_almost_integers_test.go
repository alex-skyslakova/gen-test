package main

import (
    "math/big"
    "testing"
)

func TestHickersonSeriesOfAlmostIntegers(t *testing.T) {
    ln2, _ := new(big.Rat).SetString("0.6931471805599453094172")
    h := big.NewRat(1, 2)
    h.Quo(h, ln2)
    var f big.Rat
    var w big.Int

    // Expected results for "almost integers" based on the provided assertion
    expectedAlmostIntegers := map[int64]bool{
        1: true,
        2: true,
        3: true,
        4: true,
        5: true,
        6: true,
        7: true,
        8: true,
        9: true,
        10: true,
        11: true,
        12: true,
        13: true,
        14: true,
        15: true,
        16: true,
        17: true,
    }

    for i := int64(1); i <= 17; i++ {
        h.Quo(h.Mul(h, f.SetInt64(i)), ln2)
        w.Quo(h.Num(), h.Denom())
        f.Sub(h, f.SetInt(&w))
        y, _ := f.Float64()
        d := fmt.Sprintf("%.3f", y)
        isAlmostInteger := d[2] == '0' || d[2] == '9'

        if isAlmostInteger != expectedAlmostIntegers[i] {
            t.Errorf("For n = %d, expected almost integer: %t, got: %t", i, expectedAlmostIntegers[i], isAlmostInteger)
        }
    }
}

package main

import (
	"math/big"
	"testing"
)

func TestCalculatePi(t *testing.T) {
	expectedPi := "3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196442881097566593344612847564823378678316527120190914564856692346034861045432664821339360726024914127372458700660631558817488152092096282925409171536436789259036001133053054882046652138414695194151160943305727036575959195309218611738193261179310511854807446237996274956735188575272489122793818301194912"

	one := big.NewFloat(1)
	two := big.NewFloat(2)
	four := big.NewFloat(4)
	prec := uint(768) // Adjust precision as needed

	a := big.NewFloat(1).SetPrec(prec)
	g := new(big.Float).SetPrec(prec)

	// temporary variables
	t := new(big.Float).SetPrec(prec)
	u := new(big.Float).SetPrec(prec)

	g.Quo(a, t.Sqrt(two))
	sum := new(big.Float).SetPrec(prec)
	pow := big.NewFloat(2)

	for a.Cmp(g) != 0 {
		t.Add(a, g)
		t.Quo(t, two)
		g.Sqrt(u.Mul(a, g))
		a.Set(t)
		pow.Mul(pow, two)
		t.Sub(t.Mul(a, a), u.Mul(g, g))
		sum.Add(sum, t.Mul(t, pow))
	}

	t.Mul(a, a)
	t.Mul(t, four)
	pi := t.Quo(t, u.Sub(one, sum))

	if pi.String()[:len(expectedPi)] != expectedPi {
		t.Errorf("Calculated Pi: %s, Expected Pi (prefix): %s", pi.String(), expectedPi)
	}
}


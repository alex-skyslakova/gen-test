package main

import (
	"math"
	"testing"
)

func TestZero(t *testing.T) {
	z := zero()
	if !is_zero(z) {
		t.Errorf("zero() did not return the zero point")
	}
}

func TestIsZero(t *testing.T) {
	z := zero()
	if !is_zero(z) {
		t.Errorf("is_zero() failed for zero point")
	}

	p := pt{1, 2}
	if is_zero(p) {
		t.Errorf("is_zero() returned true for non-zero point")
	}
}

func TestNeg(t *testing.T) {
	p := pt{1, 2}
	n := neg(p)
	if n.x != p.x || n.y != -p.y {
		t.Errorf("neg() failed")
	}

	z := zero()
	n = neg(z)
	if !is_zero(n) {
		t.Errorf("neg(zero) did not return zero")
	}

}

func TestDbl(t *testing.T) {
	p := from_y(2)
	r := dbl(p)
	expected := pt{x: -1.7320508075688772, y: 0.5773502691896258}
	if math.Abs(r.x-expected.x) > 1e-10 || math.Abs(r.y-expected.y) > 1e-10 {
		t.Errorf("dbl() failed, got %v, expected %v", r, expected)
	}


	z := zero()
	r = dbl(z)
	if !is_zero(r) {
		t.Errorf("dbl(zero) did not return zero")
	}
}

func TestAdd(t *testing.T) {
	a := from_y(1)
	b := from_y(2)
	c := add(a, b)
	expected := pt{x: -1.2152504370215307, y: -0.47247163070907783}
	if math.Abs(c.x-expected.x) > 1e-10 || math.Abs(c.y-expected.y) > 1e-10 {
		t.Errorf("add() failed, got %v, expected %v", c, expected)
	}

	z := zero()
	c = add(a, z)
	if c.x != a.x || c.y != a.y {
		t.Errorf("add(a, zero) did not return a")
	}
	c = add(z, b)
	if c.x != b.x || c.y != b.y {
		t.Errorf("add(zero, b) did not return b")
	}

	c = add(a, a) // Test doubling via add
	expected = dbl(a)
	if math.Abs(c.x-expected.x) > 1e-10 || math.Abs(c.y-expected.y) > 1e-10 {
		t.Errorf("add(a, a) failed, got %v, expected %v", c, expected)
	}


}


func TestMul(t *testing.T) {
	a := from_y(1)
	r := mul(a, 12345)
	expected := pt{x: 105531.73917997983, y: 1026996.790331219}
    if math.Abs(r.x-expected.x) > 1e-5 || math.Abs(r.y-expected.y) > 1e-2 {
		t.Errorf("mul() failed, got %v, expected %v", r, expected)
	}
	
	z := zero()
	r = mul(z, 12345)
	if !is_zero(r) {
		t.Errorf("mul(zero, n) did not return zero")
	}

	r = mul(a, 0)
	if !is_zero(r){
		t.Errorf("mul(a, 0) did not return zero")
	}

	r = mul(a, 1)
	if r.x != a.x || r.y != a.y {
		t.Errorf("mul(a, 1) did not return a")
	}
}



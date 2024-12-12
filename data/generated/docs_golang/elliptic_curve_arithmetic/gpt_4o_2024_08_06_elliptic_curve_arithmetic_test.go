package main

import (
    "testing"
    "math"
)

func TestZero(t *testing.T) {
    z := zero()
    if !is_zero(z) {
        t.Errorf("Expected zero point, got (%f, %f)", z.x, z.y)
    }
}

func TestNegation(t *testing.T) {
    p := pt{3, 4}
    n := neg(p)
    if n.x != p.x || n.y != -p.y {
        t.Errorf("Expected negation to be (%f, %f), got (%f, %f)", p.x, -p.y, n.x, n.y)
    }
}

func TestDoubling(t *testing.T) {
    p := pt{3, 4}
    d := dbl(p)
    if is_zero(d) {
        t.Errorf("Doubling of a non-zero point should not be zero")
    }
}

func TestAddition(t *testing.T) {
    p := pt{3, 4}
    q := pt{5, 6}
    r := add(p, q)
    if is_zero(r) {
        t.Errorf("Addition of two distinct points should not be zero")
    }
}

func TestAdditionWithZero(t *testing.T) {
    p := pt{3, 4}
    z := zero()
    r := add(p, z)
    if r != p {
        t.Errorf("Addition with zero should return the same point, got (%f, %f)", r.x, r.y)
    }
}

func TestMultiplication(t *testing.T) {
    p := pt{3, 4}
    r := mul(p, 0)
    if !is_zero(r) {
        t.Errorf("Multiplication by zero should return zero point")
    }
    r = mul(p, 1)
    if r != p {
        t.Errorf("Multiplication by one should return the same point, got (%f, %f)", r.x, r.y)
    }
}

func TestFromY(t *testing.T) {
    y := 2.0
    p := from_y(y)
    if math.Abs(p.y-y) > 1e-9 {
        t.Errorf("Expected y to be %f, got %f", y, p.y)
    }
}

func TestMainFunctionality(t *testing.T) {
    a := from_y(1)
    b := from_y(2)
    c := add(a, b)
    d := neg(c)
    if !is_zero(add(c, d)) {
        t.Errorf("c + (-c) should be zero")
    }
    if !is_zero(add(a, add(b, d))) {
        t.Errorf("a + b + (-c) should be zero")
    }
    r := mul(a, 12345)
    if is_zero(r) {
        t.Errorf("Multiplication result should not be zero")
    }
}

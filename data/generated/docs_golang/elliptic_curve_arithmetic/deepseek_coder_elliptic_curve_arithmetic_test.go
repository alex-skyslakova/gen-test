package main

import (
    "testing"
    "math"
)

func TestZero(t *testing.T) {
    z := zero()
    if !is_zero(z) {
        t.Errorf("zero() did not return a zero point")
    }
}

func TestIsZero(t *testing.T) {
    z := zero()
    if !is_zero(z) {
        t.Errorf("is_zero() did not recognize zero point")
    }

    p := pt{1, 2}
    if is_zero(p) {
        t.Errorf("is_zero() incorrectly recognized non-zero point as zero")
    }
}

func TestNeg(t *testing.T) {
    p := pt{1, 2}
    n := neg(p)
    if n.x != p.x || n.y != -p.y {
        t.Errorf("neg() did not return the correct negative point")
    }
}

func TestDbl(t *testing.T) {
    p := pt{1, 2}
    d := dbl(p)
    expectedX := (3*p.x*p.x)/(2*p.y)
    expectedX = expectedX*expectedX - 2*p.x
    expectedY := expectedX*(p.x-expectedX) - p.y
    if d.x != expectedX || d.y != expectedY {
        t.Errorf("dbl() did not return the correct double point")
    }
}

func TestAdd(t *testing.T) {
    p := pt{1, 2}
    q := pt{3, 4}
    a := add(p, q)
    expectedX := (q.y-p.y)/(q.x-p.x)
    expectedX = expectedX*expectedX - p.x - q.x
    expectedY := expectedX*(p.x-expectedX) - p.y
    if a.x != expectedX || a.y != expectedY {
        t.Errorf("add() did not return the correct sum point")
    }
}

func TestMul(t *testing.T) {
    p := pt{1, 2}
    n := 5
    m := mul(p, n)
    expected := p
    for i := 1; i < n; i++ {
        expected = add(expected, p)
    }
    if m.x != expected.x || m.y != expected.y {
        t.Errorf("mul() did not return the correct multiple point")
    }
}

func TestFromY(t *testing.T) {
    y := 2.0
    p := from_y(y)
    expectedX := math.Cbrt(y*y - bCoeff)
    if p.x != expectedX || p.y != y {
        t.Errorf("from_y() did not return the correct point")
    }
}

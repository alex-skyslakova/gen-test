package main

import (
	"math"
	"testing"
)

// TestCfSqrt2 tests the continued fraction representation of the square root of 2.
func TestCfSqrt2(t *testing.T) {
	expected := math.Sqrt(2)
	actual := cfSqrt2(20).real()
	if math.Abs(actual-expected) > 1e-9 {
		t.Errorf("cfSqrt2(20) = %v; want %v", actual, expected)
	}
}

// TestCfNap tests the continued fraction representation of Napier's Constant (e).
func TestCfNap(t *testing.T) {
	expected := math.E
	actual := cfNap(20).real()
	if math.Abs(actual-expected) > 1e-9 {
		t.Errorf("cfNap(20) = %v; want %v", actual, expected)
	}
}

// TestCfPi tests the continued fraction representation of Pi.
func TestCfPi(t *testing.T) {
	expected := math.Pi
	actual := cfPi(20).real()
	if math.Abs(actual-expected) > 1e-9 {
		t.Errorf("cfPi(20) = %v; want %v", actual, expected)
	}
}

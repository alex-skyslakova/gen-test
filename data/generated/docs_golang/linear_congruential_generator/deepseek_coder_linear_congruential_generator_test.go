package main

import (
	"testing"
)

// TestBSDLCG tests the BSD linear congruential generator with a specific seed.
func TestBSDLCG(t *testing.T) {
	seed := uint32(0)
	bsd := lcg(1103515245, 12345, 1<<31, seed)

	expected := []uint32{
		12345,
		1406932606,
		654583775,
		1449466924,
		229283573,
	}

	for i, exp := range expected {
		if got := bsd(); got != exp {
			t.Errorf("BSD LCG iteration %d: expected %d, got %d", i, exp, got)
		}
	}
}

// TestMicrosoftLCG tests the Microsoft linear congruential generator with a specific seed.
func TestMicrosoftLCG(t *testing.T) {
	seed := uint32(0)
	msf := msg(seed)

	expected := []uint32{
		38,
		7719,
		21238,
		2437,
		8855,
	}

	for i, exp := range expected {
		if got := msf(); got != exp {
			t.Errorf("Microsoft LCG iteration %d: expected %d, got %d", i, exp, got)
		}
	}
}

// TestBSDLCGWithDifferentSeed tests the BSD linear congruential generator with a different seed.
func TestBSDLCGWithDifferentSeed(t *testing.T) {
	seed := uint32(1)
	bsd := lcg(1103515245, 12345, 1<<31, seed)

	expected := []uint32{
		1103527590,
		377401575,
		662824084,
		1147902781,
		2035015474,
	}

	for i, exp := range expected {
		if got := bsd(); got != exp {
			t.Errorf("BSD LCG iteration %d: expected %d, got %d", i, exp, got)
		}
	}
}

// TestMicrosoftLCGWithDifferentSeed tests the Microsoft linear congruential generator with a different seed.
func TestMicrosoftLCGWithDifferentSeed(t *testing.T) {
	seed := uint32(1)
	msf := msg(seed)

	expected := []uint32{
		41,
		18467,
		6334,
		26500,
		19169,
	}

	for i, exp := range expected {
		if got := msf(); got != exp {
			t.Errorf("Microsoft LCG iteration %d: expected %d, got %d", i, exp, got)
		}
	}
}

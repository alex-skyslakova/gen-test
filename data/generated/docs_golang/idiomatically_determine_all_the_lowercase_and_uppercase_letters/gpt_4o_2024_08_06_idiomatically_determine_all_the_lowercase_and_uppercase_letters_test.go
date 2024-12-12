package main

import (
	"testing"
	"unicode"
)

func TestASCIICharacters(t *testing.T) {
	expectedLower := "abcdefghijklmnopqrstuvwxyz"
	expectedUpper := "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

	// Test lowercase ASCII
	for i, l := range expectedLower {
		if l != rune('a'+i) {
			t.Errorf("Expected %c, but got %c", rune('a'+i), l)
		}
	}

	// Test uppercase ASCII
	for i, l := range expectedUpper {
		if l != rune('A'+i) {
			t.Errorf("Expected %c, but got %c", rune('A'+i), l)
		}
	}
}

func TestUnicodeLowerCase(t *testing.T) {
	for _, rng := range unicode.Lower.R16 {
		for r := rng.Lo; r <= rng.Hi; r += rng.Stride {
			if !unicode.IsLower(r) {
				t.Errorf("Expected %c to be lowercase", r)
			}
		}
	}

	for _, rng := range unicode.Lower.R32 {
		for r := rng.Lo; r <= rng.Hi; r += rng.Stride {
			if !unicode.IsLower(r) {
				t.Errorf("Expected %c to be lowercase", r)
			}
		}
	}
}

func TestUnicodeUpperCase(t *testing.T) {
	for _, rng := range unicode.Upper.R16 {
		for r := rng.Lo; r <= rng.Hi; r += rng.Stride {
			if !unicode.IsUpper(r) {
				t.Errorf("Expected %c to be uppercase", r)
			}
		}
	}

	for _, rng := range unicode.Upper.R32 {
		for r := rng.Lo; r <= rng.Hi; r += rng.Stride {
			if !unicode.IsUpper(r) {
				t.Errorf("Expected %c to be uppercase", r)
			}
		}
	}
}

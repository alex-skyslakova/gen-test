package main

import (
	"fmt"
	"testing"
)

func TestDeconv(t *testing.T) {
	h := []float64{-8, -9, -3, -1, -6, 7}
	f := []float64{-3, -6, -1, 8, -6, 3, -1, -9, -9, 3, -2, 5, 2, -2, -7, -1}
	g := []float64{24, 75, 71, -34, 3, 22, -45, 23, 245, 25, 52, 25, -67, -96,
		96, 31, 55, 36, 29, -43, -7}

	// Test deconv(g, f) == h
	resultH := deconv(g, f)
	if !float64SlicesEqual(resultH, h) {
		t.Errorf("deconv(g, f) failed. Expected: %v, Got: %v", h, resultH)
	}

	// Test deconv(g, h) == f
	resultF := deconv(g, h)

        // The original code's deconv function appears to have a bug
        // when deconvolving to get 'f'.  This part of the test will fail.

	if !float64SlicesEqual(resultF, f) {
		t.Errorf("deconv(g, h) failed. Expected: %v, Got: %v", f, resultF)
	}


        // Test with shorter inputs. This is to test for edge cases
        // where len(g) is close to len(f) or len(h).
        hShort := []float64{1, 2}
        fShort := []float64{3, 4}
        gShort := []float64{3, 10, 8}

	resultHShort := deconv(gShort, fShort)
	if !float64SlicesEqual(resultHShort, hShort) {
		t.Errorf("deconv(gShort, fShort) failed. Expected: %v, Got: %v", hShort, resultHShort)
	}


	resultFShort := deconv(gShort, hShort)
	if !float64SlicesEqual(resultFShort, fShort) {
		t.Errorf("deconv(gShort, hShort) failed. Expected: %v, Got: %v", fShort, resultFShort)
	}



}




func float64SlicesEqual(a, b []float64) bool {
	if len(a) != len(b) {
		return false
	}
	for i, v := range a {
		if v != b[i] {
			return false
		}
	}
	return true
}




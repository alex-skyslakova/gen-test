package main

import (
	"math"
	"testing"
)

func TestPythagoreanMeans(t *testing.T) {
	sum, sumr, prod := 0., 0., 1.
	for n := 1.; n <= 10; n++ {
		sum += n
		sumr += 1 / n
		prod *= n
	}
	a, g, h := sum/10, math.Pow(prod, .1), 10/sumr

	expectedA := 5.5
	if a != expectedA {
		t.Errorf("Arithmetic mean incorrect. Expected: %f, Got: %f", expectedA, a)
	}

	expectedG := 3.9810717055349722
	if math.Abs(g-expectedG) > 1e-10 { // Use a tolerance for floating-point comparison
		t.Errorf("Geometric mean incorrect. Expected: %f, Got: %f", expectedG, g)
	}

	expectedH := 3.414171521474055
	if math.Abs(h-expectedH) > 1e-10 { // Use a tolerance for floating-point comparison
		t.Errorf("Harmonic mean incorrect. Expected: %f, Got: %f", expectedH, h)
	}


	if !(a >= g && g >= h) {
		t.Errorf("A >= G >= H condition not met. A: %f, G: %f, H: %f", a, g, h)
	}
}


// Additional test cases for edge scenarios.

func TestPythagoreanMeansSingleValue(t *testing.T) {
	sum, sumr, prod := 5., 1./5., 5.
	
	a, g, h := sum/1, math.Pow(prod, 1.), 1/sumr

	expectedA := 5.0
	if a != expectedA {
		t.Errorf("Arithmetic mean incorrect. Expected: %f, Got: %f", expectedA, a)
	}

	expectedG := 5.0
	if math.Abs(g-expectedG) > 1e-10 { 
		t.Errorf("Geometric mean incorrect. Expected: %f, Got: %f", expectedG, g)
	}

	expectedH := 5.0
	if math.Abs(h-expectedH) > 1e-10 { 
		t.Errorf("Harmonic mean incorrect. Expected: %f, Got: %f", expectedH, h)
	}


	if !(a >= g && g >= h) {
		t.Errorf("A >= G >= H condition not met. A: %f, G: %f, H: %f", a, g, h)
	}
}





package main

import (
	"math"
	"math/cmplx"
	"testing"
)

// Helper function to calculate the magnitude of a complex number
func magnitude(c complex128) float64 {
	return math.Sqrt(real(c)*real(c) + imag(c)*imag(c))
}

// Test for the FFT function with real input and complex output
func TestFFTComplexOutput(t *testing.T) {
	x := []float64{1, 1, 1, 1, 0, 0, 0, 0}
	expected := []complex128{
		complex(4, 0),
		complex(1, -2.4142),
		complex(0, 0),
		complex(1, -0.4142),
		complex(0, 0),
		complex(1, 0.4142),
		complex(0, 0),
		complex(1, 2.4142),
	}

	y := make([]complex128, len(x))
	ditfft2(x, y, len(x), 1)

	for i, v := range y {
		if cmplx.Abs(v-expected[i]) > 0.0001 {
			t.Errorf("Expected %v, but got %v at index %d", expected[i], v, i)
		}
	}
}

// Test for the FFT function with real input and magnitude output
func TestFFTMagnitudeOutput(t *testing.T) {
	x := []float64{1, 1, 1, 1, 0, 0, 0, 0}
	expectedMagnitudes := []float64{4, 2.6131, 0, 1.0824, 0, 1.0824, 0, 2.6131}

	y := make([]complex128, len(x))
	ditfft2(x, y, len(x), 1)

	for i, v := range y {
		mag := magnitude(v)
		if math.Abs(mag-expectedMagnitudes[i]) > 0.0001 {
			t.Errorf("Expected magnitude %v, but got %v at index %d", expectedMagnitudes[i], mag, i)
		}
	}
}

// Test for the FFT function with a single element
func TestFFTSingleElement(t *testing.T) {
	x := []float64{5}
	expected := []complex128{complex(5, 0)}

	y := make([]complex128, len(x))
	ditfft2(x, y, len(x), 1)

	for i, v := range y {
		if cmplx.Abs(v-expected[i]) > 0.0001 {
			t.Errorf("Expected %v, but got %v at index %d", expected[i], v, i)
		}
	}
}

// Test for the FFT function with zero input
func TestFFTZeroInput(t *testing.T) {
	x := []float64{0, 0, 0, 0}
	expected := []complex128{complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0)}

	y := make([]complex128, len(x))
	ditfft2(x, y, len(x), 1)

	for i, v := range y {
		if cmplx.Abs(v-expected[i]) > 0.0001 {
			t.Errorf("Expected %v, but got %v at index %d", expected[i], v, i)
		}
	}
}

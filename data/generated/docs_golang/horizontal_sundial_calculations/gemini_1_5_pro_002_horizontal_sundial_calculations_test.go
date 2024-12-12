package main

import (
	"fmt"
	"math"
	"os"
	"testing"
)

func TestCalculations(t *testing.T) {
	testCases := []struct {
		lat, lng, ref float64
		expected      []struct {
			h, hra, hla float64
		}
	}{
		{
			lat: 4.95, lng: -150.5, ref: -150,
			expected: []struct {
				h, hra, hla float64
			}{
				{-6, -90.5, -7.132},
				{-5, -75.5, -6.029},
				{-4, -60.5, -4.885},
				{-3, -45.5, -3.688},
				{-2, -30.5, -2.428},
				{-1, -15.5, -1.095},
				{0, -0.5, 0.293},
				{1, 14.5, 1.727},
				{2, 29.5, 3.198},
				{3, 44.5, 4.697},
				{4, 59.5, 6.215},
				{5, 74.5, 7.743},
				{6, 89.5, 9.273},
			},
		},
		{ // Test case for 0 latitude
			lat: 0, lng: 0, ref: 0,
			expected: []struct {
				h, hra, hla float64
			}{
				{-6, -90, -0},
				{0, 0, 0},
				{6, 90, 0},
			},
		},
		{ // Test case for 90 latitude and some longitude/meridian values
			lat: 90, lng: -150.5, ref: -150,
			expected: []struct {
				h, hra, hla float64
			}{
				{-6, -90.5, -90.5},
				{0, -0.5, -0.5},
				{6, 89.5, 89.5},
			},
		},

	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("lat=%.2f,lng=%.2f,ref=%.2f", tc.lat, tc.lng, tc.ref), func(t *testing.T) {
			slat := math.Sin(tc.lat * math.Pi / 180)
			diff := tc.lng - tc.ref

			for i, exp := range tc.expected {
				hra := 15*exp.h - diff
				s, c := math.Sincos(hra * math.Pi / 180)
				hla := math.Atan2(slat*s, c) * 180 / math.Pi


				if !closeEnough(hla, exp.hla, 0.001) || !closeEnough(hra, exp.hra, 0.001) {
					t.Errorf("For h=%.0f: expected hra=%.3f, hla=%.3f, got hra=%.3f, hla=%.3f", exp.h, exp.hra, exp.hla, hra, hla)

				}
				_ = i
			}

		})
	}
}

func closeEnough(a, b, tolerance float64) bool {
	return math.Abs(a-b) <= tolerance
}

// Mock for getnum function - for testing purposes only
func getnumTest(prompt string, val float64) float64 {
	return val
}


// Example of how to mock os.Exit - for demonstration only
func TestMain(m *testing.M) {
	origExit := os.Exit
	defer func() { os.Exit = origExit }()

	os.Exit = func(code int) {
		// Do nothing or handle the exit in a test-friendly way
	}
	m.Run()
}



package main

import (
	"github.com/fogleman/gg"
	"image/color"
	"math"
	"testing"
)

func TestHsb2rgb(t *testing.T) {
	testCases := []struct {
		hue, sat, bri float64
		r, g, b       int
	}{
		{0, 0, 0, 0, 0, 0},
		{0, 0, 1, 255, 255, 255},
		{0, 1, 1, 255, 0, 0},
		{1. / 3, 1, 1, 0, 255, 0},
		{2. / 3, 1, 1, 0, 0, 255},
		{0.5, 0.5, 0.5, 128, 191, 64},
		{1, 1, 1, 255, 0, 0}, // same as hue = 0
	}
	for _, tc := range testCases {
		r, g, b := hsb2rgb(tc.hue, tc.sat, tc.bri)
		if r != tc.r || g != tc.g || b != tc.b {
			t.Errorf("hsb2rgb(%v, %v, %v) = (%v, %v, %v), want (%v, %v, %v)", tc.hue, tc.sat, tc.bri, r, g, b, tc.r, tc.g, tc.b)
		}
	}
}

func TestColorWheel(t *testing.T) {
	const width, height = 100, 100
	dc := gg.NewContext(width, height)
	dc.SetRGB(1, 1, 1) // set background color to white
	dc.Clear()
	colorWheel(dc)

	// Check center pixel is red
	centerX, centerY := width/2, height/2
	r, g, b, _ := dc.Image().At(centerX, centerY).RGBA()
	if r != 65535 || g != 0 || b != 0 {
		t.Errorf("Center pixel color is not red, got (%v, %v, %v)", r, g, b)
	}

	// Check a pixel on the edge for correct hue
	testX, testY := width/4, height/2  // corresponds to a hue of approximately 1/12
	expectedHue := (math.Atan2(0, float64(testX-centerX)) + math.Pi) / tau
	r, g, b, _ = dc.Image().At(testX, testY).RGBA()
	actualColor := color.RGBA{uint8(r >> 8), uint8(g >> 8), uint8(b >> 8), 255}
	er, eg, eb := hsb2rgb(expectedHue, 1, 1)
	expectedColor := color.RGBA{uint8(er), uint8(eg), uint8(eb), 255}

	tolerance := 5 // allow slight variations due to rounding

	if absDiff(int(actualColor.R), int(expectedColor.R)) > tolerance ||
		absDiff(int(actualColor.G), int(expectedColor.G)) > tolerance ||
		absDiff(int(actualColor.B), int(expectedColor.B)) > tolerance {
		t.Errorf("Pixel color at (%d, %d) is incorrect. Got %v, expected approximately %v",
			testX, testY, actualColor, expectedColor)
	}

}


func absDiff(a, b int) int {
	if a > b {
		return a - b
	}
	return b - a
}


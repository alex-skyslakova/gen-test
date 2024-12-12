package main

import (
	"image/color"
	"testing"

	"github.com/fogleman/gg"
)

func TestHSB2RGB(t *testing.T) {
	tests := []struct {
		hue, sat, bri float64
		expectedR, expectedG, expectedB int
	}{
		{0, 0, 0, 0, 0, 0},       // Black
		{0, 0, 1, 255, 255, 255}, // White
		{0, 1, 1, 255, 0, 0},     // Red
		{0.333, 1, 1, 0, 255, 0}, // Green
		{0.667, 1, 1, 0, 0, 255}, // Blue
	}

	for _, test := range tests {
		r, g, b := hsb2rgb(test.hue, test.sat, test.bri)
		if r != test.expectedR || g != test.expectedG || b != test.expectedB {
			t.Errorf("hsb2rgb(%v, %v, %v) = (%v, %v, %v); want (%v, %v, %v)",
				test.hue, test.sat, test.bri, r, g, b, test.expectedR, test.expectedG, test.expectedB)
		}
	}
}

func TestColorWheel(t *testing.T) {
	const width, height = 480, 480
	dc := gg.NewContext(width, height)
	dc.SetRGB(1, 1, 1) // set background color to white
	dc.Clear()
	colorWheel(dc)

	// Check a few key points to ensure the color wheel is drawn correctly
	tests := []struct {
		x, y int
		expectedColor color.Color
	}{
		{240, 240, color.RGBA{255, 0, 0, 255}}, // Center should be red
		{480, 240, color.RGBA{0, 255, 0, 255}}, // Right edge should be green
		{0, 240, color.RGBA{0, 255, 255, 255}}, // Left edge should be cyan
		{240, 0, color.RGBA{255, 0, 255, 255}}, // Top edge should be magenta
		{240, 480, color.RGBA{255, 255, 0, 255}}, // Bottom edge should be yellow
	}

	for _, test := range tests {
		r, g, b, a := dc.Image().At(test.x, test.y).RGBA()
		expectedR, expectedG, expectedB, expectedA := test.expectedColor.RGBA()
		if r != expectedR || g != expectedG || b != expectedB || a != expectedA {
			t.Errorf("Pixel at (%v, %v) = (%v, %v, %v, %v); want (%v, %v, %v, %v)",
				test.x, test.y, r, g, b, a, expectedR, expectedG, expectedB, expectedA)
		}
	}
}

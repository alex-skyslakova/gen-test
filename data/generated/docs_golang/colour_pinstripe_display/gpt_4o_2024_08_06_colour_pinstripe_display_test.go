package main

import (
	"testing"
	"github.com/fogleman/gg"
	"image"
	"image/color"
)

func TestPinstripe(t *testing.T) {
	width := 900
	height := 600
	dc := gg.NewContext(width, height)
	pinstripe(dc)

	img := dc.Image()

	// Define expected colors in the palette
	expectedColors := []color.Color{
		color.RGBA{0, 0, 0, 255},       // black
		color.RGBA{255, 0, 0, 255},     // red
		color.RGBA{0, 255, 0, 255},     // green
		color.RGBA{0, 0, 255, 255},     // blue
		color.RGBA{255, 0, 255, 255},   // magenta
		color.RGBA{0, 255, 255, 255},   // cyan
		color.RGBA{255, 255, 0, 255},   // yellow
		color.RGBA{255, 255, 255, 255}, // white
	}

	// Check each section of the image
	for b := 1; b <= 4; b++ {
		stripeWidth := b
		yStart := (b - 1) * height / 4
		yEnd := b * height / 4

		for x := 0; x < width; x += stripeWidth {
			expectedColor := expectedColors[(x/stripeWidth)%8]

			for y := yStart; y < yEnd; y++ {
				actualColor := img.At(x, y)
				if !colorsEqual(expectedColor, actualColor) {
					t.Errorf("Expected color %v at (%d, %d), but got %v", expectedColor, x, y, actualColor)
				}
			}
		}
	}
}

func colorsEqual(c1, c2 color.Color) bool {
	r1, g1, b1, a1 := c1.RGBA()
	r2, g2, b2, a2 := c2.RGBA()
	return r1 == r2 && g1 == g2 && b1 == b2 && a1 == a2
}

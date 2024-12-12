package main

import (
	"github.com/fogleman/gg"
	"github.com/stretchr/testify/assert"
	"image/color"
	"testing"
)

func TestGreyBars(t *testing.T) {
	width := 640
	height := 320
	dc := gg.NewContext(width, height)
	greyBars(dc)

	// Test top quarter (8 bars, black to white)
	quarterHeight := height / 4
	barWidth := width / 8
	testBarColors(t, dc, 0, 0, barWidth, quarterHeight, 8, 0, 255)


	// Test second quarter (16 bars, white to black)
	barWidth = width / 16
	testBarColors(t, dc, 0, quarterHeight, barWidth, quarterHeight, 16, 255, 0)

	// Test third quarter (32 bars, black to white)
	barWidth = width / 32
	testBarColors(t, dc, 0, 2*quarterHeight, barWidth, quarterHeight, 32, 0, 255)

	// Test fourth quarter (64 bars, white to black)
	barWidth = width / 64
	testBarColors(t, dc, 0, 3*quarterHeight, barWidth, quarterHeight, 64, 255, 0)
}


func testBarColors(t *testing.T, dc *gg.Context, x, y, barWidth, barHeight float64, numBars int, startColor, endColor int) {
	colorStep := float64(endColor-startColor) / float64(numBars-1)

	for i := 0; i < numBars; i++ {
		expectedColor := int(math.Round(float64(startColor) + colorStep*float64(i)))
		// Sample the color from the center of the bar to avoid potential anti-aliasing issues at the edges
		sampledColor := dc.Image().At(int(x+barWidth/2), int(y+barHeight/2))
		r, g, b, _ := sampledColor.RGBA()

		assert.Equal(t, uint8(expectedColor), uint8(r>>8), "Red component mismatch for bar %d", i)
		assert.Equal(t, uint8(expectedColor), uint8(g>>8), "Green component mismatch for bar %d", i)
		assert.Equal(t, uint8(expectedColor), uint8(b>>8), "Blue component mismatch for bar %d", i)


		x += barWidth
	}
}




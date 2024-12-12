package main

import (
    "testing"
    "github.com/fogleman/gg"
    "image"
    "image/color"
)

func TestGreyBars(t *testing.T) {
    dc := gg.NewContext(640, 320)
    greyBars(dc)
    img := dc.Image()

    // Test top quarter (8 bars)
    testBarColors(t, img, 0, 80, 8)

    // Test second quarter (16 bars)
    testBarColors(t, img, 80, 160, 16)

    // Test third quarter (32 bars)
    testBarColors(t, img, 160, 240, 32)

    // Test bottom quarter (64 bars)
    testBarColors(t, img, 240, 320, 64)
}

func testBarColors(t *testing.T, img image.Image, yStart, yEnd, barCount int) {
    width := img.Bounds().Dx()
    barWidth := width / barCount

    for i := 0; i < barCount; i++ {
        x := i * barWidth
        y := (yStart + yEnd) / 2 // Check color in the middle of the bar vertically
        expectedColor := calculateExpectedColor(i, barCount)
        actualColor := img.At(x, y)

        r, g, b, _ := actualColor.RGBA()
        actualGray := uint8(r >> 8) // Convert to 8-bit

        if actualGray != expectedColor {
            t.Errorf("Bar %d in range (%d, %d) has color %d, expected %d", i, yStart, yEnd, actualGray, expectedColor)
        }
    }
}

func calculateExpectedColor(index, barCount int) uint8 {
    if index == 0 {
        return 0
    } else if index == barCount-1 {
        return 255
    }
    colorGap := 255.0 / float64(barCount-1)
    return uint8(math.Round(float64(index) * colorGap))
}

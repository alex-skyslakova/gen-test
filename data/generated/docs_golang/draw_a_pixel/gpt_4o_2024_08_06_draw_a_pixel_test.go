package main

import (
    "image"
    "image/color"
    "image/draw"
    "testing"
)

func TestDrawPixel(t *testing.T) {
    rect := image.Rect(0, 0, 320, 240)
    img := image.NewRGBA(rect)

    // Use green background
    green := color.RGBA{0, 255, 0, 255}
    draw.Draw(img, rect, &image.Uniform{green}, image.ZP, draw.Src)

    // Set color of pixel at (100, 100) to red
    red := color.RGBA{255, 0, 0, 255}
    img.Set(100, 100, red)

    // Test the color of the pixel at (0, 0)
    if got := img.At(0, 0); got != green {
        t.Errorf("Expected color at (0, 0) to be green, got %v", got)
    }

    // Test the color of the pixel at (100, 100)
    if got := img.At(100, 100); got != red {
        t.Errorf("Expected color at (100, 100) to be red, got %v", got)
    }
}

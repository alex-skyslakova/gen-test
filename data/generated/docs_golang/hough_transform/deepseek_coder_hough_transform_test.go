package main

import (
    "image"
    "image/color"
    "image/draw"
    "testing"
)

// TestHoughTransform tests the hough transform function with a simple image.
func TestHoughTransform(t *testing.T) {
    // Create a simple image with a single black pixel at (10, 10)
    img := image.NewGray(image.Rect(0, 0, 20, 20))
    img.SetGray(10, 10, color.Gray{0})

    // Apply the Hough transform
    houghImg := hough(img, 460, 360)

    // Check if the resulting image has the expected dimensions
    if houghImg.Bounds().Dx() != 460 || houghImg.Bounds().Dy() != 360 {
        t.Errorf("Expected dimensions (460, 360), got (%d, %d)", houghImg.Bounds().Dx(), houghImg.Bounds().Dy())
    }

    // Check if the pixel at the expected location is darker than the background
    // The exact location depends on the transformation, but we can check a few points
    expectedDarkPixelLocations := []image.Point{
        {10, 180}, // Example location, adjust based on actual transformation
        {20, 180}, // Another example location
    }

    for _, pt := range expectedDarkPixelLocations {
        col := houghImg.At(pt.X, pt.Y).(color.Gray)
        if col.Y >= 255 {
            t.Errorf("Expected dark pixel at (%d, %d), got %v", pt.X, pt.Y, col)
        }
    }
}

// TestHoughTransformEmptyImage tests the hough transform function with an empty image.
func TestHoughTransformEmptyImage(t *testing.T) {
    // Create an empty image (all white)
    img := image.NewGray(image.Rect(0, 0, 20, 20))

    // Apply the Hough transform
    houghImg := hough(img, 460, 360)

    // Check if the resulting image is completely white
    for x := 0; x < houghImg.Bounds().Dx(); x++ {
        for y := 0; y < houghImg.Bounds().Dy(); y++ {
            col := houghImg.At(x, y).(color.Gray)
            if col.Y != 255 {
                t.Errorf("Expected white pixel at (%d, %d), got %v", x, y, col)
            }
        }
    }
}

// TestHoughTransformFullBlackImage tests the hough transform function with a fully black image.
func TestHoughTransformFullBlackImage(t *testing.T) {
    // Create a fully black image
    img := image.NewGray(image.Rect(0, 0, 20, 20))
    draw.Draw(img, img.Bounds(), image.NewUniform(color.Gray{0}), image.Point{}, draw.Src)

    // Apply the Hough transform
    houghImg := hough(img, 460, 360)

    // Check if the resulting image is completely black
    for x := 0; x < houghImg.Bounds().Dx(); x++ {
        for y := 0; y < houghImg.Bounds().Dy(); y++ {
            col := houghImg.At(x, y).(color.Gray)
            if col.Y != 0 {
                t.Errorf("Expected black pixel at (%d, %d), got %v", x, y, col)
            }
        }
    }
}

// TestHoughTransformSingleLine tests the hough transform function with a single line.
func TestHoughTransformSingleLine(t *testing.T) {
    // Create an image with a single vertical line at x=10
    img := image.NewGray(image.Rect(0, 0, 20, 20))
    for y := 0; y < 20; y++ {
        img.SetGray(10, y, color.Gray{0})
    }

    // Apply the Hough transform
    houghImg := hough(img, 460, 360)

    // Check if the resulting image has the expected dark spots
    // The exact location depends on the transformation, but we can check a few points
    expectedDarkPixelLocations := []image.Point{
        {0, 180}, // Example location, adjust based on actual transformation
        {90, 180}, // Another example location
    }

    for _, pt := range expectedDarkPixelLocations {
        col := houghImg.At(pt.X, pt.Y).(color.Gray)
        if col.Y >= 255 {
            t.Errorf("Expected dark pixel at (%d, %d), got %v", pt.X, pt.Y, col)
        }
    }
}

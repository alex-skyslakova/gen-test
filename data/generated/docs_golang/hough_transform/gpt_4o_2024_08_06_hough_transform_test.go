package main

import (
    "image"
    "image/color"
    "image/draw"
    "image/png"
    "math"
    "os"
    "testing"
)

// TestHoughTransform tests the Hough transform function with a sample image.
func TestHoughTransform(t *testing.T) {
    // Open the sample image
    f, err := os.Open("Pentagon.png")
    if err != nil {
        t.Fatalf("Failed to open image: %v", err)
    }
    defer f.Close()

    // Decode the image
    pent, err := png.Decode(f)
    if err != nil {
        t.Fatalf("Failed to decode image: %v", err)
    }

    // Perform the Hough transform
    ntx, mry := 460, 360
    houghImage := hough(pent, ntx, mry)

    // Check the dimensions of the output image
    if houghImage.Bounds().Max.X != ntx || houghImage.Bounds().Max.Y != mry {
        t.Errorf("Unexpected dimensions of Hough image: got (%d, %d), want (%d, %d)",
            houghImage.Bounds().Max.X, houghImage.Bounds().Max.Y, ntx, mry)
    }

    // Check if the output image is not entirely white (indicating some lines were detected)
    isWhite := true
    for x := 0; x < ntx; x++ {
        for y := 0; y < mry; y++ {
            col := houghImage.At(x, y).(color.Gray)
            if col.Y != 255 {
                isWhite = false
                break
            }
        }
        if !isWhite {
            break
        }
    }
    if isWhite {
        t.Error("Hough image is entirely white, no lines detected")
    }
}

// TestHoughTransformEmptyImage tests the Hough transform with an empty image.
func TestHoughTransformEmptyImage(t *testing.T) {
    // Create an empty white image
    emptyImage := image.NewGray(image.Rect(0, 0, 100, 100))
    draw.Draw(emptyImage, emptyImage.Bounds(), image.NewUniform(color.White), image.Point{}, draw.Src)

    // Perform the Hough transform
    ntx, mry := 460, 360
    houghImage := hough(emptyImage, ntx, mry)

    // Check if the output image is entirely white (indicating no lines were detected)
    for x := 0; x < ntx; x++ {
        for y := 0; y < mry; y++ {
            col := houghImage.At(x, y).(color.Gray)
            if col.Y != 255 {
                t.Error("Hough image is not entirely white for an empty input image")
                return
            }
        }
    }
}

// TestHoughTransformSingleLine tests the Hough transform with an image containing a single line.
func TestHoughTransformSingleLine(t *testing.T) {
    // Create an image with a single black line
    lineImage := image.NewGray(image.Rect(0, 0, 100, 100))
    draw.Draw(lineImage, lineImage.Bounds(), image.NewUniform(color.White), image.Point{}, draw.Src)
    for i := 0; i < 100; i++ {
        lineImage.SetGray(i, i, color.Gray{Y: 0}) // Draw a diagonal line
    }

    // Perform the Hough transform
    ntx, mry := 460, 360
    houghImage := hough(lineImage, ntx, mry)

    // Check if the output image is not entirely white (indicating the line was detected)
    isWhite := true
    for x := 0; x < ntx; x++ {
        for y := 0; y < mry; y++ {
            col := houghImage.At(x, y).(color.Gray)
            if col.Y != 255 {
                isWhite = false
                break
            }
        }
        if !isWhite {
            break
        }
    }
    if isWhite {
        t.Error("Hough image is entirely white, line was not detected")
    }
}

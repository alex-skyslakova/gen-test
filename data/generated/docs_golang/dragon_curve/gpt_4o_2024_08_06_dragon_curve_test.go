package main

import (
    "image"
    "image/color"
    "image/png"
    "os"
    "testing"
)

func TestDragonCurveImageCreation(t *testing.T) {
    // Run the main function to generate the image
    main()

    // Check if the image file was created
    _, err := os.Stat("dragon.png")
    if os.IsNotExist(err) {
        t.Fatalf("Image file 'dragon.png' was not created")
    }

    // Open the generated image
    f, err := os.Open("dragon.png")
    if err != nil {
        t.Fatalf("Failed to open image file: %v", err)
    }
    defer f.Close()

    // Decode the image
    img, err := png.Decode(f)
    if err != nil {
        t.Fatalf("Failed to decode image: %v", err)
    }

    // Check the image dimensions
    bounds := img.Bounds()
    expectedWidth := sep * 11 / 6
    expectedHeight := sep * 4 / 3
    if bounds.Dx() != expectedWidth || bounds.Dy() != expectedHeight {
        t.Fatalf("Image dimensions are incorrect, got: %dx%d, expected: %dx%d", bounds.Dx(), bounds.Dy(), expectedWidth, expectedHeight)
    }
}

func TestDragonFunction(t *testing.T) {
    // Create a blank image
    width := sep * 11 / 6
    height := sep * 4 / 3
    bounds := image.Rect(0, 0, width, height)
    b = image.NewNRGBA(bounds)
    draw.Draw(b, bounds, image.NewUniform(color.White), image.ZP, draw.Src)

    // Call the dragon function
    dragon(1, 0, 1, sep, sep/2, sep*5/6)

    // Check if the starting point is colored
    startX := int(sep/2 + 0.5)
    startY := int(sep*5/6 + 0.5)
    if b.At(startX, startY) != p {
        t.Fatalf("Starting point is not colored correctly")
    }
}

func TestLineDrawing(t *testing.T) {
    // Create a blank image
    width := sep * 11 / 6
    height := sep * 4 / 3
    bounds := image.Rect(0, 0, width, height)
    b = image.NewNRGBA(bounds)
    draw.Draw(b, bounds, image.NewUniform(color.White), image.ZP, draw.Src)

    // Draw a line using the dragon function
    dragon(1, 0, 1, sep, sep/2, sep*5/6)

    // Check if the line is drawn correctly
    x1 := int(sep/2 + 0.5)
    y1 := int(sep*5/6 + 0.5)
    x2 := int(sep/2 + sep*cos[0] + 0.5)
    y2 := int(sep*5/6 + sep*sin[0] + 0.5)

    xInc := 1
    if x1 > x2 {
        xInc = -1
    }
    yInc := 1
    if y1 > y2 {
        yInc = -1
    }

    for x, y := x1, y1; ; x, y = x+xInc, y+yInc {
        if b.At(x, y) != p {
            t.Fatalf("Line is not drawn correctly at (%d, %d)", x, y)
        }
        if x == x2 {
            break
        }
    }
}

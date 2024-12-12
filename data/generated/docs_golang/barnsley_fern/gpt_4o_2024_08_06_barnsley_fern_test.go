package main

import (
    "image"
    "image/color"
    "image/png"
    "math/rand"
    "os"
    "testing"
)

// Test constants
const (
    testWidth = 200
    testN     = 1000 // Reduced for faster testing
)

// TestBarnsleyFern tests the generation of the Barnsley fern image
func TestBarnsleyFern(t *testing.T) {
    dx := xMax - xMin
    dy := yMax - yMin
    fw := float64(testWidth)
    fh := fw * dy / dx
    height := int(fh)
    r := image.Rect(0, 0, testWidth, height)
    img := image.NewRGBA(r)
    var x, y float64

    plot := func() {
        ix := int(fw * (x - xMin) / dx)
        iy := int(fh * (yMax - y) / dy)
        img.SetRGBA(ix, iy, c)
    }

    plot()
    for i := 0; i < testN; i++ {
        switch s := rand.Intn(100); {
        case s < 85:
            x, y = .85*x+.04*y, -.04*x+.85*y+1.6
        case s < 85+7:
            x, y = .2*x-.26*y, .23*x+.22*y+1.6
        case s < 85+7+7:
            x, y = -.15*x+.28*y, .26*x+.24*y+.44
        default:
            x, y = 0, .16*y
        }
        plot()
    }

    // Test if the image is created successfully
    f, err := os.Create("test_bf.png")
    if err != nil {
        t.Fatalf("Failed to create image file: %v", err)
    }
    defer f.Close()

    if err := png.Encode(f, img); err != nil {
        t.Fatalf("Failed to encode image to PNG: %v", err)
    }

    // Check if the file exists
    if _, err := os.Stat("test_bf.png"); os.IsNotExist(err) {
        t.Fatalf("Image file was not created")
    }
}

// TestTransformations tests the transformations used in the Barnsley fern
func TestTransformations(t *testing.T) {
    var x, y float64

    // Test transformation ƒ1
    x, y = 0, 0.16*y
    if x != 0 || y != 0 {
        t.Errorf("Transformation ƒ1 failed: got (%v, %v), want (0, 0)", x, y)
    }

    // Test transformation ƒ2
    x, y = 1, 1
    x, y = .85*x+.04*y, -.04*x+.85*y+1.6
    if x != 0.89 || y != 2.41 {
        t.Errorf("Transformation ƒ2 failed: got (%v, %v), want (0.89, 2.41)", x, y)
    }

    // Test transformation ƒ3
    x, y = 1, 1
    x, y = .2*x-.26*y, .23*x+.22*y+1.6
    if x != -0.06 || y != 2.05 {
        t.Errorf("Transformation ƒ3 failed: got (%v, %v), want (-0.06, 2.05)", x, y)
    }

    // Test transformation ƒ4
    x, y = 1, 1
    x, y = -.15*x+.28*y, .26*x+.24*y+.44
    if x != 0.13 || y != 0.94 {
        t.Errorf("Transformation ƒ4 failed: got (%v, %v), want (0.13, 0.94)", x, y)
    }
}

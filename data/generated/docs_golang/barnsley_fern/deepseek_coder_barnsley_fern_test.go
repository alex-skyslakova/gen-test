package main

import (
    "image"
    "image/color"
    "math/rand"
    "testing"
)

func TestBarnsleyFern(t *testing.T) {
    // Test parameters
    width = 200
    n = 1000
    c = color.RGBA{34, 139, 34, 255}

    dx := xMax - xMin
    dy := yMax - yMin
    fw := float64(width)
    fh := fw * dy / dx
    height := int(fh)
    r := image.Rect(0, 0, width, height)
    img := image.NewRGBA(r)

    var x, y float64
    plot := func() {
        // transform computed float x, y to integer image coordinates
        ix := int(fw * (x - xMin) / dx)
        iy := int(fh * (yMax - y) / dy)
        img.SetRGBA(ix, iy, c)
    }

    // Initial plot
    plot()

    // Test the transformations
    for i := 0; i < n; i++ {
        s := rand.Intn(100)
        switch {
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

    // Check if the image has been populated
    for i := 0; i < width; i++ {
        for j := 0; j < height; j++ {
            if img.At(i, j) == color.RGBA{0, 0, 0, 0} {
                t.Errorf("Image not fully populated at (%d, %d)", i, j)
            }
        }
    }
}

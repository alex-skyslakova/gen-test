package main

import (
    "image"
    "image/color"
    "math"
    "testing"
)

func TestNormalize(t *testing.T) {
    tests := []struct {
        input    vector
        expected vector
    }{
        {vector{3, 4, 0}, vector{0.6, 0.8, 0}},
        {vector{1, 1, 1}, vector{1 / math.Sqrt(3), 1 / math.Sqrt(3), 1 / math.Sqrt(3)}},
        {vector{0, 0, 0}, vector{0, 0, 0}}, // Edge case: zero vector
    }

    for _, test := range tests {
        v := test.input
        normalize(&v)
        for i := 0; i < 3; i++ {
            if math.Abs(v[i]-test.expected[i]) > 1e-9 {
                t.Errorf("Normalize(%v) = %v, expected %v", test.input, v, test.expected)
            }
        }
    }
}

func TestDot(t *testing.T) {
    tests := []struct {
        x        vector
        y        vector
        expected float64
    }{
        {vector{1, 2, 3}, vector{4, 5, 6}, 32},
        {vector{0, 0, 0}, vector{1, 1, 1}, 0},
        {vector{1, 0, 0}, vector{0, 1, 0}, 0},
    }

    for _, test := range tests {
        result := dot(&test.x, &test.y)
        if result != test.expected {
            t.Errorf("Dot(%v, %v) = %v, expected %v", test.x, test.y, result, test.expected)
        }
    }
}

func TestDrawSphere(t *testing.T) {
    r := 100
    k := 1.5
    amb := 0.2
    dir := &vector{-30, -30, 50}
    normalize(dir)

    img := drawSphere(r, k, amb, dir)

    // Check the dimensions of the image
    if img.Bounds().Dx() != r*4 || img.Bounds().Dy() != r*3 {
        t.Errorf("Image dimensions are incorrect: got %v, expected %v", img.Bounds(), image.Rect(-r*2, -r*1.5, r*2, r*1.5))
    }

    // Check some specific pixels to ensure they are within the expected range
    tests := []struct {
        x, y int
        expected uint8
    }{
        {0, 0, 51}, // Center of the sphere
        {r, 0, 0},  // Edge of the sphere
        {-r, 0, 0}, // Edge of the sphere
    }

    for _, test := range tests {
        c := img.GrayAt(test.x, test.y)
        if c.Y != test.expected {
            t.Errorf("Pixel at (%d, %d) = %d, expected %d", test.x, test.y, c.Y, test.expected)
        }
    }
}

package main

import (
    "image"
    "image/color"
    "math"
    "testing"
)

func TestNormalize(t *testing.T) {
    v := &vector{3, 4, 0}
    normalize(v)
    expected := &vector{0.6, 0.8, 0}
    if !vectorsEqual(v, expected) {
        t.Errorf("Expected %v, got %v", expected, v)
    }
}

func TestDot(t *testing.T) {
    v1 := &vector{1, 2, 3}
    v2 := &vector{4, -5, 6}
    result := dot(v1, v2)
    expected := float64(12)
    if result != expected {
        t.Errorf("Expected %v, got %v", expected, result)
    }
}

func TestDrawSphere(t *testing.T) {
    dir := &vector{-30, -30, 50}
    normalize(dir)
    img := drawSphere(10, 1.5, 0.2, dir)

    if img.Bounds().Dx() != 40 || img.Bounds().Dy() != 30 {
        t.Errorf("Expected image dimensions to be 40x30, got %dx%d", img.Bounds().Dx(), img.Bounds().Dy())
    }

    // Check if the center pixel is set (it should be part of the sphere)
    centerColor := img.GrayAt(0, 0)
    if centerColor.Y == 0 {
        t.Error("Expected center pixel to be part of the sphere and not black")
    }

    // Check if a corner pixel is not set (it should be outside the sphere)
    cornerColor := img.GrayAt(-20, -15)
    if cornerColor.Y != 0 {
        t.Error("Expected corner pixel to be outside the sphere and black")
    }
}

func vectorsEqual(v1, v2 *vector) bool {
    epsilon := 1e-9
    for i := 0; i < 3; i++ {
        if math.Abs(v1[i]-v2[i]) > epsilon {
            return false
        }
    }
    return true
}

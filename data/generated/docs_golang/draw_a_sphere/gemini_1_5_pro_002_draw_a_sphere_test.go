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
	if math.Abs(v[0]-0.6) > 1e-6 || math.Abs(v[1]-0.8) > 1e-6 || math.Abs(v[2]-0.0) > 1e-6 {
		t.Errorf("normalize({3, 4, 0}) = %v, want {0.6, 0.8, 0}", *v)
	}

	v = &vector{0, 0, 0}
	normalize(v) // Test zero vector
    if v[0] != 0 || v[1] != 0 || v[2] != 0 {
        t.Errorf("normalize({0, 0, 0}) = %v, want {0, 0, 0}", *v)
    }

    v = &vector{1, 0, 0}
    normalize(v)
    if v[0] != 1 || v[1] != 0 || v[2] != 0 {
        t.Errorf("normalize({1, 0, 0}) = %v, want {1, 0, 0}", *v)
    }

}

func TestDot(t *testing.T) {
	x := &vector{1, 2, 3}
	y := &vector{4, 5, 6}
	if dot(x, y) != 32 {
		t.Errorf("dot({1, 2, 3}, {4, 5, 6}) = %v, want 32", dot(x, y))
	}

	x = &vector{0, 0, 0}
	y = &vector{1, 1, 1}

    if dot(x,y) != 0 {
        t.Errorf("dot({0, 0, 0}, {1, 1, 1}) = %v, want 0", dot(x,y))
    }


}

func TestDrawSphere(t *testing.T) {
	dir := &vector{-30, -30, 50}
	normalize(dir)
	img := drawSphere(10, 1.5, .2, dir)

	// Check image bounds
	if img.Bounds() != image.Rect(-20, -15, 20, 15) {
		t.Errorf("Image bounds incorrect, got: %v, want: (-20, -15, 20, 15)", img.Bounds())
	}

	// Check a specific pixel near the center for expected brightness
    c := img.At(0, 0)
    gray, _ := c.(color.Gray)

    if gray.Y < 200 || gray.Y > 255 { // Expecting bright pixel due to light direction
        t.Errorf("Center pixel brightness is unexpected: %v", gray.Y)
    }


	dir = &vector{0, 0, 1}  // Light directly above
	img = drawSphere(10, 1, 0, dir)

	c = img.At(0, 0)
	gray, _ = c.(color.Gray)

	if gray.Y != 255 {
		t.Errorf("Center pixel brightness with direct overhead light incorrect: %v, want 255", gray.Y)
	}

	dir = &vector{0, 0, -1} // Light directly below - should be dim
	img = drawSphere(10, 1, 0, dir)

	c = img.At(0, 0)
	gray, _ = c.(color.Gray)

	if gray.Y != 0 {
		t.Errorf("Center pixel brightness with light below incorrect: %v, want 0", gray.Y)
	}




}



package main

import (
	"image"
	"image/color"
	"image/png"
	"math/rand"
	"os"
	"testing"
)

func TestPlot(t *testing.T) {
	// Initialize a test image
	width := 200
	height := 200
	img := image.NewRGBA(image.Rect(0, 0, width, height))

	// Test plotting a single point
	x := 0.0
	y := 0.0
	plot(img, x, y, width, height)

	// Verify that the pixel at the expected coordinates is set
	if img.At(int(float64(width)*(x-xMin)/(xMax-xMin)), int(float64(height)*(yMax-y)/(yMax-yMin))) != c {
		t.Errorf("Pixel not set correctly")
	}

}


func TestTransformations(t *testing.T) {
	tests := []struct {
		s     int
		xIn   float64
		yIn   float64
		xOut  float64
		yOut  float64
		name string
	}{
		{0, 0, 0, 0, 1.6, "f2"},
		{85, 0, 0, 0, 1.6, "f3"},
		{92, 0, 0, 0, 0.44, "f4"},
		{99, 0, 0, 0, 0, "f1"},
	}

	for _, tt := range tests {
		x := tt.xIn
		y := tt.yIn

		rand.Seed(int64(tt.s)) // set rand predictable in the test
		s := rand.Intn(100)
		switch {
		case s < 85:
			x, y =
				.85*x+.04*y,
				-.04*x+.85*y+1.6
		case s < 85+7:
			x, y =
				.2*x-.26*y,
				.23*x+.22*y+1.6
		case s < 85+7+7:
			x, y =
				-.15*x+.28*y,
				.26*x+.24*y+.44
		default:
			x, y = 0, .16*y
		}

		if x != tt.xOut || y != tt.yOut {
			t.Errorf("%s: expected (%f, %f), got (%f, %f)", tt.name, tt.xOut, tt.yOut, x, y)
		}

	}
}

// Helper function for plot to allow easier testing
func plot(img *image.RGBA, x, y float64, width, height int) {

	dx := xMax - xMin
	dy := yMax - yMin
	fw := float64(width)
	fh := float64(height)

	ix := int(fw * (x - xMin) / dx)
	iy := int(fh * (yMax - y) / dy)
	img.SetRGBA(ix, iy, c)
}


func TestMain(m *testing.M) {

	// Reduce iterations for testing
	n = 10000

	// Run the tests and record the exit code
	exitCode := m.Run()

	// Restore original n after test
	n = int(1e6)

	// Example: Create a smaller image for testing.
	width = 100 // Smaller width
	height := int(float64(width) * (yMax - yMin) / (xMax - xMin))
	r := image.Rect(0, 0, width, height)
	img := image.NewRGBA(r)
	// ... rest of your image generation code (adjusted for smaller size) ...


	f, err := os.Create("test_bf.png")
	if err != nil {
		panic(err)
	}
	defer f.Close()

	if err := png.Encode(f, img); err != nil {
		panic(err)
	}
	

	os.Exit(exitCode)



}





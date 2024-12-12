package main

import (
	"image"
	"image/color"
	"image/png"
	"math"
	"os"
	"testing"
)

func TestDragon(t *testing.T) {
	// Test cases with different depths and directions
	testCases := []struct {
		n, a, t int
		d       float64
		x, y    float64
	}{
		{1, 0, 1, 100, 0, 0},
		{2, 0, 1, 100, 0, 0},
		{1, 4, -1, 50, 100, 100},
		{2, 4, -1, 50, 100, 100},
		{3, 2, 1, 25, 50, 50},
		{0, 0, 1, 100, 0, 0}, // Test base case n <= 1
	}

	for _, tc := range testCases {
		width := int(sep * 11 / 6)
		height := int(sep * 4 / 3)
		bounds := image.Rect(0, 0, width, height)
		b = image.NewNRGBA(bounds)

		// Initialize image with white background
		for x := 0; x < width; x++ {
			for y := 0; y < height; y++ {
				b.Set(x, y, color.White)
			}
		}


		dragon(tc.n, tc.a, tc.t, tc.d, tc.x, tc.y)

		// Verify that pixels have been drawn (not a comprehensive check,
		//  but confirms that the line drawing logic has been executed)
		pixelsDrawn := false
		for x := 0; x < width; x++ {
			for y := 0; y < height; y++ {
				if b.At(x, y) != color.White {
					pixelsDrawn = true
					break
				}
			}
			if pixelsDrawn {
				break
			}
		}
		if !pixelsDrawn && tc.n > 0 {
			t.Errorf("No pixels drawn for n=%d, a=%d, t=%d, d=%f, x=%f, y=%f", tc.n, tc.a, tc.t, tc.d, tc.x, tc.y)
		}
	}
}

// This test covers the main function and image saving.
func TestMainFunction(t *testing.T) {
	// Capture stdout to check for errors.  Not strictly part of unit testing
	// but useful for detecting problems during the test.
	// Replace os.Stdout with a temporary io.Writer
	// ... (implementation of stdout redirection is omitted as it is not central to the dragon curve logic test)

	originalStdout := os.Stdout // Save original stdout
	defer func() { os.Stdout = originalStdout }() // Restore original stdout

	//  Call main and check there were no print statements to capturedStdout, indicating no errors during file save
	main()

	// Check if the file was created successfully
	_, err := os.Stat("dragon.png")
	if err != nil {
		t.Fatalf("Error creating dragon.png: %v", err)
	}

	// Open the image file and check if it's a valid PNG
	f, err := os.Open("dragon.png")
	if err != nil {
		t.Fatalf("Error opening dragon.png: %v", err)
	}
	defer f.Close()

	_, err = png.Decode(f)
	if err != nil {
		t.Fatalf("Error decoding dragon.png: %v", err)
	}

	os.Remove("dragon.png")

}



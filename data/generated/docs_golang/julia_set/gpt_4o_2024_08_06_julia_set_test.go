package main

import (
	"image"
	"image/color"
	"image/png"
	"os"
	"testing"
)

func TestJuliaSetImageCreation(t *testing.T) {
	const (
		width, height = 800, 600
		fileName      = "julia.png"
	)

	// Run the main function to generate the image
	main()

	// Check if the file is created
	if _, err := os.Stat(fileName); os.IsNotExist(err) {
		t.Fatalf("Expected file %s to be created, but it does not exist.", fileName)
	}

	// Open the generated image file
	imgFile, err := os.Open(fileName)
	if err != nil {
		t.Fatalf("Failed to open generated image file: %v", err)
	}
	defer imgFile.Close()

	// Decode the image
	img, err := png.Decode(imgFile)
	if err != nil {
		t.Fatalf("Failed to decode generated image file: %v", err)
	}

	// Check the image dimensions
	if img.Bounds().Dx() != width || img.Bounds().Dy() != height {
		t.Fatalf("Expected image dimensions to be %dx%d, but got %dx%d", width, height, img.Bounds().Dx(), img.Bounds().Dy())
	}

	// Check a few pixels to ensure they are set (not all black)
	var nonBlackPixels int
	for x := 0; x < width; x += 100 {
		for y := 0; y < height; y += 100 {
			r, g, b, _ := img.At(x, y).RGBA()
			if r != 0 || g != 0 || b != 0 {
				nonBlackPixels++
			}
		}
	}

	if nonBlackPixels == 0 {
		t.Fatalf("Expected some non-black pixels in the image, but found none.")
	}
}

func TestJuliaSetColorRange(t *testing.T) {
	const (
		width, height = 800, 600
		fileName      = "julia.png"
	)

	// Run the main function to generate the image
	main()

	// Open the generated image file
	imgFile, err := os.Open(fileName)
	if err != nil {
		t.Fatalf("Failed to open generated image file: %v", err)
	}
	defer imgFile.Close()

	// Decode the image
	img, err := png.Decode(imgFile)
	if err != nil {
		t.Fatalf("Failed to decode generated image file: %v", err)
	}

	// Check color range
	for x := 0; x < width; x += 100 {
		for y := 0; y < height; y += 100 {
			r, g, b, _ := img.At(x, y).RGBA()
			if r > 255 || g > 255 || b > 255 {
				t.Fatalf("Pixel color values should be within 0-255 range, but got R: %d, G: %d, B: %d", r, g, b)
			}
		}
	}
}

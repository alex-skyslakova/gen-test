package main

import (
	"image"
	"image/color"
	"image/png"
	"os"
	"testing"
)

func TestArchimedeanSpiralImageCreation(t *testing.T) {
	const (
		width, height = 600, 600
		fileName      = "spiral.png"
	)

	// Run the main function to create the image
	main()

	// Check if the file was created
	if _, err := os.Stat(fileName); os.IsNotExist(err) {
		t.Fatalf("Expected file %s to be created, but it does not exist.", fileName)
	}

	// Open the created image file
	imgFile, err := os.Open(fileName)
	if err != nil {
		t.Fatalf("Failed to open the image file: %v", err)
	}
	defer imgFile.Close()

	// Decode the image
	img, err := png.Decode(imgFile)
	if err != nil {
		t.Fatalf("Failed to decode the image: %v", err)
	}

	// Check the image dimensions
	if img.Bounds().Dx() != width || img.Bounds().Dy() != height {
		t.Fatalf("Expected image dimensions to be %dx%d, but got %dx%d", width, height, img.Bounds().Dx(), img.Bounds().Dy())
	}

	// Check if the center pixel is not white (indicating the spiral was drawn)
	centerColor := img.At(width/2, height/2)
	r, g, b, _ := centerColor.RGBA()
	if r == 0xFFFF && g == 0xFFFF && b == 0xFFFF {
		t.Fatalf("Expected the center of the image to not be white, indicating the spiral was drawn.")
	}
}

func TestBackgroundIsWhite(t *testing.T) {
	const (
		width, height = 600, 600
		fileName      = "spiral.png"
	)

	// Open the created image file
	imgFile, err := os.Open(fileName)
	if err != nil {
		t.Fatalf("Failed to open the image file: %v", err)
	}
	defer imgFile.Close()

	// Decode the image
	img, err := png.Decode(imgFile)
	if err != nil {
		t.Fatalf("Failed to decode the image: %v", err)
	}

	// Check a few random background pixels to ensure they are white
	white := color.RGBA{255, 255, 255, 255}
	checkPoints := []image.Point{
		{0, 0},
		{width - 1, 0},
		{0, height - 1},
		{width - 1, height - 1},
	}

	for _, point := range checkPoints {
		if img.At(point.X, point.Y) != white {
			t.Fatalf("Expected background pixel at %v to be white.", point)
		}
	}
}

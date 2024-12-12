package main

import (
	"image"
	"image/color"
	"image/png"
	"os"
	"testing"
)

func TestHough(t *testing.T) {
	// Test case 1: Empty image
	emptyImg := image.NewGray(image.Rect(0, 0, 10, 10))
	houghImg := hough(emptyImg, 20, 20)
	if houghImg.Bounds().Dx() != 20 || houghImg.Bounds().Dy() != 20 {
		t.Errorf("Expected hough image size 20x20, got %dx%d", houghImg.Bounds().Dx(), houghImg.Bounds().Dy())
	}
	// Check if all pixels are white
	for x := 0; x < 20; x++ {
		for y := 0; y < 20; y++ {
			if houghImg.At(x, y).(color.Gray).Y != 255 {
				t.Errorf("Expected white pixel at (%d, %d), got %v", x, y, houghImg.At(x, y))
			}
		}
	}


	// Test case 2: Single black pixel
	singlePixelImg := image.NewGray(image.Rect(0, 0, 10, 10))
	singlePixelImg.SetGray(5, 5, color.Gray{0})
	houghImg = hough(singlePixelImg, 20, 20)
	// Check if some pixels are not white (indicating a line)
	foundNonWhite := false
	for x := 0; x < 20; x++ {
		for y := 0; y < 20; y++ {
			if houghImg.At(x, y).(color.Gray).Y != 255 {
				foundNonWhite = true
				break
			}
		}
	}
	if !foundNonWhite {
		t.Error("Expected some non-white pixels in hough image, but all were white")
	}

	// Test case 3: Horizontal line
	horizontalLineImg := image.NewGray(image.Rect(0, 0, 10, 10))
	for x := 0; x < 10; x++ {
		horizontalLineImg.SetGray(x, 5, color.Gray{0})
	}
	houghImg = hough(horizontalLineImg, 20, 20)

	// Test case 4: Vertical Line
	verticalLineImg := image.NewGray(image.Rect(0, 0, 10, 10))
	for y := 0; y < 10; y++ {
		verticalLineImg.SetGray(5, y, color.Gray{0})
	}
	houghImg = hough(verticalLineImg, 20, 20)

	// Test case 5: Diagonal Line
	diagonalLineImg := image.NewGray(image.Rect(0, 0, 10, 10))
	for i := 0; i < 10; i++ {
		diagonalLineImg.SetGray(i, i, color.Gray{0})
	}
	houghImg = hough(diagonalLineImg, 20, 20)



	// Test with an actual image
	file, err := os.Open("Pentagon.png")
	if err != nil {
		t.Fatal(err)
	}
	defer file.Close()
	img, err := png.Decode(file)
	if err != nil {
		t.Fatal(err)
	}
	houghImg = hough(img, 460, 360) // Use the same dimensions as in main()
	if houghImg.Bounds().Dx() != 460 || houghImg.Bounds().Dy() != 360 {
		t.Errorf("Expected hough image size 460x360, got %dx%d", houghImg.Bounds().Dx(), houghImg.Bounds().Dy())
	}

}


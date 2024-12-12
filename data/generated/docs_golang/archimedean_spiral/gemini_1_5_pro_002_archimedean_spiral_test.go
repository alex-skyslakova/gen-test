package main

import (
	"image"
	"image/color"
	"image/png"
	"os"
	"testing"
)

func TestGenerateArchimedeanSpiral(t *testing.T) {
	const (
		width, height = 600, 600
		centre        = width / 2.0
		degreesIncr   = 0.1 * math.Pi / 180
		turns         = 2
		stop          = 360 * turns * 10 * degreesIncr
		fileName      = "test_spiral.png"
	)

	img := image.NewNRGBA(image.Rect(0, 0, width, height))
	bg := image.NewUniform(color.RGBA{255, 255, 255, 255})
	draw.Draw(img, img.Bounds(), bg, image.ZP, draw.Src)
	fgCol := color.RGBA{255, 0, 0, 255}

	a := 1.0
	b := 20.0

	for theta := 0.0; theta < stop; theta += degreesIncr {
		r := a + b*theta
		x := r * math.Cos(theta)
		y := r * math.Sin(theta)
		img.Set(int(centre+x), int(centre-y), fgCol)
	}

	imgFile, err := os.Create(fileName)
	if err != nil {
		t.Fatal(err)
	}
	defer imgFile.Close()

	if err := png.Encode(imgFile, img); err != nil {
		imgFile.Close()
		t.Fatal(err)
	}

	// Basic check: Verify that the image file was created
	fileInfo, err := os.Stat(fileName)
	if err != nil {
		t.Fatal(err)
	}
	if fileInfo.Size() == 0 {
		t.Error("Generated image file is empty")
	}

	//  Clean up test file (optional)
	os.Remove(fileName)


	// Test with different parameters (optional, add more as needed)

	// Test with a=0, b=10
	testGenerateSpiralWithParams(t, 0, 10, "test_spiral_a0_b10.png")

	// Test with a=50, b=1
	testGenerateSpiralWithParams(t, 50, 1, "test_spiral_a50_b1.png")


}


func testGenerateSpiralWithParams(t *testing.T, a, b float64, filename string) {

        // ... (Same image generation logic as in the main test, but with provided a and b, and filename)


        // Basic file size check (adapt as needed for more specific tests)
	fileInfo, err := os.Stat(filename)
	if err != nil {
		t.Fatal(err)
	}
	if fileInfo.Size() == 0 {
		t.Errorf("Generated image file %s is empty", filename)
	}

	os.Remove(filename) // Clean up
}


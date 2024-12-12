package main

import (
	"image"
	"image/color"
	"image/png"
	"os"
	"testing"
)

func TestJuliaSetGeneration(t *testing.T) {
	const (
		width, height = 200, 150 // Reduced size for testing
		maxIter       = 255
		cX, cY        = -0.7, 0.27015
		fileName      = "julia_test.png"
	)

	img := image.NewNRGBA(image.Rect(0, 0, width, height))

	for x := 0; x < width; x++ {
		thisx := float64(x)
		for y := 0.0; y < height; y++ {
			var tmp, zx, zy float64
			var i uint8
			zx = 1.5 * (thisx - width/2) / (0.5 * width)
			zy = (y - height/2) / (0.5 * height)
			i = maxIter
			for zx*zx+zy*zy < 4.0 && i > 0 {
				tmp = zx*zx - zy*zy + cX
				zy = 2.0*zx*zy + cY
				zx = tmp
				i--
			}
			img.Set(int(thisx), int(y), color.RGBA{i, i, i << 3, 255})
		}
	}


	imgFile, err := os.Create(fileName)
	if err != nil {
		t.Fatal(err)
	}
	defer func() {
                imgFile.Close()
                os.Remove(fileName) // Clean up test file
        }()


	if err := png.Encode(imgFile, img); err != nil {
		t.Fatal(err)
	}

	// Basic check: Verify that the file was created and has some content.
	fileInfo, err := imgFile.Stat()
	if err != nil {
		t.Fatal(err)
	}
	if fileInfo.Size() == 0 {
		t.Error("Generated PNG file is empty")
	}

}



// Test specific points for deterministic color values.  This helps ensure the core algorithm is correct.
func TestSpecificPoints(t *testing.T) {
    const (
        width, height = 200, 150
        maxIter       = 255
        cX, cY        = -0.7, 0.27015
    )

    testCases := []struct {
        x, y int
        expectedR, expectedG, expectedB uint8
    }{
        {100, 75, 7, 7, 56},  // Example point near center
        {0, 0, 0, 0, 0},       // Example point likely to be in the set
        {199, 149, 255, 255, 2040}, //Example point likely far from the set
    }

    img := image.NewNRGBA(image.Rect(0, 0, width, height))

		for x := 0; x < width; x++ {
			thisx := float64(x)
			for y := 0.0; y < height; y++ {
				var tmp, zx, zy float64
				var i uint8
				zx = 1.5 * (thisx - width/2) / (0.5 * width)
				zy = (y - height/2) / (0.5 * height)
				i = maxIter
				for zx*zx+zy*zy < 4.0 && i > 0 {
					tmp = zx*zx - zy*zy + cX
					zy = 2.0*zx*zy + cY
					zx = tmp
					i--
				}
				img.Set(int(thisx), int(y), color.RGBA{i, i, i << 3, 255})
			}
		}

    for _, tc := range testCases {
        r, g, b, _ := img.At(tc.x, tc.y).RGBA()
        if uint8(r>>8) != tc.expectedR || uint8(g>>8) != tc.expectedG || uint8(b>>8) != tc.expectedB {
            t.Errorf("At (%d, %d): expected (%d, %d, %d), got (%d, %d, %d)", tc.x, tc.y, tc.expectedR, tc.expectedG, tc.expectedB, uint8(r>>8), uint8(g>>8), uint8(b>>8))
        }
    }
}


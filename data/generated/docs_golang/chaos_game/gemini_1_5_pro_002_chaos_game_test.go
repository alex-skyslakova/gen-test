package main

import (
	"image"
	"image/color"
	"image/gif"
	"math"
	"math/rand"
	"os"
	"testing"
	"time"
)

func TestNewAnim(t *testing.T) {
	frames := 10
	delay := 100 * time.Millisecond
	anim := newAnim(frames, delay)

	if len(anim.Image) != 0 {
		t.Errorf("Expected anim.Image length 0, got %d", len(anim.Image))
	}
	if cap(anim.Image) != frames {
		t.Errorf("Expected anim.Image capacity %d, got %d", frames, cap(anim.Image))
	}
	if len(anim.Delay) != 1 {
		t.Errorf("Expected anim.Delay length 1, got %d", len(anim.Delay))
	}
	if cap(anim.Delay) != frames {
		t.Errorf("Expected anim.Delay capacity %d, got %d", frames, cap(anim.Delay))
	}
	if anim.Delay[0] != 10 {
		t.Errorf("Expected anim.Delay[0] 10, got %d", anim.Delay[0])
	}
}


func TestAddFrame(t *testing.T) {
	anim := newAnim(10, 100*time.Millisecond)
	b := image.Rect(0, 0, 100, 100)
	m := image.NewPaletted(b, bwPalette)

	addFrame(anim, m)

	if len(anim.Image) != 1 {
		t.Errorf("Expected anim.Image length 1, got %d", len(anim.Image))
	}
	if len(anim.Delay) != 1 {
		t.Errorf("Expected anim.Delay length 1, got %d", len(anim.Delay))
	}

        // Check if the image is copied correctly.  Modify the original and verify the copy is unchanged.

        m.SetColorIndex(0,0,2)
        if anim.Image[0].ColorIndexAt(0,0) == 2 {
            t.Error("Image not copied correctly, modification of original affected the copy.")
        }
}


func TestWriteAnim(t *testing.T) {
	anim := newAnim(1, 100*time.Millisecond)
	b := image.Rect(0, 0, 100, 100)
	m := image.NewPaletted(b, bwPalette)
	addFrame(anim, m)

	filename := "test_anim.gif"
	defer os.Remove(filename)

	err := writeAnim(anim, filename)
	if err != nil {
		t.Errorf("writeAnim returned error: %v", err)
	}

	_, err = os.Stat(filename)
	if err != nil {
		t.Errorf("File not created: %v", err)
	}
}



func TestChaosGameEndToEnd(t *testing.T) {

    const (
        width          = 160
        frames         = 2 // Reduce frames for testing
        pointsPerFrame = 50
        delay          = 100 * time.Millisecond
        filename       = "chaos_test.gif"
    )

    rand.Seed(0) // fixed seed for reproducibility in the test


	var tan60 = math.Sin(math.Pi / 3)
	height := int(math.Round(float64(width) * tan60))
	b := image.Rect(0, 0, width, height)
	vertices := [...]image.Point{
		{0, height}, {width, height}, {width / 2, 0},
	}

	// Make a filled triangle (simplified for test).
	m := image.NewPaletted(b, bwPalette)


	// Pick starting point (deterministic for test).
	var p image.Point
	p.Y = height /2
	p.X = width / 2


	anim := newAnim(frames, delay)
	addFrame(anim, m)
	for i := 1; i < frames; i++ {
		for j := 0; j < pointsPerFrame; j++ {
			vi := rand.Intn(len(vertices))
			v := vertices[vi]
			p.X = (p.X + v.X) / 2
			p.Y = (p.Y + v.Y) / 2
			m.SetColorIndex(p.X, p.Y, uint8(2+vi)) // Using fixed color for easier comparison
		}
		addFrame(anim, m)
	}
	if err := writeAnim(anim, filename); err != nil {
		t.Fatal(err)
	}

    // Clean up test file
    defer os.Remove(filename)


    // Load generated gif and perform checks

    f, err := os.Open(filename)
    if err != nil {
        t.Fatal(err)
    }
    defer f.Close()

    g, err := gif.DecodeAll(f)
    if err != nil {
        t.Fatal(err)
    }


    // Example check - verify frames count:
    if len(g.Image) != frames {
        t.Fatalf("Incorrect number of frames in generated GIF")

    }

    // Additional verification could include checking pixel colors in specific regions.

}





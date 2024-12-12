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

// TestNewAnim checks if a new GIF animation is created with the correct number of frames and delay.
func TestNewAnim(t *testing.T) {
	frames := 10
	delay := 100 * time.Millisecond
	anim := newAnim(frames, delay)

	if len(anim.Image) != 0 {
		t.Errorf("Expected 0 images, got %d", len(anim.Image))
	}
	if len(anim.Delay) != 1 {
		t.Errorf("Expected 1 delay entry, got %d", len(anim.Delay))
	}
	expectedDelay := int(delay / (10 * time.Millisecond))
	if anim.Delay[0] != expectedDelay {
		t.Errorf("Expected delay %d, got %d", expectedDelay, anim.Delay[0])
	}
}

// TestAddFrame checks if frames are added correctly to the GIF animation.
func TestAddFrame(t *testing.T) {
	anim := newAnim(10, 100*time.Millisecond)
	b := image.Rect(0, 0, 100, 100)
	m := image.NewPaletted(b, color.Palette{color.White, color.Black})

	addFrame(anim, m)
	if len(anim.Image) != 1 {
		t.Errorf("Expected 1 image, got %d", len(anim.Image))
	}
	if len(anim.Delay) != 1 {
		t.Errorf("Expected 1 delay entry, got %d", len(anim.Delay))
	}
}

// TestWriteAnim checks if the GIF animation is written to a file correctly.
func TestWriteAnim(t *testing.T) {
	anim := newAnim(1, 100*time.Millisecond)
	b := image.Rect(0, 0, 100, 100)
	m := image.NewPaletted(b, color.Palette{color.White, color.Black})
	addFrame(anim, m)

	filename := "test.gif"
	defer os.Remove(filename)

	err := writeAnim(anim, filename)
	if err != nil {
		t.Fatalf("Failed to write animation: %v", err)
	}

	if _, err := os.Stat(filename); os.IsNotExist(err) {
		t.Errorf("File %s was not created", filename)
	}
}

// TestChaosGame checks if the chaos game generates points within the bounds of the triangle.
func TestChaosGame(t *testing.T) {
	const (
		width  = 160
		height = 139 // height calculated using tan(60 degrees) * width
	)
	vertices := [...]image.Point{
		{0, height}, {width, height}, {width / 2, 0},
	}

	rand.Seed(time.Now().UnixNano())
	p := image.Point{
		X: rand.Intn(width),
		Y: rand.Intn(height),
	}

	for i := 0; i < 1000; i++ {
		vi := rand.Intn(len(vertices))
		v := vertices[vi]
		p.X = (p.X + v.X) / 2
		p.Y = (p.Y + v.Y) / 2

		if p.X < 0 || p.X > width || p.Y < 0 || p.Y > height {
			t.Errorf("Point out of bounds: (%d, %d)", p.X, p.Y)
		}
	}
}

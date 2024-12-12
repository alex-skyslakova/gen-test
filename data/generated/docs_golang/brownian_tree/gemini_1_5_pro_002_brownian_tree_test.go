package main

import (
	"image"
	"image/color"
	"testing"
)

func TestHasNeighbor(t *testing.T) {
	g = image.NewGray(image.Rectangle{image.Point{0, 0}, image.Point{w, h}})

	// Test with no neighbors
	p := image.Point{w / 2, h / 2}
	if hasNeighbor(p) {
		t.Errorf("hasNeighbor(%v) returned true, expected false", p)
	}

	// Test with a neighbor
	g.SetGray(p.X+1, p.Y, color.Gray{frost})
	if !hasNeighbor(p) {
		t.Errorf("hasNeighbor(%v) returned false, expected true", p)
	}

	// Test with a neighbor at the edge of the image
	p = image.Point{0, 0}
	g.SetGray(p.X+1, p.Y, color.Gray{frost})
	if !hasNeighbor(p) {
		t.Errorf("hasNeighbor(%v) returned false, expected true", p)
	}

    // Test with all neighbors
    p = image.Point{w/2, h/2}
    for _, n := range n8 {
        o := p.Add(n)
        if o.In(g.Rect){
            g.SetGray(o.X, o.Y, color.Gray{frost})
        }
    }

    if !hasNeighbor(p) {
		t.Errorf("hasNeighbor(%v) returned false, expected true", p)
	}
}



// TestMain allows us to set up the image before running tests.
func TestMain(m *testing.M) {

	g = image.NewGray(image.Rectangle{image.Point{0, 0}, image.Point{w, h}})
    // Set a seed for predictable testing.
	g.SetGray(w/3, h/3, color.Gray{frost})
	m.Run()

}



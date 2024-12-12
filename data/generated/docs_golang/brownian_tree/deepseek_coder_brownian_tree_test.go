package main

import (
    "image"
    "image/color"
    "math/rand"
    "testing"
)

// Mock random number generator to make tests deterministic
type mockRand struct{}

func (m *mockRand) Intn(n int) int {
    return 1
}

func TestHasNeighbor(t *testing.T) {
    g = image.NewGray(image.Rectangle{image.Point{0, 0}, image.Point{w, h}})

    // Test case 1: No neighbors
    g.SetGray(w/3, h/3, color.Gray{frost})
    if hasNeighbor(image.Point{w/2, h/2}) {
        t.Error("Expected no neighbor, but found one")
    }

    // Test case 2: Has neighbors
    g.SetGray(w/2, h/2, color.Gray{frost})
    if !hasNeighbor(image.Point{w/2+1, h/2+1}) {
        t.Error("Expected a neighbor, but found none")
    }
}

func TestBrownianTreeGeneration(t *testing.T) {
    // Mock random number generator
    rand.Seed(1)
    rand.New(&mockRand{})

    g = image.NewGray(image.Rectangle{image.Point{0, 0}, image.Point{w, h}})
    g.SetGray(w/3, h/3, color.Gray{frost})

    // Run the generation process
    for a := 0; a < n; {
        rp := image.Point{rand.Intn(w), rand.Intn(h)}
        if g.At(rp.X, rp.Y).(color.Gray).Y == frost {
            for {
                rp.X += rand.Intn(3) - 1
                rp.Y += rand.Intn(3) - 1
                if !rp.In(g.Rect) {
                    continue
                }
                if g.At(rp.X, rp.Y).(color.Gray).Y != frost {
                    break
                }
            }
        } else {
            for !hasNeighbor(rp) {
                rp.X += rand.Intn(3) - 1
                rp.Y += rand.Intn(3) - 1
                if !rp.In(g.Rect) {
                    continue
                }
            }
        }
        g.SetGray(rp.X, rp.Y, color.Gray{frost})
        a++
    }

    // Verify that the tree has been generated correctly
    for y := 0; y < h; y++ {
        for x := 0; x < w; x++ {
            if g.At(x, y).(color.Gray).Y == frost {
                // Ensure that each frosted point has at least one neighbor
                if !hasNeighbor(image.Point{x, y}) {
                    t.Errorf("Frosted point at (%d, %d) has no neighbor", x, y)
                }
            }
        }
    }
}

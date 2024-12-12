package main

import (
    "image"
    "image/color"
    "testing"
)

func TestLangtonsAnt(t *testing.T) {
    bounds := image.Rect(0, 0, 100, 100)
    im := image.NewGray(bounds)
    gBlack := color.Gray{0}
    gWhite := color.Gray{255}

    // Initialize the grid with all white cells
    for y := 0; y < 100; y++ {
        for x := 0; x < 100; x++ {
            if im.At(x, y) != gWhite {
                t.Errorf("Expected cell (%d, %d) to be white", x, y)
            }
        }
    }

    pos := image.Point{50, 50}
    dir := up

    // Simulate the ant's movement
    for pos.In(bounds) {
        currentColor := im.At(pos.X, pos.Y).(color.Gray).Y
        switch currentColor {
        case gBlack.Y:
            im.SetGray(pos.X, pos.Y, gWhite)
            dir--
        case gWhite.Y:
            im.SetGray(pos.X, pos.Y, gBlack)
            dir++
        }

        // Move the ant
        if dir&1 == 1 {
            pos.X += 1 - dir&2
        } else {
            pos.Y -= 1 - dir&2
        }
    }

    // Check if the ant has moved out of bounds
    if pos.In(bounds) {
        t.Errorf("Expected ant to move out of bounds, but it is at (%d, %d)", pos.X, pos.Y)
    }

    // Verify the final state of the grid
    // This is a simple check to ensure that some cells have changed color
    changed := false
    for y := 0; y < 100; y++ {
        for x := 0; x < 100; x++ {
            if im.At(x, y) != gWhite {
                changed = true
                break
            }
        }
        if changed {
            break
        }
    }

    if !changed {
        t.Error("Expected some cells to have changed color, but none did")
    }
}

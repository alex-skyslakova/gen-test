package main

import (
    "image"
    "image/color"
    "testing"
)

func TestGridInitialization(t *testing.T) {
    bounds := image.Rect(0, 0, 100, 100)
    im := image.NewGray(bounds)
    gWhite := color.Gray{255}

    for y := 0; y < 100; y++ {
        for x := 0; x < 100; x++ {
            if im.At(x, y) != gWhite {
                t.Errorf("Expected all cells to be white, but found non-white cell at (%d, %d)", x, y)
            }
        }
    }
}

func TestAntMovementAndCellColorChange(t *testing.T) {
    bounds := image.Rect(0, 0, 100, 100)
    im := image.NewGray(bounds)
    gBlack := color.Gray{0}
    gWhite := color.Gray{255}
    pos := image.Point{50, 50}
    dir := up

    // Test initial position
    if im.At(pos.X, pos.Y) != gWhite {
        t.Errorf("Expected initial cell to be white, but found non-white cell at (%d, %d)", pos.X, pos.Y)
    }

    // Simulate a few steps
    for i := 0; i < 10; i++ {
        oldPos := pos
        oldDir := dir
        oldColor := im.At(pos.X, pos.Y).(color.Gray).Y

        switch im.At(pos.X, pos.Y).(color.Gray).Y {
        case gBlack.Y:
            im.SetGray(pos.X, pos.Y, gWhite)
            dir--
        case gWhite.Y:
            im.SetGray(pos.X, pos.Y, gBlack)
            dir++
        }

        if dir&1 == 1 {
            pos.X += 1 - dir&2
        } else {
            pos.Y -= 1 - dir&2
        }

        // Verify direction change
        if oldColor == gBlack.Y && dir != (oldDir-1+4)%4 {
            t.Errorf("Expected direction to turn left, but got %d", dir)
        }
        if oldColor == gWhite.Y && dir != (oldDir+1)%4 {
            t.Errorf("Expected direction to turn right, but got %d", dir)
        }

        // Verify cell color change
        if oldColor == gBlack.Y && im.At(oldPos.X, oldPos.Y) != gWhite {
            t.Errorf("Expected cell to change to white, but got %v", im.At(oldPos.X, oldPos.Y))
        }
        if oldColor == gWhite.Y && im.At(oldPos.X, oldPos.Y) != gBlack {
            t.Errorf("Expected cell to change to black, but got %v", im.At(oldPos.X, oldPos.Y))
        }

        // Verify movement
        if oldDir == up && pos.Y != oldPos.Y-1 {
            t.Errorf("Expected ant to move up, but moved to (%d, %d)", pos.X, pos.Y)
        }
        if oldDir == rt && pos.X != oldPos.X+1 {
            t.Errorf("Expected ant to move right, but moved to (%d, %d)", pos.X, pos.Y)
        }
        if oldDir == dn && pos.Y != oldPos.Y+1 {
            t.Errorf("Expected ant to move down, but moved to (%d, %d)", pos.X, pos.Y)
        }
        if oldDir == lt && pos.X != oldPos.X-1 {
            t.Errorf("Expected ant to move left, but moved to (%d, %d)", pos.X, pos.Y)
        }
    }
}

func TestAntStopsAtBoundary(t *testing.T) {
    bounds := image.Rect(0, 0, 100, 100)
    im := image.NewGray(bounds)
    gBlack := color.Gray{0}
    gWhite := color.Gray{255}
    pos := image.Point{0, 0}
    dir := lt

    // Simulate movement until the ant goes out of bounds
    for pos.In(bounds) {
        switch im.At(pos.X, pos.Y).(color.Gray).Y {
        case gBlack.Y:
            im.SetGray(pos.X, pos.Y, gWhite)
            dir--
        case gWhite.Y:
            im.SetGray(pos.X, pos.Y, gBlack)
            dir++
        }

        if dir&1 == 1 {
            pos.X += 1 - dir&2
        } else {
            pos.Y -= 1 - dir&2
        }
    }

    // Verify that the ant stopped at the boundary
    if pos.X >= 0 && pos.X < 100 && pos.Y >= 0 && pos.Y < 100 {
        t.Errorf("Expected ant to stop at boundary, but it moved to (%d, %d)", pos.X, pos.Y)
    }
}

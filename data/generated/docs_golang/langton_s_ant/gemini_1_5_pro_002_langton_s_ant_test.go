package main

import (
	"image"
	"image/color"
	"testing"
)

func TestAntMovement(t *testing.T) {
	bounds := image.Rect(0, 0, 5, 5) // Smaller bounds for testing
	im := image.NewGray(bounds)
	gBlack := color.Gray{0}
	gWhite := color.Gray{255}
	for x := 0; x < bounds.Dx(); x++ {
		for y := 0; y < bounds.Dy(); y++ {
			im.SetGray(x, y, gWhite)
		}
	}

	testCases := []struct {
		initialPos image.Point
		initialDir int
		expectedPos image.Point
		expectedDir int
		expectedColor color.Gray
	}{
		// Test case 1: Ant on white cell, facing up
		{image.Point{2, 2}, up, image.Point{2, 1}, rt, gBlack},
		// Test case 2: Ant on black cell, facing right
		{image.Point{2, 2}, rt, image.Point{3, 2}, up, gWhite},
		// Test case 3: Ant on white cell, facing down
		{image.Point{2, 2}, dn, image.Point{2, 3}, lt, gBlack},
		// Test case 4: Ant on black cell, facing left
		{image.Point{2, 2}, lt, image.Point{1, 2}, dn, gWhite},
		
	}

	for i, tc := range testCases {
		pos := tc.initialPos
		dir := tc.initialDir
		im.SetGray(pos.X, pos.Y, gWhite) // Reset cell color for each test
		
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
		
		if pos != tc.expectedPos {
			t.Errorf("Test case %d: Expected position %v, got %v", i+1, tc.expectedPos, pos)
		}

		if dir != tc.expectedDir{
			t.Errorf("Test case %d: Expected direction %v, got %v", i+1, tc.expectedDir, dir)
		}
		if im.At(tc.expectedPos.X, tc.expectedPos.Y).(color.Gray) != tc.expectedColor{
			t.Errorf("Test case %d: Expected color %v, got %v", i+1, tc.expectedColor, im.At(tc.expectedPos.X, tc.expectedPos.Y).(color.Gray))
		}

		
	}
}

// Test direction wrapping.
func TestDirectionWrapping(t *testing.T) {

    testCases := []struct {
		initialDir int
        expectedDirAfterDecrement int
        expectedDirAfterIncrement int
    }{
        {up, lt, rt},
        {rt, up, dn},
        {dn, rt, lt},
        {lt, dn, up},
    }

    for i, tc := range testCases {
        dirDec := tc.initialDir
        dirInc := tc.initialDir
		
        dirDec--
        dirInc++

		if dirDec != tc.expectedDirAfterDecrement {
			t.Errorf("Test case decrement %d: Expected direction %v, got %v", i+1, tc.expectedDirAfterDecrement, dirDec)
		}

		if dirInc != tc.expectedDirAfterIncrement {
			t.Errorf("Test case increment %d: Expected direction %v, got %v", i+1, tc.expectedDirAfterIncrement, dirInc)
		}
    }
}




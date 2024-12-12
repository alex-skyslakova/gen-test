package main

import (
	"testing"
)

func TestNewBall(t *testing.T) {
	initializeBox()
	b := newBall(centerH, boxH-2)
	if b.x != centerH || b.y != boxH-2 || box[b.y][b.x] != ball {
		t.Errorf("newBall failed. Expected (%d, %d, %c), got (%d, %d, %c)", centerH, boxH-2, ball, b.x, b.y, box[b.y][b.x])
	}

	// Test panic when creating a ball in a non-empty cell
	defer func() {
		if r := recover(); r == nil {
			t.Errorf("The code did not panic when creating a ball in a non-empty cell")
		}
	}()
	newBall(centerH, boxH-2)

}

func TestDoStep(t *testing.T) {
	initializeBox()
	b := newBall(centerH, boxH-2)

	// Test falling into empty space
	b.doStep()
	if b.x != centerH || b.y != boxH-3 || box[b.y][b.x] != ball {
		t.Errorf("doStep failed for empty cell. Expected (%d, %d), got (%d, %d)", centerH, boxH-3, b.x, b.y)
	}

	// Test hitting a pin and falling left
	b.y = boxH - 3
    box[b.y-1][b.x] = pin
    box[b.y-1][b.x-1] = empty
    box[b.y-1][b.x+1] = ball // Force left fall
	b.doStep()

    if b.x != centerH -1 || b.y != boxH-4 || box[b.y][b.x] != ball {
		t.Errorf("doStep failed for pin and left fall. Expected (%d, %d), got (%d, %d)", centerH-1, boxH-4, b.x, b.y)
	}



		// Test hitting a pin and falling right
	b.y = boxH - 3
    box[b.y-1][b.x] = pin
    box[b.y-1][b.x-1] = ball // Force right fall
    box[b.y-1][b.x+1] = empty
	b.doStep()
	if b.x != centerH+2 || b.y != boxH-4 || box[b.y][b.x] != ball {
		t.Errorf("doStep failed for pin and right fall. Expected (%d, %d), got (%d, %d)", centerH+2, boxH-4, b.x, b.y)
	}

	// Test stopping at the bottom
	b.y = 1
	b.doStep()
	if b.y != 0 {
		t.Error("doStep failed at bottom")
	}

    // Test stacking on another ball.
    b.x = centerH
    b.y = boxH - 3
    box[b.y-1][b.x] = ball
    b.doStep()

    if b.x != centerH || b.y != boxH-3 || box[b.y][b.x] != ball {
		t.Errorf("doStep failed when stacking. Expected (%d, %d), got (%d, %d)", centerH, boxH-3, b.x, b.y)
	}

}


func TestInitializeBox(t *testing.T) {
	initializeBox()
	if box[0][0] != corner || box[0][boxW-1] != corner || box[boxH-1][0] != corner || box[boxH-1][boxW-1] != corner {
		t.Error("Corners not initialized correctly")
	}
	for i := 1; i < boxW-1; i++ {
		if box[0][i] != floor || box[boxH-1][i] != floor {
			t.Error("Floor not initialized correctly")
		}
	}
	for i := 1; i < boxH-1; i++ {
		if box[i][0] != wall || box[i][boxW-1] != wall {
			t.Error("Walls not initialized correctly")
		}
	}
	// Check for a pin (simplified check, doesn't check the whole triangle)
    if box[boxH-3][centerH] != pin{
        t.Error("Pins not initialized correctly")
    }
}




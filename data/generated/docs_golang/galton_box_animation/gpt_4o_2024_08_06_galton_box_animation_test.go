package main

import (
    "testing"
    "math/rand"
    "time"
)

func TestInitializeBox(t *testing.T) {
    initializeBox()

    // Check corners
    if box[0][0] != corner || box[0][boxW-1] != corner {
        t.Error("Corners are not set correctly.")
    }

    // Check floor
    for i := 1; i < boxW-1; i++ {
        if box[0][i] != floor {
            t.Errorf("Floor is not set correctly at position %d.", i)
        }
    }

    // Check walls
    for r := 1; r < boxH-1; r++ {
        if box[r][0] != wall || box[r][boxW-1] != wall {
            t.Errorf("Walls are not set correctly at row %d.", r)
        }
    }

    // Check pins
    for nPins := 1; nPins <= pinsBaseW; nPins++ {
        for p := 0; p < nPins; p++ {
            if box[boxH-2-nPins][centerH+1-nPins+p*2] != pin {
                t.Errorf("Pins are not set correctly at row %d, position %d.", boxH-2-nPins, centerH+1-nPins+p*2)
            }
        }
    }
}

func TestNewBall(t *testing.T) {
    initializeBox()
    b := newBall(centerH, boxH-2)

    if b.x != centerH || b.y != boxH-2 {
        t.Error("Ball was not initialized at the correct position.")
    }

    if box[boxH-2][centerH] != ball {
        t.Error("Ball was not placed correctly in the box.")
    }
}

func TestBallMovement(t *testing.T) {
    rand.Seed(time.Now().UnixNano())
    initializeBox()
    b := newBall(centerH, boxH-2)

    // Simulate a few steps
    for i := 0; i < 10; i++ {
        b.doStep()
    }

    // Check that the ball has moved
    if b.y >= boxH-2 {
        t.Error("Ball did not move downwards.")
    }
}

func TestBallBoundaryConditions(t *testing.T) {
    initializeBox()
    b := newBall(centerH, boxH-2)

    // Move the ball to the bottom
    for b.y > 0 {
        b.doStep()
    }

    // Check that the ball stops at the bottom
    if b.y != 0 {
        t.Error("Ball did not stop at the bottom.")
    }
}

func TestMultipleBalls(t *testing.T) {
    initializeBox()
    var balls []*Ball

    // Add multiple balls
    for i := 0; i < 5; i++ {
        balls = append(balls, newBall(centerH, boxH-2))
    }

    // Simulate a few steps
    for step := 0; step < 10; step++ {
        for _, b := range balls {
            b.doStep()
        }
    }

    // Check that all balls have moved
    for _, b := range balls {
        if b.y >= boxH-2 {
            t.Error("One or more balls did not move downwards.")
        }
    }
}

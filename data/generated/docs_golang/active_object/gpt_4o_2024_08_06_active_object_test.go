package main

import (
    "math"
    "testing"
    "time"
)

func TestActiveIntegratorObject(t *testing.T) {
    a := newAio() // Create the active integrator object

    // Set input to sin(2πft) where f = 0.5Hz
    a.input(func(t time.Duration) float64 {
        return math.Sin(t.Seconds() * math.Pi)
    })

    // Wait for 2 seconds
    time.Sleep(2 * time.Second)

    // Set input to constant 0
    a.input(zeroFunc)

    // Wait for 0.5 seconds
    time.Sleep(500 * time.Millisecond)

    // Get the output
    output := a.output()

    // Verify that the output is approximately 0
    if math.Abs(output) > 0.01 { // Allow a small margin of error
        t.Errorf("Expected output to be approximately 0, but got %f", output)
    }
}

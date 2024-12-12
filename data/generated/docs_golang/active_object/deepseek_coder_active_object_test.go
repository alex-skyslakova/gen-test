package main

import (
    "math"
    "testing"
    "time"
)

func TestActiveIntegrator(t *testing.T) {
    // Create a new active integrator object
    a := newAio()

    // Set the input to sin(2π f t) with f = 0.5 Hz
    a.input(func(t time.Duration) float64 {
        return math.Sin(2 * math.Pi * 0.5 * t.Seconds())
    })

    // Wait for 2 seconds
    time.Sleep(2 * time.Second)

    // Set the input to a constant 0
    a.input(zeroFunc)

    // Wait for 0.5 seconds
    time.Sleep(0.5 * time.Second)

    // Query the output
    output := a.output()

    // The output should be approximately 0, as the sine function has a period of 2 seconds
    // We allow a small tolerance due to potential inaccuracies in time slicing and clock precision
    const tolerance = 0.01
    if math.Abs(output) > tolerance {
        t.Errorf("Expected output to be near 0, but got %f", output)
    }
}

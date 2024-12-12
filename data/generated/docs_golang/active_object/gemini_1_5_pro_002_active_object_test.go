package main

import (
	"math"
	"testing"
	"time"
)

func TestAio(t *testing.T) {
	a := newAio() // create object
	f := 0.5      // frequency

	a.input(func(t time.Duration) float64 { // 1. set input to sin function
		return math.Sin(2 * math.Pi * f * t.Seconds())
	})
	time.Sleep(2 * time.Second) // 2. sleep 2 sec
	a.input(zeroFunc)           // 3. set input to zero function
	time.Sleep(time.Second / 2) // 4. sleep .5 sec

	output := a.output()
	if math.Abs(output) > 0.05 { // Allow some tolerance due to timing variations
		t.Errorf("Expected output near zero, got %f", output)
	}
}

// Test with a longer integration time to increase confidence.
func TestAioLongerIntegration(t *testing.T) {
	a := newAio() // create object
	f := 0.5      // frequency

	a.input(func(t time.Duration) float64 { // 1. set input to sin function
		return math.Sin(2 * math.Pi * f * t.Seconds())
	})
	time.Sleep(10 * time.Second) // Integrate over multiple periods
	a.input(zeroFunc)            // 3. set input to zero function
	time.Sleep(time.Second / 2)  // 4. sleep .5 sec

	output := a.output()

	if math.Abs(output) > 0.05 { // Allow some tolerance due to timing variations
		t.Errorf("Expected output near zero after longer integration, got %f", output)
	}
}

// Test with a constant input to check basic integration functionality.
func TestAioConstantInput(t *testing.T) {
	a := newAio() // create object
	const inputValue = 2.0
	expectedOutput := inputValue * 2.5 //  inputValue * (2s + 0.5s)

	a.input(func(t time.Duration) float64 {
		return inputValue
	})
	time.Sleep(2 * time.Second)
	a.input(zeroFunc)
	time.Sleep(time.Second / 2)
	output := a.output()

	tolerance := 0.05

	if math.Abs(output-expectedOutput) > tolerance {
		t.Errorf("Expected output near %f, got %f", expectedOutput, output)
	}

}

// Test output method without prior input.
func TestAioInitialOutput(t *testing.T) {
	a := newAio()
	output := a.output()
	if output != 0.0 {
		t.Errorf("Expected initial output to be 0, got %f", output)
	}
}



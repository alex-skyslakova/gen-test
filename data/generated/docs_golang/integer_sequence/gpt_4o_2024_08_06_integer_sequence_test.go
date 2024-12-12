package main

import (
	"testing"
	"time"
)

// TestIntegerSequence tests the integer sequence generation.
func TestIntegerSequence(t *testing.T) {
	// Create a channel to receive integers
	intChan := make(chan int)

	// Run the sequence generator in a separate goroutine
	go func() {
		for i := 1; ; i++ {
			intChan <- i
		}
	}()

	// Test that the first few integers are correct
	expected := []int{1, 2, 3, 4, 5}
	for _, exp := range expected {
		select {
		case val := <-intChan:
			if val != exp {
				t.Errorf("Expected %d, but got %d", exp, val)
			}
		case <-time.After(1 * time.Second):
			t.Error("Test timed out waiting for integer sequence")
		}
	}

	// Close the channel to stop the goroutine
	close(intChan)
}

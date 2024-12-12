package main

import (
	"testing"
)

func TestMain(t *testing.T) {
	defer func() {
		if r := recover(); r != nil {
			if r != 42 {
				t.Errorf("Expected panic with value 42, got %v", r)
			}
		} else {
			t.Error("Expected panic, but none occurred")
		}
	}()

	main() // Call the main function to trigger the panic
}

func TestMainPositive(t *testing.T) {
        x := 42
        if x != 42 {
                t.Errorf("Expected x to be 42 but got %v", x)

        }
}

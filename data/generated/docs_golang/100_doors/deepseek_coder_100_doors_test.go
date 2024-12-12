package main

import (
    "testing"
)

func Test100Doors(t *testing.T) {
    // Initialize the doors array
    doors := [100]bool{}

    // Perform the 100 passes
    for pass := 1; pass <= 100; pass++ {
        for door := pass-1; door < 100; door += pass {
            doors[door] = !doors[door]
        }
    }

    // Expected result: Only doors with numbers that are perfect squares should be open
    expectedOpenDoors := []int{0, 1, 4, 9, 16, 25, 36, 49, 64, 81}

    // Check the state of each door
    for i := 0; i < 100; i++ {
        if contains(expectedOpenDoors, i) {
            if !doors[i] {
                t.Errorf("Door %d should be open but is closed", i+1)
            }
        } else {
            if doors[i] {
                t.Errorf("Door %d should be closed but is open", i+1)
            }
        }
    }
}

// Helper function to check if a slice contains a specific element
func contains(slice []int, element int) bool {
    for _, item := range slice {
        if item == element {
            return true
        }
    }
    return false
}

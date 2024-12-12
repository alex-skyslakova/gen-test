package main

import (
    "reflect"
    "testing"
)

// TestDeepCopy tests the deepcopy method of the cds struct
func TestDeepCopy(t *testing.T) {
    // Create and populate a structure
    original := &cds{
        i: 1,
        s: "one",
        b: []byte("unit"),
        m: map[int]bool{1: true},
    }

    // Perform a deep copy
    copy := original.deepcopy()

    // Verify that the original and the copy are not the same instance
    if original == copy {
        t.Errorf("Expected original and copy to be different instances")
    }

    // Verify that the copied values are the same as the original
    if !reflect.DeepEqual(original, copy) {
        t.Errorf("Expected original and copy to be equal, got original: %v, copy: %v", original, copy)
    }

    // Modify the original structure
    original.i = 0
    original.s = "nil"
    copy(original.b, "zero")
    original.m[1] = false

    // Verify that the copy is unaffected by changes to the original
    if reflect.DeepEqual(original, copy) {
        t.Errorf("Expected original and copy to be different after modification, got original: %v, copy: %v", original, copy)
    }

    // Verify that the copy still holds the original values
    expectedCopy := &cds{
        i: 1,
        s: "one",
        b: []byte("unit"),
        m: map[int]bool{1: true},
    }

    if !reflect.DeepEqual(copy, expectedCopy) {
        t.Errorf("Expected copy to hold original values, got: %v, expected: %v", copy, expectedCopy)
    }
}

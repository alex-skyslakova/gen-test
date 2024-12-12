package main

import (
	"fmt"
	"testing"
)

func TestDoublyLinkedListTraversal(t *testing.T) {
	dll := &dlList{}

	// Test empty list
	if dll.String() != "<nil>" {
		t.Errorf("Expected empty list string representation <nil>, got %s", dll.String())
	}

	a := &dlNode{string: "A"}
	dll.insertTail(a)
	if dll.String() != "[A]" {
		t.Errorf("Expected list string representation [A], got %s", dll.String())
	}


	dll.insertTail(&dlNode{string: "B"})
	if dll.String() != "[A B]" {
		t.Errorf("Expected list string representation [A B], got %s", dll.String())
	}

	dll.insertAfter(a, &dlNode{string: "C"})
	if dll.String() != "[A C B]" {
		t.Errorf("Expected list string representation [A C B], got %s", dll.String())
	}


	// Test forward traversal
	expectedForward := []string{"A", "C", "B"}
	var actualForward []string
	for p := dll.head; p != nil; p = p.next {
		actualForward = append(actualForward, p.string)
	}
	if !slicesEqual(actualForward, expectedForward) {
		t.Errorf("Expected forward traversal %v, got %v", expectedForward, actualForward)
	}

	// Test backward traversal
	expectedBackward := []string{"B", "C", "A"}
	var actualBackward []string
	for p := dll.tail; p != nil; p = p.prev {
		actualBackward = append(actualBackward, p.string)
	}
	if !slicesEqual(actualBackward, expectedBackward) {
		t.Errorf("Expected backward traversal %v, got %v", expectedBackward, actualBackward)
	}


	// Test backward traversal from middle
    expectedBackwardMiddle := []string{"C", "A"}
    var actualBackwardMiddle []string
    for p := a.next; p != nil; p = p.prev {
        actualBackwardMiddle = append(actualBackwardMiddle, p.string)
    }
    if !slicesEqual(actualBackwardMiddle, expectedBackwardMiddle) {
        t.Errorf("Expected backward traversal from middle %v, got %v", expectedBackwardMiddle, actualBackwardMiddle)
    }
}



func slicesEqual(a, b []string) bool {
	if len(a) != len(b) {
		return false
	}
	for i, v := range a {
		if v != b[i] {
			return false
		}
	}
	return true
}




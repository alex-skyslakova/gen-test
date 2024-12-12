package main

import (
	"testing"
)

// TestFunc1 tests the func1 function by passing func2 as an argument.
func TestFunc1(t *testing.T) {
	expected := "func2 called with a string"
	result := func1(func2)

	if result != expected {
		t.Errorf("Expected %s but got %s", expected, result)
	}
}

// TestFunc2 tests the func2 function directly.
func TestFunc2(t *testing.T) {
	input := "test input"
	expected := "func2 called with test input"
	result := func2(input)

	if result != expected {
		t.Errorf("Expected %s but got %s", expected, result)
	}
}

// TestFunc1WithAnonymousFunction tests the func1 function by passing an anonymous function as an argument.
func TestFunc1WithAnonymousFunction(t *testing.T) {
	anonymousFunc := func(s string) string {
		return "anonymous function called with " + s
	}

	expected := "anonymous function called with a string"
	result := func1(anonymousFunc)

	if result != expected {
		t.Errorf("Expected %s but got %s", expected, result)
	}
}

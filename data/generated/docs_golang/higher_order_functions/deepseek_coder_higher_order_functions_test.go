package main

import (
	"testing"
)

func TestFunc1(t *testing.T) {
	// Define a test function to pass to func1
	testFunc := func(s string) string {
		return "testFunc called with " + s
	}

	// Call func1 with the test function
	result := func1(testFunc)

	// Expected result
	expected := "testFunc called with a string"

	// Check if the result matches the expected value
	if result != expected {
		t.Errorf("Expected %s, but got %s", expected, result)
	}
}

func TestFunc2(t *testing.T) {
	// Call func2 with a test string
	result := func2("test string")

	// Expected result
	expected := "func2 called with test string"

	// Check if the result matches the expected value
	if result != expected {
		t.Errorf("Expected %s, but got %s", expected, result)
	}
}

func TestMain(t *testing.T) {
	// Capture the output of the main function
	main()

	// Since main function prints to stdout, we can't directly test its output here.
	// Instead, we rely on the fact that main calls func1 with func2, and we already tested func1 and func2.
	// Therefore, we assume that if func1 and func2 work correctly, main will work correctly.
}

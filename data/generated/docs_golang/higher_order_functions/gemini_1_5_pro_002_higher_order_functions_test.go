package main

import "testing"

func TestFunc1(t *testing.T) {
	expected := "func2 called with a string"
	actual := func1(func2)
	if actual != expected {
		t.Errorf("Expected: %s, Actual: %s", expected, actual)
	}
}

func TestFunc1WithDifferentFunction(t *testing.T) {
	func3 := func(s string) string { return "func3 called with " + s }
	expected := "func3 called with a string"
	actual := func1(func3)
	if actual != expected {
		t.Errorf("Expected: %s, Actual: %s", expected, actual)
	}
}

func TestFunc1WithEmptyInput(t *testing.T) {
    func4 := func(s string) string { return "" }
    expected := ""
    actual := func1(func4)
    if actual != expected {
        t.Errorf("Expected: %s, Actual: %s", expected, actual)
    }
}

// Test to verify behavior with nil function.
// In Go, nil can be passed for function parameters but attempting to call them will result in a panic.
func TestFunc1WithNilFunction(t *testing.T) {
	defer func() {
		if r := recover(); r == nil {
			t.Errorf("Expected a panic but did not get one.")
		}
	}()
	var nilFunc func(string) string
	func1(nilFunc)
}




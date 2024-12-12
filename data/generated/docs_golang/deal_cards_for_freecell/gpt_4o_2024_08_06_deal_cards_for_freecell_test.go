package main

import (
	"testing"
)

// Test the random number generator
func TestRnd(t *testing.T) {
	seed = 1
	expected := []int{32767, 10174, 27926, 3035, 22861}
	for i, exp := range expected {
		if result := rnd(); result != exp {
			t.Errorf("rnd() test %d failed: expected %d, got %d", i+1, exp, result)
		}
	}
}

// Test the deal function for a specific game number
func TestDeal(t *testing.T) {
	seed = 1
	expected := []int{
		35, 13, 37, 36, 17, 27, 23, 18,
		51, 50, 38, 21, 0, 45, 49, 11,
		8, 48, 39, 25, 9, 1, 3, 10,
		16, 12, 33, 44, 15, 2, 4, 26,
		7, 34, 5, 30, 19, 6, 28, 24,
		31, 46, 47, 29, 32, 22, 20, 14,
		40, 41, 42, 43,
	}
	result := deal(1)
	for i, exp := range expected {
		if result[i] != exp {
			t.Errorf("deal(1) test failed at index %d: expected %d, got %d", i, exp, result[i])
		}
	}
}

// Test the main function with a valid game number
func TestMainValidGame(t *testing.T) {
	// Simulate command line argument for game number 1
	os.Args = []string{"cmd", "1"}
	main()
	// No assertions here, just ensure no panic or error occurs
}

// Test the main function with an invalid game number
func TestMainInvalidGame(t *testing.T) {
	// Simulate command line argument for an invalid game number
	os.Args = []string{"cmd", "32001"}
	main()
	// No assertions here, just ensure no panic or error occurs
}

// Test the main function with no arguments
func TestMainNoArgs(t *testing.T) {
	// Simulate no command line arguments
	os.Args = []string{"cmd"}
	main()
	// No assertions here, just ensure no panic or error occurs
}

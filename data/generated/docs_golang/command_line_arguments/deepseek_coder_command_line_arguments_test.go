package main

import (
	"os"
	"testing"
)

func TestMain(t *testing.T) {
	// Save the original os.Args
	originalArgs := os.Args
	defer func() { os.Args = originalArgs }()

	// Test case 1: No arguments
	os.Args = []string{"myprogram"}
	main()

	// Test case 2: Single argument
	os.Args = []string{"myprogram", "alpha"}
	main()

	// Test case 3: Multiple arguments
	os.Args = []string{"myprogram", "alpha", "beta", "gamma"}
	main()

	// Test case 4: Arguments with spaces
	os.Args = []string{"myprogram", "-c", "alpha beta", "-h", "gamma"}
	main()
}

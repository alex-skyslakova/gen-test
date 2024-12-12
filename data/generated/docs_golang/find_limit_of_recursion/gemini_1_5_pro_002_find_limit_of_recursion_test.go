package main

import (
	"fmt"
	"os"
	"runtime/debug"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestRecursionLimit(t *testing.T) {
	originalStackSize := debug.SetMaxStack(1024 * 1024) // Set a small stack size for testing
	defer debug.SetMaxStack(originalStackSize)          // Restore original stack size

	// Redirect stdout to capture printed output
	rescueStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

	// Use a closure to wrap the recursive call and recover from the panic
	var lastPrinted int
	func() {
		defer func() {
			if r := recover(); r != nil {
				// Read the captured output
				w.Close()
				out, _ := os.ReadFile(r.(*os.File).Name())
				os.Stdout = rescueStdout

				// Parse the last printed number
				fmt.Sscanf(string(out), "%d", &lastPrinted)
			}
		}()
		r(1) // Start the recursive call
	}()

	// Assert that the recursion reached a certain depth (exact number varies depending on environment)
	assert.Greater(t, lastPrinted, 1000, "Recursion should reach a depth of at least 1000 before panicing")

	// Clean up temporary files
	os.Remove(r.(*os.File).Name())
}



// Test with a specified (small) stack size to ensure a limit is reached.
func TestRecursionLimitWithStackSize(t *testing.T) {

    originalStackSize := debug.SetMaxStack(1024 * 32) // Set a very small stack size for testing
    defer debug.SetMaxStack(originalStackSize)

	rescueStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

    var lastPrinted int
    func() {
        defer func() {
            if r := recover(); r != nil {
				w.Close()
				out, _ := os.ReadFile(r.(*os.File).Name())
				os.Stdout = rescueStdout

				fmt.Sscanf(string(out), "%d", &lastPrinted)
            }
        }()
		r(1)
    }()

	assert.Greater(t, lastPrinted, 1000, "Recursion should reach a depth of at least 1000 before panicing")
    assert.Less(t, lastPrinted, 15000,  "Recursion should reach a depth of less than 15000 given small stack size, this test ensures a limit is reached") // Adjust this value if necessary

    os.Remove(r.(*os.File).Name())
}


// Test main function parsing stack size flag.

func TestMainWithStackSizeFlag(t *testing.T) {

	originalStackSize := debug.SetMaxStack(1024 * 32) // Set a very small stack size for testing
    defer debug.SetMaxStack(originalStackSize)



	if os.Getenv("TEST_MAIN") == "1" {
		os.Args = []string{"program", "-stack", "2048"} // Example stack size.
		main()
		return
	}


	cmd := exec.Command(os.Args[0], "-test.run=TestMainWithStackSizeFlag")
	cmd.Env = append(os.Environ(), "TEST_MAIN=1")

	err := cmd.Run()

    //  The main function, when it panics, will have a non-zero exit code.
	// Expecting a panic/crash here with small stack size, and we don't intercept the output.
	assert.Error(t, err) 


}


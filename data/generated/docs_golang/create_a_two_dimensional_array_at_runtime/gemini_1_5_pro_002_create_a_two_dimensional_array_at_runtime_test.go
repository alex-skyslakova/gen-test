package main

import (
	"bufio"
	"bytes"
	"fmt"
	"os"
	"strings"
	"testing"
)

func Test_main(t *testing.T) {
	testCases := []struct {
		input  string
		output string
	}{
		{"2 3", "a[0][0] = 0\na[1][2] = 7\n"},
		{"1 1", "a[0][0] = 0\na[0][0] = 7\n"},
		{"4 5", "a[0][0] = 0\na[3][4] = 7\n"},
	}

	for _, tc := range testCases {
		t.Run(tc.input, func(t *testing.T) {
			input := strings.NewReader(tc.input)
			output := new(bytes.Buffer)

			// Redirect standard input and output
			os.Stdin = input
			os.Stdout = output

			main() // Call the main function

			// Check the output
			if output.String() != tc.output {
				t.Errorf("Expected output:\n%s\nGot:\n%s", tc.output, output.String())
			}
		})
	}

    // Test for invalid input (non-numeric)
    t.Run("Invalid input", func(t *testing.T) {
        input := strings.NewReader("a b")
        output := new(bytes.Buffer)

        // Redirect standard input and output
        os.Stdin = input
        os.Stdout = output

        // Mock os.Exit to prevent the test from exiting prematurely
        oldOsExit := os.Exit
        defer func() { os.Exit = oldOsExit }()
        var gotExitCode int
        os.Exit = func(code int) { gotExitCode = code }


        // Override fmt.Scan to simulate invalid input behavior.
		origScan := fmt.Scan
		defer func() { fmt.Scan = origScan }()


		fmt.Scan = func(a ...interface{}) (n int, err error) {
			return 0, fmt.Errorf("scan error") // Simulate a scan error
		}


        main()

		if gotExitCode != 0{
			return // As the original program calls os.Exit in case of error, this is the expected behavior
		}
        t.Errorf("Expected an error for invalid input, but execution continued.")
    })
}

// Helper function to mock fmt.Scan for simulating specific error conditions
func mockScanForError(input string) {
	origScan := fmt.Scan
	defer func() { fmt.Scan = origScan }()

	fmt.Scan = func(a ...interface{}) (n int, err error) {
		scanner := bufio.NewScanner(strings.NewReader(input))
		for _, arg := range a {
			if !scanner.Scan() {
				return 0, fmt.Errorf("not enough input arguments")
			}
			_, err := fmt.Sscan(scanner.Text(), arg)
			if err != nil {
				return 0, fmt.Errorf("invalid input format") // Could be more specific
			}
		}
		return len(a), nil
	}
}

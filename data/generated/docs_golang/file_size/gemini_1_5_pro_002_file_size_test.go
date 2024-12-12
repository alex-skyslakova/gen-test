package main

import (
	"fmt"
	"os"
	"testing"
)

func TestPrintFileSize(t *testing.T) {
	// Create dummy files for testing
	cwdFile, err := os.Create("input.txt")
	if err != nil {
		t.Fatal(err)
	}
	defer os.Remove("input.txt")

	rootFile, err := os.Create("/tmp/input.txt") // Use /tmp instead of root for test
	if err != nil {
		t.Fatal(err)
	}
	defer os.Remove("/tmp/input.txt")


	// Write some data to the files to ensure they have a size
	cwdFile.WriteString("test data")
	cwdFile.Close()

    rootFile.WriteString("more test data")
    rootFile.Close()


	// Redirect stdout to capture output
	rescueStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

	// Test cases
	testCases := []struct {
		filename string
		expected string // Contains expected substring in the output
	}{
		{"input.txt", "9"}, // Size of cwd file
		{"/tmp/input.txt", "14"},  // Size of root file  (using /tmp for test)
		{"nonexistent.txt", "no such file or directory"}, // File not found
	}



	for _, tc := range testCases {
		t.Run(tc.filename, func(t *testing.T) {
			printFileSize(tc.filename)

			w.Close()
			out, _ := os.ReadFile(r.Name())
			os.Stdout = rescueStdout

			output := string(out)


            if !contains(output, tc.expected){
                t.Errorf("For %s, expected output to contain '%s', but got '%s'", tc.filename, tc.expected, output)
            }


		})
	}
}


func contains(s, substr string) bool {
    return Contains(s, substr)
}

// Contains reports whether substr is within s.
func Contains(s, substr string) bool {
    for i := 0; i+len(substr) <= len(s); i++ {
        if s[i:i+len(substr)] == substr {
            return true
        }
    }
    return false
}





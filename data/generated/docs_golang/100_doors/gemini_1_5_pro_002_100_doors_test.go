package main

import (
	"fmt"
	"testing"
)

func TestDoorStates(t *testing.T) {
	doors := [100]bool{}

	for pass := 1; pass <= 100; pass++ {
		for door := pass - 1; door < 100; door += pass {
			doors[door] = !doors[door]
		}
	}

	expected := [100]bool{}
	for i := 1; i*i <= 100; i++ {
		expected[i*i-1] = true
	}

	for i := 0; i < 100; i++ {
		if doors[i] != expected[i] {
			t.Errorf("Door %d state mismatch: expected %t, got %t", i+1, expected[i], doors[i])
		}
	}
}


func TestPrintOutput(t *testing.T) {
        doors := [100]bool{}

        for pass := 1; pass <= 100; pass++ {
                for door := pass - 1; door < 100; door += pass {
                        doors[door] = !doors[door]
                }
        }
	
	expectedOutput := getExpectedOutput()

        gotOutput := captureOutput(func() {
                // Simulate the printing part of the main function
                for i, v := range doors {
                        if v {
                                fmt.Print("1")
                        } else {
                                fmt.Print("0")
                        }

                        if i%10 == 9 {
                                fmt.Print("\n")
                        } else {
                                fmt.Print(" ")
                        }
                }
        })

	if expectedOutput != gotOutput{
		t.Errorf("Output mismatch:\nExpected:\n%s\nGot:\n%s", expectedOutput, gotOutput)
	}
}


func captureOutput(f func()) string {
        // Capture standard output during the execution of f
	originalStdout := fmt.Printf // Preserve the original function

        r, w, _ := os.Pipe()
        fmt.Printf = func(format string, a ...interface{}) int {
		return fmt.Fprintf(w, format, a...) // Redirect to our writer
        }
        defer func() {
                fmt.Printf = originalStdout // Restore the original function
        }()
	f()
	w.Close()
        var buf bytes.Buffer
	io.Copy(&buf, r) // Read everything from piped writer
	return buf.String()
}


func getExpectedOutput() string {
	// Calculate and generate the expected output string
	doors := [100]bool{}

        for pass := 1; pass <= 100; pass++ {
                for door := pass - 1; door < 100; door += pass {
                        doors[door] = !doors[door]
                }
        }

	var expectedOutput string
        for i, v := range doors {
                if v {
                        expectedOutput += "1"
                } else {
                        expectedOutput += "0"
                }

                if i%10 == 9 {
                        expectedOutput += "\n"
                } else {
                        expectedOutput += " "
                }
        }

	return expectedOutput
}

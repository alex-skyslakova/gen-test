package main

import (
    "fmt"
    "testing"
)

// TestFoo tests the behavior of the foo function.
func TestFoo(t *testing.T) {
    // Capture the output to verify the trace messages.
    output := captureOutput(func() {
        foo()
    })

    // Expected trace messages for the first call to bar (U0 exception).
    expectedFirstCall := []string{
        "foo: start",
        "bar: start",
        "baz: start",
        "baz: panicking with execption U0",
        "baz: Panic mode!",
        "baz: handling exception",
        "baz: U0 handled",
        "baz: panic over",
        "foo: complete",
    }

    // Expected trace messages for the second call to bar (U1 exception).
    expectedSecondCall := []string{
        "foo: start",
        "bar: start",
        "baz: start",
        "baz: panicking with execption U1",
        "baz: Panic mode!",
        "baz: can't recover this one!",
    }

    // Verify the first call to bar.
    verifyTrace(t, output, expectedFirstCall)

    // Verify the second call to bar.
    verifyTrace(t, output, expectedSecondCall)
}

// captureOutput captures the output of the function passed to it.
func captureOutput(f func()) string {
    old := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    f()

    w.Close()
    os.Stdout = old

    var buf bytes.Buffer
    io.Copy(&buf, r)
    return buf.String()
}

// verifyTrace verifies that the actual output matches the expected trace messages.
func verifyTrace(t *testing.T, output string, expected []string) {
    lines := strings.Split(output, "\n")
    for i, line := range expected {
        if i >= len(lines) {
            t.Errorf("Expected trace message '%s' not found", line)
            continue
        }
        if !strings.Contains(lines[i], line) {
            t.Errorf("Expected trace message '%s', got '%s'", line, lines[i])
        }
    }
}

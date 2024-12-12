package main

import (
    "testing"
    "strings"
)

// Mock trace function to capture output for testing
var traceOutput []string

func mockTrace(s string) {
    traceOutput = append(traceOutput, s)
}

func TestExceptionHandling(t *testing.T) {
    // Replace the trace function with the mockTrace
    originalTrace := trace
    trace = mockTrace
    defer func() { trace = originalTrace }() // Restore original trace function after test

    // Clear previous trace output
    traceOutput = []string{}

    // Run the main function which includes the foo function
    main()

    // Expected trace output
    expectedOutput := []string{
        "start",
        "foo: start",
        "bar: start",
        "baz: start",
        "baz: panicking with execption U0",
        "Panic mode!",
        "handling exception",
        "U0 handled",
        "panic over",
        "complete",
        "bar: start",
        "baz: start",
        "baz: panicking with execption U1",
        "Panic mode!",
        "can't recover this one!",
    }

    // Check if the trace output matches the expected output
    for i, expected := range expectedOutput {
        if i >= len(traceOutput) {
            t.Errorf("Missing trace output: %s", expected)
            continue
        }
        if !strings.Contains(traceOutput[i], expected) {
            t.Errorf("Expected trace output '%s', but got '%s'", expected, traceOutput[i])
        }
    }

    // Check if there are extra outputs
    if len(traceOutput) > len(expectedOutput) {
        for _, extra := range traceOutput[len(expectedOutput):] {
            t.Errorf("Unexpected extra trace output: %s", extra)
        }
    }
}

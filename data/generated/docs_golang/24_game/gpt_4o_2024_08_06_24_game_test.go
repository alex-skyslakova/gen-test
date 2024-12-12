package main

import (
    "bytes"
    "io"
    "math/rand"
    "os"
    "strings"
    "testing"
    "time"
)

func TestMain(t *testing.T) {
    // Seed the random number generator for reproducibility
    rand.Seed(1)

    // Redirect stdout to capture output
    originalStdout := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    // Redirect stdin to simulate user input
    originalStdin := os.Stdin
    input := "123+*"
    rIn, wIn, _ := os.Pipe()
    wIn.WriteString(input)
    wIn.Close()
    os.Stdin = rIn

    // Run the main function
    main()

    // Restore stdin
    os.Stdin = originalStdin

    // Capture and restore stdout
    w.Close()
    var buf bytes.Buffer
    io.Copy(&buf, r)
    os.Stdout = originalStdout

    output := buf.String()

    // Check if the output contains the expected numbers
    if !strings.Contains(output, "Your numbers: 1234") {
        t.Errorf("Expected numbers not found in output: %s", output)
    }

    // Check if the output contains the expected result
    if !strings.Contains(output, "incorrect.") {
        t.Errorf("Expected 'incorrect.' in output: %s", output)
    }
}

func TestInvalidExpressionLength(t *testing.T) {
    rand.Seed(1)

    originalStdout := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    originalStdin := os.Stdin
    input := "12+"
    rIn, wIn, _ := os.Pipe()
    wIn.WriteString(input)
    wIn.Close()
    os.Stdin = rIn

    main()

    os.Stdin = originalStdin

    w.Close()
    var buf bytes.Buffer
    io.Copy(&buf, r)
    os.Stdout = originalStdout

    output := buf.String()

    if !strings.Contains(output, "invalid. expression length must be 7.") {
        t.Errorf("Expected 'invalid. expression length must be 7.' in output: %s", output)
    }
}

func TestTooManyNumbers(t *testing.T) {
    rand.Seed(1)

    originalStdout := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    originalStdin := os.Stdin
    input := "12345++"
    rIn, wIn, _ := os.Pipe()
    wIn.WriteString(input)
    wIn.Close()
    os.Stdin = rIn

    main()

    os.Stdin = originalStdin

    w.Close()
    var buf bytes.Buffer
    io.Copy(&buf, r)
    os.Stdout = originalStdout

    output := buf.String()

    if !strings.Contains(output, "too many numbers.") {
        t.Errorf("Expected 'too many numbers.' in output: %s", output)
    }
}

func TestWrongNumbers(t *testing.T) {
    rand.Seed(1)

    originalStdout := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    originalStdin := os.Stdin
    input := "567+*"
    rIn, wIn, _ := os.Pipe()
    wIn.WriteString(input)
    wIn.Close()
    os.Stdin = rIn

    main()

    os.Stdin = originalStdin

    w.Close()
    var buf bytes.Buffer
    io.Copy(&buf, r)
    os.Stdout = originalStdout

    output := buf.String()

    if !strings.Contains(output, "wrong numbers.") {
        t.Errorf("Expected 'wrong numbers.' in output: %s", output)
    }
}

func TestInvalidExpressionSyntax(t *testing.T) {
    rand.Seed(1)

    originalStdout := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    originalStdin := os.Stdin
    input := "1+"
    rIn, wIn, _ := os.Pipe()
    wIn.WriteString(input)
    wIn.Close()
    os.Stdin = rIn

    main()

    os.Stdin = originalStdin

    w.Close()
    var buf bytes.Buffer
    io.Copy(&buf, r)
    os.Stdout = originalStdout

    output := buf.String()

    if !strings.Contains(output, "invalid expression syntax.") {
        t.Errorf("Expected 'invalid expression syntax.' in output: %s", output)
    }
}

func TestInvalidOperator(t *testing.T) {
    rand.Seed(1)

    originalStdout := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    originalStdin := os.Stdin
    input := "1234&"
    rIn, wIn, _ := os.Pipe()
    wIn.WriteString(input)
    wIn.Close()
    os.Stdin = rIn

    main()

    os.Stdin = originalStdin

    w.Close()
    var buf bytes.Buffer
    io.Copy(&buf, r)
    os.Stdout = originalStdout

    output := buf.String()

    if !strings.Contains(output, "& invalid.") {
        t.Errorf("Expected '& invalid.' in output: %s", output)
    }
}

func TestCorrectExpression(t *testing.T) {
    rand.Seed(1)

    originalStdout := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    originalStdin := os.Stdin
    input := "342*+"
    rIn, wIn, _ := os.Pipe()
    wIn.WriteString(input)
    wIn.Close()
    os.Stdin = rIn

    main()

    os.Stdin = originalStdin

    w.Close()
    var buf bytes.Buffer
    io.Copy(&buf, r)
    os.Stdout = originalStdout

    output := buf.String()

    if !strings.Contains(output, "correct.") {
        t.Errorf("Expected 'correct.' in output: %s", output)
    }
}

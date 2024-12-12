package main

import (
    "bytes"
    "fmt"
    "math"
    "os"
    "testing"
)

// Mock function to replace fmt.Scan for testing
func mockScan(inputs []string) func(a ...interface{}) (n int, err error) {
    var index int
    return func(a ...interface{}) (n int, err error) {
        if index < len(inputs) {
            fmt.Sscan(inputs[index], a...)
            index++
            return 1, nil
        }
        return 0, fmt.Errorf("no more input")
    }
}

func TestMainFunction(t *testing.T) {
    // Save original functions
    originalScan := fmt.Scan
    originalExit := os.Exit

    // Mock fmt.Scan and os.Exit
    fmt.Scan = mockScan([]string{"4.95", "150.5", "150"})
    os.Exit = func(code int) {
        if code != 0 {
            t.Fatalf("Unexpected exit with code %d", code)
        }
    }

    // Capture output
    var buf bytes.Buffer
    fmtPrint := fmt.Print
    fmtPrintf := fmt.Printf
    fmtPrintln := fmt.Println
    fmt.Print = func(a ...interface{}) (n int, err error) {
        return fmt.Fprint(&buf, a...)
    }
    fmt.Printf = func(format string, a ...interface{}) (n int, err error) {
        return fmt.Fprintf(&buf, format, a...)
    }
    fmt.Println = func(a ...interface{}) (n int, err error) {
        return fmt.Fprintln(&buf, a...)
    }

    // Run main function
    main()

    // Restore original functions
    fmt.Scan = originalScan
    os.Exit = originalExit
    fmt.Print = fmtPrint
    fmt.Printf = fmtPrintf
    fmt.Println = fmtPrintln

    // Expected output
    expectedOutput := `
    sine of latitude:    -0.08682408883346517
    diff longitude:      0.5

Hour, sun hour angle, dial hour line angle from 6am to 6pm
-6   -90.500   -5.002
-5   -75.500   -4.166
-4   -60.500   -3.333
-3   -45.500   -2.504
-2   -30.500   -1.679
-1   -15.500   -0.858
 0     0.500    0.000
 1    15.500    0.858
 2    30.500    1.679
 3    45.500    2.504
 4    60.500    3.333
 5    75.500    4.166
 6    90.500    5.002
`

    // Compare captured output with expected output
    if buf.String() != expectedOutput {
        t.Errorf("Unexpected output:\n%s\nExpected:\n%s", buf.String(), expectedOutput)
    }
}

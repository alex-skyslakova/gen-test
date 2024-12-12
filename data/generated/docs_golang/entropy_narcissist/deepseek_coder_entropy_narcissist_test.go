package main

import (
    "io/ioutil"
    "math"
    "os"
    "testing"
)

func TestEntropy(t *testing.T) {
    tests := []struct {
        name     string
        content  string
        expected float64
    }{
        {
            name:     "Empty file",
            content:  "",
            expected: 0.0,
        },
        {
            name:     "Uniform bytes",
            content:  "AAAA",
            expected: 0.0,
        },
        {
            name:     "Varying bytes",
            content:  "ABAB",
            expected: 1.0,
        },
        {
            name:     "Complex content",
            content:  "Hello, World!",
            expected: 3.18083298723772, // Approximate entropy for this string
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            // Create a temporary file with the test content
            tmpFile, err := ioutil.TempFile("", "entropy_test_*.txt")
            if err != nil {
                t.Fatalf("Failed to create temp file: %v", err)
            }
            defer os.Remove(tmpFile.Name())

            if _, err := tmpFile.Write([]byte(tt.content)); err != nil {
                t.Fatalf("Failed to write to temp file: %v", err)
            }

            if err := tmpFile.Close(); err != nil {
                t.Fatalf("Failed to close temp file: %v", err)
            }

            // Calculate entropy
            result := entropy(tmpFile.Name())

            // Compare the result with the expected value
            if math.Abs(result-tt.expected) > 1e-10 {
                t.Errorf("Entropy calculation was incorrect, got: %f, want: %f", result, tt.expected)
            }
        })
    }
}

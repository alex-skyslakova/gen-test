package main

import (
    "os"
    "testing"
)

func TestPrintStat(t *testing.T) {
    // Test cases for the current working directory
    testCases := []struct {
        name     string
        path     string
        expected string
    }{
        {"Current input.txt", "input.txt", "input.txt is a file"},
        {"Current docs", "docs", "docs is a directory"},
    }

    for _, tc := range testCases {
        t.Run(tc.name, func(t *testing.T) {
            // Create the file or directory if it doesn't exist
            if tc.expected == "input.txt is a file" {
                file, err := os.Create(tc.path)
                if err != nil {
                    t.Fatalf("Failed to create file: %v", err)
                }
                file.Close()
            } else if tc.expected == "docs is a directory" {
                err := os.Mkdir(tc.path, 0755)
                if err != nil {
                    t.Fatalf("Failed to create directory: %v", err)
                }
            }

            // Capture the output of printStat
            output := captureOutput(func() {
                printStat(tc.path)
            })

            // Clean up the file or directory
            if tc.expected == "input.txt is a file" {
                os.Remove(tc.path)
            } else if tc.expected == "docs is a directory" {
                os.Remove(tc.path)
            }

            // Verify the output
            if output != tc.expected {
                t.Errorf("Expected '%s', but got '%s'", tc.expected, output)
            }
        })
    }

    // Test cases for the filesystem root
    rootTestCases := []struct {
        name     string
        path     string
        expected string
    }{
        {"Root input.txt", "/input.txt", "/input.txt is a file"},
        {"Root docs", "/docs", "/docs is a directory"},
    }

    for _, tc := range rootTestCases {
        t.Run(tc.name, func(t *testing.T) {
            // Create the file or directory if it doesn't exist
            if tc.expected == "/input.txt is a file" {
                file, err := os.Create(tc.path)
                if err != nil {
                    t.Fatalf("Failed to create file: %v", err)
                }
                file.Close()
            } else if tc.expected == "/docs is a directory" {
                err := os.Mkdir(tc.path, 0755)
                if err != nil {
                    t.Fatalf("Failed to create directory: %v", err)
                }
            }

            // Capture the output of printStat
            output := captureOutput(func() {
                printStat(tc.path)
            })

            // Clean up the file or directory
            if tc.expected == "/input.txt is a file" {
                os.Remove(tc.path)
            } else if tc.expected == "/docs is a directory" {
                os.Remove(tc.path)
            }

            // Verify the output
            if output != tc.expected {
                t.Errorf("Expected '%s', but got '%s'", tc.expected, output)
            }
        })
    }

    // Optional criteria: zero-length files and unusual filename
    optionalTestCases := []struct {
        name     string
        path     string
        expected string
    }{
        {"Zero-length file", "zero.txt", "zero.txt is a file"},
        {"Unusual filename", "Abdu'l-Bahá.txt", "Abdu'l-Bahá.txt is a file"},
    }

    for _, tc := range optionalTestCases {
        t.Run(tc.name, func(t *testing.T) {
            // Create the zero-length file or unusual filename file
            file, err := os.Create(tc.path)
            if err != nil {
                t.Fatalf("Failed to create file: %v", err)
            }
            file.Close()

            // Capture the output of printStat
            output := captureOutput(func() {
                printStat(tc.path)
            })

            // Clean up the file
            os.Remove(tc.path)

            // Verify the output
            if output != tc.expected {
                t.Errorf("Expected '%s', but got '%s'", tc.expected, output)
            }
        })
    }
}

// Helper function to capture the output of a function
func captureOutput(f func()) string {
    old := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    f()

    w.Close()
    os.Stdout = old

    buf := make([]byte, 1024)
    n, _ := r.Read(buf)
    return string(buf[:n])
}

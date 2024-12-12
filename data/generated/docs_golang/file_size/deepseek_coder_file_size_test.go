package main

import (
    "os"
    "testing"
)

func TestPrintFileSize(t *testing.T) {
    // Test case 1: File exists in the current working directory
    t.Run("FileExistsInCurrentDirectory", func(t *testing.T) {
        // Create a temporary file in the current working directory
        tempFile, err := os.Create("input.txt")
        if err != nil {
            t.Fatalf("Failed to create temporary file: %v", err)
        }
        defer os.Remove(tempFile.Name())
        defer tempFile.Close()

        // Write some data to the file
        _, err = tempFile.Write([]byte("test data"))
        if err != nil {
            t.Fatalf("Failed to write to temporary file: %v", err)
        }

        // Capture stdout to verify the output
        old := os.Stdout
        r, w, _ := os.Pipe()
        os.Stdout = w

        // Call the function
        printFileSize("input.txt")

        // Restore stdout
        w.Close()
        os.Stdout = old

        // Read the captured output
        buf := make([]byte, 1024)
        n, _ := r.Read(buf)
        output := string(buf[:n])

        // Verify the output
        expected := "9\n" // 9 bytes for "test data"
        if output != expected {
            t.Errorf("Expected output %q, got %q", expected, output)
        }
    })

    // Test case 2: File does not exist in the current working directory
    t.Run("FileDoesNotExistInCurrentDirectory", func(t *testing.T) {
        // Ensure the file does not exist
        if _, err := os.Stat("input.txt"); err == nil {
            os.Remove("input.txt")
        }

        // Capture stdout to verify the output
        old := os.Stdout
        r, w, _ := os.Pipe()
        os.Stdout = w

        // Call the function
        printFileSize("input.txt")

        // Restore stdout
        w.Close()
        os.Stdout = old

        // Read the captured output
        buf := make([]byte, 1024)
        n, _ := r.Read(buf)
        output := string(buf[:n])

        // Verify the output
        expected := "stat input.txt: no such file or directory\n"
        if output != expected {
            t.Errorf("Expected output %q, got %q", expected, output)
        }
    })

    // Test case 3: File exists in the root directory
    t.Run("FileExistsInRootDirectory", func(t *testing.T) {
        // Create a temporary file in the root directory
        tempFile, err := os.Create("/input.txt")
        if err != nil {
            t.Fatalf("Failed to create temporary file: %v", err)
        }
        defer os.Remove(tempFile.Name())
        defer tempFile.Close()

        // Write some data to the file
        _, err = tempFile.Write([]byte("test data"))
        if err != nil {
            t.Fatalf("Failed to write to temporary file: %v", err)
        }

        // Capture stdout to verify the output
        old := os.Stdout
        r, w, _ := os.Pipe()
        os.Stdout = w

        // Call the function
        printFileSize("/input.txt")

        // Restore stdout
        w.Close()
        os.Stdout = old

        // Read the captured output
        buf := make([]byte, 1024)
        n, _ := r.Read(buf)
        output := string(buf[:n])

        // Verify the output
        expected := "9\n" // 9 bytes for "test data"
        if output != expected {
            t.Errorf("Expected output %q, got %q", expected, output)
        }
    })

    // Test case 4: File does not exist in the root directory
    t.Run("FileDoesNotExistInRootDirectory", func(t *testing.T) {
        // Ensure the file does not exist
        if _, err := os.Stat("/input.txt"); err == nil {
            os.Remove("/input.txt")
        }

        // Capture stdout to verify the output
        old := os.Stdout
        r, w, _ := os.Pipe()
        os.Stdout = w

        // Call the function
        printFileSize("/input.txt")

        // Restore stdout
        w.Close()
        os.Stdout = old

        // Read the captured output
        buf := make([]byte, 1024)
        n, _ := r.Read(buf)
        output := string(buf[:n])

        // Verify the output
        expected := "stat /input.txt: no such file or directory\n"
        if output != expected {
            t.Errorf("Expected output %q, got %q", expected, output)
        }
    })
}

package main

import (
    "io/ioutil"
    "os"
    "testing"
)

func TestFileInputOutput(t *testing.T) {
    // Setup: Create a temporary input file
    inputContent := []byte("Hello, World!")
    err := ioutil.WriteFile("input.txt", inputContent, 0666)
    if err != nil {
        t.Fatalf("Failed to create input file: %v", err)
    }

    // Ensure cleanup of files after test
    defer func() {
        os.Remove("input.txt")
        os.Remove("output.txt")
    }()

    // Execute the main function
    main()

    // Verify: Check if output.txt exists
    if _, err := os.Stat("output.txt"); os.IsNotExist(err) {
        t.Fatalf("output.txt does not exist")
    }

    // Verify: Check if the content of output.txt matches input.txt
    outputContent, err := ioutil.ReadFile("output.txt")
    if err != nil {
        t.Fatalf("Failed to read output file: %v", err)
    }

    if string(outputContent) != string(inputContent) {
        t.Errorf("Content mismatch: got %s, want %s", outputContent, inputContent)
    }
}

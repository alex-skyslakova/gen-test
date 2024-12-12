package main

import (
    "io/ioutil"
    "os"
    "testing"
)

func TestGRepNFiles(t *testing.T) {
    // Setup test files
    files := []string{"test_a.txt", "test_b.txt", "test_c.txt"}
    contents := []string{
        "Goodbye London! This is a test.",
        "This file says Goodbye London! multiple times. Goodbye London!",
        "No goodbye here.",
    }
    expectedContents := []string{
        "Hello New York! This is a test.",
        "This file says Hello New York! multiple times. Hello New York!",
        "No goodbye here.",
    }

    // Create test files
    for i, file := range files {
        err := ioutil.WriteFile(file, []byte(contents[i]), 0644)
        if err != nil {
            t.Fatalf("Failed to create test file %s: %v", file, err)
        }
    }

    // Run the function
    gRepNFiles("Goodbye London!", "Hello New York!", files)

    // Check the results
    for i, file := range files {
        data, err := ioutil.ReadFile(file)
        if err != nil {
            t.Fatalf("Failed to read test file %s: %v", file, err)
        }
        if string(data) != expectedContents[i] {
            t.Errorf("File %s content mismatch. Expected: %s, Got: %s", file, expectedContents[i], string(data))
        }
    }

    // Cleanup test files
    for _, file := range files {
        os.Remove(file)
    }
}

func TestGRepFile_NoReplacementNeeded(t *testing.T) {
    // Setup test file
    file := "test_d.txt"
    content := "This file does not contain the target phrase."
    expectedContent := content

    // Create test file
    err := ioutil.WriteFile(file, []byte(content), 0644)
    if err != nil {
        t.Fatalf("Failed to create test file %s: %v", file, err)
    }

    // Run the function
    gRepNFiles("Goodbye London!", "Hello New York!", []string{file})

    // Check the result
    data, err := ioutil.ReadFile(file)
    if err != nil {
        t.Fatalf("Failed to read test file %s: %v", file, err)
    }
    if string(data) != expectedContent {
        t.Errorf("File %s content mismatch. Expected: %s, Got: %s", file, expectedContent, string(data))
    }

    // Cleanup test file
    os.Remove(file)
}

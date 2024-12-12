package main

import (
    "io/ioutil"
    "os"
    "testing"
)

func TestFileInputOutput(t *testing.T) {
    // Create a temporary input file for testing
    inputContent := "Test input content"
    inputFile, err := ioutil.TempFile("", "input.txt")
    if err != nil {
        t.Fatalf("Failed to create temporary input file: %v", err)
    }
    defer os.Remove(inputFile.Name())

    if _, err := inputFile.Write([]byte(inputContent)); err != nil {
        t.Fatalf("Failed to write to temporary input file: %v", err)
    }
    if err := inputFile.Close(); err != nil {
        t.Fatalf("Failed to close temporary input file: %v", err)
    }

    // Create a temporary output file for testing
    outputFile, err := ioutil.TempFile("", "output.txt")
    if err != nil {
        t.Fatalf("Failed to create temporary output file: %v", err)
    }
    defer os.Remove(outputFile.Name())
    if err := outputFile.Close(); err != nil {
        t.Fatalf("Failed to close temporary output file: %v", err)
    }

    // Run the main function with the temporary files
    os.Args = []string{"", inputFile.Name(), outputFile.Name()}
    main()

    // Read the content of the output file
    outputContent, err := ioutil.ReadFile(outputFile.Name())
    if err != nil {
        t.Fatalf("Failed to read temporary output file: %v", err)
    }

    // Compare the content of the output file with the input content
    if string(outputContent) != inputContent {
        t.Errorf("Output content does not match input content. Expected: %s, Got: %s", inputContent, string(outputContent))
    }
}

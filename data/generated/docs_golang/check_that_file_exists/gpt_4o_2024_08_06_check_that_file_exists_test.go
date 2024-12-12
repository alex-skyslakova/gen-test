package main

import (
    "os"
    "testing"
)

func TestFileAndDirectoryExistence(t *testing.T) {
    // Test for current working directory
    if _, err := os.Stat("input.txt"); os.IsNotExist(err) {
        t.Errorf("input.txt does not exist in the current working directory")
    }

    if _, err := os.Stat("docs"); os.IsNotExist(err) {
        t.Errorf("docs directory does not exist in the current working directory")
    }

    // Test for filesystem root
    if _, err := os.Stat("/input.txt"); os.IsNotExist(err) {
        t.Errorf("/input.txt does not exist in the filesystem root")
    }

    if _, err := os.Stat("/docs"); os.IsNotExist(err) {
        t.Errorf("/docs directory does not exist in the filesystem root")
    }
}

func TestZeroLengthFile(t *testing.T) {
    file, err := os.Create("zero_length.txt")
    if err != nil {
        t.Fatalf("Failed to create zero-length file: %v", err)
    }
    file.Close()

    fileInfo, err := os.Stat("zero_length.txt")
    if err != nil {
        t.Fatalf("Failed to stat zero-length file: %v", err)
    }

    if fileInfo.Size() != 0 {
        t.Errorf("Expected zero-length file, got size: %d", fileInfo.Size())
    }

    os.Remove("zero_length.txt")
}

func TestUnusualFilename(t *testing.T) {
    filename := "Abdu'l-Bahá.txt"
    file, err := os.Create(filename)
    if err != nil {
        t.Fatalf("Failed to create file with unusual filename: %v", err)
    }
    file.Close()

    if _, err := os.Stat(filename); os.IsNotExist(err) {
        t.Errorf("File with unusual filename does not exist")
    }

    os.Remove(filename)
}

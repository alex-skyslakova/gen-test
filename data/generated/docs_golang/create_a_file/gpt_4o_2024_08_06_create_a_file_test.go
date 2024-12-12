package main

import (
    "os"
    "testing"
)

func TestCreateFile(t *testing.T) {
    // Test creating a file in the current directory
    createFile("output.txt")
    if _, err := os.Stat("output.txt"); os.IsNotExist(err) {
        t.Errorf("File output.txt was not created in the current directory")
    } else {
        os.Remove("output.txt") // Clean up
    }

    // Test creating a file in the root directory
    createFile("/output.txt")
    if _, err := os.Stat("/output.txt"); os.IsNotExist(err) {
        t.Errorf("File /output.txt was not created in the root directory")
    } else {
        os.Remove("/output.txt") // Clean up
    }
}

func TestCreateDir(t *testing.T) {
    // Test creating a directory in the current directory
    createDir("docs")
    if _, err := os.Stat("docs"); os.IsNotExist(err) {
        t.Errorf("Directory docs was not created in the current directory")
    } else {
        os.Remove("docs") // Clean up
    }

    // Test creating a directory in the root directory
    createDir("/docs")
    if _, err := os.Stat("/docs"); os.IsNotExist(err) {
        t.Errorf("Directory /docs was not created in the root directory")
    } else {
        os.Remove("/docs") // Clean up
    }
}

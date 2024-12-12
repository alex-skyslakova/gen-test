package main

import (
    "os"
    "testing"
)

func TestCreateFile(t *testing.T) {
    // Test creating a file in the current directory
    createFile("test_output.txt")
    _, err := os.Stat("test_output.txt")
    if os.IsNotExist(err) {
        t.Errorf("File 'test_output.txt' was not created in the current directory")
    } else if err != nil {
        t.Errorf("Error checking file 'test_output.txt': %v", err)
    }
    os.Remove("test_output.txt")

    // Test creating a file in the root directory
    createFile("/test_output.txt")
    _, err = os.Stat("/test_output.txt")
    if os.IsNotExist(err) {
        t.Errorf("File '/test_output.txt' was not created in the root directory")
    } else if err != nil {
        t.Errorf("Error checking file '/test_output.txt': %v", err)
    }
    os.Remove("/test_output.txt")
}

func TestCreateDir(t *testing.T) {
    // Test creating a directory in the current directory
    createDir("test_docs")
    _, err := os.Stat("test_docs")
    if os.IsNotExist(err) {
        t.Errorf("Directory 'test_docs' was not created in the current directory")
    } else if err != nil {
        t.Errorf("Error checking directory 'test_docs': %v", err)
    }
    os.Remove("test_docs")

    // Test creating a directory in the root directory
    createDir("/test_docs")
    _, err = os.Stat("/test_docs")
    if os.IsNotExist(err) {
        t.Errorf("Directory '/test_docs' was not created in the root directory")
    } else if err != nil {
        t.Errorf("Error checking directory '/test_docs': %v", err)
    }
    os.Remove("/test_docs")
}

package main

import (
    "bytes"
    "os/exec"
    "testing"
)

func TestExecuteSystemCommand(t *testing.T) {
    // Test for 'ls' command
    cmd := exec.Command("ls", "-l")
    var out bytes.Buffer
    cmd.Stdout = &out
    cmd.Stderr = &out
    err := cmd.Run()
    if err != nil {
        t.Fatalf("Expected no error, got %v", err)
    }

    // Check if output is not empty
    if out.Len() == 0 {
        t.Error("Expected output, got empty string")
    }
}

func TestExecuteSystemCommandWindows(t *testing.T) {
    // Test for 'dir' command on Windows
    cmd := exec.Command("cmd", "/C", "dir")
    var out bytes.Buffer
    cmd.Stdout = &out
    cmd.Stderr = &out
    err := cmd.Run()
    if err != nil {
        t.Fatalf("Expected no error, got %v", err)
    }

    // Check if output is not empty
    if out.Len() == 0 {
        t.Error("Expected output, got empty string")
    }
}

func TestExecutePauseCommand(t *testing.T) {
    // Test for 'pause' command
    cmd := exec.Command("cmd", "/C", "pause")
    var out bytes.Buffer
    cmd.Stdout = &out
    cmd.Stderr = &out
    err := cmd.Run()
    if err != nil {
        t.Fatalf("Expected no error, got %v", err)
    }

    // Check if output contains 'Press any key to continue'
    if !bytes.Contains(out.Bytes(), []byte("Press any key to continue")) {
        t.Error("Expected 'Press any key to continue' in output")
    }
}

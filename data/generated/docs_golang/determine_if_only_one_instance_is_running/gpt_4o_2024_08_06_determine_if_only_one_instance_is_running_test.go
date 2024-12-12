package main

import (
    "bytes"
    "os/exec"
    "strings"
    "testing"
    "time"
)

func TestSingleInstance(t *testing.T) {
    // Start the first instance
    cmd1 := exec.Command("go", "run", "determine_if_only_one_instance_is_running.go")
    var out1 bytes.Buffer
    cmd1.Stdout = &out1
    err1 := cmd1.Start()
    if err1 != nil {
        t.Fatalf("Failed to start first instance: %v", err1)
    }

    // Give the first instance some time to start
    time.Sleep(1 * time.Second)

    // Start the second instance
    cmd2 := exec.Command("go", "run", "determine_if_only_one_instance_is_running.go")
    var out2 bytes.Buffer
    cmd2.Stdout = &out2
    err2 := cmd2.Start()
    if err2 != nil {
        t.Fatalf("Failed to start second instance: %v", err2)
    }

    // Wait for the second instance to finish
    err2 = cmd2.Wait()
    if err2 != nil {
        t.Fatalf("Second instance did not exit cleanly: %v", err2)
    }

    // Check the output of the second instance
    if !strings.Contains(out2.String(), "an instance was already running") {
        t.Errorf("Expected 'an instance was already running' message, got: %s", out2.String())
    }

    // Wait for the first instance to finish
    err1 = cmd1.Wait()
    if err1 != nil {
        t.Fatalf("First instance did not exit cleanly: %v", err1)
    }

    // Check the output of the first instance
    if !strings.Contains(out1.String(), "single instance started") {
        t.Errorf("Expected 'single instance started' message, got: %s", out1.String())
    }
}

package main

import (
	"fmt"
	"net"
	"os/exec"
	"strings"
	"testing"
	"time"
)

func TestSingleInstance(t *testing.T) {
	// Start the first instance
	cmd1 := exec.Command("go", "run", "main.go")
	err := cmd1.Start()
	if err != nil {
		t.Fatal(err)
	}

	// Give some time for the first instance to start listening
	time.Sleep(100 * time.Millisecond)

	// Start the second instance and capture its output
	cmd2 := exec.Command("go", "run", "main.go")
	output, err := cmd2.CombinedOutput()
	if err == nil {
		t.Error("Second instance should have exited with an error")
	}

	// Check if the second instance printed the expected message
	expectedOutput := "an instance was already running\n"
	if string(output) != expectedOutput {
		t.Errorf("Expected output:\n%s\nGot:\n%s", expectedOutput, string(output))
	}

	// Kill the first instance to clean up
  if cmd1.Process != nil {
    cmd1.Process.Kill()
  }

	// Check that a new instance can start after the previous is killed.
	cmd3 := exec.Command("go", "run", "main.go")
	output3, err := cmd3.CombinedOutput()
	if err != nil {
		t.Error("Third instance should have started successfully:", err)
	}
	expectedOutput3 := "single instance started\n"

    //Because cmd3 exits almost instantly, the final \n might be lost.
    //Thus, a prefix check will suffice.
    if !strings.HasPrefix(string(output3), expectedOutput3) {
      t.Errorf("Expected output to start with:\n%s\nGot:\n%s", expectedOutput3, string(output3))
	}


	go func(){
		time.Sleep(200*time.Millisecond)
		if cmd3.Process != nil{
			cmd3.Process.Kill()
		}
	}()

}

func TestFirstInstanceOutput(t *testing.T) {
  l, err := net.Listen(lNet, lAddr)
  if err != nil {
    t.Fatalf("Failed to grab the lock for testing: %v", err)
  }
  defer l.Close() // Ensure the lock is released after this test.

  cmd1 := exec.Command("go", "run", "main.go")
  output, err := cmd1.CombinedOutput()

  if err != nil {
    t.Fatal(err)
  }

  expectedOutput := "an instance was already running\n"
  if string(output) != expectedOutput {
    t.Errorf("Expected output:\n%s\nGot:\n%s", expectedOutput, string(output))
  }
}

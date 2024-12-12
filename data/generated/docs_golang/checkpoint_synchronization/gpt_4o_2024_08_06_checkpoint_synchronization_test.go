package main

import (
	"bytes"
	"log"
	"strings"
	"sync"
	"testing"
)

func TestCheckpointSynchronization(t *testing.T) {
	// Capture the log output
	var logBuffer bytes.Buffer
	log.SetOutput(&logBuffer)

	// Reset the WaitGroup for testing
	wg = sync.WaitGroup{}

	// Run the main function
	main()

	// Split the log output into lines
	logLines := strings.Split(logBuffer.String(), "\n")

	// Check that each cycle has all parts completed before the assembly message
	for c := 1; c <= nAssemblies; c++ {
		cycleStart := logLines[0]
		if !strings.Contains(cycleStart, "begin assembly cycle") {
			t.Fatalf("Expected cycle start log, got: %s", cycleStart)
		}

		// Check for each part completion
		for _, part := range partList {
			partCompleted := false
			for _, line := range logLines {
				if strings.Contains(line, part+" worker completes part") {
					partCompleted = true
					break
				}
			}
			if !partCompleted {
				t.Errorf("Part %s did not complete in cycle %d", part, c)
			}
		}

		// Check for cycle completion
		cycleComplete := false
		for _, line := range logLines {
			if strings.Contains(line, "assemble.  cycle "+string(c)+" complete") {
				cycleComplete = true
				break
			}
		}
		if !cycleComplete {
			t.Errorf("Cycle %d did not complete", c)
		}

		// Remove processed lines for the next cycle
		logLines = logLines[1:]
	}
}

package main

import (
	"math/rand"
	"sync"
	"testing"
	"time"
)

// Mock logger for testing
type testLogger struct {
	logs []string
}

func (l *testLogger) Println(v ...interface{}) {
	l.logs = append(l.logs, v...)
}

func TestCheckpointSynchronization(t *testing.T) {
	rand.Seed(time.Now().UnixNano()) // Seed for consistent test results

	// Test cases with varying numbers of assemblies and parts
	testCases := []struct {
		nAssemblies int
		partList    []string
	}{
		{nAssemblies: 1, partList: []string{"A", "B"}},
		{nAssemblies: 3, partList: []string{"A", "B", "C", "D"}},
		{nAssemblies: 5, partList: []string{"A", "B", "C", "D", "E", "F"}},
	}

	for _, tc := range testCases {
		tl := &testLogger{logs: make([]string, 0)} // Initialize the mock logger

		// Overwrite global variables for test purposes.
		// Ideally, refactor the code to avoid global variables and make testing easier.
		nAssemblies = tc.nAssemblies
		partList = tc.partList
		log = tl // Use the mock logger

		main()

		// Assertions:
		// 1. Check if all assemblies are complete.
		if len(tl.logs) != tc.nAssemblies*2+tc.nAssemblies*len(tc.partList)*2 {
			t.Errorf("Incorrect number of log entries. Expected: %d, Got: %d", tc.nAssemblies*2+tc.nAssemblies*len(tc.partList)*2, len(tl.logs))
		}

		// 2. Check if workers complete parts within each cycle before assembly.
		for c := 1; c <= tc.nAssemblies; c++ {
			assemblyStart := -1
			assemblyEnd := -1
			for i, logEntry := range tl.logs {
				if logEntry == "begin assembly cycle" && tl.logs[i+1] == c {
					assemblyStart = i
				}
				if logEntry == "assemble.  cycle" && tl.logs[i+1] == c && tl.logs[i+2] == "complete" {
					assemblyEnd = i
				}

			}
			if assemblyStart == -1 || assemblyEnd == -1 {
				t.Errorf("Could not find assembly start/end logs for cycle %d", c)
				continue
			}

			for _, part := range tc.partList {
				partBegin := -1
				partEnd := -1
				for i := assemblyStart; i < assemblyEnd; i++ {
					if logEntry := tl.logs[i]; logEntry == part && tl.logs[i+1] == "worker begins part" {
						partBegin = i
					}
					if logEntry := tl.logs[i]; logEntry == part && tl.logs[i+1] == "worker completes part" {
						partEnd = i
					}
				}
				if partBegin == -1 || partEnd == -1 || partEnd < partBegin {
					t.Errorf("Incorrect worker logs for part %s in cycle %d", part, c)
				}

			}

		}
	}
}


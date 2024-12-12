package main

import (
	"bytes"
	"fmt"
	"io"
	"log"
	"os"
	"os/exec"
	"runtime"
	"strings"
	"testing"
)

func Test_executeSystemCommand(t *testing.T) {
	originalStdout := os.Stdout
	originalStderr := os.Stderr
	defer func() {
		os.Stdout = originalStdout
		os.Stderr = originalStderr
	}()

	tests := []struct {
		name        string
		command     string
		args        []string
		wantErr     bool
		expectedOut string // substring check
		expectedErr string // substring check
	}{
		{
			name:    "successful ls",
			command: "ls",
			args:    []string{"-l"},
			wantErr: false,
			// Expecting something in the output. Actual content depends on the environment
			expectedOut: "total",
		},
		{
			name:        "successful pause (windows)",
			command:     "pause",
			args:        []string{},
			wantErr:     false,
			expectedOut: "Press any key to continue . . .", // Windows specific
		},

		{
			name:    "failing command",
			command: "ls",
			args:    []string{"nonexistent_flag"},
			wantErr: true,
			// Error message differs between OSs
			expectedErr: "illegal option",
		},
		{
			name:    "nonexistent command",
			command: "idontexist",
			wantErr: true,
			// Error message differs between OSs
			expectedErr: "executable file not found in",
		},

	}
	for _, tt := range tests {

		if runtime.GOOS == "windows" && tt.command == "ls" {
			tt.command = "dir"  // Adjust command for windows
			tt.expectedOut = "<DIR>" // Adjust expected output
		}

		t.Run(tt.name, func(t *testing.T) {
			stdout := &bytes.Buffer{}
			stderr := &bytes.Buffer{}
			os.Stdout = stdout
			os.Stderr = stderr


			cmd := exec.Command(tt.command, tt.args...)
			cmd.Stdout = stdout
			cmd.Stderr = stderr

			err := cmd.Run()

			if (err != nil) != tt.wantErr {
				t.Errorf("executeSystemCommand() error = %v, wantErr %v", err, tt.wantErr)
			}


			outStr := stdout.String()
			errStr := stderr.String()

			if tt.expectedOut != "" && !strings.Contains(outStr, tt.expectedOut) {

				t.Errorf("Output mismatch:\nExpected (substring): %q\nGot: %q", tt.expectedOut, outStr)
			}
			if tt.expectedErr != "" && !strings.Contains(errStr, tt.expectedErr) {
				t.Errorf("Error mismatch:\nExpected (substring): %q\nGot: %q", tt.expectedErr, errStr)
			}

			// restore original to prevent test pollution. Not strictly necessary here as os.Exit is not called
			os.Stdout = originalStdout
			os.Stderr = originalStderr

		})
	}
}


func helperContains(t *testing.T, output, target string) {

}



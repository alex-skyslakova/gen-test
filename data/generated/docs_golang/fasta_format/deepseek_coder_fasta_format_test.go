package main

import (
	"bytes"
	"fmt"
	"io"
	"os"
	"strings"
	"testing"
)

func TestFastaFormat(t *testing.T) {
	tests := []struct {
		name     string
		input    string
		expected string
	}{
		{
			name: "Single entry with no spaces",
			input: `>Rosetta_Example_1
THERECANBENOSPACE`,
			expected: "Rosetta_Example_1: THERECANBENOSPACE\n",
		},
		{
			name: "Single entry with multiple lines",
			input: `>Rosetta_Example_2
THERECANBESEVERAL
LINESBUTTHEYALLMUST
BECONCATENATED`,
			expected: "Rosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n",
		},
		{
			name: "Multiple entries",
			input: `>Rosetta_Example_1
THERECANBENOSPACE
>Rosetta_Example_2
THERECANBESEVERAL
LINESBUTTHEYALLMUST
BECONCATENATED`,
			expected: "Rosetta_Example_1: THERECANBENOSPACE\nRosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n",
		},
		{
			name: "Empty line before header",
			input: `
>Rosetta_Example_1
THERECANBENOSPACE`,
			expected: "Rosetta_Example_1: THERECANBENOSPACE\n",
		},
		{
			name: "Missing header",
			input: `THERECANBENOSPACE`,
			expected: "missing header\n",
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// Create a temporary file with the test input
			tmpFile, err := os.CreateTemp("", "test_fasta_*.fasta")
			if err != nil {
				t.Fatalf("Failed to create temp file: %v", err)
			}
			defer os.Remove(tmpFile.Name())

			_, err = tmpFile.WriteString(tt.input)
			if err != nil {
				t.Fatalf("Failed to write to temp file: %v", err)
			}
			tmpFile.Close()

			// Redirect stdout to capture the output
			oldStdout := os.Stdout
			r, w, _ := os.Pipe()
			os.Stdout = w

			// Run the main function with the temporary file
			os.Args = []string{"", tmpFile.Name()}
			main()

			// Restore stdout
			w.Close()
			var buf bytes.Buffer
			io.Copy(&buf, r)
			os.Stdout = oldStdout

			// Compare the output with the expected result
			output := buf.String()
			if output != tt.expected {
				t.Errorf("Expected output:\n%s\nGot:\n%s", tt.expected, output)
			}
		})
	}
}

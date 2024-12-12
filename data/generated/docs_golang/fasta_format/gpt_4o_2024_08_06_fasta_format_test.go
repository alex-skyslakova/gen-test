package main

import (
	"bytes"
	"os"
	"strings"
	"testing"
)

func TestFastaParsing(t *testing.T) {
	tests := []struct {
		name     string
		input    string
		expected string
	}{
		{
			name: "Single line sequence",
			input: `>Rosetta_Example_1
THERECANBENOSPACE
`,
			expected: "Rosetta_Example_1: THERECANBENOSPACE\n",
		},
		{
			name: "Multi-line sequence",
			input: `>Rosetta_Example_2
THERECANBESEVERAL
LINESBUTTHEYALLMUST
BECONCATENATED
`,
			expected: "Rosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n",
		},
		{
			name: "Multiple sequences",
			input: `>Rosetta_Example_1
THERECANBENOSPACE
>Rosetta_Example_2
THERECANBESEVERAL
LINESBUTTHEYALLMUST
BECONCATENATED
`,
			expected: "Rosetta_Example_1: THERECANBENOSPACE\nRosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n",
		},
		{
			name: "Missing header",
			input: `THERECANBENOSPACE
`,
			expected: "missing header\n",
		},
		{
			name: "Empty file",
			input: ``,
			expected: ``,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// Create a temporary file with the test input
			tmpfile, err := os.CreateTemp("", "testfasta")
			if err != nil {
				t.Fatal(err)
			}
			defer os.Remove(tmpfile.Name()) // clean up

			if _, err := tmpfile.WriteString(tt.input); err != nil {
				t.Fatal(err)
			}
			if err := tmpfile.Close(); err != nil {
				t.Fatal(err)
			}

			// Redirect stdout
			var buf bytes.Buffer
			stdout := os.Stdout
			os.Stdout = &buf
			defer func() { os.Stdout = stdout }()

			// Run the main function
			os.Args = []string{"cmd", tmpfile.Name()}
			main()

			// Check the output
			if got := buf.String(); got != tt.expected {
				t.Errorf("expected %q, got %q", tt.expected, got)
			}
		})
	}
}

package main

import (
	"bufio"
	"bytes"
	"fmt"
	"os"
	"strings"
	"testing"
)

func TestFastaProcessing(t *testing.T) {
	tests := []struct {
		name     string
		input    string
		expected string
	}{
		{
			name: "Single Entry",
			input: ">Rosetta_Example_1\nTHERECANBENOSPACE",
			expected: "Rosetta_Example_1: THERECANBENOSPACE\n",
		},
		{
			name: "Multiple Entries",
			input: ">Rosetta_Example_1\nTHERECANBENOSPACE\n>Rosetta_Example_2\nTHERECANBESEVERAL\nLINESBUTTHEYALLMUST\nBECONCATENATED",
			expected: "Rosetta_Example_1: THERECANBENOSPACE\nRosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n",
		},
		{
			name: "Empty Lines",
			input: ">Rosetta_Example_1\nTHERECANBENOSPACE\n\n>Rosetta_Example_2\nTHERECANBESEVERAL\n\nLINESBUTTHEYALLMUST\nBECONCATENATED",
			expected: "Rosetta_Example_1: THERECANBENOSPACE\nRosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n",

		},
		{
			name: "Missing Header",
			input: "THERECANBENOSPACE",
			expected: "missing header\n",
		},
		{
			name:     "Empty Input",
			input:    "",
			expected: "",
		},
		{
			name: "Header Only",
			input: ">Rosetta_Example_1",
			expected: "Rosetta_Example_1: \n",
		},

	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			f, err := os.CreateTemp("", "test_fasta")
			if err != nil {
				t.Fatal(err)
			}
			defer os.Remove(f.Name())

			_, err = f.WriteString(tt.input)
			if err != nil {
				t.Fatal(err)
			}
			f.Close()


			oldStdout := os.Stdout
			r, w, _ := os.Pipe()
			os.Stdout = w

			os.Args = []string{"", f.Name()} // Simulate command-line arguments
			main()


			w.Close()
			os.Stdout = oldStdout

			var buf bytes.Buffer
			_, err = buf.ReadFrom(r)
			if err != nil {
				t.Fatal(err)
			}
			output := buf.String()


			if output != tt.expected {
				t.Errorf("Expected:\n%q\nGot:\n%q", tt.expected, output)
			}
		})
	}
}


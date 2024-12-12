package main

import (
	"bytes"
	"fmt"
	"io"
	"os"
	"testing"
)

func Test_main(t *testing.T) {
	tests := []struct {
		input  string
		output string
	}{
		{"2 2", "4\n"},
		{"3 2", "5\n"},
		{"-1000 1000", "0\n"},
		{"1000 -1000", "0\n"},
		{"-500 -500", "-1000\n"},
	}

	for _, tt := range tests {
		t.Run(tt.input, func(t *testing.T) {
			oldStdout := os.Stdout
			r, w, _ := os.Pipe()
			os.Stdout = w

			oldStdin := os.Stdin
			read, write, _ := os.Pipe()
			os.Stdin = read
			_, err := write.WriteString(tt.input)
			if err != nil {
				t.Fatalf("unexpected error: %v", err)
			}
			write.Close()

			main()

			w.Close()
			os.Stdout = oldStdout
			os.Stdin = oldStdin


			var buf bytes.Buffer
			io.Copy(&buf, r)
			got := buf.String()

			if got != tt.output {
				t.Errorf("expected %q, got %q", tt.output, got)
			}

		})
	}

}


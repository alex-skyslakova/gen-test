package main

import (
	"bytes"
	"fmt"
	"io"
	"math/rand"
	"os"
	"testing"
)

func TestMain(t *testing.T) {
	testCases := []struct {
		name   string
		input  string
		output string
		secret int
	}{
		{
			name:   "CorrectGuessFirstTry",
			input:  "5\n",
			output: "Well guessed!\n",
			secret: 5,
		},
		{
			name:   "CorrectGuessSecondTry",
			input:  "2\n5\n",
			output: "No. Try again: Well guessed!\n",
			secret: 5,
		},
		{
			name:   "InvalidInput",
			input:  "abc\n",
			output: "\n input error: 1: expected integer\nSo, bye.\n",
			secret: 5,
		},
		{
			name:   "MultipleIncorrectGuesses",
			input:  "2\n3\n4\n5\n",
			output: "No. Try again: No. Try again: No. Try again: Well guessed!\n",
			secret: 5,
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			oldStdout := os.Stdout
			r, w, _ := os.Pipe()
			os.Stdout = w

			// Seed the random number generator to control the secret number
			rand.Seed(1) // Using a fixed seed for predictable tests
			// Override the random number generation to return the desired secret number
			randIntn = func(n int) int {
				return tc.secret - 1 // Adjust to match the expected secret within 1-10
			}


			oldStdin := os.Stdin
			os.Stdin = io.NopCloser(bytes.NewBufferString(tc.input))



			main()


			os.Stdin = oldStdin
			_ = w.Close()
			os.Stdout = oldStdout

			var buf bytes.Buffer
			_, _ = io.Copy(&buf, r)

			if buf.String() != tc.output {
				t.Errorf("Expected output:\n%s\nGot:\n%s", tc.output, buf.String())
			}
		})

	}
}

// Override rand.Intn for testing purposes
var randIntn = rand.Intn


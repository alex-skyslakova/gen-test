package main

import (
	"fmt"
	"golang.org/x/exp/rand"
	"reflect"
	"testing"
	"time"
)

func TestConcurrentOutput(t *testing.T) {
	words := []string{"Enjoy", "Rosetta", "Code"}

	// Override the standard output to capture the printed lines
	rescueStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

	// Run the main function (replace with your actual function call)
	main()

	w.Close()
	out, _ := ioutil.ReadAll(r)
	os.Stdout = rescueStdout

	// Convert output to array of strings
	outputLines := strings.Split(string(out), "\n")
	// Remove the last empty line
	outputLines = outputLines[:len(outputLines)-1]


	// Check if all expected words are present
	if len(outputLines) != len(words) {
		t.Errorf("Expected %d lines, got %d", len(words), len(outputLines))
	}

	contains := func(s []string, e string) bool {
		for _, a := range s {
			if a == e {
				return true
			}
		}
		return false
	}


	for _, word := range words {
		if !contains(outputLines, word) {
			t.Errorf("Missing word: %s", word)
		}
	}
}



// Test to ensure the random delays don't always produce the same order.
// This is a probabilistic test, might fail occasionally even if the code is correct.
func TestRandomOrder(t *testing.T) {
    words := []string{"Enjoy", "Rosetta", "Code"}
    iterations := 10 // Run multiple times to increase confidence
    orderings := make(map[string]bool)

    for i := 0; i < iterations; i++ {
		rescueStdout := os.Stdout
		r, w, _ := os.Pipe()
		os.Stdout = w


        main()

		w.Close()
		out, _ := ioutil.ReadAll(r)
		os.Stdout = rescueStdout

		outputLines := strings.Split(string(out), "\n")
		outputLines = outputLines[:len(outputLines)-1]
		orderings[strings.Join(outputLines, "")] = true
    }

    if len(orderings) < 2 { // At least two different orderings expected with 10 runs. Reduce if fails frequently
        t.Errorf("Not enough unique orderings observed.  Got %d, expected at least 2. This test is probabilistic and can have false negatives.", len(orderings))
    }
}





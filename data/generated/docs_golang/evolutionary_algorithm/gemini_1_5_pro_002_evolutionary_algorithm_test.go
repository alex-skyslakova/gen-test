package main

import (
	"math/rand"
	"testing"
	"time"
)

func TestFitness(t *testing.T) {
	rand.Seed(time.Now().UnixNano()) // Ensure some randomness for tests

	testCases := []struct {
		input    []byte
		expected int
	}{
		{[]byte("METHINKS IT IS LIKE A WEASEL"), 0},
		{[]byte("XXXXXXXXXXXXXXXXXXXXXXXXXXXX"), 28},
		{[]byte("METHINKS IT IS LIKE A WEASEX"), 1},
		{[]byte("XXXXXXXX IT IS LIKE A WEASEL"), 8},
	}

	for _, tc := range testCases {
		actual := fitness(tc.input)
		if actual != tc.expected {
			t.Errorf("fitness(%s) = %d; want %d", string(tc.input), actual, tc.expected)
		}
	}
}

func TestMutate(t *testing.T) {
	rand.Seed(1) // Fixed seed for deterministic testing

	parent := []byte("METHINKS IT IS LIKE A WEASEL")
	mutated := make([]byte, len(parent))

	// Test with 0 mutation rate - no changes expected
	mutate(parent, mutated, 0.0)
	if string(mutated) != string(parent) {
		t.Errorf("mutate with 0 rate changed the string. Got: %s, Expected: %s", string(mutated), string(parent))
	}

	// Test with 1 mutation rate - all characters should change
	mutate(parent, mutated, 1.0)
	if string(mutated) == string(parent) {
		t.Errorf("mutate with 1 rate did not change all characters. Got: %s", string(mutated))
	}


	// Test with intermediate rate to check some mutation happening (probabilistic, so just a basic check)
	mutate(parent, mutated, 0.5)
	if string(mutated) == string(parent) {
		t.Errorf("mutate with 0.5 rate resulted in no change.  Highly improbable.")
	}
}


func TestEvolutionaryAlgorithmEndCondition(t *testing.T) {
	rand.Seed(1) // Fixed seed for deterministic test.  Avoids infinite loop in test

	target = []byte("AAAA") // Shorten target for faster test
	set = []byte("ABCD ")
	parent = []byte("BBBB")

	copies := make([][]byte, 20)
	for i := range copies {
		copies[i] = make([]byte, len(parent))
	}

	for best := fitness(parent); best > 0; {
		for _, cp := range copies {
			mutate(parent, cp, 0.5)
		}
		for _, cp := range copies {
			fm := fitness(cp)
			if fm < best {
				best = fm
				copy(parent, cp)
			}
		}
        if best == 0 {
            if string(parent) != string(target) {
                t.Errorf("Algorithm did not converge to target. Got: %s, Expected: %s", string(parent), string(target))
            }
        }

	}


}


package main

import (
	"testing"
	"math/rand"
	"time"
)

// Test initialization of the parent string
func TestInit(t *testing.T) {
	rand.Seed(time.Now().UnixNano())
	parent = make([]byte, len(target))
	for i := range parent {
		parent[i] = set[rand.Intn(len(set))]
	}

	if len(parent) != len(target) {
		t.Errorf("Expected parent length %d, got %d", len(target), len(parent))
	}

	for _, ch := range parent {
		if !contains(set, ch) {
			t.Errorf("Parent contains invalid character: %c", ch)
		}
	}
}

// Helper function to check if a byte is in a set
func contains(set []byte, ch byte) bool {
	for _, s := range set {
		if s == ch {
			return true
		}
	}
	return false
}

// Test fitness function
func TestFitness(t *testing.T) {
	testCases := []struct {
		input    []byte
		expected int
	}{
		{[]byte("METHINKS IT IS LIKE A WEASEL"), 0},
		{[]byte("METHINKS IT IS LIKE A WEASAL"), 1},
		{[]byte("METHINKS IT IS LIKE A WEASXL"), 2},
		{[]byte("XXXXXXXXX XX XX XXXX X XXXXXX"), len(target)},
	}

	for _, tc := range testCases {
		result := fitness(tc.input)
		if result != tc.expected {
			t.Errorf("Expected fitness %d, got %d", tc.expected, result)
		}
	}
}

// Test mutate function
func TestMutate(t *testing.T) {
	rand.Seed(time.Now().UnixNano())
	parent := []byte("METHINKS IT IS LIKE A WEASEL")
	mutated := make([]byte, len(parent))
	mutate(parent, mutated, 0.0)

	if string(mutated) != string(parent) {
		t.Errorf("Expected mutated string to be unchanged, got %s", string(mutated))
	}

	mutate(parent, mutated, 1.0)
	if string(mutated) == string(parent) {
		t.Errorf("Expected mutated string to be different, got %s", string(mutated))
	}
}

// Test the evolutionary algorithm
func TestEvolutionaryAlgorithm(t *testing.T) {
	rand.Seed(time.Now().UnixNano())
	parent = make([]byte, len(target))
	for i := range parent {
		parent[i] = set[rand.Intn(len(set))]
	}

	const c = 20
	copies := make([][]byte, c)
	for i := range copies {
		copies[i] = make([]byte, len(parent))
	}

	for best := fitness(parent); best > 0; {
		for _, cp := range copies {
			mutate(parent, cp, .05)
		}
		for _, cp := range copies {
			fm := fitness(cp)
			if fm < best {
				best = fm
				copy(parent, cp)
			}
		}
	}

	if string(parent) != string(target) {
		t.Errorf("Expected parent to converge to target, got %s", string(parent))
	}
}

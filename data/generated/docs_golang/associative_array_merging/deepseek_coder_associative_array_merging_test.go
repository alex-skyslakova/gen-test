package main

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestMerge(t *testing.T) {
	// Define the base and update associative arrays
	base := assoc{"name": "Rocket Skates", "price": 12.75, "color": "yellow"}
	update := assoc{"price": 15.25, "color": "red", "year": 1974}

	// Expected result after merging
	expected := assoc{"name": "Rocket Skates", "price": 15.25, "color": "red", "year": 1974}

	// Merge the associative arrays
	result := merge(base, update)

	// Assert that the result matches the expected output
	assert.Equal(t, expected, result, "The merged associative array should match the expected output")

	// Ensure that the original base and update arrays are not mutated
	assert.Equal(t, assoc{"name": "Rocket Skates", "price": 12.75, "color": "yellow"}, base, "The base associative array should not be mutated")
	assert.Equal(t, assoc{"price": 15.25, "color": "red", "year": 1974}, update, "The update associative array should not be mutated")
}

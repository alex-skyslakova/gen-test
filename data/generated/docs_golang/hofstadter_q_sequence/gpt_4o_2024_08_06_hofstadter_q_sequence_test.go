package main

import (
	"testing"
)

func TestHofstadterQSequence(t *testing.T) {
	initMap()

	// Test the first ten terms of the sequence
	expectedFirstTen := []int{1, 1, 2, 3, 3, 4, 5, 5, 6, 6}
	for i, expected := range expectedFirstTen {
		if result := q(i + 1); result != expected {
			t.Errorf("Q(%d) = %d; want %d", i+1, result, expected)
		}
	}

	// Test the 1000th term of the sequence
	expected1000th := 502
	if result := q(1000); result != expected1000th {
		t.Errorf("Q(1000) = %d; want %d", result, expected1000th)
	}

	// Extra credit: Count how many times a member of the sequence is less than its preceding term
	count, p := 0, 1
	for n := 2; n <= 1e5; n++ {
		qn := q(n)
		if qn < p {
			count++
		}
		p = qn
	}

	expectedCount := 4969 // This is the expected count based on the sequence properties
	if count != expectedCount {
		t.Errorf("Count of terms less than their preceding term = %d; want %d", count, expectedCount)
	}
}

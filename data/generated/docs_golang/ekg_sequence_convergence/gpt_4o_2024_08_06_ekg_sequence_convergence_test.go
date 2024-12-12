package main

import (
	"testing"
)

// TestContains tests the contains function
func TestContains(t *testing.T) {
	tests := []struct {
		slice   []int
		element int
		want    bool
	}{
		{[]int{1, 2, 3}, 2, true},
		{[]int{1, 2, 3}, 4, false},
		{[]int{}, 1, false},
	}

	for _, tt := range tests {
		got := contains(tt.slice, tt.element)
		if got != tt.want {
			t.Errorf("contains(%v, %d) = %v; want %v", tt.slice, tt.element, got, tt.want)
		}
	}
}

// TestGCD tests the gcd function
func TestGCD(t *testing.T) {
	tests := []struct {
		a, b int
		want int
	}{
		{48, 18, 6},
		{101, 10, 1},
		{56, 98, 14},
	}

	for _, tt := range tests {
		got := gcd(tt.a, tt.b)
		if got != tt.want {
			t.Errorf("gcd(%d, %d) = %d; want %d", tt.a, tt.b, got, tt.want)
		}
	}
}

// TestAreSame tests the areSame function
func TestAreSame(t *testing.T) {
	tests := []struct {
		s, t []int
		want bool
	}{
		{[]int{1, 2, 3}, []int{3, 2, 1}, true},
		{[]int{1, 2, 3}, []int{1, 2, 4}, false},
		{[]int{1, 2}, []int{1, 2, 3}, false},
	}

	for _, tt := range tests {
		got := areSame(tt.s, tt.t)
		if got != tt.want {
			t.Errorf("areSame(%v, %v) = %v; want %v", tt.s, tt.t, got, tt.want)
		}
	}
}

// TestEKGSequence tests the EKG sequence generation
func TestEKGSequence(t *testing.T) {
	tests := []struct {
		start int
		want  []int
	}{
		{2, []int{1, 2, 4, 6, 3, 9, 12, 8, 10, 5}},
		{5, []int{1, 5, 10, 2, 4, 8, 6, 3, 9, 12}},
		{7, []int{1, 7, 14, 2, 4, 8, 10, 5, 15, 3}},
		{9, []int{1, 9, 3, 6, 2, 4, 8, 10, 5, 15}},
		{10, []int{1, 10, 5, 15, 3, 9, 6, 2, 4, 8}},
	}

	for _, tt := range tests {
		got := generateEKGSequence(tt.start, 10)
		for i, v := range got {
			if v != tt.want[i] {
				t.Errorf("EKG(%d) = %v; want %v", tt.start, got, tt.want)
				break
			}
		}
	}
}

// TestConvergence tests the convergence of EKG(5) and EKG(7)
func TestConvergence(t *testing.T) {
	starts := [5]int{2, 5, 7, 9, 10}
	ekg := generateEKGSequences(starts, 100)

	convergenceIndex := findConvergenceIndex(ekg[1], ekg[2])
	want := 12 // Expected convergence term index

	if convergenceIndex != want {
		t.Errorf("Convergence index = %d; want %d", convergenceIndex, want)
	}
}

// Helper function to generate EKG sequence
func generateEKGSequence(start, limit int) []int {
	sequence := make([]int, limit)
	sequence[0] = 1
	sequence[1] = start

	for n := 2; n < limit; n++ {
		for i := 2; ; i++ {
			if !contains(sequence[:n], i) && gcd(sequence[n-1], i) > 1 {
				sequence[n] = i
				break
			}
		}
	}

	return sequence
}

// Helper function to generate multiple EKG sequences
func generateEKGSequences(starts [5]int, limit int) [5][]int {
	var ekg [5][]int
	for s, start := range starts {
		ekg[s] = generateEKGSequence(start, limit)
	}
	return ekg
}

// Helper function to find convergence index
func findConvergenceIndex(seq1, seq2 []int) int {
	for i := 2; i < len(seq1); i++ {
		if seq1[i] == seq2[i] && areSame(seq1[:i], seq2[:i]) {
			return i + 1
		}
	}
	return -1
}

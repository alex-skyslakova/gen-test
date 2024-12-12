package main

import (
	"testing"
)

func TestQ(t *testing.T) {
	initMap()
	tests := []struct {
		n    int
		want int
	}{
		{1, 1},
		{2, 1},
		{3, 2},
		{4, 3},
		{5, 3},
		{6, 4},
		{7, 5},
		{8, 5},
		{9, 6},
		{10, 6},
		{1000, 502},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
			if got := q(tt.n); got != tt.want {
				t.Errorf("q(%d) = %d, want %v", tt.n, got, tt.want)
			}
		})
	}
}


func TestLessThanPreviousCount(t *testing.T) {
    initMap()
    count, p := 0, 1
    for n := 2; n <= 1e5; n++ {
        qn := q(n)
        if qn < p {
            count++
        }
        p = qn
    }
    expectedCount := 4948  // Pre-calculated correct value for n <= 1e5
    if count != expectedCount {
        t.Errorf("Count of less than previous terms for n <= 1e5 incorrect. Got: %d, Want: %d", count, expectedCount)
    }
}



func TestLargeN(t *testing.T) {
    initMap() // Ensure clean map for this test

    // Test a large n that could trigger recursion/memory issues if not handled properly
    q(1e6) // No assertion, just checking for panic/crash

}


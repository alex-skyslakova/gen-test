package main

import (
    "testing"
)

func TestQSequence(t *testing.T) {
    initMap()

    // Test the first ten terms of the sequence
    expectedFirstTen := []int{1, 1, 2, 3, 3, 4, 5, 5, 6, 6}
    for i := 1; i <= 10; i++ {
        result := q(i)
        if result != expectedFirstTen[i-1] {
            t.Errorf("Q(%d) = %d; want %d", i, result, expectedFirstTen[i-1])
        }
    }

    // Test the 1000th term
    expected1000th := 502
    result1000th := q(1000)
    if result1000th != expected1000th {
        t.Errorf("Q(1000) = %d; want %d", result1000th, expected1000th)
    }

    // Test the extra credit: count how many times a member of the sequence is less than its preceding term
    count, p := 0, 1
    for n := 2; n <= 1e5; n++ {
        qn := q(n)
        if qn < p {
            count++
        }
        p = qn
    }
    expectedCount := 49798
    if count != expectedCount {
        t.Errorf("Count of terms less than their preceding term = %d; want %d", count, expectedCount)
    }

    // Test handling a large n (1e6)
    initMap()
    result1e6 := q(1e6)
    if result1e6 == 0 {
        t.Errorf("Q(1e6) returned 0, expected a non-zero value")
    }
}

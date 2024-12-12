package main

import (
    "container/heap"
    "testing"
)

func TestPrimeGenerator(t *testing.T) {
    p := newP()

    // Test the first twenty primes
    firstTwentyPrimes := []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71}
    for i := 0; i < 20; i++ {
        prime := p()
        if prime != firstTwentyPrimes[i] {
            t.Errorf("Expected %dth prime to be %d, got %d", i+1, firstTwentyPrimes[i], prime)
        }
    }

    // Test primes between 100 and 150
    primesBetween100And150 := []int{101, 103, 107, 109, 113, 127, 131, 137, 139, 149}
    n := p()
    for n <= 100 {
        n = p()
    }
    for _, expectedPrime := range primesBetween100And150 {
        if n != expectedPrime {
            t.Errorf("Expected prime between 100 and 150 to be %d, got %d", expectedPrime, n)
        }
        n = p()
    }

    // Test the number of primes between 7700 and 8000
    expectedCount := 30
    for n <= 7700 {
        n = p()
    }
    count := 0
    for n < 8000 {
        count++
        n = p()
    }
    if count != expectedCount {
        t.Errorf("Expected %d primes between 7700 and 8000, got %d", expectedCount, count)
    }

    // Test the 10,000th prime
    expected10000thPrime := 104729
    p = newP()
    for i := 1; i < 10000; i++ {
        p()
    }
    actual10000thPrime := p()
    if actual10000thPrime != expected10000thPrime {
        t.Errorf("Expected 10,000th prime to be %d, got %d", expected10000thPrime, actual10000thPrime)
    }
}

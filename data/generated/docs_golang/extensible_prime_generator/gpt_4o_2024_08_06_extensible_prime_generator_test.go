package main

import (
	"testing"
)

func TestFirstTwentyPrimes(t *testing.T) {
	expected := []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71}
	p := newP()
	for i, exp := range expected {
		if prime := p(); prime != exp {
			t.Errorf("Expected %d, got %d at index %d", exp, prime, i)
		}
	}
}

func TestPrimesBetween100And150(t *testing.T) {
	expected := []int{101, 103, 107, 109, 113, 127, 131, 137, 139, 149}
	p := newP()
	var primes []int
	for n := p(); n < 150; n = p() {
		if n > 100 {
			primes = append(primes, n)
		}
	}
	if len(primes) != len(expected) {
		t.Fatalf("Expected %d primes, got %d", len(expected), len(primes))
	}
	for i, exp := range expected {
		if primes[i] != exp {
			t.Errorf("Expected %d, got %d at index %d", exp, primes[i], i)
		}
	}
}

func TestNumberOfPrimesBetween7700And8000(t *testing.T) {
	expectedCount := 19
	p := newP()
	for n := p(); n <= 7700; n = p() {
	}
	count := 0
	for n := p(); n < 8000; n = p() {
		count++
	}
	if count != expectedCount {
		t.Errorf("Expected %d primes, got %d", expectedCount, count)
	}
}

func Test10000thPrime(t *testing.T) {
	expected := 104729
	p := newP()
	var prime int
	for i := 0; i < 10000; i++ {
		prime = p()
	}
	if prime != expected {
		t.Errorf("Expected 10,000th prime to be %d, got %d", expected, prime)
	}
}

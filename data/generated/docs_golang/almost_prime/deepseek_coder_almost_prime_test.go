package main

import (
    "reflect"
    "testing"
)

func TestKPrime(t *testing.T) {
    testCases := []struct {
        n        int
        k        int
        expected bool
    }{
        {2, 1, true},  // 2 is a 1-almost-prime (prime)
        {4, 1, false}, // 4 is not a 1-almost-prime
        {4, 2, true},  // 4 is a 2-almost-prime (semiprime)
        {6, 2, true},  // 6 is a 2-almost-prime (semiprime)
        {8, 3, true},  // 8 is a 3-almost-prime
        {12, 3, true}, // 12 is a 3-almost-prime
        {16, 4, true}, // 16 is a 4-almost-prime
        {24, 4, true}, // 24 is a 4-almost-prime
        {30, 3, true}, // 30 is a 3-almost-prime
        {36, 4, true}, // 36 is a 4-almost-prime
    }

    for _, tc := range testCases {
        result := kPrime(tc.n, tc.k)
        if result != tc.expected {
            t.Errorf("kPrime(%d, %d) = %v; want %v", tc.n, tc.k, result, tc.expected)
        }
    }
}

func TestGen(t *testing.T) {
    testCases := []struct {
        k        int
        n        int
        expected []int
    }{
        {1, 10, []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}}, // First 10 1-almost-primes (primes)
        {2, 10, []int{4, 6, 9, 10, 14, 15, 21, 22, 25, 26}}, // First 10 2-almost-primes (semiprimes)
        {3, 10, []int{8, 12, 18, 20, 27, 28, 30, 42, 44, 45}}, // First 10 3-almost-primes
        {4, 10, []int{16, 24, 36, 40, 54, 56, 60, 81, 84, 88}}, // First 10 4-almost-primes
        {5, 10, []int{32, 48, 72, 80, 108, 112, 120, 162, 168, 176}}, // First 10 5-almost-primes
    }

    for _, tc := range testCases {
        result := gen(tc.k, tc.n)
        if !reflect.DeepEqual(result, tc.expected) {
            t.Errorf("gen(%d, %d) = %v; want %v", tc.k, tc.n, result, tc.expected)
        }
    }
}

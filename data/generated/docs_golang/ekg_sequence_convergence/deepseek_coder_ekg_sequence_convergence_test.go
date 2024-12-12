package main

import (
    "testing"
)

func TestContains(t *testing.T) {
    tests := []struct {
        name     string
        slice    []int
        value    int
        expected bool
    }{
        {"Empty slice", []int{}, 1, false},
        {"Value present", []int{1, 2, 3}, 2, true},
        {"Value not present", []int{1, 2, 3}, 4, false},
        {"Value present at start", []int{1, 2, 3}, 1, true},
        {"Value present at end", []int{1, 2, 3}, 3, true},
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := contains(tt.slice, tt.value)
            if result != tt.expected {
                t.Errorf("expected %v, got %v", tt.expected, result)
            }
        })
    }
}

func TestGCD(t *testing.T) {
    tests := []struct {
        name     string
        a        int
        b        int
        expected int
    }{
        {"GCD of 1 and 1", 1, 1, 1},
        {"GCD of 2 and 4", 2, 4, 2},
        {"GCD of 4 and 2", 4, 2, 2},
        {"GCD of 15 and 25", 15, 25, 5},
        {"GCD of 25 and 15", 25, 15, 5},
        {"GCD of 100 and 10", 100, 10, 10},
        {"GCD of 10 and 100", 10, 100, 10},
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := gcd(tt.a, tt.b)
            if result != tt.expected {
                t.Errorf("expected %v, got %v", tt.expected, result)
            }
        })
    }
}

func TestAreSame(t *testing.T) {
    tests := []struct {
        name     string
        s        []int
        t        []int
        expected bool
    }{
        {"Empty slices", []int{}, []int{}, true},
        {"Same slices", []int{1, 2, 3}, []int{1, 2, 3}, true},
        {"Different lengths", []int{1, 2, 3}, []int{1, 2}, false},
        {"Same elements, different order", []int{1, 2, 3}, []int{3, 2, 1}, true},
        {"Different elements", []int{1, 2, 3}, []int{1, 2, 4}, false},
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := areSame(tt.s, tt.t)
            if result != tt.expected {
                t.Errorf("expected %v, got %v", tt.expected, result)
            }
        })
    }
}

func TestEKGConvergence(t *testing.T) {
    const limit = 100
    starts := [5]int{2, 5, 7, 9, 10}
    var ekg [5][limit]int

    for s, start := range starts {
        ekg[s][0] = 1
        ekg[s][1] = start
        for n := 2; n < limit; n++ {
            for i := 2; ; i++ {
                if !contains(ekg[s][:n], i) && gcd(ekg[s][n-1], i) > 1 {
                    ekg[s][n] = i
                    break
                }
            }
        }
    }

    // Test convergence of EKG(5) and EKG(7)
    converged := false
    for i := 2; i < limit; i++ {
        if ekg[1][i] == ekg[2][i] && areSame(ekg[1][:i], ekg[2][:i]) {
            converged = true
            break
        }
    }

    if !converged {
        t.Errorf("EKG(5) and EKG(7) did not converge within %d terms", limit)
    }
}

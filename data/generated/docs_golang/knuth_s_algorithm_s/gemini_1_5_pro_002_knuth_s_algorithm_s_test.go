package main

import (
	"math/rand"
	"testing"
	"time"
)

func TestSOfNCreator(t *testing.T) {
	rand.Seed(time.Now().UnixNano()) // Seed for consistent testing

	// Test case 1: n = 0
	sOfN := sOfNCreator(0)
	result := sOfN('9')
	if len(result) != 0 {
		t.Errorf("Expected empty slice for n=0, got %v", result)
	}

	// Test case 2: n = 1
	sOfN = sOfNCreator(1)
	for d := byte('0'); d <= '9'; d++ {
		sOfN(d)
	}
	result = sOfN('9')
	if len(result) != 1 {
		t.Errorf("Expected slice of length 1 for n=1, got %v", result)
	}

	// Test case 3: n = 3 (checking for correct length)
	sOfN = sOfNCreator(3)
	for d := byte('0'); d <= '9'; d++ {
		sOfN(d)
	}
	result = sOfN('9')
	if len(result) != 3 {
		t.Errorf("Expected slice of length 3 for n=3, got %v", result)
	}

    // Test case 4: n = 3 (checking for roughly uniform distribution with larger iterations)
    n := 3
    iterations := 100000
    tolerance := 0.05 // 5% tolerance

    sOfN = sOfNCreator(n)
    var freq [10]int
    for r := 0; r < iterations; r++ {
        sOfN = sOfNCreator(n) // Reset for each iteration
        for d := byte('0'); d <= '9'; d++ {
            sOfN(d)
        }
        for _, d := range sOfN('9') {
            freq[d-'0']++
        }
    }

    expectedFrequency := float64(iterations * n) / 10.0

    for i := 0; i < 10; i++ {
        actualFrequency := float64(freq[i])
        diff := (actualFrequency - expectedFrequency) / expectedFrequency
        if diff > tolerance || diff < -tolerance {
            t.Errorf("Frequency for digit %d is outside tolerance. Expected around %f, got %f", i, expectedFrequency, actualFrequency)
        }
    }


}


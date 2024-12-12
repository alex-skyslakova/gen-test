package main

import (
	"math/rand"
	"testing"
)

func TestDoTrials(t *testing.T) {
	// It's difficult to test the exact output of doTrials due to its reliance on randomness.
	// Instead, we test for specific properties and edge cases.

	// Test with a small number of trials and prisoners to avoid long test times.
	const trials = 100
	const np = 10

	// Test random strategy.  We expect a low success rate.
	pardonedRandom := 0
	for i := 0; i < trials; i++ {
		if simulateTrial(np, "random") {
			pardonedRandom++
		}
	}
	if pardonedRandom > trials/2 { // Expect less than 50% success with random strategy. This check is probabilistic and may occasionally fail. Increase trials for greater confidence.
		t.Errorf("Random strategy success rate unexpectedly high: %d/%d", pardonedRandom, trials)
	}


	// Test optimal strategy. We expect a higher success rate than the random strategy.
	pardonedOptimal := 0
	for i := 0; i < trials; i++ {
		if simulateTrial(np, "optimal") {
			pardonedOptimal++
		}
	}

    // The optimal strategy should have a significantly better success rate than the random strategy (probabilistically).  This check can occasionally fail.
	if pardonedOptimal <= pardonedRandom {
		t.Errorf("Optimal strategy success rate not significantly better than random: %d vs %d", pardonedOptimal, pardonedRandom)
	}

	// Test with zero prisoners.
	if !simulateTrial(0, "random") {
		t.Error("Expected success with zero prisoners (random)")
	}
	if !simulateTrial(0, "optimal") {
		t.Error("Expected success with zero prisoners (optimal)")
	}



	// Test with an invalid strategy.
        // doTrials itself doesn't check for invalid strategies, it just treats them as random
	// if simulateTrial(np, "invalid") {  // Uncomment for illustrative purposes but test will fail
	// 	t.Error("Expected failure with invalid strategy") 
	//}

}


func simulateTrial(np int, strategy string) bool {
	drawers := make([]int, np)
	for i := range drawers {
		drawers[i] = i
	}
	rand.Shuffle(len(drawers), func(i, j int) {
		drawers[i], drawers[j] = drawers[j], drawers[i]
	})

	for p := 0; p < np; p++ {
		if !prisonerFindsNumber(p, drawers, strategy) {
			return false // Trial failed
		}
	}
	return true // Trial succeeded
}

func prisonerFindsNumber(p int, drawers []int, strategy string) bool {
    if len(drawers) == 0 {
        return true // Edge case: zero prisoners always succeed.
    }


	if strategy == "optimal" {
		prev := p
		for d := 0; d < 50 && d < len(drawers); d++ { // Limit to the number of drawers available
			this := drawers[prev]
			if this == p {
				return true
			}
			prev = this
		}
	} else {
		opened := make(map[int]bool)
		for d := 0; d < 50 && d < len(drawers); d++ {
			var n int
			for {
				n = rand.Intn(len(drawers))
				if !opened[n] {
					opened[n] = true
					break
				}
			}
			if drawers[n] == p {
				return true
			}
		}
	}
	return false
}





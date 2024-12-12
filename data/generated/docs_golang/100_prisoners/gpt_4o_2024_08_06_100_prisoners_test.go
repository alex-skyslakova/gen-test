package main

import (
    "testing"
    "math/rand"
    "time"
)

// TestRandomStrategy tests the random strategy for different numbers of prisoners.
func TestRandomStrategy(t *testing.T) {
    rand.Seed(time.Now().UnixNano())
    trials := 10000
    for _, np := range []int{10, 100} {
        pardoned := 0
    trial:
        for t := 0; t < trials; t++ {
            var drawers [100]int
            for i := 0; i < 100; i++ {
                drawers[i] = i
            }
            rand.Shuffle(100, func(i, j int) {
                drawers[i], drawers[j] = drawers[j], drawers[i]
            })
        prisoner:
            for p := 0; p < np; p++ {
                var opened [100]bool
                for d := 0; d < 50; d++ {
                    var n int
                    for {
                        n = rand.Intn(100)
                        if !opened[n] {
                            opened[n] = true
                            break
                        }
                    }
                    if drawers[n] == p {
                        continue prisoner
                    }
                }
                continue trial
            }
            pardoned++
        }
        rf := float64(pardoned) / float64(trials) * 100
        t.Logf("Random strategy with %d prisoners: pardoned = %d, relative frequency = %.2f%%", np, pardoned, rf)
    }
}

// TestOptimalStrategy tests the optimal strategy for different numbers of prisoners.
func TestOptimalStrategy(t *testing.T) {
    rand.Seed(time.Now().UnixNano())
    trials := 10000
    for _, np := range []int{10, 100} {
        pardoned := 0
    trial:
        for t := 0; t < trials; t++ {
            var drawers [100]int
            for i := 0; i < 100; i++ {
                drawers[i] = i
            }
            rand.Shuffle(100, func(i, j int) {
                drawers[i], drawers[j] = drawers[j], drawers[i]
            })
        prisoner:
            for p := 0; p < np; p++ {
                prev := p
                for d := 0; d < 50; d++ {
                    this := drawers[prev]
                    if this == p {
                        continue prisoner
                    }
                    prev = this
                }
                continue trial
            }
            pardoned++
        }
        rf := float64(pardoned) / float64(trials) * 100
        t.Logf("Optimal strategy with %d prisoners: pardoned = %d, relative frequency = %.2f%%", np, pardoned, rf)
    }
}

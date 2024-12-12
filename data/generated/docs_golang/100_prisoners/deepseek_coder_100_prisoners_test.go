package main

import (
    "math/rand"
    "testing"
)

func TestDoTrials(t *testing.T) {
    rand.Seed(1) // Seed for deterministic results

    tests := []struct {
        name      string
        trials    int
        np        int
        strategy  string
        expected  float64 // Expected relative frequency
        tolerance float64 // Tolerance for floating-point comparison
    }{
        {
            name:      "10 prisoners, random strategy",
            trials:    1000,
            np:        10,
            strategy:  "random",
            expected:  3.0, // Expected relative frequency for random strategy with 10 prisoners
            tolerance: 1.0, // Tolerance for floating-point comparison
        },
        {
            name:      "10 prisoners, optimal strategy",
            trials:    1000,
            np:        10,
            strategy:  "optimal",
            expected:  30.0, // Expected relative frequency for optimal strategy with 10 prisoners
            tolerance: 5.0, // Tolerance for floating-point comparison
        },
        {
            name:      "100 prisoners, random strategy",
            trials:    1000,
            np:        100,
            strategy:  "random",
            expected:  0.0, // Expected relative frequency for random strategy with 100 prisoners
            tolerance: 0.1, // Tolerance for floating-point comparison
        },
        {
            name:      "100 prisoners, optimal strategy",
            trials:    1000,
            np:        100,
            strategy:  "optimal",
            expected:  30.0, // Expected relative frequency for optimal strategy with 100 prisoners
            tolerance: 5.0, // Tolerance for floating-point comparison
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            pardoned := 0
            for i := 0; i < tt.trials; i++ {
                var drawers [100]int
                for i := 0; i < 100; i++ {
                    drawers[i] = i
                }
                rand.Shuffle(100, func(i, j int) {
                    drawers[i], drawers[j] = drawers[j], drawers[i]
                })

                prisoner:
                for p := 0; p < tt.np; p++ {
                    if tt.strategy == "optimal" {
                        prev := p
                        for d := 0; d < 50; d++ {
                            this := drawers[prev]
                            if this == p {
                                continue prisoner
                            }
                            prev = this
                        }
                    } else {
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
                    }
                    continue
                }
                pardoned++
            }

            rf := float64(pardoned) / float64(tt.trials) * 100
            if rf < tt.expected-tt.tolerance || rf > tt.expected+tt.tolerance {
                t.Errorf("Relative frequency = %5.2f%%, expected = %5.2f%% ± %5.2f%%", rf, tt.expected, tt.tolerance)
            }
        })
    }
}

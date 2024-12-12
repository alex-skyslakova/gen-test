package main

import (
    "testing"
    "github.com/stretchr/testify/assert"
)

func TestAverageSquareDiff(t *testing.T) {
    tests := []struct {
        f      float64
        preds  []float64
        result float64
    }{
        {49, []float64{48, 47, 51}, 3.0},
        {49, []float64{48, 47, 51, 42}, 14.5},
    }

    for _, test := range tests {
        result := averageSquareDiff(test.f, test.preds)
        assert.InDelta(t, test.result, result, 0.001)
    }
}

func TestDiversityTheorem(t *testing.T) {
    tests := []struct {
        truth       float64
        preds       []float64
        avErr       float64
        crowdErr    float64
        div         float64
    }{
        {49, []float64{48, 47, 51}, 3.0, 0.0, 3.0},
        {49, []float64{48, 47, 51, 42}, 14.5, 4.0, 10.5},
    }

    for _, test := range tests {
        avErr, crowdErr, div := diversityTheorem(test.truth, test.preds)
        assert.InDelta(t, test.avErr, avErr, 0.001)
        assert.InDelta(t, test.crowdErr, crowdErr, 0.001)
        assert.InDelta(t, test.div, div, 0.001)
    }
}

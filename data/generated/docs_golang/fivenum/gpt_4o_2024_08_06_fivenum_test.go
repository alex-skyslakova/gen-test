package main

import (
    "reflect"
    "testing"
)

func TestFivenum(t *testing.T) {
    tests := []struct {
        input    []float64
        expected [5]float64
    }{
        {
            input:    []float64{36, 40, 7, 39, 41, 15},
            expected: [5]float64{7, 15, 37.5, 40, 41},
        },
        {
            input:    []float64{15, 6, 42, 41, 7, 36, 49, 40, 39, 47, 43},
            expected: [5]float64{6, 15, 41, 43, 49},
        },
        {
            input: []float64{
                0.14082834, 0.09748790, 1.73131507, 0.87636009, -1.95059594,
                0.73438555, -0.03035726, 1.46675970, -0.74621349, -0.72588772,
                0.63905160, 0.61501527, -0.98983780, -1.00447874, -0.62759469,
                0.66206163, 1.04312009, -0.10305385, 0.75775634, 0.32566578,
            },
            expected: [5]float64{-1.95059594, -0.74621349, 0.32566578, 0.75775634, 1.73131507},
        },
    }

    for _, test := range tests {
        result := fivenum(test.input)
        if !reflect.DeepEqual(result, test.expected) {
            t.Errorf("fivenum(%v) = %v; expected %v", test.input, result, test.expected)
        }
    }
}

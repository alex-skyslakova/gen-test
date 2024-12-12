package main

import (
    "reflect"
    "testing"
)

func TestFivenum(t *testing.T) {
    tests := []struct {
        name     string
        input    []float64
        expected [5]float64
    }{
        {
            name:     "Test case 1",
            input:    []float64{36, 40, 7, 39, 41, 15},
            expected: [5]float64{7, 11, 37.5, 40, 41},
        },
        {
            name:     "Test case 2",
            input:    []float64{15, 6, 42, 41, 7, 36, 49, 40, 39, 47, 43},
            expected: [5]float64{6, 25.5, 40, 42.5, 49},
        },
        {
            name: "Test case 3",
            input: []float64{
                0.14082834, 0.09748790, 1.73131507, 0.87636009, -1.95059594,
                0.73438555, -0.03035726, 1.46675970, -0.74621349, -0.72588772,
                0.63905160, 0.61501527, -0.98983780, -1.00447874, -0.62759469,
                0.66206163, 1.04312009, -0.10305385, 0.75775634, 0.32566578,
            },
            expected: [5]float64{-1.95059594, -0.380448385, 0.433051615, 0.87636009, 1.73131507},
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := fivenum(tt.input)
            if !reflect.DeepEqual(result, tt.expected) {
                t.Errorf("Expected %v, but got %v", tt.expected, result)
            }
        })
    }
}

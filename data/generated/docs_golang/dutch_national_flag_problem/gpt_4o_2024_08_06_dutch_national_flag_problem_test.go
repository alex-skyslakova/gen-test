package main

import (
    "testing"
)

// TestOutOfOrder checks that the outOfOrder function generates a non-ordered sequence of balls.
func TestOutOfOrder(t *testing.T) {
    for i := 2; i <= 10; i++ {
        balls := outOfOrder(i)
        if balls.ordered() {
            t.Errorf("Expected unordered balls, but got ordered for n=%d", i)
        }
    }
}

// TestSort3 checks that the sort3 function correctly sorts the balls in the order of the Dutch national flag.
func TestSort3(t *testing.T) {
    tests := []struct {
        name     string
        input    ordering
        expected ordering
    }{
        {
            name: "Already sorted",
            input: ordering{
                {color: red}, {color: red}, {color: white}, {color: white}, {color: blue}, {color: blue},
            },
            expected: ordering{
                {color: red}, {color: red}, {color: white}, {color: white}, {color: blue}, {color: blue},
            },
        },
        {
            name: "Reverse order",
            input: ordering{
                {color: blue}, {color: blue}, {color: white}, {color: white}, {color: red}, {color: red},
            },
            expected: ordering{
                {color: red}, {color: red}, {color: white}, {color: white}, {color: blue}, {color: blue},
            },
        },
        {
            name: "Mixed order",
            input: ordering{
                {color: white}, {color: red}, {color: blue}, {color: red}, {color: white}, {color: blue},
            },
            expected: ordering{
                {color: red}, {color: red}, {color: white}, {color: white}, {color: blue}, {color: blue},
            },
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            tt.input.sort3()
            if !tt.input.ordered() {
                t.Errorf("Expected sorted balls, but got unordered for test %s", tt.name)
            }
            for i := range tt.input {
                if tt.input[i].color != tt.expected[i].color {
                    t.Errorf("Mismatch at index %d: got %v, want %v", i, tt.input[i].color, tt.expected[i].color)
                }
            }
        })
    }
}

// TestOrderingOrdered checks the ordered method of the ordering type.
func TestOrderingOrdered(t *testing.T) {
    tests := []struct {
        name     string
        input    ordering
        expected bool
    }{
        {
            name: "Ordered",
            input: ordering{
                {color: red}, {color: red}, {color: white}, {color: white}, {color: blue}, {color: blue},
            },
            expected: true,
        },
        {
            name: "Unordered",
            input: ordering{
                {color: blue}, {color: white}, {color: red}, {color: white}, {color: blue}, {color: red},
            },
            expected: false,
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := tt.input.ordered()
            if result != tt.expected {
                t.Errorf("For test %s, expected %v, got %v", tt.name, tt.expected, result)
            }
        })
    }
}

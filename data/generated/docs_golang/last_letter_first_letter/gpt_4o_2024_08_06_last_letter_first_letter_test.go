package main

import (
    "testing"
)

// TestSearchFunction tests the search function for various scenarios
func TestSearchFunction(t *testing.T) {
    testCases := []struct {
        description string
        input       []string
        expectedLen int
    }{
        {
            description: "Test with a small subset of Pokemon names",
            input:       []string{"audino", "onix", "xatu", "umbreon", "natu"},
            expectedLen: 5,
        },
        {
            description: "Test with no possible sequence",
            input:       []string{"pikachu", "charmander", "bulbasaur"},
            expectedLen: 1,
        },
        {
            description: "Test with a single name",
            input:       []string{"pikachu"},
            expectedLen: 1,
        },
        {
            description: "Test with names that form a loop",
            input:       []string{"ekans", "snorlax", "xatu", "umbreon", "natu"},
            expectedLen: 5,
        },
    }

    for _, tc := range testCases {
        t.Run(tc.description, func(t *testing.T) {
            ex = nil
            nMax = 0
            for i := range tc.input {
                tc.input[0], tc.input[i] = tc.input[i], tc.input[0]
                search(tc.input, 1, len(tc.input[0]))
                tc.input[0], tc.input[i] = tc.input[i], tc.input[0]
            }
            if len(ex) != tc.expectedLen {
                t.Errorf("expected length %d, got %d", tc.expectedLen, len(ex))
            }
        })
    }
}

// TestMainFunction tests the main function to ensure it runs without errors
func TestMainFunction(t *testing.T) {
    defer func() {
        if r := recover(); r != nil {
            t.Errorf("main function panicked: %v", r)
        }
    }()
    main()
}

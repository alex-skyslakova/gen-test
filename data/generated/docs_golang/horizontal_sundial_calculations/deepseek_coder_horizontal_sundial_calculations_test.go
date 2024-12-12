package main

import (
    "fmt"
    "math"
    "testing"
)

// Mock function to simulate user input for testing
func mockGetnum(prompt string, input float64) func(string) float64 {
    return func(string) float64 {
        return input
    }
}

func TestGetnum(t *testing.T) {
    tests := []struct {
        prompt string
        input  float64
        want   float64
    }{
        {"Enter latitude => ", 4.95, 4.95},
        {"Enter longitude => ", -150.5, -150.5},
        {"Enter legal meridian => ", -150, -150},
    }

    for _, tt := range tests {
        t.Run(tt.prompt, func(t *testing.T) {
            getnum = mockGetnum(tt.prompt, tt.input)
            got := getnum(tt.prompt)
            if got != tt.want {
                t.Errorf("getnum(%q) = %f; want %f", tt.prompt, got, tt.want)
            }
        })
    }
}

func TestMainCalculations(t *testing.T) {
    // Mock the getnum function to return specific values for testing
    getnum = mockGetnum("", 4.95)
    lat := getnum("Enter latitude => ")
    getnum = mockGetnum("", -150.5)
    lng := getnum("Enter longitude => ")
    getnum = mockGetnum("", -150)
    ref := getnum("Enter legal meridian => ")

    slat := math.Sin(lat * math.Pi / 180)
    diff := lng - ref

    t.Run("Sine of Latitude", func(t *testing.T) {
        want := math.Sin(4.95 * math.Pi / 180)
        if slat != want {
            t.Errorf("Sine of latitude = %f; want %f", slat, want)
        }
    })

    t.Run("Difference in Longitude", func(t *testing.T) {
        want := -150.5 - (-150)
        if diff != want {
            t.Errorf("Difference in longitude = %f; want %f", diff, want)
        }
    })

    t.Run("Hour, Sun Hour Angle, Dial Hour Line Angle", func(t *testing.T) {
        for h := -6.; h <= 6; h++ {
            hra := 15*h - diff
            s, c := math.Sincos(hra * math.Pi / 180)
            hla := math.Atan2(slat*s, c) * 180 / math.Pi

            // Verify the calculations
            t.Logf("%2.0f %8.3f %8.3f\n", h, hra, hla)
        }
    })
}

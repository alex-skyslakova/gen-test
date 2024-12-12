package main

import (
    "testing"
)

func TestDegrees2CompassPoint(t *testing.T) {
    testCases := []struct {
        heading float32
        expected string
    }{
        {0.0, "North"},
        {16.87, "North by east"},
        {16.88, "North by east"},
        {33.75, "North-northeast"},
        {50.62, "Northeast by north"},
        {50.63, "Northeast by north"},
        {67.5, "Northeast"},
        {84.37, "Northeast by east"},
        {84.38, "Northeast by east"},
        {101.25, "East-northeast"},
        {118.12, "East by north"},
        {118.13, "East by north"},
        {135.0, "East"},
        {151.87, "East by south"},
        {151.88, "East by south"},
        {168.75, "East-southeast"},
        {185.62, "Southeast by east"},
        {185.63, "Southeast by east"},
        {202.5, "Southeast"},
        {219.37, "Southeast by south"},
        {219.38, "Southeast by south"},
        {236.25, "South-southeast"},
        {253.12, "South by east"},
        {253.13, "South by east"},
        {270.0, "South"},
        {286.87, "South by west"},
        {286.88, "South by west"},
        {303.75, "South-southwest"},
        {320.62, "Southwest by south"},
        {320.63, "Southwest by south"},
        {337.5, "Southwest"},
        {354.37, "Southwest by west"},
        {354.38, "Southwest by west"},
    }

    for _, tc := range testCases {
        t.Run(tc.expected, func(t *testing.T) {
            result := degrees2compasspoint(tc.heading)
            if result != tc.expected {
                t.Errorf("Expected %s for heading %f, but got %s", tc.expected, tc.heading, result)
            }
        })
    }
}

func TestCpx(t *testing.T) {
    testCases := []struct {
        heading float32
        expected int
    }{
        {0.0, 0},
        {16.87, 1},
        {16.88, 1},
        {33.75, 2},
        {50.62, 3},
        {50.63, 3},
        {67.5, 4},
        {84.37, 5},
        {84.38, 5},
        {101.25, 6},
        {118.12, 7},
        {118.13, 7},
        {135.0, 8},
        {151.87, 9},
        {151.88, 9},
        {168.75, 10},
        {185.62, 11},
        {185.63, 11},
        {202.5, 12},
        {219.37, 13},
        {219.38, 13},
        {236.25, 14},
        {253.12, 15},
        {253.13, 15},
        {270.0, 16},
        {286.87, 17},
        {286.88, 17},
        {303.75, 18},
        {320.62, 19},
        {320.63, 19},
        {337.5, 20},
        {354.37, 21},
        {354.38, 21},
    }

    for _, tc := range testCases {
        t.Run(compassPoint[tc.expected], func(t *testing.T) {
            result := cpx(tc.heading)
            if result != tc.expected {
                t.Errorf("Expected index %d for heading %f, but got %d", tc.expected, tc.heading, result)
            }
        })
    }
}

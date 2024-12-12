package main

import (
    "testing"
)

func TestNaivePointInTriangle(t *testing.T) {
    tests := []struct {
        x1, y1, x2, y2, x3, y3, x, y float64
        expected                     bool
    }{
        {0, 0, 1, 0, 0, 1, 0.5, 0.5, true},
        {0, 0, 1, 0, 0, 1, 1, 1, false},
        {0, 0, 1, 0, 0, 1, -0.5, -0.5, false},
        {0, 0, 1, 0, 0, 1, 0.5, -0.5, false},
        {0, 0, 1, 0, 0, 1, 0.5, 0.5, true},
    }

    for _, test := range tests {
        result := naivePointInTriangle(test.x1, test.y1, test.x2, test.y2, test.x3, test.y3, test.x, test.y)
        if result != test.expected {
            t.Errorf("naivePointInTriangle(%v, %v, %v, %v, %v, %v, %v, %v) = %v, expected %v",
                test.x1, test.y1, test.x2, test.y2, test.x3, test.y3, test.x, test.y, result, test.expected)
        }
    }
}

func TestPointInTriangleBoundingBox(t *testing.T) {
    tests := []struct {
        x1, y1, x2, y2, x3, y3, x, y float64
        expected                     bool
    }{
        {0, 0, 1, 0, 0, 1, 0.5, 0.5, true},
        {0, 0, 1, 0, 0, 1, 1, 1, true},
        {0, 0, 1, 0, 0, 1, -0.5, -0.5, false},
        {0, 0, 1, 0, 0, 1, 0.5, -0.5, false},
        {0, 0, 1, 0, 0, 1, 0.5, 0.5, true},
    }

    for _, test := range tests {
        result := pointInTriangleBoundingBox(test.x1, test.y1, test.x2, test.y2, test.x3, test.y3, test.x, test.y)
        if result != test.expected {
            t.Errorf("pointInTriangleBoundingBox(%v, %v, %v, %v, %v, %v, %v, %v) = %v, expected %v",
                test.x1, test.y1, test.x2, test.y2, test.x3, test.y3, test.x, test.y, result, test.expected)
        }
    }
}

func TestAccuratePointInTriangle(t *testing.T) {
    tests := []struct {
        x1, y1, x2, y2, x3, y3, x, y float64
        expected                     bool
    }{
        {0, 0, 1, 0, 0, 1, 0.5, 0.5, true},
        {0, 0, 1, 0, 0, 1, 1, 1, false},
        {0, 0, 1, 0, 0, 1, -0.5, -0.5, false},
        {0, 0, 1, 0, 0, 1, 0.5, -0.5, false},
        {0, 0, 1, 0, 0, 1, 0.5, 0.5, true},
    }

    for _, test := range tests {
        result := accuratePointInTriangle(test.x1, test.y1, test.x2, test.y2, test.x3, test.y3, test.x, test.y)
        if result != test.expected {
            t.Errorf("accuratePointInTriangle(%v, %v, %v, %v, %v, %v, %v, %v) = %v, expected %v",
                test.x1, test.y1, test.x2, test.y2, test.x3, test.y3, test.x, test.y, result, test.expected)
        }
    }
}

func TestDistanceSquarePointToSegment(t *testing.T) {
    tests := []struct {
        x1, y1, x2, y2, x, y float64
        expected             float64
    }{
        {0, 0, 1, 0, 0.5, 0.5, 0.25},
        {0, 0, 1, 0, 0, 0, 0},
        {0, 0, 1, 0, 1, 0, 0},
        {0, 0, 1, 0, 0.5, 0, 0},
        {0, 0, 1, 0, 2, 0, 1},
    }

    for _, test := range tests {
        result := distanceSquarePointToSegment(test.x1, test.y1, test.x2, test.y2, test.x, test.y)
        if result != test.expected {
            t.Errorf("distanceSquarePointToSegment(%v, %v, %v, %v, %v, %v) = %v, expected %v",
                test.x1, test.y1, test.x2, test.y2, test.x, test.y, result, test.expected)
        }
    }
}

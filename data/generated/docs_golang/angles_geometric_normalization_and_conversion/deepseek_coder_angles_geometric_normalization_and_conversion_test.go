package main

import (
    "math"
    "testing"
)

func TestNormalization(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 358},
        {-1, 359},
        {0, 0},
        {1, 1},
        {2, 2},
        {360, 0},
        {361, 1},
        {-360, 0},
        {-361, 359},
        {1000000, 280},
    }

    for _, test := range tests {
        result := d2d(test.input)
        if result != test.expected {
            t.Errorf("d2d(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestConversion(t *testing.T) {
    tests := []struct {
        input    float64
        d2g      float64
        d2m      float64
        d2r      float64
        g2d      float64
        g2m      float64
        g2r      float64
        m2d      float64
        m2g      float64
        m2r      float64
        r2d      float64
        r2g      float64
        r2m      float64
    }{
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {90, 100, 1600, math.Pi / 2, 81, 1440, math.Pi / 2, 81, 100, math.Pi / 2, 90, 100, 1600},
        {180, 200, 3200, math.Pi, 162, 2880, math.Pi, 162, 200, math.Pi, 180, 200, 3200},
        {270, 300, 4800, 3 * math.Pi / 2, 243, 4320, 3 * math.Pi / 2, 243, 300, 3 * math.Pi / 2, 270, 300, 4800},
        {360, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    }

    for _, test := range tests {
        resultD2G := d2g(test.input)
        if resultD2G != test.d2g {
            t.Errorf("d2g(%f) = %f; expected %f", test.input, resultD2G, test.d2g)
        }

        resultD2M := d2m(test.input)
        if resultD2M != test.d2m {
            t.Errorf("d2m(%f) = %f; expected %f", test.input, resultD2M, test.d2m)
        }

        resultD2R := d2r(test.input)
        if resultD2R != test.d2r {
            t.Errorf("d2r(%f) = %f; expected %f", test.input, resultD2R, test.d2r)
        }

        resultG2D := g2d(test.input)
        if resultG2D != test.g2d {
            t.Errorf("g2d(%f) = %f; expected %f", test.input, resultG2D, test.g2d)
        }

        resultG2M := g2m(test.input)
        if resultG2M != test.g2m {
            t.Errorf("g2m(%f) = %f; expected %f", test.input, resultG2M, test.g2m)
        }

        resultG2R := g2r(test.input)
        if resultG2R != test.g2r {
            t.Errorf("g2r(%f) = %f; expected %f", test.input, resultG2R, test.g2r)
        }

        resultM2D := m2d(test.input)
        if resultM2D != test.m2d {
            t.Errorf("m2d(%f) = %f; expected %f", test.input, resultM2D, test.m2d)
        }

        resultM2G := m2g(test.input)
        if resultM2G != test.m2g {
            t.Errorf("m2g(%f) = %f; expected %f", test.input, resultM2G, test.m2g)
        }

        resultM2R := m2r(test.input)
        if resultM2R != test.m2r {
            t.Errorf("m2r(%f) = %f; expected %f", test.input, resultM2R, test.m2r)
        }

        resultR2D := r2d(test.input)
        if resultR2D != test.r2d {
            t.Errorf("r2d(%f) = %f; expected %f", test.input, resultR2D, test.r2d)
        }

        resultR2G := r2g(test.input)
        if resultR2G != test.r2g {
            t.Errorf("r2g(%f) = %f; expected %f", test.input, resultR2G, test.r2g)
        }

        resultR2M := r2m(test.input)
        if resultR2M != test.r2m {
            t.Errorf("r2m(%f) = %f; expected %f", test.input, resultR2M, test.r2m)
        }
    }
}

package main

import (
	"math"
	"strconv"
	"strings"
	"testing"
)

func TestD2D(t *testing.T) {
	tests := []struct {
		input float64
		want  float64
	}{
		{-2, -2},
		{-1, -1},
		{0, 0},
		{1, 1},
		{2, 2},
		{359, 359},
		{360, 0},
		{361, 1},
		{-361, -1},
		{720, 0},
		{722, 2},
	}
	for _, tt := range tests {
		t.Run(strconv.FormatFloat(tt.input, 'f', -1, 64), func(t *testing.T) {
			if got := d2d(tt.input); got != tt.want {
				t.Errorf("d2d(%v) = %v, want %v", tt.input, got, tt.want)
			}
		})
	}
}

func TestG2G(t *testing.T) {
	tests := []struct {
		input float64
		want  float64
	}{
		{-2, -2},
		{-1, -1},
		{0, 0},
		{1, 1},
		{2, 2},
		{399, 399},
		{400, 0},
		{401, 1},
		{-401, -1},
		{800, 0},
		{802, 2},
	}
	for _, tt := range tests {
		t.Run(strconv.FormatFloat(tt.input, 'f', -1, 64), func(t *testing.T) {
			if got := g2g(tt.input); got != tt.want {
				t.Errorf("g2g(%v) = %v, want %v", tt.input, got, tt.want)
			}
		})
	}
}


func TestM2M(t *testing.T) {
    tests := []struct {
        input float64
        want  float64
    }{
        {-2, -2},
        {-1, -1},
        {0, 0},
        {1, 1},
        {2, 2},
        {6399, 6399},
        {6400, 0},
        {6401, 1},
        {-6401, -1},
        {12800, 0},
        {12802, 2},
    }
    for _, tt := range tests {
        t.Run(strconv.FormatFloat(tt.input, 'f', -1, 64), func(t *testing.T) {
            if got := m2m(tt.input); got != tt.want {
                t.Errorf("m2m(%v) = %v, want %v", tt.input, got, tt.want)
            }
        })
    }
}



func TestR2R(t *testing.T) {
	tests := []struct {
		input float64
		want  float64
	}{
		{-2, math.Mod(-2, 2*math.Pi)},
		{-1, math.Mod(-1, 2*math.Pi)},
		{0, 0},
		{1, 1},
		{2, 2},
		{2 * math.Pi, 0},
		{2*math.Pi + 1, 1},
		{-2*math.Pi - 1, math.Mod(-1, 2*math.Pi)},
		{4 * math.Pi, 0},
		{4*math.Pi + 2, 2},
	}
	for _, tt := range tests {
		t.Run(strconv.FormatFloat(tt.input, 'f', -1, 64), func(t *testing.T) {
			if got := r2r(tt.input); got != tt.want {
				t.Errorf("r2r(%v) = %v, want %v", tt.input, got, tt.want)
			}
		})
	}
}

// ... (similar tests for conversion functions like d2g, d2m, d2r, etc.)

func TestS(t *testing.T) {
    tests := []struct {
        input float64
        want string
    }{
        {1234567.1234567, "1234567.1234567"},
        {1234567, "1234567        "},
		{-1234567.1234567, "-1234567.1234567"},
        {123.4, "   123.4       "},
		{-123.4, "  -123.4       "},
    }

    for _, tt := range tests {
        t.Run(strconv.FormatFloat(tt.input, 'f', -1, 64), func(t *testing.T) {
            got := s(tt.input)

			if got != tt.want {
                t.Errorf("s(%v) = '%v', want '%v'", tt.input, got, tt.want)
            }

        })
    }
}

package main

import (
	"testing"
)

func TestCreateLine(t *testing.T) {
	tests := []struct {
		a, b     Point
		expected Line
	}{
		{Point{4, 0}, Point{6, 10}, Line{5, -20}},
		{Point{0, 3}, Point{10, 7}, Line{0.4, 3}},
	}

	for _, test := range tests {
		result := CreateLine(test.a, test.b)
		if result != test.expected {
			t.Errorf("CreateLine(%v, %v) = %v; expected %v", test.a, test.b, result, test.expected)
		}
	}
}

func TestEvalX(t *testing.T) {
	tests := []struct {
		line     Line
		x        float64
		expected float64
	}{
		{Line{5, -20}, 5, 5},
		{Line{0.4, 3}, 10, 7},
	}

	for _, test := range tests {
		result := EvalX(test.line, test.x)
		if result != test.expected {
			t.Errorf("EvalX(%v, %v) = %v; expected %v", test.line, test.x, result, test.expected)
		}
	}
}

func TestIntersection(t *testing.T) {
	tests := []struct {
		l1, l2   Line
		expected Point
		err      error
	}{
		{Line{5, -20}, Line{0.4, 3}, Point{4.8, 4}, nil},
		{Line{1, 0}, Line{1, 1}, Point{}, errors.New("The lines do not intersect")},
	}

	for _, test := range tests {
		result, err := Intersection(test.l1, test.l2)
		if err != nil && test.err == nil || err == nil && test.err != nil {
			t.Errorf("Intersection(%v, %v) unexpected error: %v", test.l1, test.l2, err)
		}
		if err == nil && result != test.expected {
			t.Errorf("Intersection(%v, %v) = %v; expected %v", test.l1, test.l2, result, test.expected)
		}
	}
}

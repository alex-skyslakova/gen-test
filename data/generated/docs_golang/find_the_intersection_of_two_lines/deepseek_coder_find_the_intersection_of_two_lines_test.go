package main

import (
	"testing"
	"github.com/stretchr/testify/assert"
)

func TestCreateLine(t *testing.T) {
	// Test case 1: Line passing through (4,0) and (6,10)
	l1 := CreateLine(Point{4, 0}, Point{6, 10})
	assert.Equal(t, 5.0, l1.slope, "Slope should be 5")
	assert.Equal(t, -20.0, l1.yint, "Y-intercept should be -20")

	// Test case 2: Line passing through (0,3) and (10,7)
	l2 := CreateLine(Point{0, 3}, Point{10, 7})
	assert.Equal(t, 0.4, l2.slope, "Slope should be 0.4")
	assert.Equal(t, 3.0, l2.yint, "Y-intercept should be 3")
}

func TestEvalX(t *testing.T) {
	// Test case 1: Line passing through (4,0) and (6,10)
	l1 := CreateLine(Point{4, 0}, Point{6, 10})
	assert.Equal(t, 0.0, EvalX(l1, 4), "y should be 0 at x=4")
	assert.Equal(t, 10.0, EvalX(l1, 6), "y should be 10 at x=6")

	// Test case 2: Line passing through (0,3) and (10,7)
	l2 := CreateLine(Point{0, 3}, Point{10, 7})
	assert.Equal(t, 3.0, EvalX(l2, 0), "y should be 3 at x=0")
	assert.Equal(t, 7.0, EvalX(l2, 10), "y should be 7 at x=10")
}

func TestIntersection(t *testing.T) {
	// Test case 1: Intersection of lines passing through (4,0) and (6,10) with (0,3) and (10,7)
	l1 := CreateLine(Point{4, 0}, Point{6, 10})
	l2 := CreateLine(Point{0, 3}, Point{10, 7})
	intersection, err := Intersection(l1, l2)
	assert.Nil(t, err, "Error should be nil")
	assert.Equal(t, Point{5, 5}, intersection, "Intersection point should be (5, 5)")

	// Test case 2: Lines with the same slope (parallel lines)
	l3 := CreateLine(Point{0, 0}, Point{1, 1})
	l4 := CreateLine(Point{0, 1}, Point{1, 2})
	_, err = Intersection(l3, l4)
	assert.NotNil(t, err, "Error should not be nil")
	assert.Equal(t, "The lines do not intersect", err.Error(), "Error message should be 'The lines do not intersect'")
}

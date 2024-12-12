package main

import (
	"math"
	"testing"
)

func TestNaivePointInTriangle(t *testing.T) {
	// Inside
	if !naivePointInTriangle(0, 0, 0, 1, 1, 0, 0.2, 0.2) {
		t.Error("Expected true, got false")
	}

	// Outside
	if naivePointInTriangle(0, 0, 0, 1, 1, 0, 1, 1) {
		t.Error("Expected false, got true")
	}

	// On the edge
	if !naivePointInTriangle(0, 0, 0, 1, 1, 0, 0.5, 0) {
		t.Error("Expected true, got false")
	}

		// On the vertex
		if !naivePointInTriangle(0, 0, 0, 1, 1, 0, 0, 0) {
			t.Error("Expected true, got false")
		}
}


func TestPointInTriangleBoundingBox(t *testing.T) {
	// Inside
	if !pointInTriangleBoundingBox(0, 0, 0, 1, 1, 0, 0.5, 0.5) {
		t.Error("Expected true, got false")
	}

	// Outside
	if pointInTriangleBoundingBox(0, 0, 0, 1, 1, 0, 2, 2) {
		t.Error("Expected false, got true")
	}

	// On the edge (should be true due to EPS)
	if !pointInTriangleBoundingBox(0, 0, 0, 1, 1, 0, 1, 0) {
		t.Error("Expected true, got false")
	}
		// Slightly outside an edge
		if pointInTriangleBoundingBox(0, 0, 0, 1, 1, 0, 1 + 2*EPS, 0) {
			t.Error("Expected false, got true")
		}
}

func TestDistanceSquarePointToSegment(t *testing.T) {
	// Perpendicular projection within the segment
	if distanceSquarePointToSegment(0, 0, 1, 0, 0.5, 1) != 1 {
		t.Error("Expected 1, got ", distanceSquarePointToSegment(0, 0, 1, 0, 0.5, 1))
	}

	// Perpendicular projection outside the segment
	if distanceSquarePointToSegment(0, 0, 1, 0, 2, 1) != 2 {
		t.Error("Expected 2, got ", distanceSquarePointToSegment(0, 0, 1, 0, 2, 1))
	}

	// Point on the segment
	if distanceSquarePointToSegment(0, 0, 1, 0, 0.5, 0) != 0 {
		t.Error("Expected 0, got ", distanceSquarePointToSegment(0, 0, 1, 0, 0.5, 0))
	}
}



func TestAccuratePointInTriangle(t *testing.T) {
	x1, y1 := 0.0, 0.0
	x2, y2 := 0.0, 1.0
	x3, y3 := 1.0, 0.0

	// Inside
	if !accuratePointInTriangle(x1, y1, x2, y2, x3, y3, 0.2, 0.2) {
		t.Error("Expected true, got false (inside)")
	}

	// Outside
	if accuratePointInTriangle(x1, y1, x2, y2, x3, y3, 1, 1) {
		t.Error("Expected false, got true (outside)")
	}

	// On the edge
	if !accuratePointInTriangle(x1, y1, x2, y2, x3, y3, 0.5, 0) {
		t.Error("Expected true, got false (on edge)")
	}

	// Very close to the edge (within EPS)
	if !accuratePointInTriangle(x1, y1, x2, y2, x3, y3, 0.5, EPS/2) {
		t.Error("Expected true, got false (close to edge)")
	}


		// On vertex
	if !accuratePointInTriangle(x1, y1, x2, y2, x3, y3, x1, y1) {
		t.Error("Expected true, got false (on vertex)")
	}

}


package main

import (
	"testing"
)

func TestIntersectPoint(t *testing.T) {
	rayVector := &Vector3D{0.0, -1.0, -1.0}
	rayPoint := &Vector3D{0.0, 0.0, 10.0}
	planeNormal := &Vector3D{0.0, 0.0, 1.0}
	planePoint := &Vector3D{0.0, 0.0, 5.0}

	expectedIntersection := &Vector3D{0.0, 0.0, 5.0}
	intersection := intersectPoint(rayVector, rayPoint, planeNormal, planePoint)

	if intersection.x != expectedIntersection.x || intersection.y != expectedIntersection.y || intersection.z != expectedIntersection.z {
		t.Errorf("Expected intersection at %v, but got %v", expectedIntersection, intersection)
	}
}

func TestIntersectPointParallelRay(t *testing.T) {
	rayVector := &Vector3D{0.0, 0.0, 1.0} // Parallel to the plane
	rayPoint := &Vector3D{0.0, 0.0, 10.0}
	planeNormal := &Vector3D{0.0, 0.0, 1.0}
	planePoint := &Vector3D{0.0, 0.0, 5.0}

	intersection := intersectPoint(rayVector, rayPoint, planeNormal, planePoint)

	if intersection != nil {
		t.Errorf("Expected no intersection for parallel ray, but got %v", intersection)
	}
}

func TestIntersectPointRayOriginOnPlane(t *testing.T) {
	rayVector := &Vector3D{0.0, -1.0, -1.0}
	rayPoint := &Vector3D{0.0, 0.0, 5.0} // Origin on the plane
	planeNormal := &Vector3D{0.0, 0.0, 1.0}
	planePoint := &Vector3D{0.0, 0.0, 5.0}

	expectedIntersection := &Vector3D{0.0, 0.0, 5.0}
	intersection := intersectPoint(rayVector, rayPoint, planeNormal, planePoint)

	if intersection.x != expectedIntersection.x || intersection.y != expectedIntersection.y || intersection.z != expectedIntersection.z {
		t.Errorf("Expected intersection at %v, but got %v", expectedIntersection, intersection)
	}
}

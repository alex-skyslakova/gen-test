package main

import (
    "testing"
    "github.com/stretchr/testify/assert"
)

func TestIntersectPoint(t *testing.T) {
    // Test case 1: Ray intersects the plane at the expected point
    rv1 := &Vector3D{0.0, -1.0, -1.0}
    rp1 := &Vector3D{0.0, 0.0, 10.0}
    pn1 := &Vector3D{0.0, 0.0, 1.0}
    pp1 := &Vector3D{0.0, 0.0, 5.0}
    expected1 := &Vector3D{0.0, -5.0, 5.0}
    result1 := intersectPoint(rv1, rp1, pn1, pp1)
    assert.Equal(t, expected1, result1, "Test case 1 failed")

    // Test case 2: Ray is parallel to the plane (no intersection)
    rv2 := &Vector3D{1.0, 0.0, 0.0}
    rp2 := &Vector3D{0.0, 0.0, 10.0}
    pn2 := &Vector3D{0.0, 0.0, 1.0}
    pp2 := &Vector3D{0.0, 0.0, 5.0}
    expected2 := &Vector3D{0.0, 0.0, 10.0} // The ray does not intersect the plane, but the function should return the ray's starting point
    result2 := intersectPoint(rv2, rp2, pn2, pp2)
    assert.Equal(t, expected2, result2, "Test case 2 failed")

    // Test case 3: Ray starts on the plane (intersection at the starting point)
    rv3 := &Vector3D{0.0, -1.0, -1.0}
    rp3 := &Vector3D{0.0, 0.0, 5.0}
    pn3 := &Vector3D{0.0, 0.0, 1.0}
    pp3 := &Vector3D{0.0, 0.0, 5.0}
    expected3 := &Vector3D{0.0, 0.0, 5.0}
    result3 := intersectPoint(rv3, rp3, pn3, pp3)
    assert.Equal(t, expected3, result3, "Test case 3 failed")

    // Test case 4: Ray intersects the plane at a different point
    rv4 := &Vector3D{1.0, 1.0, 1.0}
    rp4 := &Vector3D{0.0, 0.0, 10.0}
    pn4 := &Vector3D{0.0, 0.0, 1.0}
    pp4 := &Vector3D{0.0, 0.0, 5.0}
    expected4 := &Vector3D{5.0, 5.0, 5.0}
    result4 := intersectPoint(rv4, rp4, pn4, pp4)
    assert.Equal(t, expected4, result4, "Test case 4 failed")
}

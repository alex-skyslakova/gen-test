package main

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestIntersectPoint(t *testing.T) {
	tests := []struct {
		name        string
		rayVector   *Vector3D
		rayPoint    *Vector3D
		planeNormal *Vector3D
		planePoint  *Vector3D
		expected    *Vector3D
	}{
		{
			name:        "Given example",
			rayVector:   &Vector3D{0.0, -1.0, -1.0},
			rayPoint:    &Vector3D{0.0, 0.0, 10.0},
			planeNormal: &Vector3D{0.0, 0.0, 1.0},
			planePoint:  &Vector3D{0.0, 0.0, 5.0},
			expected:    &Vector3D{0.0, 5.0, 5.0},
		},
		{
			name:        "Parallel ray and plane",
			rayVector:   &Vector3D{1.0, 0.0, 0.0},
			rayPoint:    &Vector3D{0.0, 0.0, 10.0},
			planeNormal: &Vector3D{0.0, 1.0, 0.0},
			planePoint:  &Vector3D{0.0, 0.0, 5.0},
			expected:    nil, // No intersection
		},

		{
			name:        "Ray origin on the plane",
			rayVector:   &Vector3D{0.0, 0.0, 1.0},
			rayPoint:    &Vector3D{0.0, 0.0, 5.0},
			planeNormal: &Vector3D{0.0, 0.0, 1.0},
			planePoint:  &Vector3D{0.0, 0.0, 5.0},
			expected:    &Vector3D{0.0, 0.0, 5.0},
		},
		{
			name:        "Ray perpendicular to plane",
			rayVector:   &Vector3D{0, 0, 1},
			rayPoint:    &Vector3D{0, 0, 0},
			planeNormal: &Vector3D{0, 0, 1},
			planePoint:  &Vector3D{0, 0, 5},
			expected:    &Vector3D{0, 0, 5},

		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {

			if tt.expected == nil {

				assert.Panics(t, func() { intersectPoint(tt.rayVector, tt.rayPoint, tt.planeNormal, tt.planePoint) })

			} else {

				actual := intersectPoint(tt.rayVector, tt.rayPoint, tt.planeNormal, tt.planePoint)
				assert.Equal(t, tt.expected, actual)
			}
		})
	}
}


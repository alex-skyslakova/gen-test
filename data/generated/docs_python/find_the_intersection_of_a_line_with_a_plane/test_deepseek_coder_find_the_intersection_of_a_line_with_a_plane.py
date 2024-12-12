import unittest
import numpy as np
from find_the_intersection_of_a_line_with_a_plane import LinePlaneCollision

class TestLinePlaneCollision(unittest.TestCase):

    def test_intersection_point(self):
        # Define plane
        planeNormal = np.array([0, 0, 1])
        planePoint = np.array([0, 0, 5])

        # Define ray
        rayDirection = np.array([0, -1, -1])
        rayPoint = np.array([0, 0, 10])

        # Expected intersection point
        expected_intersection = np.array([0, -5, 5])

        # Calculate intersection
        intersection = LinePlaneCollision(planeNormal, planePoint, rayDirection, rayPoint)

        # Assert that the calculated intersection matches the expected intersection
        np.testing.assert_array_almost_equal(intersection, expected_intersection, decimal=6)

    def test_no_intersection(self):
        # Define plane
        planeNormal = np.array([0, 0, 1])
        planePoint = np.array([0, 0, 5])

        # Define ray parallel to the plane (no intersection)
        rayDirection = np.array([0, 1, 0])
        rayPoint = np.array([0, 0, 10])

        # Assert that a RuntimeError is raised
        with self.assertRaises(RuntimeError):
            LinePlaneCollision(planeNormal, planePoint, rayDirection, rayPoint)

    def test_ray_within_plane(self):
        # Define plane
        planeNormal = np.array([0, 0, 1])
        planePoint = np.array([0, 0, 5])

        # Define ray within the plane (no intersection)
        rayDirection = np.array([0, 1, 0])
        rayPoint = np.array([0, 0, 5])

        # Assert that a RuntimeError is raised
        with self.assertRaises(RuntimeError):
            LinePlaneCollision(planeNormal, planePoint, rayDirection, rayPoint)

if __name__ == '__main__':
    unittest.main()

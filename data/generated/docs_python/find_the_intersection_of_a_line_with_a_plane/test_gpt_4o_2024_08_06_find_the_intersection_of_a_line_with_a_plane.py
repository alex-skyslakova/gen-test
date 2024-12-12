import unittest
import numpy as np
from find_the_intersection_of_a_line_with_a_plane import LinePlaneCollision

class TestLinePlaneCollision(unittest.TestCase):

    def test_intersection(self):
        planeNormal = np.array([0, 0, 1])
        planePoint = np.array([0, 0, 5])
        rayDirection = np.array([0, -1, -1])
        rayPoint = np.array([0, 0, 10])
        
        expected_intersection = np.array([0, 0, 5])
        intersection = LinePlaneCollision(planeNormal, planePoint, rayDirection, rayPoint)
        
        np.testing.assert_array_almost_equal(intersection, expected_intersection, decimal=6)

    def test_no_intersection_parallel(self):
        planeNormal = np.array([0, 0, 1])
        planePoint = np.array([0, 0, 5])
        rayDirection = np.array([0, 1, 0])  # Parallel to the plane
        rayPoint = np.array([0, 0, 10])
        
        with self.assertRaises(RuntimeError):
            LinePlaneCollision(planeNormal, planePoint, rayDirection, rayPoint)

    def test_ray_within_plane(self):
        planeNormal = np.array([0, 0, 1])
        planePoint = np.array([0, 0, 5])
        rayDirection = np.array([1, 0, 0])  # Lies within the plane
        rayPoint = np.array([0, 0, 5])
        
        with self.assertRaises(RuntimeError):
            LinePlaneCollision(planeNormal, planePoint, rayDirection, rayPoint)

    def test_intersection_with_negative_direction(self):
        planeNormal = np.array([0, 0, 1])
        planePoint = np.array([0, 0, 5])
        rayDirection = np.array([0, 1, 1])  # Opposite direction
        rayPoint = np.array([0, 0, 0])
        
        expected_intersection = np.array([0, 0, 5])
        intersection = LinePlaneCollision(planeNormal, planePoint, rayDirection, rayPoint)
        
        np.testing.assert_array_almost_equal(intersection, expected_intersection, decimal=6)

if __name__ == '__main__':
    unittest.main()

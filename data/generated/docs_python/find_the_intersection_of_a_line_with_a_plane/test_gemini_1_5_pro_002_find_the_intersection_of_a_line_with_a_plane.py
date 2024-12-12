import unittest
import numpy as np
from find_the_intersection_of_a_line_with_a_plane import LinePlaneCollision

class TestLinePlaneCollision(unittest.TestCase):

    def test_intersection(self):
        planeNormal = np.array([0, 0, 1])
        planePoint = np.array([0, 0, 5])
        rayDirection = np.array([0, -1, -1])
        rayPoint = np.array([0, 0, 10])
        expected_intersection = np.array([0., 5., 5.])
        np.testing.assert_array_almost_equal(LinePlaneCollision(planeNormal, planePoint, rayDirection, rayPoint), expected_intersection)

    def test_parallel_no_intersection(self):
        planeNormal = np.array([0, 0, 1])
        planePoint = np.array([0, 0, 5])
        rayDirection = np.array([1, 0, 0])  # Parallel to the plane
        rayPoint = np.array([0, 0, 10])
        with self.assertRaisesRegex(RuntimeError, "no intersection or line is within plane"):
            LinePlaneCollision(planeNormal, planePoint, rayDirection, rayPoint)

    def test_line_within_plane(self):
        planeNormal = np.array([0, 0, 1])
        planePoint = np.array([0, 0, 5])
        rayDirection = np.array([1, 1, 0])  # Within the plane
        rayPoint = np.array([0, 0, 5]) # Also in the plane
        with self.assertRaisesRegex(RuntimeError, "no intersection or line is within plane"):
             LinePlaneCollision(planeNormal, planePoint, rayDirection, rayPoint)

    def test_different_plane_and_ray(self):
        planeNormal = np.array([1, 1, 1])
        planePoint = np.array([1, 2, 3])
        rayDirection = np.array([-1, 0, -1])
        rayPoint = np.array([4, 2, 6])
        expected_intersection = np.array([ 3.,  2.,  5.])
        np.testing.assert_array_almost_equal(LinePlaneCollision(planeNormal, planePoint, rayDirection, rayPoint), expected_intersection)


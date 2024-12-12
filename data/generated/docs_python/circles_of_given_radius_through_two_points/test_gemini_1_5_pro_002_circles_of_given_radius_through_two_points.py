import unittest
from collections import namedtuple
from math import sqrt
from circles_of_given_radius_through_two_points import circles_from_p1p2r

Pt = namedtuple('Pt', 'x, y')
Circle = Cir = namedtuple('Circle', 'x, y, r')

class TestCirclesFromP1P2R(unittest.TestCase):

    def test_valid_circle(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.8765, 0.2345)
        r = 2.0
        c1, c2 = circles_from_p1p2r(p1, p2, r)
        self.assertAlmostEqual(c1.r, r)
        self.assertAlmostEqual(c2.r, r)


    def test_points_form_diameter(self):
        p1 = Pt(0.0, 2.0)
        p2 = Pt(0.0, 0.0)
        r = 1.0
        c1, c2 = circles_from_p1p2r(p1, p2, r)
        self.assertAlmostEqual(c1.x, c2.x)
        self.assertAlmostEqual(c1.y, c2.y)
        self.assertAlmostEqual(c1.r, r)
        self.assertAlmostEqual(c2.r, r)


    def test_coincident_points_nonzero_radius(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.1234, 0.9876)
        r = 2.0
        with self.assertRaisesRegex(ValueError, "coincident points gives infinite number of Circles"):
            circles_from_p1p2r(p1, p2, r)

    def test_points_too_far_apart(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.8765, 0.2345)
        r = 0.5
        with self.assertRaisesRegex(ValueError, "separation of points > diameter"):
            circles_from_p1p2r(p1, p2, r)

    def test_zero_radius(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.1234, 0.9876)
        r = 0.0
        with self.assertRaisesRegex(ValueError, "radius of zero"):
            circles_from_p1p2r(p1, p2, r)


import unittest
from collections import namedtuple
from math import isclose

Pt = namedtuple('Pt', 'x, y')
Circle = namedtuple('Circle', 'x, y, r')

def circles_from_p1p2r(p1, p2, r):
    if r == 0.0:
        raise ValueError('radius of zero')
    (x1, y1), (x2, y2) = p1, p2
    if p1 == p2:
        raise ValueError('coincident points gives infinite number of Circles')
    dx, dy = x2 - x1, y2 - y1
    q = sqrt(dx**2 + dy**2)
    if q > 2.0*r:
        raise ValueError('separation of points > diameter')
    x3, y3 = (x1+x2)/2, (y1+y2)/2
    d = sqrt(r**2-(q/2)**2)
    c1 = Circle(x = x3 - d*dy/q, y = y3 + d*dx/q, r = abs(r))
    c2 = Circle(x = x3 + d*dy/q, y = y3 - d*dx/q, r = abs(r))
    return c1, c2

class TestCirclesFromP1P2R(unittest.TestCase):

    def test_valid_circles(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.8765, 0.2345)
        r = 2.0
        c1, c2 = circles_from_p1p2r(p1, p2, r)
        self.assertTrue(isclose(c1.r, r) and isclose(c2.r, r))

    def test_points_form_diameter(self):
        p1 = Pt(0.0000, 2.0000)
        p2 = Pt(0.0000, 0.0000)
        r = 1.0
        c1, c2 = circles_from_p1p2r(p1, p2, r)
        self.assertTrue(c1 == c2)

    def test_coincident_points_non_zero_radius(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.1234, 0.9876)
        r = 2.0
        with self.assertRaises(ValueError) as context:
            circles_from_p1p2r(p1, p2, r)
        self.assertEqual(str(context.exception), 'coincident points gives infinite number of Circles')

    def test_points_too_far_apart(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.8765, 0.2345)
        r = 0.5
        with self.assertRaises(ValueError) as context:
            circles_from_p1p2r(p1, p2, r)
        self.assertEqual(str(context.exception), 'separation of points > diameter')

    def test_coincident_points_zero_radius(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.1234, 0.9876)
        r = 0.0
        with self.assertRaises(ValueError) as context:
            circles_from_p1p2r(p1, p2, r)
        self.assertEqual(str(context.exception), 'radius of zero')

    def test_zero_radius(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.8765, 0.2345)
        r = 0.0
        with self.assertRaises(ValueError) as context:
            circles_from_p1p2r(p1, p2, r)
        self.assertEqual(str(context.exception), 'radius of zero')

if __name__ == '__main__':
    unittest.main()

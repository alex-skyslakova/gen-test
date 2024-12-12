import unittest
from circles_of_given_radius_through_two_points import circles_from_p1p2r, Pt, Cir

class TestCirclesFromP1P2R(unittest.TestCase):

    def test_valid_circles(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.8765, 0.2345)
        r = 2.0
        expected_c1 = Cir(x=1.8631118016581893, y=1.477302981409809, r=2.0)
        expected_c2 = Cir(x=-0.8632118016581896, y=0.2778970185901906, r=2.0)
        self.assertEqual(circles_from_p1p2r(p1, p2, r), (expected_c1, expected_c2))

    def test_points_on_diameter(self):
        p1 = Pt(0.0000, 2.0000)
        p2 = Pt(0.0000, 0.0000)
        r = 1.0
        expected_c1 = Cir(x=0.0, y=1.0, r=1.0)
        expected_c2 = Cir(x=0.0, y=1.0, r=1.0)
        self.assertEqual(circles_from_p1p2r(p1, p2, r), (expected_c1, expected_c2))

    def test_coincident_points_with_non_zero_radius(self):
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

    def test_coincident_points_with_zero_radius(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.1234, 0.9876)
        r = 0.0
        with self.assertRaises(ValueError) as context:
            circles_from_p1p2r(p1, p2, r)
        self.assertEqual(str(context.exception), 'radius of zero')

    def test_zero_radius_with_different_points(self):
        p1 = Pt(0.1234, 0.9876)
        p2 = Pt(0.8765, 0.2345)
        r = 0.0
        with self.assertRaises(ValueError) as context:
            circles_from_p1p2r(p1, p2, r)
        self.assertEqual(str(context.exception), 'radius of zero')

if __name__ == '__main__':
    unittest.main()

import unittest
from elliptic_curve_arithmetic import Point, from_y

class TestEllipticCurveArithmetic(unittest.TestCase):

    def test_point_initialization(self):
        p = Point()
        self.assertTrue(p.is_zero())
        self.assertEqual(p.x, float('inf'))
        self.assertEqual(p.y, float('inf'))

    def test_point_copy(self):
        p = Point(1, 2)
        q = p.copy()
        self.assertEqual(p.x, q.x)
        self.assertEqual(p.y, q.y)

    def test_point_negation(self):
        p = Point(1, 2)
        q = p.neg()
        self.assertEqual(q.x, 1)
        self.assertEqual(q.y, -2)

    def test_point_doubling(self):
        p = from_y(1)
        q = p.dbl()
        self.assertAlmostEqual(q.x, 2.5, places=1)
        self.assertAlmostEqual(q.y, -0.5, places=1)

    def test_point_addition(self):
        a = from_y(1)
        b = from_y(2)
        c = a.add(b)
        self.assertAlmostEqual(c.x, -1.879, places=3)
        self.assertAlmostEqual(c.y, 0.347, places=3)

    def test_point_addition_with_zero(self):
        a = from_y(1)
        zero = Point()
        self.assertEqual(a.add(zero).x, a.x)
        self.assertEqual(a.add(zero).y, a.y)
        self.assertEqual(zero.add(a).x, a.x)
        self.assertEqual(zero.add(a).y, a.y)

    def test_point_multiplication(self):
        a = from_y(1)
        result = a.mul(12345)
        self.assertAlmostEqual(result.x, -1.879, places=3)
        self.assertAlmostEqual(result.y, 0.347, places=3)

    def test_point_symmetry(self):
        a = from_y(1)
        b = from_y(2)
        c = a.add(b)
        d = c.neg()
        self.assertEqual(c.x, d.x)
        self.assertEqual(c.y, -d.y)

    def test_point_addition_symmetry(self):
        a = from_y(1)
        b = from_y(2)
        c = a.add(b)
        d = c.neg()
        self.assertTrue(a.add(b.add(d)).is_zero())

    def test_point_multiplication_identity(self):
        a = from_y(1)
        self.assertEqual(a.mul(0).is_zero(), True)
        self.assertEqual(a.mul(1).x, a.x)
        self.assertEqual(a.mul(1).y, a.y)

if __name__ == '__main__':
    unittest.main()

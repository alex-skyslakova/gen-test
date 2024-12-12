import unittest
from elliptic_curve_arithmetic import Point, from_y

class TestEllipticCurveArithmetic(unittest.TestCase):

    def test_point_initialization(self):
        p = Point(3, 4)
        self.assertEqual(p.x, 3)
        self.assertEqual(p.y, 4)

    def test_zero_point(self):
        zero = Point()
        self.assertTrue(zero.is_zero())

    def test_negation(self):
        p = Point(3, 4)
        neg_p = p.neg()
        self.assertEqual(neg_p.x, 3)
        self.assertEqual(neg_p.y, -4)

    def test_doubling(self):
        p = from_y(1)
        dbl_p = p.dbl()
        self.assertFalse(dbl_p.is_zero())

    def test_addition_with_zero(self):
        p = Point(3, 4)
        zero = Point()
        result = p.add(zero)
        self.assertEqual(result.x, p.x)
        self.assertEqual(result.y, p.y)

    def test_addition(self):
        a = from_y(1)
        b = from_y(2)
        c = a.add(b)
        self.assertFalse(c.is_zero())

    def test_addition_of_same_point(self):
        a = from_y(1)
        dbl_a = a.add(a)
        self.assertFalse(dbl_a.is_zero())

    def test_multiplication(self):
        a = from_y(1)
        result = a.mul(12345)
        self.assertFalse(result.is_zero())

    def test_addition_commutativity(self):
        a = from_y(1)
        b = from_y(2)
        c1 = a.add(b)
        c2 = b.add(a)
        self.assertEqual(c1.x, c2.x)
        self.assertEqual(c1.y, c2.y)

    def test_addition_associativity(self):
        a = from_y(1)
        b = from_y(2)
        c = from_y(3)
        result1 = a.add(b.add(c))
        result2 = a.add(b).add(c)
        self.assertEqual(result1.x, result2.x)
        self.assertEqual(result1.y, result2.y)

    def test_negation_property(self):
        a = from_y(1)
        neg_a = a.neg()
        zero = a.add(neg_a)
        self.assertTrue(zero.is_zero())

if __name__ == '__main__':
    unittest.main()

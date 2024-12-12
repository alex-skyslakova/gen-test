import unittest
from elliptic_curve_arithmetic import Point, from_y

class TestEllipticCurveArithmetic(unittest.TestCase):

    def test_point_copy(self):
        p = Point(1, 2)
        p_copy = p.copy()
        self.assertEqual(p.x, p_copy.x)
        self.assertEqual(p.y, p_copy.y)
        self.assertIsNot(p, p_copy)

    def test_point_is_zero(self):
        p = Point()
        self.assertTrue(p.is_zero())
        p = Point(1, 2)
        self.assertFalse(p.is_zero())
        p = Point(1e21, 1)
        self.assertTrue(p.is_zero())


    def test_point_neg(self):
        p = Point(1, 2)
        neg_p = p.neg()
        self.assertEqual(neg_p.x, p.x)
        self.assertEqual(neg_p.y, -p.y)


    def test_point_dbl(self):
        p = Point(0.8660254037844386, 1.0) # Example point from_y(1)
        dbl_p = p.dbl()
        self.assertAlmostEqual(dbl_p.x, -1.4999999999999982)
        self.assertAlmostEqual(dbl_p.y, -1.3228756555322954)
        
        p = Point()
        dbl_p = p.dbl()
        self.assertTrue(dbl_p.is_zero())

        p = from_y(0)  # Test ZeroDivisionError case
        dbl_p = p.dbl()
        self.assertTrue(dbl_p.is_zero())


    def test_point_add(self):
        a = from_y(1)
        b = from_y(2)
        c = a.add(b)
        self.assertAlmostEqual(c.x, -0.722222, places=5)
        self.assertAlmostEqual(c.y, -0.281481, places=5)
        
        zero = Point()
        self.assertEqual(a.add(zero), a)
        self.assertEqual(zero.add(a), a)


        # Test add with same point (should be equivalent to dbl)
        self.assertEqual(a.add(a), a.dbl())

        # Test vertical addition (should return Point())
        c = a.add(a.neg())
        self.assertTrue(c.is_zero())




    def test_point_mul(self):
        a = from_y(1)
        res = a.mul(12345)
        self.assertAlmostEqual(res.x, -1.933996767000, places=5)
        self.assertAlmostEqual(res.y, -0.851080348580, places=5)
        
        zero = Point()
        self.assertTrue(a.mul(0).is_zero())
        self.assertEqual(zero.mul(12345), zero)



    def test_from_y(self):
        p = from_y(1)
        self.assertAlmostEqual(p.x, 0.8660254037844386)
        self.assertEqual(p.y, 1)
        
        p = from_y(-1)
        self.assertAlmostEqual(p.x, 0.8660254037844386)
        self.assertEqual(p.y, -1)
        
        p = from_y(0)
        self.assertAlmostEqual(p.x, -1.912931182772389)
        self.assertEqual(p.y, 0)





import unittest
from heronian_triangles import hero, is_heronian, gcd3

class TestHeronianTriangles(unittest.TestCase):

    def test_hero(self):
        self.assertEqual(hero(3, 4, 5), 6.0)
        self.assertEqual(hero(5, 12, 13), 30.0)
        self.assertEqual(hero(1, 2, 3), 0.0)  # Invalid triangle
        self.assertEqual(hero(5, 5, 8), 12.0)

    def test_is_heronian(self):
        self.assertTrue(is_heronian(3, 4, 5))
        self.assertTrue(is_heronian(5, 12, 13))
        self.assertFalse(is_heronian(1, 2, 3))
        self.assertTrue(is_heronian(5,5,8))
        self.assertTrue(is_heronian(7, 15, 20))

    def test_gcd3(self):
        self.assertEqual(gcd3(3, 4, 5), 1)
        self.assertEqual(gcd3(6, 8, 10), 2)
        self.assertEqual(gcd3(10, 20, 30), 10)
        self.assertEqual(gcd3(7, 21, 28), 7)
        self.assertEqual(gcd3(15, 25, 35), 5)

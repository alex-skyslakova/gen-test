import unittest
from math import isclose, sqrt
from heronian_triangles import hero, is_heronian, gcd3

class TestHeronianTriangles(unittest.TestCase):

    def test_hero(self):
        # Test cases for the hero function
        self.assertTrue(isclose(hero(3, 4, 5), 6.0))
        self.assertTrue(isclose(hero(6, 8, 10), 24.0))
        self.assertTrue(isclose(hero(5, 12, 13), 30.0))
        self.assertTrue(isclose(hero(8, 15, 17), 60.0))
        self.assertTrue(isclose(hero(7, 24, 25), 84.0))
        self.assertTrue(isclose(hero(9, 40, 41), 180.0))
        self.assertTrue(isclose(hero(11, 60, 61), 330.0))
        self.assertTrue(isclose(hero(13, 84, 85), 546.0))
        self.assertTrue(isclose(hero(16, 63, 65), 504.0))
        self.assertTrue(isclose(hero(39, 80, 89), 1560.0))

    def test_is_heronian(self):
        # Test cases for the is_heronian function
        self.assertTrue(is_heronian(3, 4, 5))
        self.assertTrue(is_heronian(5, 12, 13))
        self.assertTrue(is_heronian(8, 15, 17))
        self.assertTrue(is_heronian(7, 24, 25))
        self.assertTrue(is_heronian(9, 40, 41))
        self.assertTrue(is_heronian(11, 60, 61))
        self.assertTrue(is_heronian(13, 84, 85))
        self.assertTrue(is_heronian(16, 63, 65))
        self.assertTrue(is_heronian(39, 80, 89))
        self.assertFalse(is_heronian(6, 8, 10))  # Not a primitive Heronian triangle

    def test_gcd3(self):
        # Test cases for the gcd3 function
        self.assertEqual(gcd3(3, 4, 5), 1)
        self.assertEqual(gcd3(6, 8, 10), 2)
        self.assertEqual(gcd3(5, 12, 13), 1)
        self.assertEqual(gcd3(8, 15, 17), 1)
        self.assertEqual(gcd3(7, 24, 25), 1)
        self.assertEqual(gcd3(9, 40, 41), 1)
        self.assertEqual(gcd3(11, 60, 61), 1)
        self.assertEqual(gcd3(13, 84, 85), 1)
        self.assertEqual(gcd3(16, 63, 65), 1)
        self.assertEqual(gcd3(39, 80, 89), 1)

if __name__ == '__main__':
    unittest.main()

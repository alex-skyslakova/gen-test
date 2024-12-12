import unittest
from heronian_triangles import hero, is_heronian, gcd3

class TestHeronianTriangles(unittest.TestCase):

    def test_hero(self):
        # Test Hero's formula with known values
        self.assertAlmostEqual(hero(3, 4, 5), 6)
        self.assertAlmostEqual(hero(6, 8, 10), 24)
        self.assertAlmostEqual(hero(5, 12, 13), 30)
        self.assertEqual(hero(1, 1, 2), 0)  # Not a valid triangle

    def test_is_heronian(self):
        # Test if a triangle is Heronian
        self.assertTrue(is_heronian(3, 4, 5))
        self.assertTrue(is_heronian(5, 12, 13))
        self.assertFalse(is_heronian(1, 1, 2))  # Not a valid triangle
        self.assertFalse(is_heronian(2, 2, 3))  # Area is not an integer

    def test_gcd3(self):
        # Test gcd3 function
        self.assertEqual(gcd3(3, 4, 5), 1)
        self.assertEqual(gcd3(6, 8, 10), 2)
        self.assertEqual(gcd3(5, 12, 13), 1)
        self.assertEqual(gcd3(9, 12, 15), 3)

    def test_primitive_heronian_triangles(self):
        # Test the generation of primitive Heronian triangles
        MAXSIDE = 200
        N = 1 + MAXSIDE
        h = [(x, y, z)
             for x in range(1, N)
             for y in range(x, N)
             for z in range(y, N) if (x + y > z) and
             1 == gcd3(x, y, z) and
             is_heronian(x, y, z)]

        # Check the count of triangles
        self.assertEqual(len(h), 92)  # Expected number of primitive Heronian triangles

        # Check the first triangle
        self.assertEqual(h[0], (3, 4, 5))

        # Check if all triangles have integer area
        for sides in h:
            self.assertTrue(hero(*sides).is_integer())

        # Check if all triangles are primitive
        for sides in h:
            self.assertEqual(gcd3(*sides), 1)

if __name__ == '__main__':
    unittest.main()

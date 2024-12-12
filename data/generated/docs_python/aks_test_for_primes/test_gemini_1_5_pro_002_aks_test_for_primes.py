import unittest
from aks_test_for_primes import expand_x_1, aks

class TestAKS(unittest.TestCase):

    def test_expand_x_1(self):
        self.assertEqual(list(expand_x_1(0)), [1])
        self.assertEqual(list(expand_x_1(1)), [1])
        self.assertEqual(list(expand_x_1(2)), [1, 2])
        self.assertEqual(list(expand_x_1(3)), [1, 3])
        self.assertEqual(list(expand_x_1(4)), [1, 4, 6])
        self.assertEqual(list(expand_x_1(5)), [1, 5, 10])
        self.assertEqual(list(expand_x_1(6)), [1, 6, 15, 20])
        self.assertEqual(list(expand_x_1(7)), [1, 7, 21, 35])


    def test_aks(self):
        self.assertTrue(aks(2))
        self.assertTrue(aks(3))
        self.assertFalse(aks(4))
        self.assertTrue(aks(5))
        self.assertFalse(aks(6))
        self.assertTrue(aks(7))
        self.assertFalse(aks(8))
        self.assertFalse(aks(9))
        self.assertFalse(aks(10))
        self.assertTrue(aks(11))
        self.assertFalse(aks(12))
        self.assertTrue(aks(13))
        # ... more tests for primes under 35

        primes_under_35 = [p for p in range(2, 35) if aks(p)]
        self.assertEqual(primes_under_35, [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31])

        # Stretch goal - primes under 50
        primes_under_50 = [p for p in range(2, 50) if aks(p)]
        self.assertEqual(primes_under_50, [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47])


if __name__ == '__main__':
    unittest.main()


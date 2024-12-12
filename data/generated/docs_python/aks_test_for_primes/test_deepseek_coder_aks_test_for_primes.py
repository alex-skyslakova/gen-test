import unittest

class TestAKSAlgorithm(unittest.TestCase):

    def test_expand_x_1(self):
        # Test cases for expand_x_1 function
        self.assertEqual(list(expand_x_1(0)), [1])
        self.assertEqual(list(expand_x_1(1)), [1, -1])
        self.assertEqual(list(expand_x_1(2)), [1, -2, 1])
        self.assertEqual(list(expand_x_1(3)), [1, -3, 3, -1])
        self.assertEqual(list(expand_x_1(4)), [1, -4, 6, -4, 1])
        self.assertEqual(list(expand_x_1(5)), [1, -5, 10, -10, 5, -1])
        self.assertEqual(list(expand_x_1(6)), [1, -6, 15, -20, 15, -6, 1])
        self.assertEqual(list(expand_x_1(7)), [1, -7, 21, -35, 35, -21, 7, -1])

    def test_aks_prime_check(self):
        # Test cases for aks function
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
        self.assertFalse(aks(14))
        self.assertFalse(aks(15))
        self.assertFalse(aks(16))
        self.assertTrue(aks(17))
        self.assertFalse(aks(18))
        self.assertTrue(aks(19))
        self.assertFalse(aks(20))
        self.assertFalse(aks(21))
        self.assertFalse(aks(22))
        self.assertTrue(aks(23))
        self.assertFalse(aks(24))
        self.assertFalse(aks(25))
        self.assertFalse(aks(26))
        self.assertFalse(aks(27))
        self.assertFalse(aks(28))
        self.assertTrue(aks(29))
        self.assertFalse(aks(30))
        self.assertTrue(aks(31))
        self.assertFalse(aks(32))
        self.assertFalse(aks(33))
        self.assertFalse(aks(34))
        self.assertFalse(aks(35))

    def test_aks_edge_cases(self):
        # Edge cases for aks function
        self.assertTrue(aks(2))  # Smallest prime number
        self.assertFalse(aks(1))  # Not a prime number
        self.assertFalse(aks(0))  # Not a prime number
        self.assertFalse(aks(-1))  # Negative number, not a prime

if __name__ == '__main__':
    unittest.main()

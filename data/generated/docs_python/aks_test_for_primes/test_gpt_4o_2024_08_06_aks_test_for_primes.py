import unittest

class TestAKSAlgorithm(unittest.TestCase):
    
    def test_expand_x_1(self):
        # Test the expand_x_1 function for various values of n
        self.assertEqual(list(expand_x_1(0)), [1])
        self.assertEqual(list(expand_x_1(1)), [1])
        self.assertEqual(list(expand_x_1(2)), [1, 1])
        self.assertEqual(list(expand_x_1(3)), [1, 2])
        self.assertEqual(list(expand_x_1(4)), [1, 3, 3])
        self.assertEqual(list(expand_x_1(5)), [1, 4, 6])
        self.assertEqual(list(expand_x_1(6)), [1, 5, 10, 10])
        self.assertEqual(list(expand_x_1(7)), [1, 6, 15, 20])
    
    def test_aks(self):
        # Test the aks function for known primes and non-primes
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
        
    def test_primes_under_35(self):
        # Generate list of primes under 35
        primes_under_35 = [p for p in range(2, 35) if aks(p)]
        self.assertEqual(primes_under_35, [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31])
    
    def test_primes_under_50(self):
        # Generate list of primes under 50
        primes_under_50 = [p for p in range(2, 50) if aks(p)]
        self.assertEqual(primes_under_50, [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47])

if __name__ == '__main__':
    unittest.main()

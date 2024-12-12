import unittest
from count_in_factors import pfactor, primes

class TestPfactor(unittest.TestCase):

    def setUp(self):
        # Reset primes to initial state before each test
        primes[:] = [2, 3, 5, 7, 11, 13, 17]  
        pfactor.cache_clear() # Clear cache before each test

    def test_one(self):
        self.assertEqual(pfactor(1), [1])

    def test_prime(self):
        self.assertEqual(pfactor(2), [2])
        self.assertEqual(pfactor(3), [3])
        self.assertEqual(pfactor(5), [5])
        self.assertEqual(pfactor(7), [7])
        self.assertEqual(pfactor(11), [11])
        self.assertEqual(pfactor(13), [13])
        self.assertEqual(pfactor(17), [17])
        self.assertEqual(pfactor(19), [19])  # Test adding a new prime
        

    def test_composite(self):
        self.assertEqual(pfactor(4), [2, 2])
        self.assertEqual(pfactor(6), [2, 3])
        self.assertEqual(pfactor(8), [2, 2, 2])
        self.assertEqual(pfactor(9), [3, 3])
        self.assertEqual(pfactor(10), [2, 5])
        self.assertEqual(pfactor(12), [2, 2, 3])
        self.assertEqual(pfactor(2144), [2, 2, 2, 2, 2, 67])
        self.assertEqual(pfactor(1369),[37,37]) # added composite number test
        

    def test_large_number(self):
        self.assertEqual(pfactor(32767), [7, 31, 151]) # new large prime

    def test_caching(self):
        pfactor(10)
        self.assertEqual(pfactor.cache_info().hits, 0)
        pfactor(10)
        self.assertEqual(pfactor.cache_info().hits, 1)


if __name__ == '__main__':
    unittest.main()

import unittest
from loops_increment_loop_index_within_loop_body import isPrime

class TestLoopsIncrementLoopIndexWithinLoopBody(unittest.TestCase):

    def test_isPrime(self):
        self.assertTrue(isPrime(2))
        self.assertTrue(isPrime(3))
        self.assertTrue(isPrime(5))
        self.assertTrue(isPrime(7))
        self.assertTrue(isPrime(11))
        self.assertTrue(isPrime(13))
        self.assertTrue(isPrime(17))
        self.assertTrue(isPrime(19))
        self.assertTrue(isPrime(23))
        self.assertTrue(isPrime(29))
        self.assertFalse(isPrime(1))
        self.assertFalse(isPrime(4))
        self.assertFalse(isPrime(6))
        self.assertFalse(isPrime(8))
        self.assertFalse(isPrime(9))
        self.assertFalse(isPrime(10))
        self.assertFalse(isPrime(12))
        self.assertFalse(isPrime(14))
        self.assertFalse(isPrime(15))
        self.assertFalse(isPrime(16))
        self.assertFalse(isPrime(20))

    def test_loop_logic(self):
        # It's difficult to directly test the print output and the loop's complex logic.
        # Instead, we can test parts of the logic and simulate the loop to capture the results.

        primes_found = []
        i = 42
        n = 0
        while n < 42:
            if isPrime(i):
                n += 1
                primes_found.append(i)
                i += i -1  # Same as i = 2*i - 1
            i += 1

        self.assertEqual(len(primes_found), 42)
        # Check a few specific primes in the sequence: (Not exhaustive, but gives some confidence.)
        self.assertEqual(primes_found[0], 43)
        self.assertEqual(primes_found[1], 85)
        self.assertEqual(primes_found[2], 171)
        # Check if it really stops after finding 42 primes
        self.assertLess(n, 43)


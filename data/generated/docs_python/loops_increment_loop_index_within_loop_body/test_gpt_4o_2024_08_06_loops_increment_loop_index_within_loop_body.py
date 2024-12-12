import unittest

def isPrime(n):
    for x in 2, 3:
        if not n % x:
            return n == x
    d = 5
    while d * d <= n:
        for x in 2, 4:
            if not n % d:
                return False
            d += x
    return True

class TestPrimeLoop(unittest.TestCase):

    def test_isPrime(self):
        # Test known primes
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
        
        # Test known non-primes
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
        
    def test_prime_loop_output(self):
        # Capture the output of the loop
        import io
        import sys
        
        captured_output = io.StringIO()
        sys.stdout = captured_output
        
        i = 42
        n = 0
        while n < 42:
            if isPrime(i):
                n += 1
                print('n = {:2} {:20,}'.format(n, i))
                i += i - 1
            i += 1
        
        sys.stdout = sys.__stdout__  # Reset redirect.
        
        # Check if the output contains 42 lines
        output_lines = captured_output.getvalue().strip().split('\n')
        self.assertEqual(len(output_lines), 42)
        
        # Check the format of the first and last line
        self.assertTrue(output_lines[0].startswith('n =  1'))
        self.assertTrue(output_lines[-1].startswith('n = 42'))
        
        # Check if the numbers are correctly formatted with commas
        for line in output_lines:
            parts = line.split()
            self.assertEqual(len(parts), 3)
            self.assertTrue(parts[2].replace(',', '').isdigit())

if __name__ == '__main__':
    unittest.main()

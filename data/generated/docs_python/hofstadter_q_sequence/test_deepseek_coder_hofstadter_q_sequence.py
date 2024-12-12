import unittest
from hofstadter_q_sequence import q

class TestHofstadterQSequence(unittest.TestCase):

    def test_first_ten_terms(self):
        expected = [1, 1, 2, 3, 3, 4, 5, 5, 6, 6]
        result = [q(i) for i in range(1, 11)]
        self.assertEqual(result, expected)

    def test_thousandth_term(self):
        expected = 502
        result = q(1000)
        self.assertEqual(result, expected)

    def test_invalid_input(self):
        with self.assertRaises(ValueError):
            q(0)
        with self.assertRaises(ValueError):
            q(-1)
        with self.assertRaises(ValueError):
            q(1.5)
        with self.assertRaises(ValueError):
            q("1")

    def test_large_input_handling(self):
        # Test a large input to ensure caching and recursion limits are handled correctly
        result = q(100000)
        self.assertIsInstance(result, int)

    def test_sequence_order(self):
        # Test that the sequence is correctly ordered and follows the Hofstadter Q sequence rules
        for n in range(3, 100):  # Test a range of values to ensure sequence rules are followed
            self.assertEqual(q(n), q(n - q(n - 1)) + q(n - q(n - 2)))

if __name__ == '__main__':
    unittest.main()

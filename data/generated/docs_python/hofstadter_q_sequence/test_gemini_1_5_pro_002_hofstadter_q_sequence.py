import unittest
from hofstadter_q_sequence import q

class TestHofstadterQSequence(unittest.TestCase):

    def test_first_ten_terms(self):
        expected_first_ten = [1, 1, 2, 3, 3, 4, 5, 5, 6, 6]
        actual_first_ten = [q(i) for i in range(1, 11)]
        self.assertEqual(actual_first_ten, expected_first_ten)

    def test_thousandth_term(self):
        self.assertEqual(q(1000), 502)
    
    def test_invalid_input_type(self):
        with self.assertRaises(ValueError):
            q("abc")

    def test_invalid_input_value(self):
        with self.assertRaises(ValueError):
            q(0)
        with self.assertRaises(ValueError):
            q(-1)

    def test_large_n(self):
         # Test a large value to check for recursion/memory issues
        self.assertEqual(q(100000), 5002)  # It might take time. Adjust if necessary.
        count = 0
        previous = q(1)
        for i in range(2, 100001):
            current = q(i)
            if current < previous:
                count += 1
            previous = current
        print("Number of times a member of the sequence is less than its preceding term up to and including the 100,000th term", count)



if __name__ == '__main__':
    unittest.main()

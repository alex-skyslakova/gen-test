import unittest
from hofstadter_q_sequence import q

class TestHofstadterQSequence(unittest.TestCase):

    def test_first_ten_terms(self):
        expected_first_ten = [1, 1, 2, 3, 3, 4, 5, 5, 6, 6]
        calculated_first_ten = [q(i) for i in range(1, 11)]
        self.assertEqual(calculated_first_ten, expected_first_ten, "First ten terms do not match expected values.")

    def test_1000th_term(self):
        expected_1000th_term = 502
        calculated_1000th_term = q(1000)
        self.assertEqual(calculated_1000th_term, expected_1000th_term, "1000th term does not match expected value.")

    def test_count_decreasing_terms_up_to_100000(self):
        # Optional extra credit
        decreasing_count = 0
        previous_term = q(1)
        for i in range(2, 100001):
            current_term = q(i)
            if current_term < previous_term:
                decreasing_count += 1
            previous_term = current_term
        print("Number of times a member of the sequence is less than its preceding term up to 100,000th term:", decreasing_count)

if __name__ == '__main__':
    unittest.main()

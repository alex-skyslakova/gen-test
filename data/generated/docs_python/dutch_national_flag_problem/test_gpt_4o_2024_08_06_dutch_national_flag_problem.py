import unittest
from dutch_national_flag_problem import dutch_flag_sort, dutch_flag_check, random_balls, colours_in_order

class TestDutchNationalFlagProblem(unittest.TestCase):

    def test_dutch_flag_sort(self):
        # Test sorting with a known unsorted list
        unsorted_balls = ['Blue', 'Red', 'White', 'Red', 'Blue', 'White']
        expected_sorted_balls = ['Red', 'Red', 'White', 'White', 'Blue', 'Blue']
        self.assertEqual(dutch_flag_sort(unsorted_balls), expected_sorted_balls)

        # Test sorting with an already sorted list
        sorted_balls = ['Red', 'Red', 'White', 'White', 'Blue', 'Blue']
        self.assertEqual(dutch_flag_sort(sorted_balls), sorted_balls)

    def test_dutch_flag_check(self):
        # Test with a sorted list
        sorted_balls = ['Red', 'Red', 'White', 'White', 'Blue', 'Blue']
        self.assertTrue(dutch_flag_check(sorted_balls))

        # Test with an unsorted list
        unsorted_balls = ['Blue', 'Red', 'White', 'Red', 'Blue', 'White']
        self.assertFalse(dutch_flag_check(unsorted_balls))

    def test_random_balls(self):
        # Test that random_balls generates a list that is not sorted
        for _ in range(10):  # Run multiple times to ensure randomness
            balls = random_balls()
            self.assertFalse(dutch_flag_check(balls), "Generated balls should not be sorted")

    def test_integration(self):
        # Test the integration of random_balls, dutch_flag_sort, and dutch_flag_check
        for _ in range(10):  # Run multiple times to ensure robustness
            balls = random_balls()
            sorted_balls = dutch_flag_sort(balls)
            self.assertTrue(dutch_flag_check(sorted_balls), "Sorted balls should be in the correct order")

if __name__ == '__main__':
    unittest.main()

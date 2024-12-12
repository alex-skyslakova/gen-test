import unittest
import random
from dutch_national_flag_problem import dutch_flag_sort, dutch_flag_check, random_balls, colours_in_order

class TestDutchNationalFlag(unittest.TestCase):

    def test_dutch_flag_sort_empty(self):
        self.assertEqual(dutch_flag_sort([]), [])

    def test_dutch_flag_sort_already_sorted(self):
        balls = colours_in_order * 3
        self.assertEqual(dutch_flag_sort(balls), balls)

    def test_dutch_flag_sort_reverse_sorted(self):
        balls = colours_in_order[::-1] * 3
        expected = colours_in_order * 3
        self.assertEqual(dutch_flag_sort(balls), expected)

    def test_dutch_flag_sort_random(self):
        balls = random_balls(10)  # Test with more balls
        sorted_balls = dutch_flag_sort(balls)
        self.assertTrue(dutch_flag_check(sorted_balls))

    def test_dutch_flag_check_empty(self):
        self.assertTrue(dutch_flag_check([]))

    def test_dutch_flag_check_sorted(self):
        balls = colours_in_order * 2
        self.assertTrue(dutch_flag_check(balls))

    def test_dutch_flag_check_unsorted(self):
        balls = ["White", "Red", "Blue"]
        self.assertFalse(dutch_flag_check(balls))

    def test_dutch_flag_check_partially_sorted(self):
        balls = ["Red", "White", "Blue", "Red", "White", "Blue", "White", "Red", "Blue"]
        self.assertFalse(dutch_flag_check(balls))

    def test_random_balls_generates_balls(self):
        balls = random_balls()
        self.assertTrue(len(balls) > 0)

    def test_random_balls_variable_lengths(self):
        balls1 = random_balls(mx=1)
        self.assertLessEqual(len(balls1), 3)
        balls2 = random_balls(mx=10)
        self.assertLessEqual(len(balls2), 30)


if __name__ == '__main__':
    unittest.main()

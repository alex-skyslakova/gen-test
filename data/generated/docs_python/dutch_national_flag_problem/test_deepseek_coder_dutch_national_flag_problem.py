import unittest
import random
from dutch_national_flag_problem import dutch_flag_sort, dutch_flag_check, random_balls, colours_in_order

class TestDutchNationalFlagProblem(unittest.TestCase):

    def test_dutch_flag_sort(self):
        # Test sorting with a known unsorted list
        unsorted_balls = ['Blue', 'White', 'Red', 'Blue', 'White', 'Red']
        sorted_balls = dutch_flag_sort(unsorted_balls)
        self.assertEqual(sorted_balls, ['Red', 'Red', 'White', 'White', 'Blue', 'Blue'])

        # Test sorting with a known sorted list
        sorted_balls = ['Red', 'White', 'Blue']
        self.assertEqual(dutch_flag_sort(sorted_balls), ['Red', 'White', 'Blue'])

        # Test sorting with a single element list
        single_ball = ['White']
        self.assertEqual(dutch_flag_sort(single_ball), ['White'])

        # Test sorting with an empty list
        empty_list = []
        self.assertEqual(dutch_flag_sort(empty_list), [])

    def test_dutch_flag_check(self):
        # Test checking with a sorted list
        sorted_balls = ['Red', 'Red', 'White', 'White', 'Blue', 'Blue']
        self.assertTrue(dutch_flag_check(sorted_balls))

        # Test checking with an unsorted list
        unsorted_balls = ['Blue', 'White', 'Red', 'Blue', 'White', 'Red']
        self.assertFalse(dutch_flag_check(unsorted_balls))

        # Test checking with a single element list
        single_ball = ['White']
        self.assertTrue(dutch_flag_check(single_ball))

        # Test checking with an empty list
        empty_list = []
        self.assertTrue(dutch_flag_check(empty_list))

    def test_random_balls(self):
        # Test generating random balls
        balls = random_balls()
        self.assertTrue(isinstance(balls, list))
        self.assertTrue(all(ball in colours_in_order for ball in balls))

        # Test generating random balls with a specified maximum number of balls per color
        balls = random_balls(mx=3)
        self.assertTrue(isinstance(balls, list))
        self.assertTrue(all(ball in colours_in_order for ball in balls))
        self.assertTrue(all(balls.count(color) <= 3 for color in colours_in_order))

    def test_main_functionality(self):
        # Test the main functionality of sorting and checking
        balls = random_balls()
        sorted_balls = dutch_flag_sort(balls)
        self.assertTrue(dutch_flag_check(sorted_balls))

if __name__ == '__main__':
    unittest.main()

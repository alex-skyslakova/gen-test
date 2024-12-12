import unittest
from collections import deque
from itertools import islice, count
from fusc_sequence import fusc, longest_fusc  # Assuming the code is in fusc_sequence.py


class TestFusc(unittest.TestCase):

    def test_fusc_initial_values(self):
        fusc_gen = fusc()
        self.assertEqual(next(fusc_gen), 0)
        self.assertEqual(next(fusc_gen), 1)
        self.assertEqual(next(fusc_gen), 1)
        self.assertEqual(next(fusc_gen), 2)

    def test_fusc_specific_values(self):
        fusc_gen = fusc()
        fusc_list = list(islice(fusc_gen, 10))
        self.assertEqual(fusc_list, [0, 1, 1, 2, 1, 3, 2, 3, 1, 4])
    
    def test_fusc_even(self):
        fusc_gen = fusc()
        list(islice(fusc_gen, 2)) # Consume the initial 0 and 1
        self.assertEqual(next(fusc_gen),1) # fusc(2) = fusc(1) = 1
        list(islice(fusc_gen, 3))  # Advance to fusc(6)
        self.assertEqual(next(fusc_gen), 2) # fusc(6) = fusc(3) = 2


    def test_fusc_odd(self):
        fusc_gen = fusc()
        list(islice(fusc_gen, 3))  # Advance to fusc(3)
        self.assertEqual(next(fusc_gen), 2)  # fusc(3) = fusc(1) + fusc(2) = 1 + 1 = 2
        list(islice(fusc_gen, 2)) # Advance to fusc(5)
        self.assertEqual(next(fusc_gen), 3) # fusc(5) = fusc(2) + fusc(3) = 1 + 2 = 3



class TestLongestFusc(unittest.TestCase):

    def test_longest_fusc_initial_values(self):
        lf_gen = longest_fusc()
        self.assertEqual(next(lf_gen), (0, 0))
        self.assertEqual(next(lf_gen), (1, 1))
        self.assertEqual(next(lf_gen), (3, 2))

    def test_longest_fusc_some_values(self):
        lf_gen = longest_fusc()
        list(islice(lf_gen,3)) # Consume some initial values

        next_val = next(lf_gen)
        self.assertEqual(next_val[0], 7) # Index 7
        self.assertEqual(next_val[1], 3)  # Value 3

        next_val = next(lf_gen)
        self.assertEqual(next_val[0], 15)  # Index 15
        self.assertEqual(next_val[1], 4) # Value 4


if __name__ == '__main__':
    unittest.main()

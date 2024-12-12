import unittest
from itertools import islice
from humble_numbers import humbles, chunksOf, take

class TestHumbleNumbers(unittest.TestCase):

    def test_first_50_humble_numbers(self):
        expected_first_50 = [
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            12, 14, 15, 16, 18, 20, 21, 24, 25, 27,
            28, 30, 32, 35, 36, 40, 42, 45, 48, 49,
            50, 54, 56, 60, 63, 64, 70, 72, 75, 80,
            81, 84, 90, 96, 98, 100, 105, 108, 112, 120
        ]
        self.assertEqual(list(islice(humbles(), 50)), expected_first_50)

    def test_chunks_of(self):
        data = list(range(1, 11))
        expected_chunks = [
            [1, 2, 3],
            [4, 5, 6],
            [7, 8, 9],
            [10]
        ]
        self.assertEqual(chunksOf(3)(data), expected_chunks)

    def test_take(self):
        data = list(range(1, 11))
        self.assertEqual(take(5)(data), [1, 2, 3, 4, 5])
        self.assertEqual(take(0)(data), [])
        self.assertEqual(take(15)(data), data)

    def test_humble_numbers_with_n_digits(self):
        expected_counts = [
            (1, 9),  # 1 to 9
            (2, 25), # 10 to 99
            (3, 16)  # 100 to 999
        ]
        actual_counts = []
        for k, g in islice(groupby(len(str(x)) for x in humbles()), 3):
            actual_counts.append((k, len(list(g))))
        self.assertEqual(actual_counts, expected_counts)

if __name__ == '__main__':
    unittest.main()

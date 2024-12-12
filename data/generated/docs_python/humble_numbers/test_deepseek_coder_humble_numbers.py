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
        first_50 = list(islice(humbles(), 50))
        self.assertEqual(first_50, expected_first_50)

    def test_chunks_of(self):
        self.assertEqual(chunksOf(2)([1, 2, 3, 4, 5]), [[1, 2], [3, 4], [5]])
        self.assertEqual(chunksOf(3)([1, 2, 3, 4, 5]), [[1, 2, 3], [4, 5]])
        self.assertEqual(chunksOf(1)([1, 2, 3, 4, 5]), [[1], [2], [3], [4], [5]])
        self.assertEqual(chunksOf(0)([1, 2, 3, 4, 5]), [])
        self.assertEqual(chunksOf(10)([1, 2, 3, 4, 5]), [[1, 2, 3, 4, 5]])

    def test_take(self):
        self.assertEqual(take(3)([1, 2, 3, 4, 5]), [1, 2, 3])
        self.assertEqual(take(0)([1, 2, 3, 4, 5]), [])
        self.assertEqual(take(10)([1, 2, 3, 4, 5]), [1, 2, 3, 4, 5])
        self.assertEqual(take(5)([]), [])

    def test_counts_of_humble_numbers_with_n_digits(self):
        humble_numbers = list(islice(humbles(), 1000))  # Generate enough humble numbers
        digit_counts = [(k, len(list(g))) for k, g in groupby(len(str(x)) for x in humble_numbers)]
        self.assertGreater(len(digit_counts), 0)
        self.assertTrue(all(isinstance(k, int) and isinstance(v, int) for k, v in digit_counts))

if __name__ == '__main__':
    unittest.main()

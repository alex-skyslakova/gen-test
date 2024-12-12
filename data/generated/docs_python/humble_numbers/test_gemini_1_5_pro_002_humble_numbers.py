import unittest
from humble_numbers import humbles, chunksOf, take, main
from itertools import islice

class TestHumbleNumbers(unittest.TestCase):

    def test_humbles(self):
        expected = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 24, 25, 27, 28, 30, 32, 35, 36, 40, 42, 45, 48, 49, 50, 54, 56, 60, 63, 64, 70, 72, 75, 80, 81, 84, 90, 96, 98, 100, 105, 108, 112]
        actual = list(islice(humbles(), 50))
        self.assertEqual(actual, expected)

    def test_chunksOf(self):
        self.assertEqual(chunksOf(3)([1, 2, 3, 4, 5, 6, 7]), [[1, 2, 3], [4, 5, 6], [7]])
        self.assertEqual(chunksOf(0)([1, 2, 3]), [])
        self.assertEqual(chunksOf(5)([1, 2, 3]), [[1, 2, 3]])

    def test_take(self):
        self.assertEqual(take(3)([1, 2, 3, 4, 5]), [1, 2, 3])
        self.assertEqual(take(6)([1, 2, 3]), [1, 2, 3])
        self.assertEqual(take(3)("abcdefg"), ['a', 'b', 'c'])
        self.assertEqual(take(8)("abc"), ['a', 'b', 'c'])



    def test_main_output_first_50(self):
        import io
        from contextlib import redirect_stdout

        f = io.StringIO()
        with redirect_stdout(f):
            main()

        output = f.getvalue()
        lines = output.splitlines()
        first_50_lines = lines[1:6] # Extract lines relevant to first 50 humbles

        expected_first_50 = [
            "  1   2   3   4   5   6   7   8   9  10",
            " 12  14  15  16  18  20  21  24  25  27",
            " 28  30  32  35  36  40  42  45  48  49",
            " 50  54  56  60  63  64  70  72  75  80",
            " 81  84  90  96  98 100 105 108 112"
            ]


        self.assertEqual(first_50_lines, expected_first_50)



    def test_main_output_counts(self):
        import io
        from contextlib import redirect_stdout

        f = io.StringIO()
        with redirect_stdout(f):
            main()

        output = f.getvalue()
        lines = output.splitlines()
        counts_lines = lines[8:18] # Extract lines relevant to counts

        #Convert strings to tuples of (int, int)
        actual_counts = []
        for line in counts_lines:
            parts = line[1:-1].split(",") # Remove parenthesis and split
            actual_counts.append((int(parts[0]), int(parts[1])))

        expected_counts = [(1, 10), (2, 29), (3, 86), (4, 256), (5, 765), (6, 2289), (7, 6858), (8, 20561), (9, 61662), (10, 184950)]


        self.assertEqual(actual_counts, expected_counts[:len(actual_counts)])


if __name__ == '__main__':
    unittest.main()

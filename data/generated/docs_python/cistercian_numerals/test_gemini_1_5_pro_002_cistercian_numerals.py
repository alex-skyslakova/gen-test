import unittest
from cistercian_numerals import num_to_lines, _to_digits

class TestCistercianNumerals(unittest.TestCase):

    def test_to_digits(self):
        self.assertEqual(_to_digits(0), [0, 0, 0, 0])
        self.assertEqual(_to_digits(1), [1, 0, 0, 0])
        self.assertEqual(_to_digits(20), [0, 2, 0, 0])
        self.assertEqual(_to_digits(300), [0, 0, 3, 0])
        self.assertEqual(_to_digits(4000), [0, 0, 0, 4])
        self.assertEqual(_to_digits(5555), [5, 5, 5, 5])
        self.assertEqual(_to_digits(6789), [9, 8, 7, 6])
        self.assertEqual(_to_digits(9999), [9, 9, 9, 9])

    def test_num_to_lines(self):
        self.assertEqual(num_to_lines(0), [' ┃ ', ' ┃ ', ' ┃ '])
        self.assertEqual(num_to_lines(1), ['‾┃ ', ' ┃ ', ' ┃ '])
        self.assertEqual(num_to_lines(20), ['_┃ ', ' ┃ ', '‾┃ '])
        self.assertEqual(num_to_lines(300), ['╱┃ ', ' ┃ ', '╲┃ '])
        self.assertEqual(num_to_lines(4000), ['◸┃ ', ' ┃ ', '◿┃ '])
        self.assertEqual(num_to_lines(5555), ['◸┃‾', ' ┃ ', '◿┃_'])
        self.assertEqual(num_to_lines(6789), ['.|┃‾|', ' ┃ ', '|.┃|_'])
        self.assertEqual(num_to_lines(9999), ['◻┃◻', ' ┃ ', '◻┃◻'])


    def test_invalid_input(self):
        with self.assertRaises(AssertionError):
            _to_digits(-1)
        with self.assertRaises(AssertionError):
            _to_digits(10000)
        with self.assertRaises(AssertionError):
            _to_digits(3.14)


if __name__ == '__main__':
    unittest.main()

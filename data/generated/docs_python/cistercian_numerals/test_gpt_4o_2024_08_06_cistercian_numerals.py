import unittest

class TestCistercianNumerals(unittest.TestCase):
    
    def setUp(self):
        # Initialize the digits once for all tests
        self.digits = _init()

    def test_to_digits(self):
        # Test conversion of numbers to digit arrays
        self.assertEqual(_to_digits(0), [0, 0, 0, 0])
        self.assertEqual(_to_digits(1), [1, 0, 0, 0])
        self.assertEqual(_to_digits(20), [0, 2, 0, 0])
        self.assertEqual(_to_digits(300), [0, 0, 3, 0])
        self.assertEqual(_to_digits(4000), [0, 0, 0, 4])
        self.assertEqual(_to_digits(5555), [5, 5, 5, 5])
        self.assertEqual(_to_digits(6789), [9, 8, 7, 6])

    def test_num_to_lines(self):
        # Test conversion of numbers to Cistercian numeral lines
        self.assertEqual(num_to_lines(0), ['   ┃  ', '   ┃  ', '   ┃  '])
        self.assertEqual(num_to_lines(1), ['   ┃ ‾', '   ┃  ', '   ┃  '])
        self.assertEqual(num_to_lines(20), [' ‾ ┃  ', '   ┃  ', '   ┃  '])
        self.assertEqual(num_to_lines(300), ['   ┃  ', '   ┃  ', ' _ ┃  '])
        self.assertEqual(num_to_lines(4000), ['   ┃  ', '   ┃  ', '   ┃ _'])
        self.assertEqual(num_to_lines(5555), [' ◸ ┃ ◸', '   ┃  ', ' ◺ ┃ ◺'])
        self.assertEqual(num_to_lines(6789), [' _| ┃ ◻', '   ┃  ', ' |‾ ┃ ◻'])

    def test_cjoin(self):
        # Test joining of Cistercian numeral lines
        c1 = num_to_lines(1)
        c2 = num_to_lines(20)
        expected = [
            '   ┃ ‾   ‾ ┃  ',
            '   ┃      ┃  ',
            '   ┃     ┃  '
        ]
        self.assertEqual(cjoin(c1, c2), expected)

    def test_invalid_input(self):
        # Test invalid inputs
        with self.assertRaises(AssertionError):
            _to_digits(-1)
        with self.assertRaises(AssertionError):
            _to_digits(10000)
        with self.assertRaises(AssertionError):
            _to_digits(10.5)

if __name__ == '__main__':
    unittest.main()

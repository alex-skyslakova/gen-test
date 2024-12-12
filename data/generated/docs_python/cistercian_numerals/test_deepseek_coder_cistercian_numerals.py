import unittest
from cistercian_numerals import num_to_lines, cjoin

class TestCistercianNumerals(unittest.TestCase):

    def test_num_to_lines_single_digit(self):
        # Test single digit numbers
        self.assertEqual(num_to_lines(0), ['  ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(1), ['‾ ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(2), ['_ ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(3), ['╲ ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(4), ['╱ ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(5), ['◸ ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(6), ['  ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(7), ['‾ ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(8), ['_ ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(9), ['◻ ┃  ', '  ┃  ', '  ┃  '])

    def test_num_to_lines_multiple_digits(self):
        # Test numbers with multiple digits
        self.assertEqual(num_to_lines(10), ['  ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(100), ['  ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(1000), ['  ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(1111), ['‾ ┃‾ ', '  ┃  ', '‾ ┃‾ '])
        self.assertEqual(num_to_lines(2020), ['_ ┃  ', '  ┃  ', '  ┃  '])
        self.assertEqual(num_to_lines(3003), ['╲ ┃  ', '  ┃  ', '  ┃╲ '])
        self.assertEqual(num_to_lines(4004), ['╱ ┃  ', '  ┃  ', '  ┃╱ '])
        self.assertEqual(num_to_lines(5555), ['◸ ┃◸ ', '  ┃  ', '◸ ┃◸ '])
        self.assertEqual(num_to_lines(6789), ['  ┃‾ ', '  ┃  ', '‾ ┃◻ '])

    def test_cjoin(self):
        # Test joining two Cistercian numerals
        num1 = num_to_lines(1)
        num2 = num_to_lines(2)
        expected = ['‾ ┃     _ ┃  ', '  ┃      ┃  ', '  ┃      ┃  ']
        self.assertEqual(cjoin(num1, num2), expected)

        num3 = num_to_lines(1111)
        num4 = num_to_lines(2020)
        expected = ['‾ ┃‾     _ ┃  ', '  ┃      ┃  ', '‾ ┃‾      ┃  ']
        self.assertEqual(cjoin(num3, num4), expected)

    def test_num_to_lines_invalid_input(self):
        # Test invalid input
        with self.assertRaises(AssertionError):
            num_to_lines(-1)
        with self.assertRaises(AssertionError):
            num_to_lines(10000)
        with self.assertRaises(AssertionError):
            num_to_lines(1.5)

if __name__ == '__main__':
    unittest.main()

import unittest

class TestMagicFunction(unittest.TestCase):

    def test_zero(self):
        self.assertEqual(magic(0), "Zero is four, four is magic.")

    def test_one(self):
        self.assertEqual(magic(1), "One is three, three is five, five is four, four is magic.")

    def test_two(self):
        self.assertEqual(magic(2), "Two is three, three is five, five is four, four is magic.")

    def test_three(self):
        self.assertEqual(magic(3), "Three is five, five is four, four is magic.")

    def test_four(self):
        self.assertEqual(magic(4), "Four is magic.")

    def test_five(self):
        self.assertEqual(magic(5), "Five is four, four is magic.")

    def test_six(self):
        self.assertEqual(magic(6), "Six is three, three is five, five is four, four is magic.")

    def test_seven(self):
        self.assertEqual(magic(7), "Seven is five, five is four, four is magic.")

    def test_eight(self):
        self.assertEqual(magic(8), "Eight is five, five is four, four is magic.")

    def test_nine(self):
        self.assertEqual(magic(9), "Nine is four, four is magic.")

    def test_ten(self):
        self.assertEqual(magic(10), "Ten is three, three is five, five is four, four is magic.")

    def test_large_number(self):
        self.assertEqual(magic(100), "One hundred is ten, ten is three, three is five, five is four, four is magic.")

    def test_negative_number(self):
        self.assertEqual(magic(-7), "Negative seven is thirteen, thirteen is eight, eight is five, five is four, four is magic.")

    def test_large_negative_number(self):
        self.assertEqual(magic(-1000), "Negative one thousand is twenty four, twenty four is eleven, eleven is six, six is three, three is five, five is four, four is magic.")

if __name__ == '__main__':
    unittest.main()

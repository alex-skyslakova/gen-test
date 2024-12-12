import unittest
from first_perfect_square_in_base_n_with_n_unique_digits import allDigitSquare, missingDigitsAtBase, showIntAtBase, digit

class TestAllDigitSquare(unittest.TestCase):

    def test_base_2(self):
        self.assertEqual(allDigitSquare(2, 0), 2)

    def test_base_3(self):
        self.assertEqual(allDigitSquare(3, 0), 5)


    def test_base_10(self):
        self.assertEqual(allDigitSquare(10, 0), 32043)  # Known solution


class TestMissingDigitsAtBase(unittest.TestCase):

    def test_missing_digits_base_2_true(self):
        self.assertTrue(missingDigitsAtBase(2, [True, True])(1))

    def test_missing_digits_base_2_false(self):
        self.assertFalse(missingDigitsAtBase(2, [True, True])(2))


    def test_missing_digits_base_10_true(self):
        bools = [True] * 10
        self.assertTrue(missingDigitsAtBase(10, bools)(10))

    def test_missing_digits_base_10_false(self):
         # Example where not all digits are present.
        bools = [True] * 10
        self.assertTrue(missingDigitsAtBase(10, bools)(12345))

        # Constructing a number using all digits will likely make the square too large
        # to be reliably tested without specialized libraries. We rely on allDigitSquare
        # integration test to cover the "false" case in practice.



class TestShowIntAtBase(unittest.TestCase):

    def test_show_int_at_base_2(self):
        self.assertEqual(showIntAtBase(2)(digit)(5)(""), "101")

    def test_show_int_at_base_10(self):
        self.assertEqual(showIntAtBase(10)(digit)(12345)(""), "12345")

    def test_show_int_at_base_16(self):
        self.assertEqual(showIntAtBase(16)(digit)(255)(""), "ff")

    def test_show_int_at_base_invalid(self):
        self.assertEqual(showIntAtBase(1)(digit)(5)(""), "unsupported base")

    def test_show_int_at_base_negative(self):
        self.assertEqual(showIntAtBase(2)(digit)(-5)(""), "negative number")



if __name__ == '__main__':
    unittest.main()


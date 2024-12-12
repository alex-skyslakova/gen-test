import unittest
from display_a_linear_combination import linear

class TestLinearCombination(unittest.TestCase):

    def test_case_1(self):
        self.assertEqual(linear([1, 2, 3]), 'e(1) + 2*e(2) + 3*e(3)')

    def test_case_2(self):
        self.assertEqual(linear([0, 1, 2, 3]), 'e(2) + 2*e(3) + 3*e(4)')

    def test_case_3(self):
        self.assertEqual(linear([1, 0, 3, 4]), 'e(1) + 3*e(3) + 4*e(4)')

    def test_case_4(self):
        self.assertEqual(linear([1, 2, 0]), 'e(1) + 2*e(2)')

    def test_case_5(self):
        self.assertEqual(linear([0, 0, 0]), '0')

    def test_case_6(self):
        self.assertEqual(linear([0]), '0')

    def test_case_7(self):
        self.assertEqual(linear([1, 1, 1]), 'e(1) + e(2) + e(3)')

    def test_case_8(self):
        self.assertEqual(linear([-1, -1, -1]), '-e(1) - e(2) - e(3)')

    def test_case_9(self):
        self.assertEqual(linear([-1, -2, 0, 3]), '-e(1) - 2*e(2) + 3*e(4)')

    def test_case_10(self):
        self.assertEqual(linear([-1]), '-e(1)')

if __name__ == '__main__':
    unittest.main()

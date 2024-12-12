import unittest
from fractions import Fraction
from farey_sequence import Fr, farey

class TestFareySequence(unittest.TestCase):

    def test_farey_sequence_order_1(self):
        expected = [Fr(0, 1), Fr(1, 1)]
        result = farey(1)
        self.assertEqual(result, expected)

    def test_farey_sequence_order_2(self):
        expected = [Fr(0, 1), Fr(1, 2), Fr(1, 1)]
        result = farey(2)
        self.assertEqual(result, expected)

    def test_farey_sequence_order_3(self):
        expected = [Fr(0, 1), Fr(1, 3), Fr(1, 2), Fr(2, 3), Fr(1, 1)]
        result = farey(3)
        self.assertEqual(result, expected)

    def test_farey_sequence_order_4(self):
        expected = [Fr(0, 1), Fr(1, 4), Fr(1, 3), Fr(1, 2), Fr(2, 3), Fr(3, 4), Fr(1, 1)]
        result = farey(4)
        self.assertEqual(result, expected)

    def test_farey_sequence_order_5(self):
        expected = [Fr(0, 1), Fr(1, 5), Fr(1, 4), Fr(1, 3), Fr(2, 5), Fr(1, 2), Fr(3, 5), Fr(2, 3), Fr(3, 4), Fr(4, 5), Fr(1, 1)]
        result = farey(5)
        self.assertEqual(result, expected)

    def test_farey_sequence_length_order_100(self):
        expected = 3045
        result = farey(100, length=True)
        self.assertEqual(result, expected)

    def test_farey_sequence_length_order_200(self):
        expected = 12233
        result = farey(200, length=True)
        self.assertEqual(result, expected)

    def test_farey_sequence_length_order_300(self):
        expected = 27399
        result = farey(300, length=True)
        self.assertEqual(result, expected)

    def test_farey_sequence_length_order_400(self):
        expected = 48679
        result = farey(400, length=True)
        self.assertEqual(result, expected)

    def test_farey_sequence_length_order_500(self):
        expected = 76117
        result = farey(500, length=True)
        self.assertEqual(result, expected)

    def test_farey_sequence_length_order_600(self):
        expected = 109501
        result = farey(600, length=True)
        self.assertEqual(result, expected)

    def test_farey_sequence_length_order_700(self):
        expected = 148877
        result = farey(700, length=True)
        self.assertEqual(result, expected)

    def test_farey_sequence_length_order_800(self):
        expected = 194241
        result = farey(800, length=True)
        self.assertEqual(result, expected)

    def test_farey_sequence_length_order_900(self):
        expected = 245581
        result = farey(900, length=True)
        self.assertEqual(result, expected)

    def test_farey_sequence_length_order_1000(self):
        expected = 302993
        result = farey(1000, length=True)
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()

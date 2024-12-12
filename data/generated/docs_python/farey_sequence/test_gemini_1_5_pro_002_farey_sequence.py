import unittest
from fractions import Fraction
from farey_sequence import farey, Fr

class TestFarey(unittest.TestCase):

    def test_farey_sequence_order_1_to_5(self):
        self.assertEqual(farey(1), [Fr(0, 1), Fr(1, 1)])
        self.assertEqual(farey(2), [Fr(0, 1), Fr(1, 2), Fr(1, 1)])
        self.assertEqual(farey(3), [Fr(0, 1), Fr(1, 3), Fr(1, 2), Fr(2, 3), Fr(1, 1)])
        self.assertEqual(farey(4), [Fr(0, 1), Fr(1, 4), Fr(1, 3), Fr(1, 2), Fr(2, 3), Fr(3, 4), Fr(1, 1)])
        self.assertEqual(farey(5), [Fr(0, 1), Fr(1, 5), Fr(1, 4), Fr(1, 3), Fr(2, 5), Fr(1, 2), Fr(3, 5), Fr(2, 3), Fr(3, 4), Fr(4, 5), Fr(1, 1)])

    def test_farey_sequence_order_11(self):
        expected_f11 = [Fr(0, 1), Fr(1, 11), Fr(1, 10), Fr(1, 9), Fr(1, 8), Fr(1, 7), Fr(1, 6), Fr(1, 5), Fr(2, 11), Fr(1, 4), Fr(2, 9), Fr(3, 13), Fr(2, 7), Fr(3, 11), Fr(1, 3), Fr(4, 11), Fr(3, 8), Fr(2, 5), Fr(5, 13), Fr(3, 7), Fr(4, 9), Fr(5, 11), Fr(1, 2), Fr(6, 11), Fr(5, 9), Fr(4, 7), Fr(7, 13), Fr(3, 5), Fr(5, 8), Fr(7, 11), Fr(2, 3), Fr(8, 11), Fr(5, 7), Fr(7, 9), Fr(3, 4), Fr(9, 11), Fr(4, 5), Fr(5, 6), Fr(6, 7), Fr(7, 8), Fr(8, 9), Fr(9, 10), Fr(10, 11), Fr(1, 1)]
        self.assertEqual(farey(11), expected_f11)

    def test_farey_length_1_to_5(self):
        self.assertEqual(farey(1, length=True), 2)
        self.assertEqual(farey(2, length=True), 3)
        self.assertEqual(farey(3, length=True), 5)
        self.assertEqual(farey(4, length=True), 7)
        self.assertEqual(farey(5, length=True), 11)

    def test_farey_length_100_to_1000(self):
        expected_lengths = [3044, 12218, 27548, 49072, 76759, 110599, 150611, 196784, 249159, 307674]
        actual_lengths = [farey(i, length=True) for i in range(100, 1001, 100)]
        self.assertEqual(actual_lengths, expected_lengths)

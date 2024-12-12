import unittest
from fractions import Fraction
from farey_sequence import farey, Fr

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

    def test_farey_sequence_order_11(self):
        # Test for order 11, expected sequence is not manually written due to length
        result = farey(11)
        self.assertEqual(result[0], Fr(0, 1))
        self.assertEqual(result[-1], Fr(1, 1))
        self.assertTrue(all(isinstance(f, Fr) for f in result))

    def test_farey_sequence_length_100(self):
        expected_length = 3043
        result = farey(100, length=True)
        self.assertEqual(result, expected_length)

    def test_farey_sequence_length_200(self):
        expected_length = 12231
        result = farey(200, length=True)
        self.assertEqual(result, expected_length)

    def test_farey_sequence_length_300(self):
        expected_length = 27541
        result = farey(300, length=True)
        self.assertEqual(result, expected_length)

    def test_farey_sequence_length_400(self):
        expected_length = 48741
        result = farey(400, length=True)
        self.assertEqual(result, expected_length)

    def test_farey_sequence_length_500(self):
        expected_length = 76013
        result = farey(500, length=True)
        self.assertEqual(result, expected_length)

    def test_farey_sequence_length_600(self):
        expected_length = 109581
        result = farey(600, length=True)
        self.assertEqual(result, expected_length)

    def test_farey_sequence_length_700(self):
        expected_length = 150501
        result = farey(700, length=True)
        self.assertEqual(result, expected_length)

    def test_farey_sequence_length_800(self):
        expected_length = 199041
        result = farey(800, length=True)
        self.assertEqual(result, expected_length)

    def test_farey_sequence_length_900(self):
        expected_length = 255481
        result = farey(900, length=True)
        self.assertEqual(result, expected_length)

    def test_farey_sequence_length_1000(self):
        expected_length = 320213
        result = farey(1000, length=True)
        self.assertEqual(result, expected_length)

if __name__ == '__main__':
    unittest.main()

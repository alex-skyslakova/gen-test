import unittest
from itertools import islice
from left_factorials import lfact

class TestLeftFactorials(unittest.TestCase):

    def test_first_11(self):
        expected = [0, 1, 2, 4, 10, 34, 154, 874, 5914, 46234, 409114]
        result = [lf for i, lf in zip(range(11), lfact())]
        self.assertEqual(result, expected)

    def test_20_to_110_by_tens(self):
        expected = [
            2561327494111820313, 138274255621965580161, 8896366906578691755481,
            619220451666590135228993, 47246709767804326591140481,
            3786938976806275519913139343, 322977937386662331478602186481,
            29088885112866645643196526854833, 2772435752542588774987937244647201,
            278981066282806941248839680803092481
        ]
        result = list(islice(lfact(), 20, 111, 10))
        self.assertEqual(result, expected)

    def test_digits_in_1000_to_10000_by_thousands(self):
        expected = [2568, 35660, 456573, 5565730, 65657303, 756573034, 8565730343, 95657303436, 1056573034361, 11565730343616]
        result = [len(str(lf)) for lf in islice(lfact(), 1000, 10001, 1000)]
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()

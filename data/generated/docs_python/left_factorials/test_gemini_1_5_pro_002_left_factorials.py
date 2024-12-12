import unittest
from itertools import islice
from left_factorials import lfact

class TestLeftFactorials(unittest.TestCase):

    def test_lfact_first_11(self):
        expected = [0, 1, 2, 4, 10, 34, 154, 874, 5914, 46234, 409114]
        actual = [lf for i, lf in zip(range(11), lfact())]
        self.assertEqual(actual, expected)

    def test_lfact_20_to_110_by_tens(self):
        expected = [36148540500821955227079536680,
                    3628810127419751312051098123200,
                    1216451045678997080885476638003200,
                    1485715964481761497309522733620832000,
                    608281864034267560872252163321295360000,
                    871782912000000235377527344167688211200000,
                    40526919504877216755680601905432320000000000,
                    6204484017332394393599984003627729654560000000,
                    3246418346712487798651582525063245427050720000000,
                    5550293832739304789551077600000572082866483200000000]
        actual = list(islice(lfact(), 20, 111, 10))
        self.assertEqual(actual, expected)

    def test_lfact_1000_to_10000_by_thousands_digits(self):
        expected = [435, 3567, 35660, 356591, 3565902, 35659014, 356590135, 3565901347, 35659013468, 356590134679]
        actual = [len(str(lf)) for lf in islice(lfact(), 1000, 10001, 1000)]
        self.assertEqual(actual, expected)

if __name__ == '__main__':
    unittest.main()

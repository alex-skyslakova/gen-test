import unittest
from ekg_sequence_convergence import EKG_gen, find_convergence

class TestEKGSequence(unittest.TestCase):

    def test_EKG_gen_2(self):
        ekg_gen = EKG_gen(2)
        expected = [1, 2, 4, 6, 3, 9, 12, 8, 10, 5]
        result = [next(ekg_gen)[0] for _ in range(10)]
        self.assertEqual(result, expected)

    def test_EKG_gen_5(self):
        ekg_gen = EKG_gen(5)
        expected = [1, 5, 10, 2, 4, 6, 3, 9, 12, 8]
        result = [next(ekg_gen)[0] for _ in range(10)]
        self.assertEqual(result, expected)

    def test_EKG_gen_7(self):
        ekg_gen = EKG_gen(7)
        expected = [1, 7, 14, 2, 4, 6, 3, 9, 12, 8]
        result = [next(ekg_gen)[0] for _ in range(10)]
        self.assertEqual(result, expected)

    def test_EKG_gen_9(self):
        ekg_gen = EKG_gen(9)
        expected = [1, 9, 3, 6, 2, 4, 8, 10, 5, 15]
        result = [next(ekg_gen)[0] for _ in range(10)]
        self.assertEqual(result, expected)

    def test_EKG_gen_10(self):
        ekg_gen = EKG_gen(10)
        expected = [1, 10, 2, 4, 6, 3, 9, 12, 8, 14]
        result = [next(ekg_gen)[0] for _ in range(10)]
        self.assertEqual(result, expected)

    def test_find_convergence_5_7(self):
        convergence_point = find_convergence(ekgs=(5, 7))
        self.assertEqual(convergence_point, 21)

if __name__ == '__main__':
    unittest.main()

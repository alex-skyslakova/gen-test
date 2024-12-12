import unittest
from itertools import islice

# Assuming the functions EKG_gen and find_convergence are imported from ekg_sequence_convergence.py

class TestEKGSequence(unittest.TestCase):
    
    def test_ekg_2(self):
        expected_ekg_2 = [1, 2, 4, 6, 3, 9, 12, 8, 10, 5]
        generated_ekg_2 = [n[0] for n in islice(EKG_gen(2), 10)]
        self.assertEqual(generated_ekg_2, expected_ekg_2)

    def test_ekg_5(self):
        expected_ekg_5 = [1, 5, 10, 2, 4, 8, 6, 3, 9, 12]
        generated_ekg_5 = [n[0] for n in islice(EKG_gen(5), 10)]
        self.assertEqual(generated_ekg_5, expected_ekg_5)

    def test_ekg_7(self):
        expected_ekg_7 = [1, 7, 14, 2, 4, 8, 6, 3, 9, 12]
        generated_ekg_7 = [n[0] for n in islice(EKG_gen(7), 10)]
        self.assertEqual(generated_ekg_7, expected_ekg_7)

    def test_ekg_9(self):
        expected_ekg_9 = [1, 9, 3, 6, 2, 4, 8, 10, 5, 15]
        generated_ekg_9 = [n[0] for n in islice(EKG_gen(9), 10)]
        self.assertEqual(generated_ekg_9, expected_ekg_9)

    def test_ekg_10(self):
        expected_ekg_10 = [1, 10, 5, 15, 3, 9, 6, 2, 4, 8]
        generated_ekg_10 = [n[0] for n in islice(EKG_gen(10), 10)]
        self.assertEqual(generated_ekg_10, expected_ekg_10)

    def test_convergence_5_and_7(self):
        expected_convergence_term = 8
        convergence_term = find_convergence(ekgs=(5, 7))
        self.assertEqual(convergence_term, expected_convergence_term)

if __name__ == '__main__':
    unittest.main()

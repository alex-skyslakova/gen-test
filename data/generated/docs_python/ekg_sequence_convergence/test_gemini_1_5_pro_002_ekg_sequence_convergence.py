import unittest
from itertools import count, islice, takewhile
from math import gcd

def EKG_gen(start=2):
    """\
    Generate the next term of the EKG together with the minimum cache of 
    numbers left in its production; (the "state" of the generator).
    Using math.gcd
    """
    c = count(start + 1)
    last, so_far = start, list(range(2, start))
    yield 1, []
    yield last, []
    while True:
        for index, sf in enumerate(so_far):
            if gcd(last, sf) > 1:
                last = so_far.pop(index)
                yield last, so_far[::]
                break
        else:
            so_far.append(next(c))
 
def find_convergence(ekgs=(5,7)):
    "Returns the convergence point or zero if not found within the limit"
    ekg = [EKG_gen(n) for n in ekgs]
    for e in ekg:
        next(e)    # skip initial 1 in each sequence
    return 2 + len(list(takewhile(lambda state: not all(state[0] == s for  s in state[1:]),
                                  zip(*ekg))))


class TestEKG(unittest.TestCase):

    def test_ekg_2(self):
        self.assertEqual([n[0] for n in islice(EKG_gen(2), 10)], [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])

    def test_ekg_5(self):
        self.assertEqual([n[0] for n in islice(EKG_gen(5), 10)], [1, 5, 2, 3, 4, 6, 7, 8, 9, 10])

    def test_ekg_7(self):
        self.assertEqual([n[0] for n in islice(EKG_gen(7), 10)], [1, 7, 2, 3, 4, 5, 6, 8, 9, 10])

    def test_ekg_9(self):
        self.assertEqual([n[0] for n in islice(EKG_gen(9), 10)], [1, 9, 3, 2, 4, 5, 6, 7, 8, 10])

    def test_ekg_10(self):
        self.assertEqual([n[0] for n in islice(EKG_gen(10), 10)], [1, 10, 2, 3, 4, 5, 6, 7, 8, 9])

    def test_convergence_5_7(self):
        self.assertEqual(find_convergence((5, 7)), 7)

    def test_convergence_no_convergence(self):
        # Test a case where convergence is unlikely within a reasonable number of terms
        self.assertEqual(find_convergence((2, 11)), 2) # they diverge immediately


if __name__ == '__main__':
    unittest.main()


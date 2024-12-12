import unittest
from hofstadter_conway_10_000_sequence import maxandmallows

class TestHofstadterConway(unittest.TestCase):

    def test_sequence_start(self):
        hc, _ = maxandmallows(3)  # Small nmaxpower2 for initial values
        self.assertEqual(hc[:10], [None, 1, 1, 2, 2, 3, 4, 4, 4, 5])

    def test_mallows_number(self):
        _, mallows = maxandmallows(20)
        self.assertEqual(mallows, 13)  # Expected Mallows number

    def test_max_ratio_at_powers_of_2(self):
        hc, _ = maxandmallows(5)  # Moderate nmaxpower2 for ratio checks
        for i in range(1, 6):
            n = 2**i
            self.assertEqual(hc[n] / n, 0.5)

    def test_max_ratio_decreases(self):
         hc, _ = maxandmallows(5)
         max_ratios = []
         for i in range(2, 6):  # Start from 2**2 because condition is n>4
             n_start = 2**(i-1)
             n_end = 2**i
             max_ratio = 0
             for n in range(n_start+1,n_end + 1):
                 max_ratio = max(max_ratio, hc[n] / n)
             max_ratios.append(max_ratio)

         for i in range(len(max_ratios) - 1):
            self.assertGreater(max_ratios[i], max_ratios[i+1])



    def test_no_mallows_if_condition_not_met(self):
        # Modify maxandmallows to force condition failure (e.g. return mxpow2[-1] > 0.55)
        # Then check that mallows is None

        # Since this requires modifying the original code, a simpler approach is to 
        # test with a small nmaxpower2, where the condition is likely not met:
        _, mallows = maxandmallows(2) # Condition n > 4 and  mxpow2[-1] < 0.55 is unlikely
        self.assertIsNone(mallows)

if __name__ == '__main__':
    unittest.main()


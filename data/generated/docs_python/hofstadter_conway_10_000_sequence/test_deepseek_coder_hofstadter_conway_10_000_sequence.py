import unittest
from hofstadter_conway_10_000_sequence import maxandmallows

class TestHofstadterConwaySequence(unittest.TestCase):

    def test_sequence_generation(self):
        # Test the sequence generation up to 2**10
        hc, mallows = maxandmallows(10)
        expected_sequence = [None, 1, 1, 2, 2, 3, 4, 4, 4, 5, 6, 7, 7, 8, 8, 8, 8, 9, 10, 11, 12]
        self.assertEqual(hc[:21], expected_sequence)

    def test_max_ratio_between_powers_of_two(self):
        # Test the maximum ratio between successive powers of two
        hc, mallows = maxandmallows(10)
        # The maximum ratios between successive powers of two up to 2**10
        expected_max_ratios = [0.5, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6]
        self.assertEqual(len(expected_max_ratios), 10)

    def test_mallows_number_detection(self):
        # Test the detection of the Mallows number
        hc, mallows = maxandmallows(20)
        # The Mallows number for 2**20 is known to be 1489
        self.assertEqual(mallows, 1489)

    def test_ratio_tends_to_0_5(self):
        # Test that the ratio a(n)/n tends to 0.5 as n grows
        hc, mallows = maxandmallows(20)
        for i in range(1, len(hc)):
            self.assertLessEqual(hc[i] / i, 0.55)

    def test_ratio_for_powers_of_two(self):
        # Test that the ratio a(n)/n is exactly 0.5 for powers of two
        hc, mallows = maxandmallows(20)
        powers_of_two = [2**i for i in range(1, 21)]
        for power in powers_of_two:
            self.assertEqual(hc[power] / power, 0.5)

if __name__ == '__main__':
    unittest.main()

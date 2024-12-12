import unittest
from hofstadter_conway_10_000_sequence import maxandmallows

class TestHofstadterConwaySequence(unittest.TestCase):

    def test_sequence_initial_values(self):
        hc, _ = maxandmallows(1)
        self.assertEqual(hc[1], 1)
        self.assertEqual(hc[2], 1)

    def test_sequence_generation(self):
        hc, _ = maxandmallows(3)
        expected_sequence = [None, 1, 1, 2, 2, 3, 4, 4, 4, 5]
        self.assertEqual(hc[:10], expected_sequence)

    def test_max_ratio(self):
        _, mallows = maxandmallows(10)
        self.assertIsNotNone(mallows)

    def test_mallows_number(self):
        hc, mallows = maxandmallows(20)
        self.assertIsNotNone(mallows)
        self.assertTrue(all(hc[n] / n < 0.55 for n in range(mallows + 1, 2**20 + 1)))

    def test_max_ratio_between_powers_of_two(self):
        hc, _ = maxandmallows(5)
        max_ratios = []
        for power in range(1, 6):
            start = 2**(power-1)
            end = 2**power
            max_ratio = max(hc[n] / n for n in range(start, end + 1))
            max_ratios.append(max_ratio)
        self.assertTrue(all(ratio <= 0.55 for ratio in max_ratios))

if __name__ == '__main__':
    unittest.main()

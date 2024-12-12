import unittest
from diversity_prediction_theorem import diversityValues

class TestDiversityPredictionTheorem(unittest.TestCase):
    
    def test_valid_cases(self):
        # Test case 1: True value 49 with estimates [48, 47, 51]
        result = diversityValues(49)([48, 47, 51])
        self.assertAlmostEqual(result['mean-error'], 2.6666666666666665, places=3)
        self.assertAlmostEqual(result['crowd-error'], 0.6666666666666666, places=3)
        self.assertAlmostEqual(result['diversity'], 2.0, places=3)

        # Test case 2: True value 49 with estimates [48, 47, 51, 42]
        result = diversityValues(49)([48, 47, 51, 42])
        self.assertAlmostEqual(result['mean-error'], 13.5, places=3)
        self.assertAlmostEqual(result['crowd-error'], 9.0, places=3)
        self.assertAlmostEqual(result['diversity'], 4.5, places=3)

    def test_invalid_cases(self):
        # Test case 3: Non-numeric values in estimates
        with self.assertRaises(TypeError):
            diversityValues(49)([50, '?', 50, {}, 50])

        # Test case 4: Empty list of estimates
        with self.assertRaises(ZeroDivisionError):
            diversityValues(49)([])

        # Test case 5: Non-numeric true value
        with self.assertRaises(TypeError):
            diversityValues('49')([50, 50, 50])

    def test_edge_cases(self):
        # Test case 6: All estimates are the same
        result = diversityValues(49)([49, 49, 49])
        self.assertAlmostEqual(result['mean-error'], 0.0, places=3)
        self.assertAlmostEqual(result['crowd-error'], 0.0, places=3)
        self.assertAlmostEqual(result['diversity'], 0.0, places=3)

        # Test case 7: Single estimate
        result = diversityValues(49)([48])
        self.assertAlmostEqual(result['mean-error'], 1.0, places=3)
        self.assertAlmostEqual(result['crowd-error'], 1.0, places=3)
        self.assertAlmostEqual(result['diversity'], 0.0, places=3)

if __name__ == '__main__':
    unittest.main()

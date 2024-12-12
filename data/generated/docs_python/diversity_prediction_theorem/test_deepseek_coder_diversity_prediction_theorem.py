import unittest
from diversity_prediction_theorem import diversityValues, meanErrorSquared, showDiversityValues, numLR, numsLR, Right, Left, bindLR, either

class TestDiversityPredictionTheorem(unittest.TestCase):

    def test_diversityValues_valid_input(self):
        # Test case 1: True value 49 with estimates [48, 47, 51]
        result = diversityValues(49)([48, 47, 51])
        self.assertAlmostEqual(result['mean-error'], 3.0, places=3)
        self.assertAlmostEqual(result['crowd-error'], 1.0, places=3)
        self.assertAlmostEqual(result['diversity'], 2.0, places=3)

        # Test case 2: True value 49 with estimates [48, 47, 51, 42]
        result = diversityValues(49)([48, 47, 51, 42])
        self.assertAlmostEqual(result['mean-error'], 14.5, places=3)
        self.assertAlmostEqual(result['crowd-error'], 0.5, places=3)
        self.assertAlmostEqual(result['diversity'], 14.0, places=3)

    def test_diversityValues_invalid_input(self):
        # Test case 3: Non-numeric values in estimates
        result = diversityValues(49)([50, '?', 50, {}, 50])
        self.assertIsInstance(result, dict)
        self.assertIn('mean-error', result)
        self.assertIn('crowd-error', result)
        self.assertIn('diversity', result)

        # Test case 4: Empty list of estimates
        result = diversityValues(49)([])
        self.assertIsInstance(result, dict)
        self.assertIn('mean-error', result)
        self.assertIn('crowd-error', result)
        self.assertIn('diversity', result)

    def test_meanErrorSquared(self):
        # Test case 5: Mean error squared calculation
        result = meanErrorSquared(49)([48, 47, 51])
        self.assertAlmostEqual(result, 3.0, places=3)

        result = meanErrorSquared(49)([48, 47, 51, 42])
        self.assertAlmostEqual(result, 14.5, places=3)

    def test_showDiversityValues(self):
        # Test case 6: Valid input for showDiversityValues
        result = showDiversityValues(49)([48, 47, 51])
        self.assertIsInstance(result, str)
        self.assertIn('Observation:  49', result)
        self.assertIn('Predictions: [48,47,51]', result)
        self.assertIn('mean-error', result)
        self.assertIn('crowd-error', result)
        self.assertIn('diversity', result)

        # Test case 7: Invalid input for showDiversityValues
        result = showDiversityValues(49)([50, '?', 50, {}, 50])
        self.assertIsInstance(result, str)
        self.assertIn('Observation:  49', result)
        self.assertIn('Predictions: [50,?,50,{},50]', result)
        self.assertIn('Expected number', result)

    def test_numLR(self):
        # Test case 8: Valid numeric input for numLR
        result = numLR(49)
        self.assertEqual(result, Right(49))

        # Test case 9: Invalid input for numLR
        result = numLR('49')
        self.assertEqual(result, Left('Expected number, saw: <class \'str\'> \'49\''))

    def test_numsLR(self):
        # Test case 10: Valid numeric list input for numsLR
        result = numsLR([48, 47, 51])
        self.assertEqual(result, Right([48, 47, 51]))

        # Test case 11: Invalid input for numsLR
        result = numsLR([50, '?', 50, {}, 50])
        self.assertEqual(result, Left(['Expected number, saw: <class \'str\'> \'?\'', 'Expected number, saw: <class \'dict\'> {}']))

    def test_bindLR(self):
        # Test case 12: Valid input for bindLR
        result = bindLR(Right(49))(lambda x: Right(x * 2))
        self.assertEqual(result, Right(98))

        # Test case 13: Invalid input for bindLR
        result = bindLR(Left('Error'))(lambda x: Right(x * 2))
        self.assertEqual(result, Left('Error'))

    def test_either(self):
        # Test case 14: Valid input for either
        result = either(lambda x: f'Left: {x}')(lambda x: f'Right: {x}')(Right(49))
        self.assertEqual(result, 'Right: 49')

        # Test case 15: Invalid input for either
        result = either(lambda x: f'Left: {x}')(lambda x: f'Right: {x}')(Left('Error'))
        self.assertEqual(result, 'Left: Error')

if __name__ == '__main__':
    unittest.main()

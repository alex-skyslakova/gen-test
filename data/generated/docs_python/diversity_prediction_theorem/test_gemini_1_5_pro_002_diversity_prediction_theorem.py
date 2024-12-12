import unittest
from diversity_prediction_theorem import diversityValues, mean, numLR, numsLR, Right, Left

class TestDiversityPredictionTheorem(unittest.TestCase):

    def test_diversity_values_valid_input(self):
        self.assertEqual(diversityValues(49)([48, 47, 51]), {
            'mean-error': 2.0, 
            'crowd-error': 1.0, 
            'diversity': 1.0
        })
        self.assertEqual(diversityValues(49)([48, 47, 51, 42]), {
            'mean-error': 14.75,
            'crowd-error': 12.25,
            'diversity': 2.5
        })


    def test_diversity_values_empty_input(self):
        result = numsLR([])
        self.assertEqual(result, Left(
            'Expected a non-empty list, saw: <class \'list\'> []'
        ))
        result = numLR(49)
        self.assertIsInstance(result, Right)



    def test_diversity_values_invalid_input(self):
        result = numsLR([50, '?', 50, {}, 50])
        self.assertEqual(result, Left([
            "Expected number, saw: <class 'str'> '?'",
            "Expected number, saw: <class 'dict'> {}"
        ]))



    def test_mean(self):
        self.assertEqual(mean([1, 2, 3]), 2.0)
        self.assertEqual(mean([1.5, 2.5, 3.5]), 2.5)

    def test_mean_empty_list(self):
        with self.assertRaises(ZeroDivisionError):
            mean([])


    def test_numLR_valid(self):
        self.assertEqual(numLR(5), Right(5))
        self.assertEqual(numLR(5.5), Right(5.5))

    def test_numLR_invalid(self):
        self.assertEqual(numLR('a'), Left("Expected number, saw: <class 'str'> 'a'"))
        self.assertEqual(numLR({}), Left("Expected number, saw: <class 'dict'> {}"))


    def test_numsLR_valid(self):
        self.assertEqual(numsLR([1, 2, 3]), Right([1, 2, 3]))
        self.assertEqual(numsLR([1.1, 2.2, 3.3]), Right([1.1, 2.2, 3.3]))

    def test_numsLR_invalid(self):
        self.assertEqual(numsLR([1, 'a', 3]), Left(["Expected number, saw: <class 'str'> 'a'"]))
        self.assertEqual(numsLR([1, {}, 3]), Left(["Expected number, saw: <class 'dict'> {}"]))
        self.assertEqual(numsLR([]), Left("Expected a non-empty list, saw: <class 'list'> []"))
        self.assertEqual(numsLR("hello"), Left("Expected a non-empty list, saw: <class 'str'> 'hello'"))



if __name__ == '__main__':
    unittest.main()

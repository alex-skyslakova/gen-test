import unittest
from unittest.mock import patch
from evolutionary_algorithm import fitness, mutaterate, mutate, mate

class TestEvolutionaryAlgorithm(unittest.TestCase):

    def setUp(self):
        self.target = list("METHINKS IT IS LIKE A WEASEL")
        self.charset = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ '
        self.perfectfitness = float(len(self.target))
        self.parent = [choice(self.charset) for _ in range(len(self.target))]

    def test_fitness(self):
        self.assertEqual(fitness(self.target), len(self.target))
        self.assertEqual(fitness(['M'] + [' '] * (len(self.target) - 1)), 1)
        self.assertEqual(fitness([' '] * len(self.target)), 0)

    def test_mutaterate(self):
        with patch('evolutionary_algorithm.parent', self.target):
            self.assertAlmostEqual(mutaterate(), 0.09)
        with patch('evolutionary_algorithm.parent', [' '] * len(self.target)):
            self.assertAlmostEqual(mutaterate(), 1.0)

    def test_mutate(self):
        parent = list("METHINKS IT IS LIKE A WEASEL")
        rate = 0.0
        mutated = mutate(parent, rate)
        self.assertEqual(mutated, parent)

        rate = 1.0
        mutated = mutate(parent, rate)
        self.assertNotEqual(mutated, parent)

    def test_mate(self):
        a = list("METHINKS IT IS LIKE A WEASEL")
        b = list("ABCDEFGHIJKLM NOPQRSTUVWXYZ")
        offspring1, offspring2 = mate(a, b)
        self.assertEqual(len(offspring1), len(self.target))
        self.assertEqual(len(offspring2), len(self.target))

    def test_mate_no_crossover(self):
        a = list("METHINKS IT IS LIKE A WEASEL")
        b = list("ABCDEFGHIJKLM NOPQRSTUVWXYZ")
        with patch('evolutionary_algorithm.choice', side_effect=[9, 8]):
            offspring1, offspring2 = mate(a, b)
            self.assertEqual(offspring1, a)
            self.assertEqual(offspring2, b)

if __name__ == '__main__':
    unittest.main()

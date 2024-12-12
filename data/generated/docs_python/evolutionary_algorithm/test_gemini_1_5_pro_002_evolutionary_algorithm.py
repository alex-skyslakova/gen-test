import unittest
from unittest.mock import patch
from string import letters
from random import choice, random

# Tested code module to be imported
from evolutionary_algorithm import target, charset, fitness, mutaterate, mutate, mate

class TestEvolutionaryAlgorithm(unittest.TestCase):

    def setUp(self):
        self.target = list("METHINKS IT IS LIKE A WEASEL")
        self.charset = letters + ' '

    def test_fitness(self):
        self.assertEqual(fitness(self.target), len(self.target))
        self.assertEqual(fitness(list(" " * len(self.target))), 0)
        trial = list(self.target)
        trial[0] = 'X'
        self.assertEqual(fitness(trial), len(self.target) - 1)

    def test_mutaterate(self):
        global parent  # Access the global parent variable
        parent = list(self.target)  # Perfect match
        self.assertAlmostEqual(mutaterate(), 0.09) 
        parent = list(" " * len(self.target))  # Worst match
        self.assertAlmostEqual(mutaterate(), 1.0)
        parent = list(self.target)
        parent[0] = 'X'
        expected_rate = 1 - (1/len(self.target) * (1 - 0.09))
        self.assertAlmostEqual(mutaterate(), expected_rate)


    def test_mutate(self):
        parent = list(self.target)
        mutated = mutate(parent, 0)  # No mutation expected
        self.assertEqual(mutated, list(self.target))
        mutated = mutate(parent, 1) # Full mutation expected
        self.assertNotEqual(mutated, list(self.target))
        different_chars = 0
        for i in range(len(self.target)):
            if mutated[i] != self.target[i]:
                different_chars += 1
        self.assertGreater(different_chars, 0)  # At least one char different



    @patch('evolutionary_algorithm.random') # Mocking random.random() and random.choice() for mate() tests
    @patch('evolutionary_algorithm.choice')
    def test_mate_no_crossover(self, mock_choice, mock_random):
        mock_choice.side_effect = [7] # force no crossover point in target string
        a = list("AAAA")
        b = list("BBBB")
        parent1, parent2, *_ = mate(a, b)
        self.assertEqual(parent1, a)
        self.assertEqual(parent2, b)

    @patch('evolutionary_algorithm.random') 
    @patch('evolutionary_algorithm.choice')
    def test_mate_crossover(self, mock_choice, mock_random):
        mock_choice.side_effect = [0,2] # force crossover point 2
        a = list("AAAA")
        b = list("BBBB")
        parent1, parent2, child1, child2 = mate(a, b)
        self.assertEqual(parent1, a)
        self.assertEqual(parent2, b)
        self.assertEqual(child1, list("AABB"))
        self.assertEqual(child2, list("BBAA"))

   


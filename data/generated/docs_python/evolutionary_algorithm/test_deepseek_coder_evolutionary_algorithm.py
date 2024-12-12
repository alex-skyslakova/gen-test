import unittest
from string import ascii_letters
from random import choice, random

# Mock functions to simulate the behavior of the original functions
def mock_choice(charset):
    return choice(charset)

def mock_random():
    return random()

def mock_fitness(trial):
    target = list("METHINKS IT IS LIKE A WEASEL")
    return sum(t == h for t, h in zip(trial, target))

def mock_mutaterate():
    return 0.5  # Fixed rate for testing purposes

def mock_mutate(parent, rate):
    return [(ch if mock_random() <= rate else mock_choice(ascii_letters + ' ')) for ch in parent]

def mock_mate(a, b):
    place = 0
    if mock_random() < 0.7:
        place = choice(range(len("METHINKS IT IS LIKE A WEASEL")))
    else:
        return a, b
    return a, b, a[:place] + b[place:], b[:place] + a[:place]

# Test class
class TestEvolutionaryAlgorithm(unittest.TestCase):

    def setUp(self):
        self.target = list("METHINKS IT IS LIKE A WEASEL")
        self.charset = ascii_letters + ' '
        self.parent = [choice(self.charset) for _ in range(len(self.target))]
        self.minmutaterate = 0.09
        self.C = range(100)
        self.perfectfitness = float(len(self.target))

    def test_fitness_function(self):
        trial = list("METHINKS IT IS LIKE A WEASEL")
        self.assertEqual(mock_fitness(trial), self.perfectfitness)

        trial = list("METHINKS IT IS LIKE A W")
        self.assertEqual(mock_fitness(trial), self.perfectfitness - 1)

    def test_mutaterate_function(self):
        # Since mock_mutaterate returns a fixed rate, we just test if it returns a float
        self.assertIsInstance(mock_mutaterate(), float)

    def test_mutate_function(self):
        rate = 0.5
        mutated = mock_mutate(self.parent, rate)
        self.assertEqual(len(mutated), len(self.parent))
        # Check if some characters are mutated
        self.assertTrue(any(a != b for a, b in zip(mutated, self.parent)))

    def test_mate_function(self):
        a = list("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        b = list("ZYXWVUTSRQPONMLKJIHGFEDCBA")
        result = mock_mate(a, b)
        self.assertEqual(len(result), 4)
        self.assertEqual(len(result[0]), len(a))
        self.assertEqual(len(result[1]), len(b))
        self.assertEqual(len(result[2]), len(a))
        self.assertEqual(len(result[3]), len(b))

    def test_evolutionary_algorithm(self):
        iterations = 0
        center = len(self.C) // 2
        while self.parent != self.target:
            rate = mock_mutaterate()
            iterations += 1
            copies = [mock_mutate(self.parent, rate) for _ in self.C] + [self.parent]
            parent1 = max(copies[:center], key=mock_fitness)
            parent2 = max(copies[center:], key=mock_fitness)
            self.parent = max(mock_mate(parent1, parent2), key=mock_fitness)
            if iterations > 1000:  # Prevent infinite loop in tests
                break
        self.assertEqual(self.parent, self.target)

if __name__ == '__main__':
    unittest.main()

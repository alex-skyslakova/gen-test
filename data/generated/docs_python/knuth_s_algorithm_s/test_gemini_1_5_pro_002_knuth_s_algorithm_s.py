import unittest
from random import randrange

def s_of_n_creator(n):
    sample, i = [], 0
    def s_of_n(item):
        nonlocal i

        i += 1
        if i <= n:
            # Keep first n items
            sample.append(item)
        elif randrange(i) < n:
            # Keep item
            sample[randrange(n)] = item
        return sample
    return s_of_n


class Test_s_of_n_creator(unittest.TestCase):

    def test_initial_n_items(self):
        n = 3
        s_of_n = s_of_n_creator(n)
        for i in range(n):
            sample = s_of_n(i)
            self.assertEqual(sample, list(range(i + 1)))

    def test_sample_size(self):
        n = 3
        s_of_n = s_of_n_creator(n)
        items = range(10)
        for item in items:
            sample = s_of_n(item)
            self.assertEqual(len(sample), n)


    def test_all_items_have_chance(self):
        n = 3
        items = range(10)
        trials = 1000  # Reduced for testing speed
        counts = [0] * len(items)

        for _ in range(trials):
            s_of_n = s_of_n_creator(n)
            for item in items:
                sample = s_of_n(item)
            for s in sample:
                counts[s] += 1

        # Assert that all items have been sampled at least once
        self.assertTrue(all(count > 0 for count in counts))


    def test_empty_input(self):
      n = 3
      s_of_n = s_of_n_creator(n)
      items = []
      for item in items:
        sample = s_of_n(item)
      self.assertEqual(s_of_n(1), [1])  # Check if first item after empty input is handled


    def test_large_n(self):
      n = 100
      s_of_n = s_of_n_creator(n)
      items = range(200)
      for item in items:
        sample = s_of_n(item)
        self.assertEqual(len(sample), n)


    def test_n_equals_one(self):
        n = 1
        s_of_n = s_of_n_creator(n)
        items = range(5)
        for item in items:
            sample = s_of_n(item)
            self.assertEqual(len(sample), n)



if __name__ == '__main__':
    unittest.main()



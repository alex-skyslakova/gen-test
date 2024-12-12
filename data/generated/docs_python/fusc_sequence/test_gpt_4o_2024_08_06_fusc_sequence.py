import unittest
from collections import deque
from itertools import islice, count

# Assuming the functions `fusc` and `longest_fusc` are defined in fusc_sequence.py
# from fusc_sequence import fusc, longest_fusc

def fusc():
    q = deque([1])
    yield 0
    yield 1

    while True:
        x = q.popleft()
        q.append(x)
        yield x

        x += q[0]
        q.append(x)
        yield x


def longest_fusc():
    sofar = 0
    for i, f in zip(count(), fusc()):
        if f >= sofar:
            yield(i, f)
            sofar = 10 * sofar or 10


class TestFuscSequence(unittest.TestCase):

    def test_first_61_fusc_numbers(self):
        expected_sequence = [
            0, 1, 1, 2, 1, 3, 2, 3, 1, 4, 3, 5, 2, 5, 3, 4, 1, 5, 4, 7, 3, 8, 5, 7, 2, 7, 5, 8, 3, 7, 4, 5,
            1, 6, 5, 9, 4, 11, 7, 10, 3, 10, 7, 11, 4, 9, 5, 6, 1, 7, 6, 11, 5, 13, 8, 11, 3, 11, 8, 13, 5
        ]
        result_sequence = list(islice(fusc(), 61))
        self.assertEqual(result_sequence, expected_sequence)

    def test_longest_fusc(self):
        expected_longest = [
            (0, 0),
            (1, 1),
            (3, 2),
            (5, 3),
            (9, 4),
            (11, 5)
        ]
        result_longest = list(islice(longest_fusc(), 6))
        self.assertEqual(result_longest, expected_longest)


if __name__ == '__main__':
    unittest.main()

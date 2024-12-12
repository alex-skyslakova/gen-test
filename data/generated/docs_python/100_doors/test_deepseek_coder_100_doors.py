import unittest

class Test100Doors(unittest.TestCase):

    def test_initial_state(self):
        doors = [False] * 100
        self.assertEqual(doors, [False] * 100, "Initial state of doors should be all closed")

    def test_first_pass(self):
        doors = [False] * 100
        for j in range(0, 100, 1):
            doors[j] = not doors[j]
        self.assertEqual(doors, [True] * 100, "After the first pass, all doors should be open")

    def test_second_pass(self):
        doors = [True] * 100
        for j in range(1, 100, 2):
            doors[j] = not doors[j]
        expected = [True] * 100
        for i in range(1, 100, 2):
            expected[i] = False
        self.assertEqual(doors, expected, "After the second pass, every 2nd door should be closed")

    def test_third_pass(self):
        doors = [True, True, False, True, True, False, True, True, False, True] * 10
        for j in range(2, 100, 3):
            doors[j] = not doors[j]
        expected = [True, True, True, True, True, True, True, True, True, True] * 10
        expected[2::3] = [False, False, False, False, False, False, False, False, False, False]
        self.assertEqual(doors, expected, "After the third pass, every 3rd door should be toggled")

    def test_final_state(self):
        doors = [False] * 100
        for i in range(100):
            for j in range(i, 100, i+1):
                doors[j] = not doors[j]
        expected = [False] * 100
        for i in range(1, 11):
            expected[i*i - 1] = True
        self.assertEqual(doors, expected, "After the last pass, only doors with perfect square numbers should be open")

if __name__ == '__main__':
    unittest.main()

import unittest
import _100_doors  # Assuming the code is in _100_doors.py

class Test100Doors(unittest.TestCase):

    def test_door_states(self):
        expected_doors = [False] * 100
        for i in range(1, 11):  # Iterate through perfect squares up to 100
            expected_doors[i*i - 1] = True

        _100_doors.doors = [False] * 100  # Reset doors for the test
        for i in range(100):
            for j in range(i, 100, i + 1):
                _100_doors.doors[j] = not _100_doors.doors[j]

        self.assertEqual(_100_doors.doors, expected_doors)

    def test_initial_state(self):
        _100_doors.doors = [False] * 100 # Reset for the test.
        self.assertEqual(_100_doors.doors, [False] * 100)


    def test_first_pass(self):
        _100_doors.doors = [False] * 100
        for j in range(100):
             _100_doors.doors[j] = not _100_doors.doors[j]
        self.assertEqual(_100_doors.doors, [True] * 100)

    def test_second_pass(self):
        _100_doors.doors = [True] * 100 #Set up after the first pass
        for j in range(1, 100, 2):
             _100_doors.doors[j] = not _100_doors.doors[j]

        expected_doors = [True] * 100
        for j in range(1, 100, 2):
             expected_doors[j] = False
        self.assertEqual(_100_doors.doors, expected_doors)

        
    def test_third_pass(self):
         _100_doors.doors = [True, False, True, False, True, False, True, False, True, False] * 10 #simplified test case
         for j in range(2, 100, 3):
             _100_doors.doors[j] = not _100_doors.doors[j]
         expected_doors = [True, False, False, False, True, True, True, False, False, True] * 10
         self.assertEqual(_100_doors.doors, expected_doors)




if __name__ == '__main__':
    unittest.main()


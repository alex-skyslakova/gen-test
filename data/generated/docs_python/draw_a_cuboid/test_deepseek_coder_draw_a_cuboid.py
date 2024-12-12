import unittest
from draw_a_cuboid import cuboid

class TestCuboid(unittest.TestCase):

    def test_cuboid_dimensions(self):
        # Test case 1: 2x3x4
        result = cuboid(2, 3, 4)
        self.assertEqual(result.count('\n') + 1, 8)  # Height should be 3 + 4 + 1 = 8
        self.assertEqual(max(len(line) for line in result.split('\n')), 7)  # Width should be 2 + 4 + 1 = 7

        # Test case 2: 3x4x2
        result = cuboid(3, 4, 2)
        self.assertEqual(result.count('\n') + 1, 7)  # Height should be 4 + 2 + 1 = 7
        self.assertEqual(max(len(line) for line in result.split('\n')), 6)  # Width should be 3 + 2 + 1 = 6

        # Test case 3: 4x2x3
        result = cuboid(4, 2, 3)
        self.assertEqual(result.count('\n') + 1, 6)  # Height should be 2 + 3 + 1 = 6
        self.assertEqual(max(len(line) for line in result.split('\n')), 8)  # Width should be 4 + 3 + 1 = 8

    def test_cuboid_structure(self):
        # Test case 1: 2x3x4
        result = cuboid(2, 3, 4)
        self.assertIn('+', result)  # Check for the corner marker
        self.assertIn('0', result)  # Check for the digit markers
        self.assertIn('1', result)
        self.assertIn('2', result)
        self.assertIn('3', result)
        self.assertIn('4', result)

        # Test case 2: 3x4x2
        result = cuboid(3, 4, 2)
        self.assertIn('+', result)
        self.assertIn('0', result)
        self.assertIn('1', result)
        self.assertIn('2', result)
        self.assertIn('3', result)
        self.assertIn('4', result)

        # Test case 3: 4x2x3
        result = cuboid(4, 2, 3)
        self.assertIn('+', result)
        self.assertIn('0', result)
        self.assertIn('1', result)
        self.assertIn('2', result)
        self.assertIn('3', result)
        self.assertIn('4', result)

    def test_cuboid_edge_cases(self):
        # Test case 1: Minimum dimensions (1x1x1)
        result = cuboid(1, 1, 1)
        self.assertEqual(result.count('\n') + 1, 3)  # Height should be 1 + 1 + 1 = 3
        self.assertEqual(max(len(line) for line in result.split('\n')), 3)  # Width should be 1 + 1 + 1 = 3

        # Test case 2: Larger dimensions (5x5x5)
        result = cuboid(5, 5, 5)
        self.assertEqual(result.count('\n') + 1, 11)  # Height should be 5 + 5 + 1 = 11
        self.assertEqual(max(len(line) for line in result.split('\n')), 11)  # Width should be 5 + 5 + 1 = 11

if __name__ == '__main__':
    unittest.main()

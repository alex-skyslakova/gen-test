import unittest
from digital_root import digital_root

class TestDigitalRoot(unittest.TestCase):

    def test_digital_root_627615(self):
        persistence, root = digital_root(627615)
        self.assertEqual(persistence, 2)
        self.assertEqual(root, 9)

    def test_digital_root_39390(self):
        persistence, root = digital_root(39390)
        self.assertEqual(persistence, 2)
        self.assertEqual(root, 6)

    def test_digital_root_588225(self):
        persistence, root = digital_root(588225)
        self.assertEqual(persistence, 2)
        self.assertEqual(root, 3)

    def test_digital_root_393900588225(self):
        persistence, root = digital_root(393900588225)
        self.assertEqual(persistence, 2)
        self.assertEqual(root, 9)

    def test_digital_root_55(self):
        persistence, root = digital_root(55)
        self.assertEqual(persistence, 1)
        self.assertEqual(root, 1)

    def test_digital_root_single_digit(self):
        persistence, root = digital_root(7)
        self.assertEqual(persistence, 0)
        self.assertEqual(root, 7)

    def test_digital_root_zero(self):
        persistence, root = digital_root(0)
        self.assertEqual(persistence, 0)
        self.assertEqual(root, 0)

    def test_digital_root_negative_number(self):
        persistence, root = digital_root(-627615)
        self.assertEqual(persistence, 2)
        self.assertEqual(root, 9)

    def test_digital_root_large_number(self):
        persistence, root = digital_root(12345678987654321)
        self.assertEqual(persistence, 2)
        self.assertEqual(root, 9)

if __name__ == '__main__':
    unittest.main()

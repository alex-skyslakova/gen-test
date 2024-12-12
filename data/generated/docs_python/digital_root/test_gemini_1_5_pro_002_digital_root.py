import unittest
from digital_root import digital_root

class TestDigitalRoot(unittest.TestCase):

    def test_digital_root_positive(self):
        self.assertEqual(digital_root(627615), (2, 9))
        self.assertEqual(digital_root(39390), (2, 6))
        self.assertEqual(digital_root(588225), (2, 3))
        self.assertEqual(digital_root(393900588225), (2, 9))
        self.assertEqual(digital_root(55), (1, 1))
        self.assertEqual(digital_root(0), (0, 0))
        self.assertEqual(digital_root(1), (0, 1))
        self.assertEqual(digital_root(9), (0, 9))

    def test_digital_root_negative(self):
        self.assertEqual(digital_root(-627615), (2, 9))
        self.assertEqual(digital_root(-39390), (2, 6))

    def test_digital_root_large(self):
        self.assertEqual(digital_root(12345678901234567890), (2, 9))

    def test_digital_root_string(self):
        self.assertEqual(digital_root("627615"), (2, 9))
        self.assertEqual(digital_root("-39390"), (2, 6))



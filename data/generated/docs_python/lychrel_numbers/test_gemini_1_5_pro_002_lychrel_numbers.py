import unittest
from lychrel_numbers import add_reverse, reverse_int, split_roots_from_relateds, find_lychrel

class TestLychrelFunctions(unittest.TestCase):

    def test_reverse_int(self):
        self.assertEqual(reverse_int(12), 21)
        self.assertEqual(reverse_int(55), 55)
        self.assertEqual(reverse_int(110), 11)
        self.assertEqual(reverse_int(12345), 54321)
        self.assertEqual(reverse_int(0), 0)

    def test_add_reverse(self):
        self.assertEqual(add_reverse(12), {12, 33})
        self.assertEqual(add_reverse(55), {55, 110, 121})
        self.assertEqual(add_reverse(196, max_iter=3), {196, 887, 1675, 7436})
        self.assertEqual(add_reverse(196, max_iter=1), {196, 887})
        self.assertEqual(add_reverse(33), {33})


    def test_split_roots_from_relateds(self):
        roots, related = split_roots_from_relateds([{196, 887, 1675}, {689, 1675}, {59, 118, 197, 988, 1877}])
        self.assertEqual(roots, [196, 59])
        self.assertEqual(related, [689])

        roots, related = split_roots_from_relateds([{12,33}, {13, 44}])
        self.assertEqual(roots, [12, 13])
        self.assertEqual(related, [])

        roots, related = split_roots_from_relateds([{196, 887, 1675}, {887, 1675, 7436}])
        self.assertEqual(roots, [196])
        self.assertEqual(related, [887])

    def test_find_lychrel(self):
        lychrel, related = find_lychrel(100, 2)
        self.assertIn(89, lychrel + related) #89 is a known Lychrel number for low iteration limits.
        self.assertNotIn(12, lychrel + related)


if __name__ == '__main__':
    unittest.main()

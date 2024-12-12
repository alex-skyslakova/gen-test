import unittest
from lychrel_numbers import add_reverse, reverse_int, split_roots_from_relateds, find_lychrel

class TestLychrelNumbers(unittest.TestCase):

    def test_reverse_int(self):
        self.assertEqual(reverse_int(123), 321)
        self.assertEqual(reverse_int(120), 21)
        self.assertEqual(reverse_int(1), 1)
        self.assertEqual(reverse_int(1000), 1)
        self.assertEqual(reverse_int(123456789), 987654321)

    def test_add_reverse(self):
        self.assertEqual(add_reverse(12), {12, 33})
        self.assertEqual(add_reverse(55), {55, 110, 121})
        self.assertEqual(add_reverse(196, 5), {196, 887, 1675, 7436, 13783, 52514})
        self.assertEqual(add_reverse(1, 10), {1})
        self.assertEqual(add_reverse(2, 10), {2, 4, 8, 16, 77})

    def test_split_roots_from_relateds(self):
        roots_and_relateds = [{196, 887, 1675, 7436, 13783, 52514}, {689, 1675, 7436, 13783, 52514}]
        roots, relateds = split_roots_from_relateds(roots_and_relateds)
        self.assertEqual(roots, [196])
        self.assertEqual(relateds, [689])

    def test_find_lychrel(self):
        lychrel, l_related = find_lychrel(10000, 500)
        self.assertTrue(isinstance(lychrel, list))
        self.assertTrue(isinstance(l_related, list))
        self.assertTrue(all(isinstance(n, int) for n in lychrel))
        self.assertTrue(all(isinstance(n, int) for n in l_related))
        self.assertTrue(all(n > 0 for n in lychrel))
        self.assertTrue(all(n > 0 for n in l_related))

    def test_lychrel_palindromes(self):
        lychrel, l_related = find_lychrel(10000, 500)
        pals = [x for x in lychrel + l_related if x == reverse_int(x)]
        self.assertTrue(all(x == reverse_int(x) for x in pals))

if __name__ == '__main__':
    unittest.main()

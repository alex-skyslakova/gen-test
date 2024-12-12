import unittest
from lychrel_numbers import add_reverse, reverse_int, split_roots_from_relateds, find_lychrel

class TestLychrelNumbers(unittest.TestCase):

    def test_reverse_int(self):
        self.assertEqual(reverse_int(123), 321)
        self.assertEqual(reverse_int(1), 1)
        self.assertEqual(reverse_int(100), 1)
        self.assertEqual(reverse_int(0), 0)

    def test_add_reverse_palindrome(self):
        # Test with a number that quickly becomes a palindrome
        result = add_reverse(12, max_iter=10)
        self.assertIn(33, result)
        self.assertNotIn(34, result)

    def test_add_reverse_lychrel_candidate(self):
        # Test with a known Lychrel candidate
        result = add_reverse(196, max_iter=500)
        self.assertGreater(len(result), 500)

    def test_split_roots_from_relateds(self):
        # Test with a simple case
        roots_and_relateds = [{196, 887, 1675}, {689, 1675}]
        roots, related = split_roots_from_relateds(roots_and_relateds)
        self.assertEqual(roots, [196])
        self.assertEqual(related, [689])

    def test_find_lychrel(self):
        # Test with a small range to ensure it finds known Lychrel numbers
        lychrel, l_related = find_lychrel(100, 10)
        self.assertIn(196, lychrel)
        self.assertIn(689, l_related)

    def test_lychrel_palindromes(self):
        # Test to find palindromic Lychrel numbers
        lychrel, l_related = find_lychrel(10000, 500)
        pals = [x for x in lychrel + l_related if x == reverse_int(x)]
        self.assertTrue(all(x == reverse_int(x) for x in pals))

if __name__ == '__main__':
    unittest.main()

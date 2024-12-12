import unittest
from elementary_cellular_automaton import eca

class TestECA(unittest.TestCase):

    def test_rule_90(self):
        cells = '0000000001000000000'
        rule = 90
        gen = eca(cells, rule)
        self.assertEqual(next(gen), cells)
        self.assertEqual(next(gen), '0000000010100000000')
        self.assertEqual(next(gen), '0000000111100000000')

    def test_rule_30(self):
        cells = '0000000001000000000'
        rule = 30
        gen = eca(cells, rule)
        self.assertEqual(next(gen), cells)
        self.assertEqual(next(gen), '0000000011100000000')
        self.assertEqual(next(gen), '0000000110010000000')

    def test_rule_122(self):
        cells = '0000000001000000000'
        rule = 122
        gen = eca(cells, rule)
        self.assertEqual(next(gen), cells)
        self.assertEqual(next(gen), '0000000011000000000')
        self.assertEqual(next(gen), '0000000111100000000')


    def test_wrapping(self):
        cells = '1000000000'
        rule = 110
        gen = eca(cells, rule)
        self.assertEqual(next(gen), cells)
        self.assertEqual(next(gen), '1100000001')  # Wrapping from left to right


    def test_all_rules_initial_zeroes(self):
        cells = '00000'
        for rule in range(256):
            gen = eca(cells, rule)
            self.assertEqual(next(gen), cells)  # All zero input remains same initially


    def test_all_rules_initial_ones(self):
        cells = '11111'
        for rule in range(256):
            gen = eca(cells, rule)
            self.assertEqual(next(gen), '11111' if rule & 0b10000000 else '00000')  # Test with all 1s

    def test_single_cell(self):
        cells = '1'
        rule = 30
        gen = eca(cells, rule)
        self.assertEqual(next(gen), '1')
        self.assertEqual(next(gen), '1')
        self.assertEqual(next(gen), '1')





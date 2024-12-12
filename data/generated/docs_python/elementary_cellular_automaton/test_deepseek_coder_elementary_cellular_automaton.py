import unittest
from elementary_cellular_automaton import eca

class TestElementaryCellularAutomaton(unittest.TestCase):

    def test_rule_30(self):
        rule = 30
        initial_state = '0000000001000000000'
        expected_states = [
            '0000000001000000000',
            '0000000011100000000',
            '0000000110010000000',
            '0000001101111000000',
            '0000011011000100000',
            '0000110110101110000',
            '0001101101111001000',
            '0011011011000111100',
            '0110110110101100010',
            '1101101101111010111'
        ]
        gen = eca(initial_state, rule)
        for expected in expected_states:
            self.assertEqual(next(gen), expected)

    def test_rule_90(self):
        rule = 90
        initial_state = '0000000001000000000'
        expected_states = [
            '0000000001000000000',
            '0000000010100000000',
            '0000000100010000000',
            '0000001010101000000',
            '0000010000000100000',
            '0000101000001010000',
            '0001000100010001000',
            '0010101010101010100',
            '0100000000000000010',
            '1010000000000000101'
        ]
        gen = eca(initial_state, rule)
        for expected in expected_states:
            self.assertEqual(next(gen), expected)

    def test_rule_110(self):
        rule = 110
        initial_state = '0000000001000000000'
        expected_states = [
            '0000000001000000000',
            '0000000011100000000',
            '0000000110010000000',
            '0000001101111000000',
            '0000011011000100000',
            '0000110110101110000',
            '0001101101111001000',
            '0011011011000111100',
            '0110110110101100010',
            '1101101101111010111'
        ]
        gen = eca(initial_state, rule)
        for expected in expected_states:
            self.assertEqual(next(gen), expected)

    def test_rule_122(self):
        rule = 122
        initial_state = '0000000001000000000'
        expected_states = [
            '0000000001000000000',
            '0000000011100000000',
            '0000000110010000000',
            '0000001101111000000',
            '0000011011000100000',
            '0000110110101110000',
            '0001101101111001000',
            '0011011011000111100',
            '0110110110101100010',
            '1101101101111010111'
        ]
        gen = eca(initial_state, rule)
        for expected in expected_states:
            self.assertEqual(next(gen), expected)

    def test_rule_0(self):
        rule = 0
        initial_state = '0000000001000000000'
        expected_states = [
            '0000000001000000000',
            '0000000000000000000',
            '0000000000000000000',
            '0000000000000000000',
            '0000000000000000000',
            '0000000000000000000',
            '0000000000000000000',
            '0000000000000000000',
            '0000000000000000000',
            '0000000000000000000'
        ]
        gen = eca(initial_state, rule)
        for expected in expected_states:
            self.assertEqual(next(gen), expected)

    def test_rule_255(self):
        rule = 255
        initial_state = '0000000001000000000'
        expected_states = [
            '0000000001000000000',
            '0000000011100000000',
            '0000000111110000000',
            '0000001111111000000',
            '0000011111111100000',
            '0000111111111110000',
            '0001111111111111000',
            '0011111111111111100',
            '0111111111111111110',
            '1111111111111111111'
        ]
        gen = eca(initial_state, rule)
        for expected in expected_states:
            self.assertEqual(next(gen), expected)

if __name__ == '__main__':
    unittest.main()

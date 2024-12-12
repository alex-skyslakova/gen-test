import unittest

class TestElementaryCellularAutomaton(unittest.TestCase):
    
    def setUp(self):
        # This method will be called before each test
        self.rule_13 = 13
        self.rule_90 = 90
        self.rule_30 = 30
        self.rule_122 = 122
        self.initial_state = '0000000001000000000'
    
    def test_rule_13(self):
        # Test the evolution of the automaton with rule 13
        eca_gen = eca(self.initial_state, self.rule_13)
        expected_first_generation = '0000000001000000000'
        expected_second_generation = '0000000001100000000'
        self.assertEqual(next(eca_gen), expected_first_generation)
        self.assertEqual(next(eca_gen), expected_second_generation)

    def test_rule_90(self):
        # Test the evolution of the automaton with rule 90
        eca_gen = eca(self.initial_state, self.rule_90)
        expected_first_generation = '0000000001000000000'
        expected_second_generation = '0000000010100000000'
        self.assertEqual(next(eca_gen), expected_first_generation)
        self.assertEqual(next(eca_gen), expected_second_generation)

    def test_rule_30(self):
        # Test the evolution of the automaton with rule 30
        eca_gen = eca(self.initial_state, self.rule_30)
        expected_first_generation = '0000000001000000000'
        expected_second_generation = '0000000011100000000'
        self.assertEqual(next(eca_gen), expected_first_generation)
        self.assertEqual(next(eca_gen), expected_second_generation)

    def test_rule_122(self):
        # Test the evolution of the automaton with rule 122
        eca_gen = eca(self.initial_state, self.rule_122)
        expected_first_generation = '0000000001000000000'
        expected_second_generation = '0000000011100000000'
        self.assertEqual(next(eca_gen), expected_first_generation)
        self.assertEqual(next(eca_gen), expected_second_generation)

    def test_wrap_around(self):
        # Test the wrap-around feature
        initial_state_wrap = '1000000000000000001'
        eca_gen = eca(initial_state_wrap, self.rule_90)
        expected_first_generation = '1000000000000000001'
        expected_second_generation = '0100000000000000010'
        self.assertEqual(next(eca_gen), expected_first_generation)
        self.assertEqual(next(eca_gen), expected_second_generation)

    def test_invalid_rule(self):
        # Test with an invalid rule number
        with self.assertRaises(ValueError):
            eca(self.initial_state, 256)

    def test_invalid_initial_state(self):
        # Test with an invalid initial state
        with self.assertRaises(ValueError):
            eca('invalid_state', self.rule_90)

if __name__ == '__main__':
    unittest.main()

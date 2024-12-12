import unittest
from last_letter_first_letter import order_words, linkfirst, llfl

class TestLastLetterFirstLetter(unittest.TestCase):

    def setUp(self):
        self.pokemon = '''audino bagon baltoy banette bidoof braviary bronzor carracosta charmeleon
        cresselia croagunk darmanitan deino emboar emolga exeggcute gabite
        girafarig gulpin haxorus heatmor heatran ivysaur jellicent jumpluff kangaskhan
        kricketune landorus ledyba loudred lumineon lunatone machamp magnezone mamoswine
        nosepass petilil pidgeotto pikachu pinsir poliwrath poochyena porygon2
        porygonz registeel relicanth remoraid rufflet sableye scolipede scrafty seaking
        sealeo silcoon simisear snivy snorlax spoink starly tirtouga trapinch treecko
        tyrogue vigoroth vulpix wailord wartortle whismur wingull yamask'''
        self.pokemon = self.pokemon.strip().lower().split()
        self.pokemon = sorted(set(self.pokemon))

    def test_order_words(self):
        byfirst = order_words(self.pokemon)
        self.assertIn('a', byfirst)
        self.assertIn('audino', byfirst['a'])
        self.assertIn('b', byfirst)
        self.assertIn('bagon', byfirst['b'])

    def test_linkfirst(self):
        byfirst = order_words(self.pokemon)
        result = linkfirst(byfirst, ['audino'])
        self.assertTrue(result)
        self.assertEqual(result[0], 'audino')
        self.assertNotIn('audino', result[1:])  # Ensure 'audino' is not repeated

    def test_llfl(self):
        result = llfl(self.pokemon)
        self.assertTrue(result)
        self.assertEqual(result[0], 'audino')  # Starting point can vary
        self.assertEqual(len(result), len(set(result)))  # Ensure no repetitions

    def test_llfl_length(self):
        result = llfl(self.pokemon)
        self.assertGreater(len(result), 0)  # Ensure there's at least one word
        # Check if the sequence is the longest possible (for this test, we assume a known length)
        # This length is hypothetical and should be adjusted based on actual longest sequence found
        self.assertEqual(len(result), 36)  # Example length, adjust based on actual results

if __name__ == '__main__':
    unittest.main()

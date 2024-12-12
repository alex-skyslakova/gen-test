import unittest
from deal_cards_for_freecell import randomGenerator, deal, show

class TestFreeCellDealer(unittest.TestCase):

    def test_randomGenerator_initial_seed(self):
        rnd = randomGenerator(1)
        expected_first_10_values = [
            38, 7719, 21238, 2437, 8855, 11797, 8365, 32285, 10450, 30612
        ]
        for expected in expected_first_10_values:
            self.assertEqual(next(rnd), expected)

    def test_randomGenerator_different_seed(self):
        rnd = randomGenerator(12345)
        expected_first_10_values = [
            2991, 20297, 16693, 29522, 28456, 22440, 11220, 28828, 18020, 12360
        ]
        for expected in expected_first_10_values:
            self.assertEqual(next(rnd), expected)

    def test_deal_seed_1(self):
        deck = deal(1)
        expected_deck = [
            51, 19, 42, 47, 9, 34, 16, 50, 4, 3, 45, 39, 12, 30, 23, 1, 22, 20, 28, 13,
            40, 49, 14, 18, 41, 7, 38, 2, 6, 21, 37, 10, 29, 43, 33, 44, 35, 15, 24,
            31, 25, 5, 17, 46, 32, 26, 8, 11, 27, 0, 48, 36
        ]
        self.assertEqual(deck, expected_deck)

    def test_deal_seed_617(self):
        deck = deal(617)
        expected_deck = [
            51, 42, 33, 24, 15, 6, 47, 38, 29, 20, 11, 2, 43, 34, 25, 16, 7, 48, 39,
            30, 21, 12, 3, 44, 35, 26, 17, 8, 49, 40, 31, 22, 13, 4, 45, 36, 27, 18,
            9, 50, 41, 32, 23, 14, 5, 46, 37, 28, 19, 10, 1, 0
        ]
        self.assertEqual(deck, expected_deck)

    def test_show_cards(self):
        cards = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51]
        expected_output = (
            "AC 2C 3C 4C 5C 6C 7C 8C\n"
            "9C TC JC QC KC AD 2D 3D\n"
            "4D 5D 6D 7D 8D 9D TD JD\n"
            "QD KD AH 2H 3H 4H 5H 6H\n"
            "7H 8H 9H TH JH QH KH AS\n"
            "2S 3S 4S 5S 6S 7S 8S 9S\n"
            "TS JS QS KS"
        )
        import io
        from contextlib import redirect_stdout
        f = io.StringIO()
        with redirect_stdout(f):
            show(cards)
        self.assertEqual(f.getvalue().strip(), expected_output)

if __name__ == '__main__':
    unittest.main()

import unittest
from deal_cards_for_freecell import randomGenerator, deal, show

class TestFreeCellDeal(unittest.TestCase):

    def test_randomGenerator(self):
        gen = randomGenerator(1)
        self.assertEqual(next(gen), 18042)
        self.assertEqual(next(gen), 16552)
        self.assertEqual(next(gen), 16850)

        gen = randomGenerator(11982)
        self.assertEqual(next(gen), 7427)
        self.assertEqual(next(gen), 27174)


    def test_deal(self):
        self.assertEqual(deal(1)[:5], [25, 6, 23, 50, 11]) # Test first 5 cards
        self.assertEqual(deal(11982)[:5], [38, 36, 2, 15, 51]) # Test first 5 cards with different seed
        self.assertEqual(len(deal(1)), 52) # Check deck size


    def test_show(self):
        # Redirect stdout to capture the printed output
        import io
        from contextlib import redirect_stdout

        # Test case 1
        cards1 = deal(1)
        f = io.StringIO()
        with redirect_stdout(f):
            show(cards1)
        output1 = f.getvalue().strip()
        expected_output1 = """7H QH KD AS 8C 3S AD 4S
AC 4D 2H 9C 9S KS TD 5D
2D 5C TH TC 9H 3D 3C 4C
QC 8H 7D 7C 5H QD 8D 9D
JH 6C JD JC 2C AH 2S TS
6S KH 4H JS 6H 5S KC 7S
QS 3H 8S 6D JH"""
        self.assertEqual(output1, expected_output1)



        # Test case 2
        cards2 = deal(617)
        f = io.StringIO()
        with redirect_stdout(f):
            show(cards2)
        output2 = f.getvalue().strip()
        expected_output2 = """7D AD 5C 3S 5S 8C 2D AH
TD 7S QD AC 6D 8H AS KH
TH QC 3H 9D 6S 8D 3D TC
KD 5H 9S 3C 8S 7H 4D JS
4C QS 9C 9H 7C 6H 2C 2S
4S TS 2H 5D JC 6C JH QH
JD KS KC 4H"""

        self.assertEqual(output2, expected_output2)




if __name__ == '__main__':
    unittest.main()

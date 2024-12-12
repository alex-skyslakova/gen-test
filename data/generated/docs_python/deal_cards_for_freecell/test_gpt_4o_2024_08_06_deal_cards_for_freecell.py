import unittest

class TestFreeCellDeal(unittest.TestCase):
    
    def test_random_generator(self):
        # Test the random number generator with a known seed
        rng = randomGenerator(1)
        first_value = next(rng)
        second_value = next(rng)
        
        # These values are expected based on the LCG algorithm
        self.assertEqual(first_value, 34603)
        self.assertEqual(second_value, 31886)

    def test_deal(self):
        # Test the deal function with a known seed
        expected_deal_1 = [
            36, 1, 34, 37, 17, 27, 26, 16, 51, 50, 35, 18, 0, 47, 49, 10,
            46, 48, 33, 19, 31, 3, 2, 11, 4, 30, 45, 5, 44, 9, 8, 25,
            43, 7, 6, 24, 23, 22, 21, 20, 32, 15, 14, 13, 12, 29, 28, 42,
            41, 40, 39, 38
        ]
        self.assertEqual(deal(1), expected_deal_1)

        # Test another known deal
        expected_deal_617 = [
            45, 0, 17, 42, 18, 31, 1, 48, 8, 44, 34, 3, 50, 19, 4, 2,
            51, 9, 49, 35, 36, 5, 33, 47, 32, 46, 6, 7, 10, 11, 12, 13,
            14, 15, 16, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 39, 38,
            37, 43, 41, 40
        ]
        self.assertEqual(deal(617), expected_deal_617)

    def test_show(self):
        # Test the show function with a known deck
        deck = [
            36, 1, 34, 37, 17, 27, 26, 16, 51, 50, 35, 18, 0, 47, 49, 10,
            46, 48, 33, 19, 31, 3, 2, 11, 4, 30, 45, 5, 44, 9, 8, 25,
            43, 7, 6, 24, 23, 22, 21, 20, 32, 15, 14, 13, 12, 29, 28, 42,
            41, 40, 39, 38
        ]
        expected_output = (
            "JD 2D 9H JC 5D 7H 7C 5H\n"
            "KD KC 9S 5S AD QC KH 3H\n"
            "2S KS 9D QD JS AS AH 3C\n"
            "4C 5C TS QH 4H AC 4D 7S\n"
            "3S TD 4S TH 8H 2C JH 7D\n"
            "6D 8S 8D QS 6C 3D 8C TC\n"
            "6S 9C 2H 6H\n"
        )
        with unittest.mock.patch('sys.stdout', new_callable=io.StringIO) as mock_stdout:
            show(deck)
            self.assertEqual(mock_stdout.getvalue(), expected_output)

if __name__ == '__main__':
    unittest.main()

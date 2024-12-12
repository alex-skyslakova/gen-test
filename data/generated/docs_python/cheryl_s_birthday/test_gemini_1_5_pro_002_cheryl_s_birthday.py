import unittest
from cheryl_s_birthday import monthsWithUniqueDays, uniquePairing, bindPairs, dictFromPairs, fst, snd

class TestCherylBirthday(unittest.TestCase):

    def test_monthsWithUniqueDays_include(self):
        dates = [('May', '15'), ('May', '16'), ('June', '17'), ('June', '18'), ('July', '14')]
        expected = [('July', '14')]
        self.assertEqual(monthsWithUniqueDays(True)(dates), expected)

    def test_monthsWithUniqueDays_exclude(self):
        dates = [('May', '15'), ('May', '16'), ('June', '17'), ('June', '18'), ('July', '14')]
        expected = [('May', '15'), ('May', '16'), ('June', '17'), ('June', '18')]
        self.assertEqual(monthsWithUniqueDays(False)(dates), expected)

    def test_uniquePairing_month(self):
        dates = [('May', '15'), ('May', '16'), ('June', '17'), ('July', '14')]
        expected = [('June', '17'), ('July', '14')]
        self.assertEqual(uniquePairing(0)(dates), expected)


    def test_uniquePairing_day(self):
        dates = [('May', '15'), ('May', '16'), ('June', '17'), ('July', '14')]
        expected = [('May', '15'), ('May', '16'), ('June', '17'), ('July', '14')]
        self.assertEqual(uniquePairing(1)(dates), expected)



    def test_bindPairs(self):
        dates = [('May', '15'), ('May', '16'), ('June', '17')]
        
        def f(dicts):
            month_dict, day_dict = dicts
            return [(k, v[0]) for k, v in month_dict.items() if len(v) == 1]
        
        expected = [('June', '17')]
        self.assertEqual(bindPairs(dates)(f), expected)


    def test_dictFromPairs(self):
        dates = [('May', '15'), ('May', '16'), ('June', '17')]
        expected = {'May': ['15', '16'], 'June': ['17']}
        self.assertEqual(dictFromPairs(dates), expected)


    def test_fst(self):
        self.assertEqual(fst(('a', 'b')), 'a')

    def test_snd(self):
        self.assertEqual(snd(('a', 'b')), 'b')



if __name__ == '__main__':
    unittest.main()

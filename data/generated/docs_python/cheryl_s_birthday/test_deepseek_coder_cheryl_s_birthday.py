import unittest
from cheryl_s_birthday import (
    main, monthsWithUniqueDays, uniquePairing, bindPairs, dictFromPairs, ap, fst, snd
)

class TestCherylSBirthday(unittest.TestCase):

    def test_main(self):
        # The main function should print the correct birthday
        import io
        import sys
        from contextlib import redirect_stdout

        captured_output = io.StringIO()
        with redirect_stdout(captured_output):
            main()

        self.assertEqual(captured_output.getvalue().strip(), "('July', '16')")

    def test_monthsWithUniqueDays_include(self):
        dates = [
            ('May', '15'), ('May', '16'), ('May', '19'),
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        result = monthsWithUniqueDays(True)(dates)
        self.assertEqual(result, [('May', '19'), ('June', '17'), ('June', '18'), ('August', '17')])

    def test_monthsWithUniqueDays_exclude(self):
        dates = [
            ('May', '15'), ('May', '16'), ('May', '19'),
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        result = monthsWithUniqueDays(False)(dates)
        self.assertEqual(result, [('May', '15'), ('May', '16'), ('July', '14'), ('July', '16'), ('August', '14'), ('August', '15')])

    def test_uniquePairing_month(self):
        dates = [
            ('May', '15'), ('May', '16'), ('May', '19'),
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        result = uniquePairing(0)(dates)
        self.assertEqual(result, [('May', '19'), ('June', '17'), ('June', '18'), ('August', '17')])

    def test_uniquePairing_day(self):
        dates = [
            ('May', '15'), ('May', '16'), ('May', '19'),
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        result = uniquePairing(1)(dates)
        self.assertEqual(result, [('May', '19'), ('June', '17'), ('June', '18'), ('August', '17')])

    def test_bindPairs(self):
        dates = [
            ('May', '15'), ('May', '16'), ('May', '19'),
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        result = bindPairs(dates)(lambda x: x)
        self.assertEqual(result, (
            {'May': ['15', '16', '19'], 'June': ['17', '18'], 'July': ['14', '16'], 'August': ['14', '15', '17']},
            {'15': ['May'], '16': ['May'], '19': ['May'], '17': ['June', 'August'], '18': ['June'], '14': ['July', 'August'], '16': ['July'], '15': ['August']}
        ))

    def test_dictFromPairs(self):
        dates = [
            ('May', '15'), ('May', '16'), ('May', '19'),
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        result = dictFromPairs(dates)
        self.assertEqual(result, {
            'May': ['15', '16', '19'],
            'June': ['17', '18'],
            'July': ['14', '16'],
            'August': ['14', '15', '17']
        })

    def test_ap(self):
        def add(x):
            return lambda y: x + y

        def double(x):
            return x * 2

        result = ap(add)(double)(5)
        self.assertEqual(result, 15)

    def test_fst(self):
        self.assertEqual(fst(('May', '15')), 'May')

    def test_snd(self):
        self.assertEqual(snd(('May', '15')), '15')

if __name__ == '__main__':
    unittest.main()

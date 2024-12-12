import unittest
from cheryl_s_birthday import main, monthsWithUniqueDays, uniquePairing, bindPairs, dictFromPairs

class TestCherylsBirthday(unittest.TestCase):

    def test_months_with_unique_days_exclude(self):
        dates = [
            ('May', '15'), ('May', '16'), ('May', '19'),
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        result = monthsWithUniqueDays(False)(dates)
        expected = [
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        self.assertEqual(result, expected)

    def test_months_with_unique_days_include(self):
        dates = [
            ('May', '15'), ('May', '16'), ('May', '19'),
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        result = monthsWithUniqueDays(True)(dates)
        expected = [
            ('May', '15'), ('May', '16'), ('May', '19')
        ]
        self.assertEqual(result, expected)

    def test_unique_pairing_day(self):
        dates = [
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        result = uniquePairing(1)(dates)
        expected = [
            ('July', '16'), ('August', '15'), ('August', '17')
        ]
        self.assertEqual(result, expected)

    def test_unique_pairing_month(self):
        dates = [
            ('July', '16'), ('August', '15'), ('August', '17')
        ]
        result = uniquePairing(0)(dates)
        expected = [
            ('July', '16')
        ]
        self.assertEqual(result, expected)

    def test_bind_pairs(self):
        dates = [
            ('May', '15'), ('May', '16'), ('May', '19'),
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        result = bindPairs(dates)(lambda x: x)
        expected = (
            {
                'May': ['15', '16', '19'],
                'June': ['17', '18'],
                'July': ['14', '16'],
                'August': ['14', '15', '17']
            },
            {
                '15': ['May', 'August'],
                '16': ['May', 'July'],
                '19': ['May'],
                '17': ['June', 'August'],
                '18': ['June'],
                '14': ['July', 'August']
            }
        )
        self.assertEqual(result, expected)

    def test_dict_from_pairs(self):
        dates = [
            ('May', '15'), ('May', '16'), ('May', '19'),
            ('June', '17'), ('June', '18'),
            ('July', '14'), ('July', '16'),
            ('August', '14'), ('August', '15'), ('August', '17')
        ]
        result = dictFromPairs(dates)
        expected = {
            'May': ['15', '16', '19'],
            'June': ['17', '18'],
            'July': ['14', '16'],
            'August': ['14', '15', '17']
        }
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()

import unittest
from chinese_zodiac import calculate

class TestChineseZodiac(unittest.TestCase):

    def test_1984(self):
        # Wood Rat (yang) - year 1 of the cycle
        with self.assertLogs(level='INFO') as cm:
            calculate(1984)
        self.assertEqual(cm.output, ['INFO:root:1984: 甲子 (jiă-zĭ, Wood Rat; yang - year 1 of the cycle)'])

    def test_1985(self):
        # Wood Ox (yin) - year 2 of the cycle
        with self.assertLogs(level='INFO') as cm:
            calculate(1985)
        self.assertEqual(cm.output, ['INFO:root:1985: 乙丑 (yĭ-chŏu, Wood Ox; yin - year 2 of the cycle)'])

    def test_1986(self):
        # Fire Tiger (yang) - year 3 of the cycle
        with self.assertLogs(level='INFO') as cm:
            calculate(1986)
        self.assertEqual(cm.output, ['INFO:root:1986: 丙寅 (bĭng-yín, Fire Tiger; yang - year 3 of the cycle)'])

    def test_2022(self):
        # Water Tiger (yang) - year 39 of the cycle
        with self.assertLogs(level='INFO') as cm:
            calculate(2022)
        self.assertEqual(cm.output, ['INFO:root:2022: 壬寅 (rén-yín, Water Tiger; yang - year 39 of the cycle)'])

    def test_4(self):  # First year of the Common Era cycle
        # Wood Rat (yang) - year 1 of the cycle
        with self.assertLogs(level='INFO') as cm:
           calculate(4)
        self.assertEqual(cm.output, ['INFO:root:4: 甲子 (jiă-zĭ, Wood Rat; yang - year 1 of the cycle)'])
    
    def test_1935(self): # Pig (yin), Wood, 27 of the cycle
        with self.assertLogs(level='INFO') as cm:
            calculate(1935)
        self.assertEqual(cm.output, ['INFO:root:1935: 乙亥 (yĭ-hài, Wood Pig; yin - year 27 of the cycle)'])
    
    def test_1938(self): # Tiger (yang), Earth, 30 of the cycle
        with self.assertLogs(level='INFO') as cm:
            calculate(1938)
        self.assertEqual(cm.output, ['INFO:root:1938: 戊寅 (wù-yín, Earth Tiger; yang - year 30 of the cycle)'])
    
    def test_1968(self): # Monkey (yang), Earth, 56 of the cycle
        with self.assertLogs(level='INFO') as cm:
            calculate(1968)
        self.assertEqual(cm.output, ['INFO:root:1968: 戊申 (wù-shēn, Earth Monkey; yang - year 56 of the cycle)'])


    def test_string_input(self): # Test string input type for year
        with self.assertLogs(level='INFO') as cm:
            calculate("2024") # Dragon (yang), Wood, 41
        self.assertEqual(cm.output, ['INFO:root:2024: 甲辰 (jiă-chén, Wood Dragon; yang - year 41 of the cycle)'])



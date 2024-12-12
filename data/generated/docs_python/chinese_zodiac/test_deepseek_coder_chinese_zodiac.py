import unittest
from chinese_zodiac import calculate

class TestChineseZodiac(unittest.TestCase):

    def test_1984(self):
        # 1984: 甲子 (jiă-zĭ, or jia3-zi3). Wood Rat (yang)
        with self.assertLogs() as captured:
            calculate(1984)
        self.assertIn("1984: 甲子 (jiă-zĭ, Wood Rat; yang - year 1 of the cycle)", captured.output[0])

    def test_1985(self):
        # 1985: 乙丑 (yĭ-chŏu, or yi3-chou3). Wood Ox (yin)
        with self.assertLogs() as captured:
            calculate(1985)
        self.assertIn("1985: 乙丑 (yĭ-chŏu, Wood Ox; yin - year 2 of the cycle)", captured.output[0])

    def test_1986(self):
        # 1986: 丙寅 (bĭng-yín, or bing3-yin2). Fire Tiger (yang)
        with self.assertLogs() as captured:
            calculate(1986)
        self.assertIn("1986: 丙寅 (bĭng-yín, Fire Tiger; yang - year 3 of the cycle)", captured.output[0])

    def test_2022(self):
        # 2022: 壬寅 (rén-yín or ren2-yin2). Water Tiger (yang)
        with self.assertLogs() as captured:
            calculate(2022)
        self.assertIn("2022: 壬寅 (rén-yín, Water Tiger; yang - year 39 of the cycle)", captured.output[0])

    def test_2023(self):
        # 2023: 癸卯 (gŭi-măo or gui3-mao3). Water Rabbit (yin)
        with self.assertLogs() as captured:
            calculate(2023)
        self.assertIn("2023: 癸卯 (gŭi-măo, Water Rabbit; yin - year 40 of the cycle)", captured.output[0])

    def test_1935(self):
        # 1935: 乙亥 (yĭ-hài, or yi3-hai4). Wood Pig (yin)
        with self.assertLogs() as captured:
            calculate(1935)
        self.assertIn("1935: 乙亥 (yĭ-hài, Wood Pig; yin - year 52 of the cycle)", captured.output[0])

    def test_1938(self):
        # 1938: 戊寅 (wù-yín, or wu4-yin2). Earth Tiger (yang)
        with self.assertLogs() as captured:
            calculate(1938)
        self.assertIn("1938: 戊寅 (wù-yín, Earth Tiger; yang - year 55 of the cycle)", captured.output[0])

    def test_1968(self):
        # 1968: 戊申 (wù-shēn, or wu4-shen1). Earth Monkey (yang)
        with self.assertLogs() as captured:
            calculate(1968)
        self.assertIn("1968: 戊申 (wù-shēn, Earth Monkey; yang - year 25 of the cycle)", captured.output[0])

    def test_1972(self):
        # 1972: 壬子 (rén-zĭ, or ren2-zi3). Water Rat (yang)
        with self.assertLogs() as captured:
            calculate(1972)
        self.assertIn("1972: 壬子 (rén-zĭ, Water Rat; yang - year 29 of the cycle)", captured.output[0])

    def test_1976(self):
        # 1976: 丙辰 (bĭng-chén, or bing3-chen2). Fire Dragon (yang)
        with self.assertLogs() as captured:
            calculate(1976)
        self.assertIn("1976: 丙辰 (bĭng-chén, Fire Dragon; yang - year 33 of the cycle)", captured.output[0])

    def test_current_year(self):
        # Test the current year dynamically
        from datetime import datetime
        current_year = datetime.now().year
        with self.assertLogs() as captured:
            calculate(current_year)
        self.assertIn(f"{current_year}:", captured.output[0])

if __name__ == '__main__':
    unittest.main()

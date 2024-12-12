import unittest
from decimal import Decimal as D
from collections import namedtuple

# Assuming the code from currency.py is imported here
# from currency import Item, items, tax_rate, calculate_totals

class TestCurrencyCalculations(unittest.TestCase):
    def setUp(self):
        self.items = dict(
            hamburger=Item(D('5.50'), D('4000000000000000')),
            milkshake=Item(D('2.86'), D('2'))
        )
        self.tax_rate = D('0.0765')

    def test_total_before_tax(self):
        total_before_tax = sum(price * quant for price, quant in self.items.values())
        expected_total_before_tax = D('22000000000000005.72')
        self.assertEqual(total_before_tax, expected_total_before_tax)

    def test_tax_calculation(self):
        total_before_tax = sum(price * quant for price, quant in self.items.values())
        tax = (self.tax_rate * total_before_tax).quantize(D('0.00'))
        expected_tax = D('1683000000000000.44')
        self.assertEqual(tax, expected_tax)

    def test_total_with_tax(self):
        total_before_tax = sum(price * quant for price, quant in self.items.values())
        tax = (self.tax_rate * total_before_tax).quantize(D('0.00'))
        total = total_before_tax + tax
        expected_total = D('23683000000000006.16')
        self.assertEqual(total, expected_total)

if __name__ == '__main__':
    unittest.main()

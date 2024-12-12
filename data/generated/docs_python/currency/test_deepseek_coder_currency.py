import unittest
from decimal import Decimal as D
from collections import namedtuple

# Import the code to be tested
from currency import Item, items, tax_rate, fmt, total_before_tax, tax, total

class TestCurrency(unittest.TestCase):

    def test_item_creation(self):
        hamburger = Item(D('5.50'), D('4000000000000000'))
        milkshake = Item(D('2.86'), D('2'))
        self.assertEqual(hamburger.price, D('5.50'))
        self.assertEqual(hamburger.quant, D('4000000000000000'))
        self.assertEqual(milkshake.price, D('2.86'))
        self.assertEqual(milkshake.quant, D('2'))

    def test_items_dict(self):
        self.assertEqual(items['hamburger'].price, D('5.50'))
        self.assertEqual(items['hamburger'].quant, D('4000000000000000'))
        self.assertEqual(items['milkshake'].price, D('2.86'))
        self.assertEqual(items['milkshake'].quant, D('2'))

    def test_tax_rate(self):
        self.assertEqual(tax_rate, D('0.0765'))

    def test_total_before_tax_calculation(self):
        expected_total_before_tax = D('22000000000000005.72')
        self.assertEqual(total_before_tax, expected_total_before_tax)

    def test_tax_calculation(self):
        expected_tax = D('1683000000000000.44')
        self.assertEqual(tax, expected_tax)

    def test_total_calculation(self):
        expected_total = D('23683000000000006.16')
        self.assertEqual(total, expected_total)

if __name__ == '__main__':
    unittest.main()

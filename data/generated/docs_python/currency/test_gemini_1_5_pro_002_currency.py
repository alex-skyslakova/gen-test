import unittest
from decimal import Decimal as D
from collections import namedtuple
from currency import Item, items, tax_rate

class CurrencyTest(unittest.TestCase):

    def test_hamburger_extension(self):
        self.assertEqual(items['hamburger'].price * items['hamburger'].quant, D('22000000000000000.00'))

    def test_milkshake_extension(self):
        self.assertEqual(items['milkshake'].price * items['milkshake'].quant, D('5.72'))

    def test_total_before_tax(self):
        total_before_tax = 0
        for item, (price, quant) in sorted(items.items()):
            total_before_tax += price * quant
        self.assertEqual(total_before_tax, D('22000000000000005.72'))

    def test_tax_calculation(self):
        total_before_tax = D('22000000000000005.72')
        tax = (tax_rate * total_before_tax).quantize(D('0.00'))
        self.assertEqual(tax, D('1683000000000000.44'))

    def test_total_with_tax(self):
        total_before_tax = D('22000000000000005.72')
        tax = D('1683000000000000.44')
        total = total_before_tax + tax
        self.assertEqual(total, D('23683000000000006.16'))

    def test_empty_items(self):
        empty_items = {}
        total_before_tax = 0
        for item, (price, quant) in sorted(empty_items.items()):
            total_before_tax += price * quant
        tax = (tax_rate * total_before_tax).quantize(D('0.00'))
        total = total_before_tax + tax
        self.assertEqual(total, D('0.00'))


    def test_zero_tax_rate(self):
        zero_tax_rate = D('0.00')
        total_before_tax = D('22000000000000005.72')
        tax = (zero_tax_rate * total_before_tax).quantize(D('0.00'))
        total = total_before_tax + tax
        self.assertEqual(total, total_before_tax)



if __name__ == '__main__':
    unittest.main()


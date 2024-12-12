import unittest
from cyclotomic_polynomial import cyclotomic, terms, to_text

class TestCyclotomicPolynomial(unittest.TestCase):

    def test_first_30_cyclotomic_polynomials(self):
        expected_polynomials = [
            "1", "x - 1", "x + 1", "x² + x + 1", "x² + 1", "x⁴ + x³ + x² + x + 1",
            "x² - x + 1", "x⁴ + 1", "x⁶ + x³ + 1", "x⁴ - x² + 1", "x¹⁰ + x⁹ + x⁸ + x⁷ + x⁶ + x⁵ + x⁴ + x³ + x² + x + 1",
            "x² - x + 1", "x¹² + x¹¹ + x¹⁰ + x⁹ + x⁸ + x⁷ + x⁶ + x⁵ + x⁴ + x³ + x² + x + 1", "x⁶ - x³ + 1",
            "x⁸ + x⁷ + x⁶ + x⁵ + x⁴ + x³ + x² + x + 1", "x⁸ - x⁴ + 1", "x¹⁶ + x¹⁵ + x¹⁴ + x¹³ + x¹² + x¹¹ + x¹⁰ + x⁹ + x⁸ + x⁷ + x⁶ + x⁵ + x⁴ + x³ + x² + x + 1",
            "x⁶ - x³ + 1", "x¹⁸ + x¹⁷ + x¹⁶ + x¹⁵ + x¹⁴ + x¹³ + x¹² + x¹¹ + x¹⁰ + x⁹ + x⁸ + x⁷ + x⁶ + x⁵ + x⁴ + x³ + x² + x + 1", "x⁸ - x⁴ + 1",
            "x¹² - x⁶ + 1", "x²² + x²¹ + x²⁰ + x¹⁹ + x¹⁸ + x¹⁷ + x¹⁶ + x¹⁵ + x¹⁴ + x¹³ + x¹² + x¹¹ + x¹⁰ + x⁹ + x⁸ + x⁷ + x⁶ + x⁵ + x⁴ + x³ + x² + x + 1",
            "x¹⁰ - x⁵ + 1", "x²⁴ + x²³ + x²² + x²¹ + x²⁰ + x¹⁹ + x¹⁸ + x¹⁷ + x¹⁶ + x¹⁵ + x¹⁴ + x¹³ + x¹² + x¹¹ + x¹⁰ + x⁹ + x⁸ + x⁷ + x⁶ + x⁵ + x⁴ + x³ + x² + x + 1",
            "x¹² - x⁶ + 1", "x²⁶ + x²⁵ + x²⁴ + x²³ + x²² + x²¹ + x²⁰ + x¹⁹ + x¹⁸ + x¹⁷ + x¹⁶ + x¹⁵ + x¹⁴ + x¹³ + x¹² + x¹¹ + x¹⁰ + x⁹ + x⁸ + x⁷ + x⁶ + x⁵ + x⁴ + x³ + x² + x + 1",
            "x¹⁴ - x⁷ + 1", "x²⁸ + x²⁷ + x²⁶ + x²⁵ + x²⁴ + x²³ + x²² + x²¹ + x²⁰ + x¹⁹ + x¹⁸ + x¹⁷ + x¹⁶ + x¹⁵ + x¹⁴ + x¹³ + x¹² + x¹¹ + x¹⁰ + x⁹ + x⁸ + x⁷ + x⁶ + x⁵ + x⁴ + x³ + x² + x + 1",
            "x¹⁶ - x⁸ + 1", "x³⁰ + x²⁹ + x²⁸ + x²⁷ + x²⁶ + x²⁵ + x²⁴ + x²³ + x²² + x²¹ + x²⁰ + x¹⁹ + x¹⁸ + x¹⁷ + x¹⁶ + x¹⁵ + x¹⁴ + x¹³ + x¹² + x¹¹ + x¹⁰ + x⁹ + x⁸ + x⁷ + x⁶ + x⁵ + x⁴ + x³ + x² + x + 1"
        ]
        for n in range(31):
            poly = to_text(terms(cyclotomic(n)))
            self.assertEqual(poly, expected_polynomials[n])

    def test_order_of_first_10_cyclotomic_with_n_or_minus_n(self):
        expected_orders = [3, 4, 7, 9, 11, 13, 15, 17, 19, 21]
        found_orders = []
        want = 1
        for n in range(100):  # Arbitrary limit to find the first 10
            c = [c for c, _ in terms(cyclotomic(n))]
            if want in c or -want in c:
                found_orders.append(n)
                want += 1
            if len(found_orders) == 10:
                break
        self.assertEqual(found_orders, expected_orders)

if __name__ == '__main__':
    unittest.main()

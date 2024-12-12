import unittest
from largest_number_divisible_by_its_digits import (
    main, intFromDigits, concatMap, delete, lcm
)

class TestLargestNumberDivisibleByItsDigits(unittest.TestCase):

    def test_intFromDigits(self):
        self.assertEqual(intFromDigits([1, 2, 3]), 123)
        self.assertEqual(intFromDigits([9, 8, 7]), 987)
        self.assertEqual(intFromDigits([0, 1, 2]), 12)
        self.assertEqual(intFromDigits([]), 0)

    def test_delete(self):
        self.assertEqual(delete([1, 2, 3])(2), [1, 3])
        self.assertEqual(delete([1, 2, 3, 2])(2), [1, 3, 2])
        self.assertEqual(delete([1, 2, 3])(4), [1, 2, 3])
        self.assertEqual(delete([])(1), [])

    def test_lcm(self):
        self.assertEqual(lcm(4, 6), 12)
        self.assertEqual(lcm(3, 5), 15)
        self.assertEqual(lcm(0, 5), 0)
        self.assertEqual(lcm(5, 0), 0)
        self.assertEqual(lcm(1, 1), 1)

    def test_concatMap(self):
        def square(x):
            return [x * x]
        self.assertEqual(list(concatMap(square)([1, 2, 3])), [1, 4, 9])
        self.assertEqual(list(concatMap(square)([])), [])

    def test_main(self):
        # Since main() prints the result, we capture stdout to check the output
        import io
        from contextlib import redirect_stdout

        f = io.StringIO()
        with redirect_stdout(f):
            main()
        output = f.getvalue().strip()
        self.assertEqual(output, '9867312')

if __name__ == '__main__':
    unittest.main()

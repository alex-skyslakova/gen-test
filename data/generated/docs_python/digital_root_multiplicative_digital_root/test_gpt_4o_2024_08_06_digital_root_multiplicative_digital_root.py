import unittest
from functools import reduce

def mdroot(n):
    'Multiplicative digital root'
    mdr = [n]
    while mdr[-1] > 9:
        mdr.append(reduce(int.__mul__, (int(dig) for dig in str(mdr[-1])), 1))
    return len(mdr) - 1, mdr[-1]

class TestMultiplicativeDigitalRoot(unittest.TestCase):
    
    def test_mdroot(self):
        # Test cases for specific numbers
        self.assertEqual(mdroot(123321), (1, 6))
        self.assertEqual(mdroot(7739), (3, 3))
        self.assertEqual(mdroot(893), (2, 6))
        self.assertEqual(mdroot(899998), (2, 0))
    
    def test_mdr_table(self):
        # Generate the table for the first five numbers having each MDR
        table, n = {i: [] for i in range(10)}, 0
        while min(len(row) for row in table.values()) < 5:
            mpersistence, mdr = mdroot(n)
            table[mdr].append(n)
            n += 1
        
        # Expected results
        expected_table = {
            0: [0, 10, 20, 30, 40],
            1: [1, 11, 111, 1111, 11111],
            2: [2, 12, 21, 22, 31],
            3: [3, 13, 23, 32, 33],
            4: [4, 14, 24, 34, 41],
            5: [5, 15, 25, 35, 45],
            6: [6, 16, 26, 36, 42],
            7: [7, 17, 27, 37, 47],
            8: [8, 18, 28, 38, 48],
            9: [9, 19, 29, 39, 49]
        }
        
        for mdr in range(10):
            self.assertEqual(table[mdr][:5], expected_table[mdr])

if __name__ == '__main__':
    unittest.main()

import unittest
from four_bit_adder import xor, ha, fa, fa4, int2bus, bus2int

class TestFourBitAdder(unittest.TestCase):

    def test_xor(self):
        self.assertEqual(xor(0, 0), 0)
        self.assertEqual(xor(0, 1), 1)
        self.assertEqual(xor(1, 0), 1)
        self.assertEqual(xor(1, 1), 0)

    def test_ha(self):
        self.assertEqual(ha(0, 0), (0, 0))
        self.assertEqual(ha(0, 1), (1, 0))
        self.assertEqual(ha(1, 0), (1, 0))
        self.assertEqual(ha(1, 1), (0, 1))

    def test_fa(self):
        self.assertEqual(fa(0, 0, 0), (0, 0))
        self.assertEqual(fa(0, 0, 1), (1, 0))
        self.assertEqual(fa(0, 1, 0), (1, 0))
        self.assertEqual(fa(0, 1, 1), (0, 1))
        self.assertEqual(fa(1, 0, 0), (1, 0))
        self.assertEqual(fa(1, 0, 1), (0, 1))
        self.assertEqual(fa(1, 1, 0), (0, 1))
        self.assertEqual(fa(1, 1, 1), (1, 1))

    def test_fa4(self):
        width = 4
        tot = [None] * (width + 1)
        for a in range(2**width):
            for b in range(2**width):
                tot[:width], tot[width] = fa4(int2bus(a), int2bus(b))
                self.assertEqual(a + b, bus2int(tot), f"totals don't match: {a} + {b} != {tot}")

    def test_int2bus(self):
        self.assertEqual(int2bus(0), [0, 0, 0, 0])
        self.assertEqual(int2bus(1), [1, 0, 0, 0])
        self.assertEqual(int2bus(2), [0, 1, 0, 0])
        self.assertEqual(int2bus(3), [1, 1, 0, 0])
        self.assertEqual(int2bus(4), [0, 0, 1, 0])
        self.assertEqual(int2bus(5), [1, 0, 1, 0])
        self.assertEqual(int2bus(6), [0, 1, 1, 0])
        self.assertEqual(int2bus(7), [1, 1, 1, 0])
        self.assertEqual(int2bus(8), [0, 0, 0, 1])
        self.assertEqual(int2bus(9), [1, 0, 0, 1])
        self.assertEqual(int2bus(10), [0, 1, 0, 1])
        self.assertEqual(int2bus(11), [1, 1, 0, 1])
        self.assertEqual(int2bus(12), [0, 0, 1, 1])
        self.assertEqual(int2bus(13), [1, 0, 1, 1])
        self.assertEqual(int2bus(14), [0, 1, 1, 1])
        self.assertEqual(int2bus(15), [1, 1, 1, 1])

    def test_bus2int(self):
        self.assertEqual(bus2int([0, 0, 0, 0]), 0)
        self.assertEqual(bus2int([1, 0, 0, 0]), 1)
        self.assertEqual(bus2int([0, 1, 0, 0]), 2)
        self.assertEqual(bus2int([1, 1, 0, 0]), 3)
        self.assertEqual(bus2int([0, 0, 1, 0]), 4)
        self.assertEqual(bus2int([1, 0, 1, 0]), 5)
        self.assertEqual(bus2int([0, 1, 1, 0]), 6)
        self.assertEqual(bus2int([1, 1, 1, 0]), 7)
        self.assertEqual(bus2int([0, 0, 0, 1]), 8)
        self.assertEqual(bus2int([1, 0, 0, 1]), 9)
        self.assertEqual(bus2int([0, 1, 0, 1]), 10)
        self.assertEqual(bus2int([1, 1, 0, 1]), 11)
        self.assertEqual(bus2int([0, 0, 1, 1]), 12)
        self.assertEqual(bus2int([1, 0, 1, 1]), 13)
        self.assertEqual(bus2int([0, 1, 1, 1]), 14)
        self.assertEqual(bus2int([1, 1, 1, 1]), 15)

if __name__ == '__main__':
    unittest.main()

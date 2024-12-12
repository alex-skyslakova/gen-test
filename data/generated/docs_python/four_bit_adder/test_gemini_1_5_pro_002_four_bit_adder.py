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
        self.assertEqual(fa4(int2bus(0), int2bus(0)), ([0, 0, 0, 0], 0))
        self.assertEqual(fa4(int2bus(1), int2bus(0)), ([1, 0, 0, 0], 0))
        self.assertEqual(fa4(int2bus(0), int2bus(1)), ([1, 0, 0, 0], 0))
        self.assertEqual(fa4(int2bus(1), int2bus(1)), ([0, 1, 0, 0], 0))
        self.assertEqual(fa4(int2bus(7), int2bus(7)), ([0, 0, 1, 1], 1))  # 7 + 7 = 14
        self.assertEqual(fa4(int2bus(15), int2bus(1)), ([0, 0, 0, 0], 1)) # 15 + 1 = 16

    def test_int2bus(self):
        self.assertEqual(int2bus(0), [0, 0, 0, 0])
        self.assertEqual(int2bus(1), [1, 0, 0, 0])
        self.assertEqual(int2bus(7), [1, 1, 1, 0])
        self.assertEqual(int2bus(15), [1, 1, 1, 1])

    def test_bus2int(self):
        self.assertEqual(bus2int([0, 0, 0, 0]), 0)
        self.assertEqual(bus2int([1, 0, 0, 0]), 1)
        self.assertEqual(bus2int([1, 1, 1, 0]), 7)
        self.assertEqual(bus2int([1, 1, 1, 1]), 15)


if __name__ == '__main__':
    unittest.main()

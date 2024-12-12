import unittest
from four_bit_adder import xor, ha, fa, fa4, int2bus, bus2int

class TestFourBitAdder(unittest.TestCase):

    def test_xor(self):
        # Test all combinations of inputs for the xor gate
        self.assertEqual(xor(0, 0), 0)
        self.assertEqual(xor(0, 1), 1)
        self.assertEqual(xor(1, 0), 1)
        self.assertEqual(xor(1, 1), 0)

    def test_ha(self):
        # Test all combinations of inputs for the half adder
        self.assertEqual(ha(0, 0), (0, 0))
        self.assertEqual(ha(0, 1), (1, 0))
        self.assertEqual(ha(1, 0), (1, 0))
        self.assertEqual(ha(1, 1), (0, 1))

    def test_fa(self):
        # Test all combinations of inputs for the full adder
        self.assertEqual(fa(0, 0, 0), (0, 0))
        self.assertEqual(fa(0, 1, 0), (1, 0))
        self.assertEqual(fa(1, 0, 0), (1, 0))
        self.assertEqual(fa(1, 1, 0), (0, 1))
        self.assertEqual(fa(0, 0, 1), (1, 0))
        self.assertEqual(fa(0, 1, 1), (0, 1))
        self.assertEqual(fa(1, 0, 1), (0, 1))
        self.assertEqual(fa(1, 1, 1), (1, 1))

    def test_fa4(self):
        # Test the 4-bit adder with all combinations of inputs
        width = 4
        for a in range(2**width):
            for b in range(2**width):
                sum_bits, carry_out = fa4(int2bus(a), int2bus(b))
                result = bus2int(sum_bits) + (carry_out << width)
                self.assertEqual(result, a + b, f"Failed for inputs: {a}, {b}")

    def test_int2bus(self):
        # Test conversion from integer to bus
        self.assertEqual(int2bus(0), [0, 0, 0, 0])
        self.assertEqual(int2bus(1), [1, 0, 0, 0])
        self.assertEqual(int2bus(2), [0, 1, 0, 0])
        self.assertEqual(int2bus(15), [1, 1, 1, 1])

    def test_bus2int(self):
        # Test conversion from bus to integer
        self.assertEqual(bus2int([0, 0, 0, 0]), 0)
        self.assertEqual(bus2int([1, 0, 0, 0]), 1)
        self.assertEqual(bus2int([0, 1, 0, 0]), 2)
        self.assertEqual(bus2int([1, 1, 1, 1]), 15)

if __name__ == '__main__':
    unittest.main()

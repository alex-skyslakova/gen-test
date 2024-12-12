import unittest
from unittest.mock import patch
import math

class TestHorizontalSundialCalculations(unittest.TestCase):

    @patch('builtins.input', side_effect=[4.95, -150.5, -150])
    def test_sundial_calculations(self, mock_input):
        # Import the function to be tested
        from horizontal_sundial_calculations import slat, hra, hla

        # Expected results based on the provided inputs
        expected_slat = math.sin(math.radians(4.95))
        expected_hra = [15 * h - (-150.5 - -150) for h in range(-6, 7)]
        expected_hla = [math.degrees(math.atan(expected_slat * math.tan(math.radians(hra)))) for hra in expected_hra]

        # Check sine of latitude
        self.assertAlmostEqual(slat, expected_slat, places=3)

        # Check hour, sun hour angle, and dial hour line angle
        for h in range(-6, 7):
            hra_index = h + 6
            self.assertAlmostEqual(hra[hra_index], expected_hra[hra_index], places=3)
            self.assertAlmostEqual(hla[hra_index], expected_hla[hra_index], places=3)

if __name__ == '__main__':
    unittest.main()

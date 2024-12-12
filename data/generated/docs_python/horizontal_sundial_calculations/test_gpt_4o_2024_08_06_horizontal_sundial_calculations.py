import unittest
from unittest.mock import patch
import math

class TestHorizontalSundialCalculations(unittest.TestCase):

    @patch('builtins.input', side_effect=['-4.95', '-150.5', '-150'])
    def test_sundial_calculations(self, mock_input):
        # Expected values based on the input
        expected_slat = math.sin(math.radians(-4.95))
        expected_diff_longitude = -150.5 - (-150)
        
        # Expected results for each hour from 6am to 6pm
        expected_results = []
        for h in range(-6, 7):
            hra = 15 * h
            hra -= (-150.5) - (-150)
            hla = math.degrees(math.atan(expected_slat * math.tan(math.radians(hra))))
            expected_results.append((h, hra, hla))
        
        # Capture the printed output
        with patch('sys.stdout') as mock_stdout:
            import horizontal_sundial_calculations
            output = mock_stdout.getvalue().strip().split('\n')
        
        # Check sine of latitude
        self.assertIn(f"sine of latitude:   {expected_slat:.3f}", output[0])
        
        # Check difference in longitude
        self.assertIn(f"diff longitude:     {expected_diff_longitude:.3f}", output[1])
        
        # Check hour, sun hour angle, and dial hour line angle
        for i, (h, hra, hla) in enumerate(expected_results):
            self.assertIn(f"HR={h:3d}; HRA={hra:7.3f}; HLA={hla:7.3f}", output[i+3])

if __name__ == '__main__':
    unittest.main()

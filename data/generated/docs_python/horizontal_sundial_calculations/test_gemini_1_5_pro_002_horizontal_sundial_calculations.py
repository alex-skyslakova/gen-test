import unittest
import math
from unittest.mock import patch
from io import StringIO

# Assuming the code is saved in a file named horizontal_sundial_calculations.py
# Import the functions from the file (if any)
# If no functions are defined, you can still test the output using mocks

class TestSundialCalculations(unittest.TestCase):

    @patch('builtins.input', side_effect=['4.95', '150.5', '150'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_lincoln_island(self, mock_stdout, mock_input):
        # Execute the code
        import horizontal_sundial_calculations 

        expected_output = """
    sine of latitude:   0.086
    diff longitude:     0.500

Hour, sun hour angle, dial hour line angle from 6am to 6pm
HR= -6; HRA= -90.500; HLA=  -7.865
HR= -5; HRA= -75.500; HLA=  -6.556
HR= -4; HRA= -60.500; HLA=  -5.249
HR= -3; HRA= -45.500; HLA=  -3.946
HR= -2; HRA= -30.500; HLA=  -2.647
HR= -1; HRA= -15.500; HLA=  -1.351
HR=  0; HRA=  -0.500; HLA=  -0.057
HR=  1; HRA=  14.500; HLA=   1.234
HR=  2; HRA=  29.500; HLA=   2.519
HR=  3; HRA=  44.500; HLA=   3.800
HR=  4; HRA=  59.500; HLA=   5.075
HR=  5; HRA=  74.500; HLA=   6.345
HR=  6; HRA=  89.500; HLA=   7.609
"""
        self.assertEqual(mock_stdout.getvalue(), expected_output)


    @patch('builtins.input', side_effect=['0', '0', '0'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_equator_prime_meridian(self, mock_stdout, mock_input):
         # Execute the code
        import horizontal_sundial_calculations

        expected_output = """
    sine of latitude:   0.000
    diff longitude:     0.000

Hour, sun hour angle, dial hour line angle from 6am to 6pm
HR= -6; HRA= -90.000; HLA=   0.000
HR= -5; HRA= -75.000; HLA=   0.000
HR= -4; HRA= -60.000; HLA=   0.000
HR= -3; HRA= -45.000; HLA=   0.000
HR= -2; HRA= -30.000; HLA=   0.000
HR= -1; HRA= -15.000; HLA=   0.000
HR=  0; HRA=   0.000; HLA=   0.000
HR=  1; HRA=  15.000; HLA=   0.000
HR=  2; HRA=  30.000; HLA=   0.000
HR=  3; HRA=  45.000; HLA=   0.000
HR=  4; HRA=  60.000; HLA=   0.000
HR=  5; HRA=  75.000; HLA=   0.000
HR=  6; HRA=  90.000; HLA=   0.000
"""
        self.assertEqual(mock_stdout.getvalue(), expected_output)




if __name__ == '__main__':
    unittest.main()


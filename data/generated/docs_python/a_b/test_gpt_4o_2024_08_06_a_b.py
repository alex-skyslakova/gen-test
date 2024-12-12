import subprocess
import unittest

class TestSumAB(unittest.TestCase):

    def run_script(self, input_data):
        """Helper function to run the script with given input and return the output."""
        process = subprocess.Popen(
            ['python3', 'a_b.py'],
            stdin=subprocess.PIPE,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True
        )
        stdout, stderr = process.communicate(input=input_data)
        return stdout.strip(), stderr.strip()

    def test_positive_numbers(self):
        input_data = "2 2\n"
        expected_output = "4"
        output, _ = self.run_script(input_data)
        self.assertEqual(output, expected_output)

    def test_mixed_numbers(self):
        input_data = "3 2\n"
        expected_output = "5"
        output, _ = self.run_script(input_data)
        self.assertEqual(output, expected_output)

    def test_negative_numbers(self):
        input_data = "-5 -3\n"
        expected_output = "-8"
        output, _ = self.run_script(input_data)
        self.assertEqual(output, expected_output)

    def test_zero(self):
        input_data = "0 0\n"
        expected_output = "0"
        output, _ = self.run_script(input_data)
        self.assertEqual(output, expected_output)

    def test_large_numbers(self):
        input_data = "1000 1000\n"
        expected_output = "2000"
        output, _ = self.run_script(input_data)
        self.assertEqual(output, expected_output)

    def test_large_negative_numbers(self):
        input_data = "-1000 -1000\n"
        expected_output = "-2000"
        output, _ = self.run_script(input_data)
        self.assertEqual(output, expected_output)

    def test_mixed_sign_numbers(self):
        input_data = "-1000 1000\n"
        expected_output = "0"
        output, _ = self.run_script(input_data)
        self.assertEqual(output, expected_output)

    def test_single_space_separation(self):
        input_data = "7 3\n"
        expected_output = "10"
        output, _ = self.run_script(input_data)
        self.assertEqual(output, expected_output)

    def test_multiple_space_separation(self):
        input_data = "7    3\n"
        expected_output = "10"
        output, _ = self.run_script(input_data)
        self.assertEqual(output, expected_output)

if __name__ == '__main__':
    unittest.main()

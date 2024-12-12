import unittest
from subprocess import run, PIPE

class TestLuckyNumbersCLI(unittest.TestCase):

    def run_cli(self, *args):
        result = run(['python3', 'lucky_and_even_lucky_numbers.py'] + list(args), stdout=PIPE, stderr=PIPE)
        return result.stdout.decode('utf-8').strip(), result.stderr.decode('utf-8').strip()

    def test_missing_arguments(self):
        stdout, stderr = self.run_cli()
        self.assertIn("missing arguments", stderr.lower())

    def test_too_many_arguments(self):
        stdout, stderr = self.run_cli('1', '2', '3', '4', '5')
        self.assertIn("too many arguments", stderr.lower())

    def test_illegal_number(self):
        stdout, stderr = self.run_cli('a')
        self.assertIn("aren't legal", stderr.lower())

    def test_misspelled_argument(self):
        stdout, stderr = self.run_cli('1', 'evnLucky')
        self.assertIn("misspelled argument", stderr.lower())

    def test_mixed_case_handling(self):
        stdout, stderr = self.run_cli('1', 'LUCKY')
        self.assertIn("1st lucky number", stdout.lower())

    def test_single_lucky_number(self):
        stdout, stderr = self.run_cli('1', 'lucky')
        self.assertIn("1st lucky number", stdout.lower())

    def test_single_even_lucky_number(self):
        stdout, stderr = self.run_cli('1', 'evenLucky')
        self.assertIn("1st even lucky number", stdout.lower())

    def test_range_lucky_numbers(self):
        stdout, stderr = self.run_cli('1', '5', 'lucky')
        self.assertIn("1st through 5th lucky numbers", stdout.lower())

    def test_range_even_lucky_numbers(self):
        stdout, stderr = self.run_cli('1', '5', 'evenLucky')
        self.assertIn("1st through 5th even lucky numbers", stdout.lower())

    def test_value_range_lucky_numbers(self):
        stdout, stderr = self.run_cli('6000', '-6100', 'lucky')
        self.assertIn("all lucky numbers in the range 6000 ──► 6100", stdout.lower())

    def test_value_range_even_lucky_numbers(self):
        stdout, stderr = self.run_cli('6000', '-6100', 'evenLucky')
        self.assertIn("all even lucky numbers in the range 6000 ──► 6100", stdout.lower())

    def test_10000th_lucky_number(self):
        stdout, stderr = self.run_cli('10000', 'lucky')
        self.assertIn("10000th lucky number", stdout.lower())

    def test_10000th_even_lucky_number(self):
        stdout, stderr = self.run_cli('10000', 'evenLucky')
        self.assertIn("10000th even lucky number", stdout.lower())

    def test_first_twenty_lucky_numbers(self):
        stdout, stderr = self.run_cli('1', '20', 'lucky')
        self.assertIn("1st through 20th lucky numbers", stdout.lower())

    def test_first_twenty_even_lucky_numbers(self):
        stdout, stderr = self.run_cli('1', '20', 'evenLucky')
        self.assertIn("1st through 20th even lucky numbers", stdout.lower())

if __name__ == '__main__':
    unittest.main()

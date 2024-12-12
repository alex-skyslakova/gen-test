import unittest
from unittest.mock import patch
from io import StringIO

# Assuming the function is in a file named 'abbreviations_automatic.py'
from abbreviations_automatic import shortest_abbreviation_length, automatic_abbreviations


class TestShortestAbbreviationLength(unittest.TestCase):

    def test_unique_abbreviations(self):
        self.assertEqual(shortest_abbreviation_length("Sunday Monday Tuesday Wednesday Thursday Friday Saturday", 7), 3)

    def test_short_words(self):
        self.assertEqual(shortest_abbreviation_length("Sun Mon Tue Wed Thu Fri Sat", 7), 3)

    def test_duplicate_prefixes(self):
        self.assertEqual(shortest_abbreviation_length("Saturday Saturnday Satunday Saturday Thursday Friday Satday", 7), 4)


    def test_invalid_word_count(self):
        with self.assertRaises(ValueError) as context:
            shortest_abbreviation_length("Sunday Monday Tuesday", 7)
        self.assertEqual(str(context.exception), "Not enough entries, expected 7 found 3")
    
    def test_empty_input(self):
        with self.assertRaises(ValueError) as context:
            shortest_abbreviation_length("", 7)
        self.assertEqual(str(context.exception), "Not enough entries, expected 7 found 0")


class TestAutomaticAbbreviations(unittest.TestCase):

    @patch('builtins.open', return_value=StringIO(
        "Sunday Monday Tuesday Wednesday Thursday Friday Saturday\n"
        "Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag\n"
        "\n"
        "E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë\n"
        "Sun Mon Tue Wed Thu Fri Sat"
    ))
    @patch('sys.stdout', new_callable=StringIO)
    def test_file_processing(self, mock_stdout, mock_open):
        automatic_abbreviations('daysOfWeek.txt', 7)
        expected_output = (
            " 3 Sunday Monday Tuesday Wednesday Thursday Friday Saturday\n"
            " 3 Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag\n"
            "\n"
            " 3 E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë\n"
            " 3 Sun Mon Tue Wed Thu Fri Sat\n"

        )
        self.assertEqual(mock_stdout.getvalue(), expected_output)

    @patch('builtins.open', return_value=StringIO(""))
    @patch('sys.stdout', new_callable=StringIO)
    def test_empty_file(self, mock_stdout, mock_open):
        automatic_abbreviations('daysOfWeek.txt', 7)
        self.assertEqual(mock_stdout.getvalue(), "")


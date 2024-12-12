import unittest
from abbreviations_automatic import shortest_abbreviation_length, automatic_abbreviations

class TestAbbreviationsAutomatic(unittest.TestCase):

    def test_shortest_abbreviation_length(self):
        # Test case with unique abbreviations at length 3
        line = "Sunday Monday Tuesday Wednesday Thursday Friday Saturday"
        self.assertEqual(shortest_abbreviation_length(line, 7), 3)

        # Test case with unique abbreviations at length 2
        line = "Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag"
        self.assertEqual(shortest_abbreviation_length(line, 7), 2)

        # Test case with unique abbreviations at length 1
        line = "A B C D E F G"
        self.assertEqual(shortest_abbreviation_length(line, 7), 1)

        # Test case with not enough entries
        with self.assertRaises(ValueError):
            shortest_abbreviation_length("Sunday Monday Tuesday", 7)

        # Test case with more entries than expected
        with self.assertRaises(ValueError):
            shortest_abbreviation_length("Sunday Monday Tuesday Wednesday Thursday Friday Saturday Sunday", 7)

    def test_automatic_abbreviations(self):
        # Test case with a sample file
        import tempfile
        import os

        content = """Sunday Monday Tuesday Wednesday Thursday Friday Saturday
        Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag
        A B C D E F G
        """
        with tempfile.NamedTemporaryFile(delete=False) as temp_file:
            temp_file.write(content.encode('utf-8'))
            temp_file_name = temp_file.name

        try:
            import io
            captured_output = io.StringIO()
            import sys
            sys.stdout = captured_output

            automatic_abbreviations(temp_file_name, 7)

            sys.stdout = sys.__stdout__
            output = captured_output.getvalue()

            expected_output = " 3 Sunday Monday Tuesday Wednesday Thursday Friday Saturday\n 2 Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag\n 1 A B C D E F G\n"
            self.assertEqual(output, expected_output)
        finally:
            os.remove(temp_file_name)

if __name__ == '__main__':
    unittest.main()

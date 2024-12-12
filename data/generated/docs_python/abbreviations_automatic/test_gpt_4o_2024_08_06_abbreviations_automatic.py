import unittest

class TestAbbreviationsAutomatic(unittest.TestCase):
    def test_shortest_abbreviation_length(self):
        # Test with a normal line
        line = "Sunday Monday Tuesday Wednesday Thursday Friday Saturday"
        self.assertEqual(shortest_abbreviation_length(line, 7), 3)

        # Test with a line with unique first letters
        line = "Sun Mon Tue Wed Thu Fri Sat"
        self.assertEqual(shortest_abbreviation_length(line, 7), 1)

        # Test with a line where all words are the same
        line = "Day Day Day Day Day Day Day"
        self.assertEqual(shortest_abbreviation_length(line, 7), 3)

        # Test with a line with mixed case
        line = "sunday Monday tuesday Wednesday thursday Friday saturday"
        self.assertEqual(shortest_abbreviation_length(line, 7), 3)

        # Test with a line with special characters
        line = "Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag"
        self.assertEqual(shortest_abbreviation_length(line, 7), 3)

        # Test with a line with underscores
        line = "E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë"
        self.assertEqual(shortest_abbreviation_length(line, 7), 3)

        # Test with a line with unexpected number of entries
        line = "Sunday Monday Tuesday"
        with self.assertRaises(ValueError):
            shortest_abbreviation_length(line, 7)

    def test_automatic_abbreviations(self):
        # Create a temporary file with test data
        with open('test_daysOfWeek.txt', 'w') as file:
            file.write("Sunday Monday Tuesday Wednesday Thursday Friday Saturday\n")
            file.write("Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag\n")
            file.write("E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë\n")
            file.write("\n")
            file.write("Ehud Segno Maksegno Erob Hamus Arbe Kedame\n")

        # Capture the output of the function
        from io import StringIO
        import sys

        captured_output = StringIO()
        sys.stdout = captured_output
        automatic_abbreviations('test_daysOfWeek.txt', 7)
        sys.stdout = sys.__stdout__

        # Check the output
        expected_output = " 3 Sunday Monday Tuesday Wednesday Thursday Friday Saturday\n" \
                          " 3 Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag\n" \
                          " 3 E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë\n" \
                          "\n" \
                          " 2 Ehud Segno Maksegno Erob Hamus Arbe Kedame\n"
        self.assertEqual(captured_output.getvalue(), expected_output)

if __name__ == '__main__':
    unittest.main()

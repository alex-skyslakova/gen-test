import unittest
from unittest.mock import patch
from io import StringIO
import re
from create_an_html_table import create_an_html_table

class TestCreateHTMLTable(unittest.TestCase):

    @patch('sys.stdout', new_callable=StringIO)
    def test_table_structure(self, mock_stdout):
        create_an_html_table()
        output = mock_stdout.getvalue()

        # Check for opening and closing table tags
        self.assertIn("<table>", output)
        self.assertIn("</table>", output)

        # Check for header row with correct columns
        self.assertIn("<th></th>", output)  # Empty header for row numbers
        self.assertIn("<th>X</th>", output)
        self.assertIn("<th>Y</th>", output)
        self.assertIn("<th>Z</th>", output)

        # Check for at least three data rows
        self.assertGreaterEqual(output.count("<tr>"), 4) # 1 header + at least 3 data rows


    @patch('sys.stdout', new_callable=StringIO)
    def test_row_numbers(self, mock_stdout):
        create_an_html_table()
        output = mock_stdout.getvalue()

        # Extract row numbers and check for sequence
        row_numbers = []
        for match in re.finditer(r"<td style=\"font-weight: bold;\">(\d+)</td>", output):
            row_numbers.append(int(match.group(1)))

        self.assertGreaterEqual(len(row_numbers), 3)
        self.assertEqual(row_numbers, list(range(1, len(row_numbers) + 1))) # Check for sequence from 1

    @patch('sys.stdout', new_callable=StringIO)
    def test_data_format(self, mock_stdout):
        create_an_html_table()
        output = mock_stdout.getvalue()

        # Check if data cells contain 4-digit or less integers
        for match in re.finditer(r"<td>(\d+)</td>", output):
            number = int(match.group(1))
            self.assertLessEqual(number, 9999)

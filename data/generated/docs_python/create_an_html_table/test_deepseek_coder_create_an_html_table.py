import unittest
from unittest.mock import patch
from create_an_html_table import rand9999, tag

class TestCreateAnHtmlTable(unittest.TestCase):

    def test_rand9999(self):
        # Test that rand9999 returns a number between 1000 and 9999
        result = rand9999()
        self.assertTrue(1000 <= result <= 9999)

    def test_tag(self):
        # Test the tag function with a simple example
        result = tag(p='Hello, World!')
        self.assertEqual(result, '<p>Hello, World!</p>')

        # Test the tag function with attributes
        result = tag(attr=' class="test"', p='Hello, World!')
        self.assertEqual(result, '<p class="test">Hello, World!</p>')

    @patch('create_an_html_table.rand9999')
    def test_create_html_table(self, mock_rand9999):
        # Mock the rand9999 function to return predictable values
        mock_rand9999.side_effect = [1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000]

        # Expected HTML table structure
        expected_header = '<tr><th></th><th>X</th><th>Y</th><th>Z</th></tr>\n'
        expected_rows = (
            '<tr><td style="font-weight: bold;">1</td><td>1000</td><td>2000</td><td>3000</td></tr>\n'
            '<tr><td style="font-weight: bold;">2</td><td>4000</td><td>5000</td><td>6000</td></tr>\n'
            '<tr><td style="font-weight: bold;">3</td><td>7000</td><td>8000</td><td>9000</td></tr>'
        )
        expected_table = '<table>\n' + expected_header + expected_rows + '\n</table>'

        # Capture the output of the main function
        import io
        from contextlib import redirect_stdout
        with io.StringIO() as buf, redirect_stdout(buf):
            import create_an_html_table
            output = buf.getvalue()

        # Test the output against the expected table
        self.assertEqual(output.strip(), expected_table)

if __name__ == '__main__':
    unittest.main()

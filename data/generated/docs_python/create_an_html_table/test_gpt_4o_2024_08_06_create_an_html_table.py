import unittest
from create_an_html_table import rand9999, tag

class TestCreateAnHtmlTable(unittest.TestCase):

    def test_rand9999(self):
        # Test that rand9999 returns a number between 1000 and 9999
        for _ in range(100):
            result = rand9999()
            self.assertGreaterEqual(result, 1000)
            self.assertLessEqual(result, 9999)

    def test_tag_single(self):
        # Test the tag function with a single tag and no attributes
        result = tag(td='1234')
        self.assertEqual(result, '<td>1234</td>')

    def test_tag_with_attributes(self):
        # Test the tag function with a single tag and attributes
        result = tag(' style="font-weight: bold;"', td='1234')
        self.assertEqual(result, '<td style="font-weight: bold;">1234</td>')

    def test_tag_multiple(self):
        # Test the tag function with multiple tags
        result = tag(tr=tag(td='1234') + tag(td='5678'))
        self.assertEqual(result, '<tr><td>1234</td><td>5678</td></tr>')

    def test_table_structure(self):
        # Test the overall structure of the generated table
        header = tag(tr=''.join(tag(th=txt) for txt in ',X,Y,Z'.split(','))) + '\n'
        rows = '\n'.join(tag(tr=tag(' style="font-weight: bold;"', td=i)
                                        + ''.join(tag(td=rand9999())
                                                  for j in range(3)))
                         for i in range(1, 6))
        table = tag(table='\n' + header + rows + '\n')
        
        # Check if the table starts and ends with the correct tags
        self.assertTrue(table.startswith('<table>'))
        self.assertTrue(table.endswith('</table>'))

        # Check if the header is correctly formatted
        self.assertIn('<tr><th></th><th>X</th><th>Y</th><th>Z</th></tr>', table)

        # Check if there are 5 rows (excluding the header)
        self.assertEqual(table.count('<tr><td style="font-weight: bold;">'), 5)

if __name__ == '__main__':
    unittest.main()

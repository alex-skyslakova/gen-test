import io
import unittest
from fasta_format import fasta_parse

class TestFastaParse(unittest.TestCase):
    def setUp(self):
        self.fasta_data = '''\
>Rosetta_Example_1
THERECANBENOSPACE
>Rosetta_Example_2
THERECANBESEVERAL
LINESBUTTHEYALLMUST
BECONCATENATED'''

    def test_single_line_sequence(self):
        fasta_input = io.StringIO('''\
>Example_1
SEQUENCE''')
        result = list(fasta_parse(fasta_input))
        expected = [('Example_1', 'SEQUENCE')]
        self.assertEqual(result, expected)

    def test_multi_line_sequence(self):
        fasta_input = io.StringIO('''\
>Example_2
MULTILINE
SEQUENCEPART1
SEQUENCEPART2''')
        result = list(fasta_parse(fasta_input))
        expected = [('Example_2', 'MULTILINESEQUENCEPART1SEQUENCEPART2')]
        self.assertEqual(result, expected)

    def test_multiple_entries(self):
        fasta_input = io.StringIO(self.fasta_data)
        result = list(fasta_parse(fasta_input))
        expected = [
            ('Rosetta_Example_1', 'THERECANBENOSPACE'),
            ('Rosetta_Example_2', 'THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED')
        ]
        self.assertEqual(result, expected)

    def test_empty_file(self):
        fasta_input = io.StringIO('')
        result = list(fasta_parse(fasta_input))
        expected = []
        self.assertEqual(result, expected)

    def test_no_sequence(self):
        fasta_input = io.StringIO('''\
>Example_3''')
        result = list(fasta_parse(fasta_input))
        expected = [('Example_3', '')]
        self.assertEqual(result, expected)

    def test_no_header(self):
        fasta_input = io.StringIO('SEQUENCEWITHOUTHEADER')
        result = list(fasta_parse(fasta_input))
        expected = []
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()

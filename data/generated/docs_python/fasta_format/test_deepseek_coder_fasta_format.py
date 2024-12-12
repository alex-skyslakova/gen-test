import io
import unittest

class TestFastaParse(unittest.TestCase):

    def test_single_entry_no_space(self):
        FASTA = '''\
>Rosetta_Example_1
THERECANBENOSPACE'''
        infile = io.StringIO(FASTA)
        result = list(fasta_parse(infile))
        self.assertEqual(result, [('Rosetta_Example_1', 'THERECANBENOSPACE')])

    def test_single_entry_with_multiple_lines(self):
        FASTA = '''\
>Rosetta_Example_2
THERECANBESEVERAL
LINESBUTTHEYALLMUST
BECONCATENATED'''
        infile = io.StringIO(FASTA)
        result = list(fasta_parse(infile))
        self.assertEqual(result, [('Rosetta_Example_2', 'THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED')])

    def test_multiple_entries(self):
        FASTA = '''\
>Rosetta_Example_1
THERECANBENOSPACE
>Rosetta_Example_2
THERECANBESEVERAL
LINESBUTTHEYALLMUST
BECONCATENATED'''
        infile = io.StringIO(FASTA)
        result = list(fasta_parse(infile))
        self.assertEqual(result, [('Rosetta_Example_1', 'THERECANBENOSPACE'), ('Rosetta_Example_2', 'THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED')])

    def test_empty_file(self):
        FASTA = ''''''
        infile = io.StringIO(FASTA)
        result = list(fasta_parse(infile))
        self.assertEqual(result, [])

    def test_no_entries(self):
        FASTA = '''\
This is not a FASTA file'''
        infile = io.StringIO(FASTA)
        result = list(fasta_parse(infile))
        self.assertEqual(result, [])

    def test_entry_with_spaces_in_name(self):
        FASTA = '''\
>Rosetta Example 1
THERECANBENOSPACE'''
        infile = io.StringIO(FASTA)
        result = list(fasta_parse(infile))
        self.assertEqual(result, [('Rosetta', 'THERECANBENOSPACE')])

    def test_entry_with_spaces_in_sequence(self):
        FASTA = '''\
>Rosetta_Example_1
THERE CAN BE NO SPACE'''
        infile = io.StringIO(FASTA)
        result = list(fasta_parse(infile))
        self.assertEqual(result, [('Rosetta_Example_1', 'THERE CAN BE NO SPACE')])

    def test_entry_with_empty_sequence(self):
        FASTA = '''\
>Rosetta_Example_1
>Rosetta_Example_2
THERECANBESEVERAL'''
        infile = io.StringIO(FASTA)
        result = list(fasta_parse(infile))
        self.assertEqual(result, [('Rosetta_Example_1', ''), ('Rosetta_Example_2', 'THERECANBESEVERAL')])

if __name__ == '__main__':
    unittest.main()

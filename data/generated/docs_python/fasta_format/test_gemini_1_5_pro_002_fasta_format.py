import unittest
import io
from fasta_format import fasta_parse

class TestFastaParse(unittest.TestCase):

    def test_single_entry(self):
        fasta_string = ">seq1\nACGT"
        infile = io.StringIO(fasta_string)
        expected = [("seq1", "ACGT")]
        self.assertEqual(list(fasta_parse(infile)), expected)

    def test_multiple_entries(self):
        fasta_string = """>seq1
ACGT
>seq2
TGCA"""
        infile = io.StringIO(fasta_string)
        expected = [("seq1", "ACGT"), ("seq2", "TGCA")]
        self.assertEqual(list(fasta_parse(infile)), expected)

    def test_multiline_sequence(self):
        fasta_string = """>seq1
ACGT
TTTT
>seq2
TGCA"""
        infile = io.StringIO(fasta_string)
        expected = [("seq1", "ACGTTTTT"), ("seq2", "TGCA")]
        self.assertEqual(list(fasta_parse(infile)), expected)

    def test_empty_input(self):
        fasta_string = ""
        infile = io.StringIO(fasta_string)
        expected = []
        self.assertEqual(list(fasta_parse(infile)), expected)


    def test_only_header(self):
        fasta_string = ">seq1\n"
        infile = io.StringIO(fasta_string)
        expected = [("seq1", "")]
        self.assertEqual(list(fasta_parse(infile)), expected)

    def test_header_with_spaces(self):  # Handles extra spaces in the header line
        fasta_string = ">seq1 description \nACGT"
        infile = io.StringIO(fasta_string)
        expected = [("seq1", "ACGT")]
        self.assertEqual(list(fasta_parse(infile)), expected)

    def test_consecutive_headers(self): # Handles consecutive headers correctly
        fasta_string = """>seq1
>seq2
ACGT"""
        infile = io.StringIO(fasta_string)
        expected = [("seq1", ""), ("seq2", "ACGT")]
        self.assertEqual(list(fasta_parse(infile)), expected)

    def test_trailing_newline(self): # Handles a trailing newline gracefully
        fasta_string = """>seq1
ACGT
\n"""
        infile = io.StringIO(fasta_string)
        expected = [("seq1", "ACGT")]
        self.assertEqual(list(fasta_parse(infile)), expected)



if __name__ == '__main__':
    unittest.main()


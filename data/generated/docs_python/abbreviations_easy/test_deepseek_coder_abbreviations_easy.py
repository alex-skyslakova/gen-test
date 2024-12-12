import unittest
from abbreviations_easy import find_abbreviations_length, find_abbreviations, parse_user_string

class TestAbbreviations(unittest.TestCase):

    def setUp(self):
        self.command_table_text = """Add ALTer  BAckup Bottom  CAppend Change SCHANGE  CInsert CLAst COMPress COpy
COUnt COVerlay CURsor DELete CDelete Down DUPlicate Xedit EXPand EXTract Find
NFind NFINDUp NFUp CFind FINdup FUp FOrward GET Help HEXType Input POWerinput
Join SPlit SPLTJOIN  LOAD  Locate CLocate  LOWercase UPPercase  LPrefix MACRO
MErge MODify MOve MSG Next Overlay PARSE PREServe PURge PUT PUTD  Query  QUIT
READ  RECover REFRESH RENum REPeat  Replace CReplace  RESet  RESTore  RGTLEFT
RIght LEft  SAVE  SET SHift SI  SORT  SOS  STAck STATus  TOP TRAnsfer Type Up"""

        self.user_words = "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin"
        self.expected_output = "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT"

    def test_find_abbreviations_length(self):
        command_table = find_abbreviations_length(self.command_table_text)
        self.assertEqual(command_table['ALTer'], 3)
        self.assertEqual(command_table['COUnt'], 2)
        self.assertEqual(command_table['LOAD'], 4)
        self.assertEqual(command_table['LOWercase'], 8)

    def test_find_abbreviations(self):
        command_table = find_abbreviations_length(self.command_table_text)
        abbreviations_table = find_abbreviations(command_table)
        self.assertEqual(abbreviations_table['alt'], 'ALTER')
        self.assertEqual(abbreviations_table['count'], 'COUNT')
        self.assertEqual(abbreviations_table['load'], 'LOAD')
        self.assertEqual(abbreviations_table['lowercase'], 'LOWERCASE')

    def test_parse_user_string(self):
        command_table = find_abbreviations_length(self.command_table_text)
        abbreviations_table = find_abbreviations(command_table)
        result = parse_user_string(self.user_words, abbreviations_table)
        self.assertEqual(result, self.expected_output)

    def test_parse_user_string_empty_input(self):
        command_table = find_abbreviations_length(self.command_table_text)
        abbreviations_table = find_abbreviations(command_table)
        result = parse_user_string("", abbreviations_table)
        self.assertEqual(result, "")

    def test_parse_user_string_null_input(self):
        command_table = find_abbreviations_length(self.command_table_text)
        abbreviations_table = find_abbreviations(command_table)
        result = parse_user_string(None, abbreviations_table)
        self.assertEqual(result, "")

    def test_parse_user_string_invalid_abbreviations(self):
        command_table = find_abbreviations_length(self.command_table_text)
        abbreviations_table = find_abbreviations(command_table)
        invalid_input = "AL ALF ALTERS TER A o ov oVe over overL overla"
        expected_output = "*error* *error* *error* *error* *error* OVER OVER OVER OVER OVER OVER"
        result = parse_user_string(invalid_input, abbreviations_table)
        self.assertEqual(result, expected_output)

if __name__ == '__main__':
    unittest.main()

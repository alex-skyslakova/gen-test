import unittest

class TestAbbreviations(unittest.TestCase):
    def setUp(self):
        self.command_table_text = """Add ALTer  BAckup Bottom  CAppend Change SCHANGE  CInsert CLAst COMPress COpy
        COUnt COVerlay CURsor DELete CDelete Down DUPlicate Xedit EXPand EXTract Find
        NFind NFINDUp NFUp CFind FINdup FUp FOrward GET Help HEXType Input POWerinput
        Join SPlit SPLTJOIN  LOAD  Locate CLocate  LOWercase UPPercase  LPrefix MACRO
        MErge MODify MOve MSG Next Overlay PARSE PREServe PURge PUT PUTD  Query  QUIT
        READ  RECover REFRESH RENum REPeat  Replace CReplace  RESet  RESTore  RGTLEFT
        RIght LEft  SAVE  SET SHift SI  SORT  SOS  STAck STATus  TOP TRAnsfer Type Up"""
        
        self.command_table = find_abbreviations_length(self.command_table_text)
        self.abbreviations_table = find_abbreviations(self.command_table)

    def test_valid_abbreviations(self):
        user_words = "riG rePEAT put mo rest poweRin"
        expected_output = "RIGHT REPEAT PUT MOVE RESTORE POWERINPUT"
        result = parse_user_string(user_words, self.abbreviations_table)
        self.assertEqual(result, expected_output)

    def test_invalid_abbreviations(self):
        user_words = "copies types fup."
        expected_output = "*error* *error* *error*"
        result = parse_user_string(user_words, self.abbreviations_table)
        self.assertEqual(result, expected_output)

    def test_mixed_valid_invalid(self):
        user_words = "riG rePEAT copies put mo rest types fup. poweRin"
        expected_output = "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* POWERINPUT"
        result = parse_user_string(user_words, self.abbreviations_table)
        self.assertEqual(result, expected_output)

    def test_blank_input(self):
        user_words = ""
        expected_output = ""
        result = parse_user_string(user_words, self.abbreviations_table)
        self.assertEqual(result, expected_output)

    def test_null_input(self):
        user_words = None
        expected_output = ""
        result = parse_user_string(user_words or "", self.abbreviations_table)
        self.assertEqual(result, expected_output)

if __name__ == '__main__':
    unittest.main()

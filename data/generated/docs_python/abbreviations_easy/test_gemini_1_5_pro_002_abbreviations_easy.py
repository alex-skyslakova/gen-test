import unittest
from code_abbreviations_easy import find_abbreviations_length, find_abbreviations, parse_user_string

class TestAbbreviations(unittest.TestCase):

    command_table_text = \
"""Add ALTer  BAckup Bottom  CAppend Change SCHANGE  CInsert CLAst COMPress COpy
COUnt COVerlay CURsor DELete CDelete Down DUPlicate Xedit EXPand EXTract Find
NFind NFINDUp NFUp CFind FINdup FUp FOrward GET Help HEXType Input POWerinput
Join SPlit SPLTJOIN  LOAD  Locate CLocate  LOWercase UPPercase  LPrefix MACRO
MErge MODify MOve MSG Next Overlay PARSE PREServe PURge PUT PUTD  Query  QUIT
READ  RECover REFRESH RENum REPeat  Replace CReplace  RESet  RESTore  RGTLEFT
RIght LEft  SAVE  SET SHift SI  SORT  SOS  STAck STATus  TOP TRAnsfer Type Up"""

    def test_find_abbreviations_length(self):
        expected_lengths = {
            'Add': 1, 'ALTer': 3, 'BAckup': 2, 'Bottom': 1, 'CAppend': 2, 'Change': 1, 
            'SCHANGE': 2, 'CInsert': 2, 'CLAst': 2, 'COMPress': 2, 'COpy': 2, 'COUnt': 2, 
            'COVerlay': 2, 'CURsor': 1, 'DELete': 2, 'CDelete': 2, 'Down': 1, 'DUPlicate': 2, 
            'Xedit': 1, 'EXPand': 2, 'EXTract': 2, 'Find': 1, 'NFind': 1, 'NFINDUp': 2, 
            'NFUp': 2, 'CFind': 2, 'FINdup': 2, 'FUp': 2, 'FOrward': 2, 'GET': 1, 'Help': 1, 
            'HEXType': 2, 'Input': 1, 'POWerinput': 3, 'Join': 1, 'SPlit': 2, 'SPLTJOIN': 3, 
            'LOAD': 1, 'Locate': 1, 'CLocate': 2, 'LOWercase': 2, 'UPPercase': 2, 'LPrefix': 2, 
            'MACRO': 1, 'MErge': 2, 'MODify': 2, 'MOve': 2, 'MSG': 1, 'Next': 1, 'Overlay': 1, 
            'PARSE': 1, 'PREServe': 2, 'PURge': 2, 'PUT': 1, 'PUTD': 1, 'Query': 1, 'QUIT': 1, 
            'READ': 1, 'RECover': 2, 'REFRESH': 1, 'RENum': 2, 'REPeat': 2, 'Replace': 1, 
            'CReplace': 2, 'RESet': 2, 'RESTore': 2, 'RGTLEFT': 2, 'RIght': 2, 'LEft': 1, 
            'SAVE': 1, 'SET': 1, 'SHift': 2, 'SI': 1, 'SORT': 1, 'SOS': 1, 'STAck': 2, 
            'STATus': 2, 'TOP': 1, 'TRAnsfer': 2, 'Type': 1, 'Up': 1
        }
        self.assertEqual(find_abbreviations_length(self.command_table_text), expected_lengths)

    def test_find_abbreviations(self):
        command_table = find_abbreviations_length(self.command_table_text)
        abbreviations = find_abbreviations(command_table)
        self.assertEqual(abbreviations['alt'], 'ALTER')
        self.assertEqual(abbreviations['alte'], 'ALTER')
        self.assertEqual(abbreviations['alter'], 'ALTER')
        self.assertEqual(abbreviations['over'], 'OVERLAY')
        self.assertEqual(abbreviations['overl'], 'OVERLAY')
        self.assertEqual(abbreviations['overla'], 'OVERLAY')
        self.assertEqual(abbreviations['overlay'], 'OVERLAY')

    def test_parse_user_string(self):
        command_table = find_abbreviations_length(self.command_table_text)
        abbreviations = find_abbreviations(command_table)
        self.assertEqual(parse_user_string("riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin", abbreviations), "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT")
        self.assertEqual(parse_user_string("", abbreviations), "")
        self.assertEqual(parse_user_string("   ", abbreviations), "")
        self.assertEqual(parse_user_string("add alt", abbreviations), "ADD ALTER")
        self.assertEqual(parse_user_string("a", abbreviations), "ADD")


if __name__ == '__main__':
    unittest.main()

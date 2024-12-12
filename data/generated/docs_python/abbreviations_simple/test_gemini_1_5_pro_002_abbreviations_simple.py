import unittest
from abbreviations_simple import find_abbreviations_length, find_abbreviations, parse_user_string

command_table_text = """add 1  alter 3  backup 2  bottom 1  Cappend 2  change 1  Schange  Cinsert 2  Clast 3
   compress 4 copy 2 count 3 Coverlay 3 cursor 3  delete 3 Cdelete 2  down 1  duplicate
   3 xEdit 1 expand 3 extract 3  find 1 Nfind 2 Nfindup 6 NfUP 3 Cfind 2 findUP 3 fUP 2
   forward 2  get  help 1 hexType 4  input 1 powerInput 3  join 1 split 2 spltJOIN load
   locate 1 Clocate 2 lowerCase 3 upperCase 3 Lprefix 2  macro  merge 2 modify 3 move 2
   msg  next 1 overlay 1 parse preserve 4 purge 3 put putD query 1 quit  read recover 3
   refresh renum 3 repeat 3 replace 1 Creplace 2 reset 3 restore 4 rgtLEFT right 2 left
   2  save  set  shift 2  si  sort  sos  stack 3 status 4 top  transfer 3  type 1  up 1"""

class TestAbbreviations(unittest.TestCase):

    def test_find_abbreviations_length(self):
        expected_table = {'add': 1, 'alter': 3, 'backup': 2, 'bottom': 1, 'Cappend': 2, 'change': 1, 'Schange': 7, 'Cinsert': 2, 'Clast': 3, 'compress': 4, 'copy': 2, 'count': 3, 'Coverlay': 3, 'cursor': 3, 'delete': 3, 'Cdelete': 2, 'down': 1, 'duplicate': 3, 'xEdit': 1, 'expand': 3, 'extract': 3, 'find': 1, 'Nfind': 2, 'Nfindup': 6, 'NfUP': 3, 'Cfind': 2, 'findUP': 3, 'fUP': 2, 'forward': 2, 'get': 3, 'help': 1, 'hexType': 4, 'input': 1, 'powerInput': 3, 'join': 1, 'split': 2, 'spltJOIN': 8, 'load': 4, 'locate': 1, 'Clocate': 2, 'lowerCase': 3, 'upperCase': 3, 'Lprefix': 2, 'macro': 5, 'merge': 2, 'modify': 3, 'move': 2, 'msg': 3, 'next': 1, 'overlay': 1, 'parse': 5, 'preserve': 4, 'purge': 3, 'put': 3, 'putD': 4, 'query': 1, 'quit': 4, 'read': 4, 'recover': 3, 'refresh': 7, 'renum': 3, 'repeat': 3, 'replace': 1, 'Creplace': 2, 'reset': 3, 'restore': 4, 'rgtLEFT': 7, 'right': 2, 'left': 2, 'save': 4, 'set': 3, 'shift': 2, 'si': 2, 'sort': 4, 'sos': 3, 'stack': 3, 'status': 4, 'top': 3, 'transfer': 3, 'type': 1, 'up': 1}
        self.assertEqual(find_abbreviations_length(command_table_text), expected_table)

    def test_find_abbreviations(self):
        command_table = find_abbreviations_length(command_table_text)
        abbreviations = find_abbreviations(command_table)
        self.assertIn('a', abbreviations)
        self.assertEqual(abbreviations['a'], 'ADD')
        self.assertIn('alt', abbreviations)
        self.assertEqual(abbreviations['alt'], 'ALTER')
        self.assertIn('alte', abbreviations)  # Test abbreviation longer than min length
        self.assertEqual(abbreviations['alte'], 'ALTER')


    def test_parse_user_string(self):
        command_table = find_abbreviations_length(command_table_text)
        abbreviations = find_abbreviations(command_table)
        self.assertEqual(parse_user_string("riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin", abbreviations), "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT")
        self.assertEqual(parse_user_string("", abbreviations), "") # Test empty string
        self.assertEqual(parse_user_string("add alt", abbreviations), "ADD ALTER") # Test valid abbreviations
        self.assertEqual(parse_user_string("al", abbreviations), "*error*")  # Test invalid abbreviation
        self.assertEqual(parse_user_string("     ", abbreviations), "") #Test string with only spaces


if __name__ == '__main__':
    unittest.main()

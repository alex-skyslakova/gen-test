import unittest

class TestAbbreviations(unittest.TestCase):
    def setUp(self):
        self.command_table_text = """add 1  alter 3  backup 2  bottom 1  Cappend 2  change 1  Schange  Cinsert 2  Clast 3
           compress 4 copy 2 count 3 Coverlay 3 cursor 3  delete 3 Cdelete 2  down 1  duplicate
           3 xEdit 1 expand 3 extract 3  find 1 Nfind 2 Nfindup 6 NfUP 3 Cfind 2 findUP 3 fUP 2
           forward 2  get  help 1 hexType 4  input 1 powerInput 3  join 1 split 2 spltJOIN load
           locate 1 Clocate 2 lowerCase 3 upperCase 3 Lprefix 2  macro  merge 2 modify 3 move 2
           msg  next 1 overlay 1 parse preserve 4 purge 3 put putD query 1 quit  read recover 3
           refresh renum 3 repeat 3 replace 1 Creplace 2 reset 3 restore 4 rgtLEFT right 2 left
           2  save  set  shift 2  si  sort  sos  stack 3 status 4 top  transfer 3  type 1  up 1"""
        
        self.command_table = find_abbreviations_length(self.command_table_text)
        self.abbreviations_table = find_abbreviations(self.command_table)

    def test_valid_abbreviations(self):
        user_words = "riG rePEAT put mo rest poweRin"
        expected_output = "RIGHT REPEAT PUT MOVE RESTORE POWERINPUT"
        result = parse_user_string(user_words, self.abbreviations_table)
        self.assertEqual(result, expected_output)

    def test_invalid_abbreviations(self):
        user_words = "copies types fup. 6"
        expected_output = "*error* *error* *error* *error*"
        result = parse_user_string(user_words, self.abbreviations_table)
        self.assertEqual(result, expected_output)

    def test_mixed_valid_invalid(self):
        user_words = "riG rePEAT copies put mo rest types fup. 6 poweRin"
        expected_output = "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT"
        result = parse_user_string(user_words, self.abbreviations_table)
        self.assertEqual(result, expected_output)

    def test_empty_input(self):
        user_words = ""
        expected_output = ""
        result = parse_user_string(user_words, self.abbreviations_table)
        self.assertEqual(result, expected_output)

    def test_null_input(self):
        user_words = None
        expected_output = ""
        result = parse_user_string(user_words, self.abbreviations_table)
        self.assertEqual(result, expected_output)

if __name__ == '__main__':
    unittest.main()

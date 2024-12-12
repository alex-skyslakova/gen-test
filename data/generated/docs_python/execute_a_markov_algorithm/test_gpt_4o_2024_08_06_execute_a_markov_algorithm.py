import unittest

class TestMarkovAlgorithm(unittest.TestCase):

    def setUp(self):
        self.grammar1 = """\
# This rules file is extracted from Wikipedia:
# http://en.wikipedia.org/wiki/Markov_Algorithm
A -> apple
B -> bag
S -> shop
T -> the
the shop -> my brother
a never used -> .terminating rule
"""
        self.grammar2 = '''\
# Slightly modified from the rules on Wikipedia
A -> apple
B -> bag
S -> .shop
T -> the
the shop -> my brother
a never used -> .terminating rule
'''
        self.grammar3 = '''\
# BNF Syntax testing rules
A -> apple
WWWW -> with
Bgage -> ->.*
B -> bag
->.* -> money
W -> WW
S -> .shop
T -> the
the shop -> my brother
a never used -> .terminating rule
'''
        self.grammar4 = '''\
### Unary Multiplication Engine, for testing Markov Algorithm implementations
### By Donal Fellows.
# Unary addition engine
_+1 -> _1+
1+1 -> 11+
# Pass for converting from the splitting of multiplication into ordinary
# addition
1! -> !1
,! -> !+
_! -> _
# Unary multiplication by duplicating left side, right side times
1*1 -> x,@y
1x -> xX
X, -> 1,1
X1 -> 1X
_x -> _X
,x -> ,X
y1 -> 1y
y_ -> _
# Next phase of applying
1@1 -> x,@y
1@_ -> @_
,@_ -> !_
++ -> +
# Termination cleanup for addition
_1 -> 1
1+_ -> 1
_+_ -> 
'''
        self.grammar5 = '''\
# Turing machine: three-state busy beaver
#
# state A, symbol 0 => write 1, move right, new state B
A0 -> 1B
# state A, symbol 1 => write 1, move left, new state C
0A1 -> C01
1A1 -> C11
# state B, symbol 0 => write 1, move left, new state A
0B0 -> A01
1B0 -> A11
# state B, symbol 1 => write 1, move right, new state B
B1 -> 1B
# state C, symbol 0 => write 1, move left, new state B
0C0 -> B01
1C0 -> B11
# state C, symbol 1 => write 1, move left, halt
0C1 -> H01
1C1 -> H11
'''
        self.text1 = "I bought a B of As from T S."
        self.text2 = "I bought a B of As W my Bgage from T S."
        self.text3 = '_1111*11111_'
        self.text4 = '000000A000000'

    def test_ruleset1(self):
        replacements = extractreplacements(self.grammar1)
        result = replace(self.text1, replacements)
        self.assertEqual(result, 'I bought a bag of apples from my brother.')

    def test_ruleset2(self):
        replacements = extractreplacements(self.grammar2)
        result = replace(self.text1, replacements)
        self.assertEqual(result, 'I bought a bag of apples from T shop.')

    def test_ruleset3(self):
        replacements = extractreplacements(self.grammar3)
        result = replace(self.text2, replacements)
        self.assertEqual(result, 'I bought a bag of apples with my money from T shop.')

    def test_ruleset4(self):
        replacements = extractreplacements(self.grammar4)
        result = replace(self.text3, replacements)
        self.assertEqual(result, '11111111111111111111')

    def test_ruleset5(self):
        replacements = extractreplacements(self.grammar5)
        result = replace(self.text4, replacements)
        self.assertEqual(result, '00011H1111000')

if __name__ == '__main__':
    unittest.main()

import (
	"regexp"
	"strings"
	"testing"
)

func TestInterpret(t *testing.T) {
	tests := []struct {
		ruleset string
		input   string
		want    string
		wantErr bool
	}{
		// Test cases from the original code's testSet
		{
			ruleset: `# This rules file is extracted from Wikipedia:
# http://en.wikipedia.org/wiki/Markov_Algorithm
A -> apple
B -> bag
S -> shop
T -> the
the shop -> my brother
a never used -> .terminating rule
`,
			input: "I bought a B of As from T S.",
			want:  "I bought a bag of apples from my brother.",
		},
		{
			ruleset: `# Slightly modified from the rules on Wikipedia
A -> apple
B -> bag
S -> .shop
T -> the
the shop -> my brother
a never used -> .terminating rule
`,
			input: "I bought a B of As from T S.",
			want:  "I bought a bag of apples from T shop.",
		},
		{
			ruleset: `# BNF Syntax testing rules
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
`,
			input: "I bought a B of As W my Bgage from T S.",
			want:  "I bought a bag of apples with my money from T shop.",
		},
		{
			ruleset: `### Unary Multiplication Engine, for testing Markov Algorithm implementations
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
`,
			input: "_1111*11111_",
			want:  "11111111111111111111",
		},
		{
			ruleset: `# Turing machine: three-state busy beaver
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
`,
			input: "000000A000000",
			want:  "00011H1111000",
		},

		// Additional Test Cases for edge cases
		{
			ruleset: "",
			input:   "test",
			want:    "test",
		},
		{
			ruleset: "# Only comment\n",
			input:   "test",
			want:    "test",
		},
		{
			ruleset: "a -> .b\n", //terminating rule
			input:   "aa",
			want:    "ba",
		},
		{
			ruleset: "a -> b\na -> c", // Multiple rules
			input:   "a",
			want:    "b",
		},
		{
			ruleset: "abc -> def",
			input:   "test abc test",
			want:    "test def test",
		},

		// Invalid Ruleset Test
		{
			ruleset: "-> ->",  //Invalid Rule
			input:   "test",
			wantErr: true,
		},


	}
	for i, tt := range tests {
		got, ok := interpret(tt.ruleset, tt.input)
		if (err := (ok == false); err != tt.wantErr) {
			t.Errorf("Test %d: interpret() error = %v, wantErr %v", i+1, err, tt.wantErr)
			continue
		}
		if got != tt.want {
			t.Errorf("Test %d: interpret() = %v, want %v", i+1, got, tt.want)
		}
	}
}


// Additional test for parse function
func TestParse(t *testing.T) {
	tests := []struct {
		ruleset string
		want    []rule
		wantErr bool
	}{
		{
			ruleset: "A -> apple\nB -> .bag",
			want: []rule{
				{pat: "A", rep: "apple", term: false},
				{pat: "B", rep: "bag", term: true},
			},
		},
		{
			ruleset: "-> ->", // Invalid rule
			wantErr: true,
		},
	}
	for i, tt := range tests {
		got, ok := parse(tt.ruleset)
		if ok != !tt.wantErr {
			t.Errorf("%d. parse() error = %v, wantErr %v", i+1, !ok, tt.wantErr)
			continue
		}

		if !tt.wantErr && len(got) != len(tt.want) {
			t.Errorf("%d. parse() got = %v, want %v", i+1, got, tt.want)
			continue
		}

		if !tt.wantErr {
			for j := range got {
				if got[j] != tt.want[j] {
					t.Errorf("%d. parse()[%d] got = %v, want %v", i+1, j, got[j], tt.want[j])
				}
			}

		}
	}
}

// Added tests for regex components.
func TestRegex(t *testing.T) {
	testCases := []struct {
		regex   *regexp.Regexp
		input   string
		matches bool
	}{
		{rxSet, "# Comment\nA -> b\n", true},
		{rxSet, "A -> b\nInvalid", false},
		{rxEle, "# Comment\n", true},
		{rxEle, "A -> b\n", true},
		{rxEle, "Invalid", false},
	}

	for _, tc := range testCases {
		if tc.regex.MatchString(tc.input) != tc.matches {
			t.Errorf("Regex %s failed for input %s", tc.regex, tc.input)
		}
	}
}




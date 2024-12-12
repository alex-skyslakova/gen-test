package main

import (
	"testing"
)

func TestCollapse(t *testing.T) {
	tests := []struct {
		input          string
		expectedOutput string
		expectedOLen   int
		expectedCLen   int
	}{
		{"", "", 0, 0},
		{`"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln `, `"If I were two-faced, would I be wearing this one?" - Abraham Lincoln `, 72, 70},
		{"..1111111111111111111111111111111111111111111111111111111111111117777888", ".1", 72, 2},
		{"I never give 'em hell, I just tell the truth, and they think it's hell. ", "I never give 'em hel, I just tel the truth, and they think it's hel. ", 72, 70},
		{"                                                   ---  Harry S Truman  ", " - Hary S Truman ", 72, 17},
		{"The better the 4-wheel drive, the further you'll be from help when ya get stuck!", "The beter the 4-whel drive, the further you'll be from help when ya get stuck!", 80, 78},
		{"headmistressship", "headmistreship", 16, 14},
		{"aardvark", "aardvark", 8, 8},
		{"😍😀🙌💃😍😍😍🙌", "😍😀🙌💃😍🙌", 9, 6},
	}

	for _, test := range tests {
		output, oLen, cLen := collapse(test.input)
		if output != test.expectedOutput || oLen != test.expectedOLen || cLen != test.expectedCLen {
			t.Errorf("For input %q, expected output %q with lengths (%d, %d), but got output %q with lengths (%d, %d)",
				test.input, test.expectedOutput, test.expectedOLen, test.expectedCLen, output, oLen, cLen)
		}
	}
}

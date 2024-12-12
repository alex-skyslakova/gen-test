package main

import (
	"testing"
)

func TestCollapse(t *testing.T) {
	testCases := []struct {
		input    string
		expected string
		origLen  int
		collLen  int
	}{
		{"", "", 0, 0},
		{`"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln `, `"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln `, 72, 72},
		{"..1111111111111111111111111111111111111111111111111111111111111117777888", "..1178", 72, 6},
		{"I never give 'em hell, I just tell the truth, and they think it's hell. ", "I never give 'em hel, I just tel the truth, and they think it's hel. ", 72, 69},
		{"                                                   ---  Harry S Truman  ", " ---  Harry S Truman  ", 72, 22},
		{"The better the 4-wheel drive, the further you'll be from help when ya get stuck!", "The beter the 4-whel drive, the further you'l be from help when ya get stuck!", 72, 70},
		{"headmistressship", "headmistreship", 15, 14},
		{"aardvark", "aardvark", 8, 8},
		{"😍😀🙌💃😍😍😍🙌", "😍😀🙌💃😍🙌", 10, 7},
	}

	for _, tc := range testCases {
		collapsed, origLen, collLen := collapse(tc.input)
		if collapsed != tc.expected {
			t.Errorf("For input '%s', expected collapsed string '%s', but got '%s'", tc.input, tc.expected, collapsed)
		}
		if origLen != tc.origLen {
			t.Errorf("For input '%s', expected original length %d, but got %d", tc.input, tc.origLen, origLen)
		}
		if collLen != tc.collLen {
			t.Errorf("For input '%s', expected collapsed length %d, but got %d", tc.input, tc.collLen, collLen)
		}
	}
}


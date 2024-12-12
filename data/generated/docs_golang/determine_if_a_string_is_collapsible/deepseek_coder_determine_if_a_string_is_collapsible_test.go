package main

import (
	"testing"
)

func TestCollapse(t *testing.T) {
	tests := []struct {
		input    string
		expected string
		origLen  int
		newLen   int
	}{
		{"", "", 0, 0},
		{`"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln `, `"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln `, 72, 72},
		{"..1111111111111111111111111111111111111111111111111111111111111117777888", "..111111111111111111111111111111111111111111111111111111111111111777788", 72, 71},
		{"I never give 'em hell, I just tell the truth, and they think it's hell. ", "I never give 'em hell, I just tell the truth, and they think it's hell. ", 72, 72},
		{"                                                   ---  Harry S Truman  ", "                                                   ---  Harry S Truman  ", 72, 72},
		{"The better the 4-wheel drive, the further you'll be from help when ya get stuck!", "The beter the 4-whel drive, the further you'l be from help when ya get stuck!", 72, 72},
		{"headmistressship", "headmistreship", 16, 15},
		{"aardvark", "aardvark", 8, 8},
		{"😍😀🙌💃😍😍😍🙌", "😍😀🙌💃😍🙌", 8, 6},
	}

	for _, test := range tests {
		result, origLen, newLen := collapse(test.input)
		if result != test.expected || origLen != test.origLen || newLen != test.newLen {
			t.Errorf("For input '%s', expected '%s' with lengths (%d, %d), but got '%s' with lengths (%d, %d)",
				test.input, test.expected, test.origLen, test.newLen, result, origLen, newLen)
		}
	}
}

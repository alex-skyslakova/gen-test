package main

import (
	"testing"
)

func TestSqueeze(t *testing.T) {
	tests := []struct {
		input    string
		char     rune
		expected string
	}{
		{"", ' ', ""},
		{`"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln `, '-', `"If I were two-faced, would I be wearing this one?" -- Abraham Lincoln `},
		{"..1111111111111111111111111111111111111111111111111111111111111117777888", '7', "..1111111111111111111111111111111111111111111111111111111111111117888"},
		{"I never give 'em hell, I just tell the truth, and they think it's hell. ", '.', "I never give 'em hell, I just tell the truth, and they think it's hell "},
		{"                                                   ---  Harry S Truman  ", ' ', " --- Harry S Truman "},
		{"                                                   ---  Harry S Truman  ", '-', "                                                   - Harry S Truman  "},
		{"                                                   ---  Harry S Truman  ", 'r', "                                                   ---  Hary S Truman  "},
		{"The better the 4-wheel drive, the further you'll be from help when ya get stuck!", 'e', "The beter the 4-whel drive, the further you'll be from help when ya get stuck!"},
		{"headmistressship", 's', "headmistreship"},
		{"aardvark", 'a', "ardvark"},
		{"😍😀🙌💃😍😍😍🙌", '😍', "😍😀🙌💃😍🙌"},
	}

	for _, test := range tests {
		result, _, _ := squeeze(test.input, test.char)
		if result != test.expected {
			t.Errorf("For input '%s' with char '%c', expected '%s' but got '%s'", test.input, test.char, test.expected, result)
		}
	}
}

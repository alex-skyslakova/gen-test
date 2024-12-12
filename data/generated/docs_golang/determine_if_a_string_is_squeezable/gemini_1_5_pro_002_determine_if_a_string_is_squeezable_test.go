import (
	"testing"
)

func TestSqueeze(t *testing.T) {
	testCases := []struct {
		inputString string
		char       rune
		expected   string
		origLen    int
		squeezedLen int
	}{
		{"", ' ', "", 0, 0},
		{`"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln `, '-', `"If I were two-faced, would I be wearing this one?" -- Abraham Lincoln `, 72, 71},
		{"..1111111111111111111111111111111111111111111111111111111111111117777888", '7', "..11111111111111111111111111111111111111111111111111111111111111177888", 72, 70},
		{"I never give 'em hell, I just tell the truth, and they think it's hell. ", '.', "I never give 'em hell, I just tell the truth, and they think it's hell ", 72, 71},
		{"                                                   ---  Harry S Truman  ", ' ', "---  Harry S Truman  ", 72, 21},
		{"                                                   ---  Harry S Truman  ", '-', "-- Harry S Truman  ", 72, 20},
		{"                                                   ---  Harry S Truman  ", 'r', "                                                   --  Harry S Truman  ", 72, 71},
		{"The better the 4-wheel drive, the further you'll be from help when ya get stuck!", 'e', "The beter the 4-whel drive, the further you'll be from help when ya get stuck!", 72, 70},
		{"headmistressship", 's', "headmistreship", 16, 14},
		{"aardvark", 'a', "ardvark", 8, 7},
		{"😍😀🙌💃😍😍😍🙌", '😍', "😍😀🙌💃😍🙌", 10, 7},


	}

	for _, tc := range testCases {
		s, olen, slen := squeeze(tc.inputString, tc.char)
		if s != tc.expected || olen != tc.origLen || slen != tc.squeezedLen {
			t.Errorf("For input %q and char %q, expected %q, %d, %d; got %q, %d, %d", tc.inputString, tc.char, tc.expected, tc.origLen, tc.squeezedLen, s, olen, slen)
		}
	}
}


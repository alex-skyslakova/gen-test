import (
	"math"
	"testing"
)

func TestFourIsMagic(t *testing.T) {
	testCases := []struct {
		input    int64
		expected string
	}{
		{0, "Zero is four, four is magic."},
		{1, "One is three, three is five, five is four, four is magic."},
		{2, "Two is three, three is five, five is four, four is magic."},
		{3, "Three is five, five is four, four is magic."},
		{4, "Four is magic."},
		{5, "Five is four, four is magic."},
		{6, "Six is three, three is five, five is four, four is magic."},
		{7, "Seven is five, five is four, four is magic."},
		{8, "Eight is five, five is four, four is magic."},
		{9, "Nine is four, four is magic."},
		{11, "Eleven is six, six is three, three is five, five is four, four is magic."},
		{13, "Thirteen is eight, eight is five, five is four, four is magic."},
		{75, "Seventy-five is eleven, eleven is six, six is three, three is five, five is four, four is magic."},
		{100, "One hundred is ten, ten is three, three is five, five is four, four is magic."},
		{337, "Three hundred thirty-seven is twenty-one, twenty-one is nine, nine is four, four is magic."},
		{-164, "Negative one hundred sixty-four is twenty-four, twenty-four is ten, ten is three, three is five, five is four, four is magic."},
		{math.MaxInt64, "Nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred eight is one hundred, one hundred is ten, ten is three, three is five, five is four, four is magic."},


	}

	for _, tc := range testCases {
		actual := fourIsMagic(tc.input)
		if actual != tc.expected {
			t.Errorf("Input: %d, Expected: %s, Actual: %s", tc.input, tc.expected, actual)
		}
	}
}



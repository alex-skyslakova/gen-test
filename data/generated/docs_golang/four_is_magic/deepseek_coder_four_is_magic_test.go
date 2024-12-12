package main

import (
	"testing"
)

func TestFourIsMagic(t *testing.T) {
	tests := []struct {
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
		{10, "Ten is three, three is five, five is four, four is magic."},
		{11, "Eleven is six, six is three, three is five, five is four, four is magic."},
		{12, "Twelve is six, six is three, three is five, five is four, four is magic."},
		{13, "Thirteen is eight, eight is five, five is four, four is magic."},
		{14, "Fourteen is eight, eight is five, five is four, four is magic."},
		{15, "Fifteen is seven, seven is five, five is four, four is magic."},
		{16, "Sixteen is seven, seven is five, five is four, four is magic."},
		{17, "Seventeen is nine, nine is four, four is magic."},
		{18, "Eighteen is eight, eight is five, five is four, four is magic."},
		{19, "Nineteen is eight, eight is five, five is four, four is magic."},
		{20, "Twenty is six, six is three, three is five, five is four, four is magic."},
		{21, "Twenty-one is ten, ten is three, three is five, five is four, four is magic."},
		{22, "Twenty-two is nine, nine is four, four is magic."},
		{23, "Twenty-three is eleven, eleven is six, six is three, three is five, five is four, four is magic."},
		{24, "Twenty-four is ten, ten is three, three is five, five is four, four is magic."},
		{25, "Twenty-five is ten, ten is three, three is five, five is four, four is magic."},
		{100, "One hundred is eleven, eleven is six, six is three, three is five, five is four, four is magic."},
		{337, "Three hundred thirty-seven is twenty-six, twenty-six is ten, ten is three, three is five, five is four, four is magic."},
		{-164, "Negative one hundred sixty-four is twenty-seven, twenty-seven is ten, ten is three, three is five, five is four, four is magic."},
		{9223372036854775807, "Nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred seven is one hundred sixty-six, one hundred sixty-six is twenty-two, twenty-two is ten, ten is three, three is five, five is four, four is magic."},
	}

	for _, test := range tests {
		result := fourIsMagic(test.input)
		if result != test.expected {
			t.Errorf("For input %d, expected '%s', but got '%s'", test.input, test.expected, result)
		}
	}
}

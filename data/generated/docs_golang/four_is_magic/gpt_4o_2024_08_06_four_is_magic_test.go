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
		{11, "Eleven is six, six is three, three is five, five is four, four is magic."},
		{13, "Thirteen is eight, eight is five, five is four, four is magic."},
		{75, "Seventy-five is ten, ten is three, three is five, five is four, four is magic."},
		{100, "One hundred is ten, ten is three, three is five, five is four, four is magic."},
		{337, "Three hundred thirty-seven is twenty-three, twenty-three is eleven, eleven is six, six is three, three is five, five is four, four is magic."},
		{-164, "Negative one hundred sixty-four is twenty-four, twenty-four is eleven, eleven is six, six is three, three is five, five is four, four is magic."},
	}

	for _, test := range tests {
		t.Run("", func(t *testing.T) {
			result := fourIsMagic(test.input)
			if result != test.expected {
				t.Errorf("Expected %q but got %q", test.expected, result)
			}
		})
	}
}

package main

import (
	"reflect"
	"testing"
)

func TestCompress(t *testing.T) {
	tests := []struct {
		input    string
		expected []int
	}{
		{"", []int{}},
		{"a", []int{97}},
		{"ab", []int{97, 98}},
		{"aa", []int{97, 256}},
		{"TOBEORNOTTOBEORTOBEORNOT", []int{84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263, 267, 269}},
	}

	for _, test := range tests {
		result := compress(test.input)
		if !reflect.DeepEqual(result, test.expected) {
			t.Errorf("compress(%q) = %v; expected %v", test.input, result, test.expected)
		}
	}
}

func TestDecompress(t *testing.T) {
	tests := []struct {
		input    []int
		expected string
		err      error
	}{
		{[]int{}, "", nil},
		{[]int{97}, "a", nil},
		{[]int{97, 98}, "ab", nil},
		{[]int{97, 256}, "aa", nil},
		{[]int{84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263, 267, 269}, "TOBEORNOTTOBEORTOBEORNOT", nil},
		{[]int{256}, "", BadSymbolError(256)},
	}

	for _, test := range tests {
		result, err := decompress(test.input)
		if result != test.expected || err != test.err {
			t.Errorf("decompress(%v) = %q, %v; expected %q, %v", test.input, result, err, test.expected, test.err)
		}
	}
}

func TestCompressDecompress(t *testing.T) {
	tests := []struct {
		input string
	}{
		{""},
		{"a"},
		{"ab"},
		{"aa"},
		{"TOBEORNOTTOBEORTOBEORNOT"},
	}

	for _, test := range tests {
		compressed := compress(test.input)
		decompressed, err := decompress(compressed)
		if err != nil {
			t.Errorf("decompress(%v) returned error: %v", compressed, err)
		}
		if decompressed != test.input {
			t.Errorf("decompress(compress(%q)) = %q; expected %q", test.input, decompressed, test.input)
		}
	}
}

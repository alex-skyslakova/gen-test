package main

import (
	"testing"
)

func TestCanMakeWord(t *testing.T) {
	sp := newSpeller(
		"BO XK DQ CP NA GT RE TG QD FS JW HU VI AN OB ER FS LY PC ZM")

	tests := []struct {
		word     string
		expected bool
	}{
		{"A", true},
		{"BARK", true},
		{"BOOK", false},
		{"TREAT", true},
		{"COMMON", false},
		{"SQUAD", true},
		{"CONFUSE", true},
	}

	for _, test := range tests {
		result := sp(test.word)
		if result != test.expected {
			t.Errorf("For word %s, expected %v, but got %v", test.word, test.expected, result)
		}
	}
}

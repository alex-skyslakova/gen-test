package main

import (
	"strings"
	"testing"
)

func TestValidate(t *testing.T) {
	table := strings.TrimSpace(table)
	commands := strings.Fields(table)
	clen := len(commands)
	minLens := make([]int, clen)
	for i := 0; i < clen; i++ {
		count := 0
		for _, c := range commands[i] {
			if c >= 'A' && c <= 'Z' {
				count++
			}
		}
		minLens[i] = count
	}

	tests := []struct {
		input    string
		expected string
	}{
		{"riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin", "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT"},
		{"aLt ALTE ALTERS TER A", "ALTER ALTER *error* *error* *error*"},
		{"o ov oVe over overL overla Overlay OVERLAYs", "OVERLAY OVERLAY OVERLAY OVERLAY OVERLAY OVERLAY OVERLAY *error*"},
		{"", ""},
		{"  ", ""},
		{"GET get gEt", "GET GET GET"},
		{"ShifT", "SHIFT"},
		{"shi", "SHIFT"},
		{"sh", "SHIFT"},
		{"s", "*error*"}, // Too short for SHIFT (min len 2)
		{"shifts", "*error*"}, // Too long for SHIFT
		{"sos SOS sos", "SOS SOS SOS"}, // All caps command
		{" x x x", "*error* *error* *error*"},  // Testing for proper spacing and multiple errors
	}

	for _, test := range tests {
		words := strings.Fields(test.input)
		results := validate(commands, words, minLens)
		output := strings.Join(results, " ")
		if output != test.expected {
			t.Errorf("Input: %s, Expected: %s, Got: %s", test.input, test.expected, output)
		}
	}
}


package main

import (
	"math/big"
	"strings"
	"testing"
)

func TestElem(t *testing.T) {
	testCases := []struct {
		rule        uint
		cells       int
		generations int
		initial     *big.Int
		expected    []string
	}{
		{
			rule:        90,
			cells:       5,
			generations: 3,
			initial:     big.NewInt(16), // 0b10000 (Single 1 in the middle)
			expected: []string{
				"    #",
				"   ##",
				"  # #",
				" # ##",
			},
		},
		{
			rule:        30,
			cells:       5,
			generations: 2,
			initial:     big.NewInt(7), // 0b00111
			expected: []string{
				"  ###",
				" #  #",
				"# # #",
			},
		},
		{
			rule:        110,
			cells:       3,
			generations: 2,
			initial:     big.NewInt(1), // 0b001
			expected: []string{
				"  #",
				" ##",
				"# #",
			},
		},
		{
			rule:        255, // All ones
			cells:       3,
			generations: 2,
			initial:     big.NewInt(0), // 0b000
			expected: []string{
				"   ",
				"###",
				"###",
			},

		},
		{
			rule:        0, // All zeros
			cells:       3,
			generations: 2,
			initial:     big.NewInt(7), // 0b111
			expected: []string{
				"###",
				"   ",
				"   ",
			},
		},

	}

	for i, tc := range testCases {
		output := []string{}
		captureOutput := func(s string) {
			output = append(output, s)

		}

		elemTest(tc.rule, tc.cells, tc.generations, tc.initial, captureOutput)

		if len(output) != len(tc.expected) {
			t.Errorf("Test case %d: Expected %d lines of output, got %d", i, len(tc.expected), len(output))
			continue
		}

		for j := 0; j < len(tc.expected); j++ {
			if output[j] != tc.expected[j] {
				t.Errorf("Test case %d, generation %d: Expected '%s', got '%s'", i, j, tc.expected[j], output[j])

			}
		}

	}
}


func elemTest(rule uint, cells, generations int, a *big.Int, output func(string)) {
	output(strings.Replace(strings.Replace(
		fmt.Sprintf("%0*b", cells, a), "0", " ", -1), "1", "#", -1))

	a1 := new(big.Int)
	set := func(cell int, k uint) {
		a1.SetBit(a1, cell, rule>>k&1)
	}
	last := cells - 1
	for r := 0; r < generations; r++ {
		k := a.Bit(last) | a.Bit(0)<<1 | a.Bit(1)<<2
		set(0, k)
		for c := 1; c < last; c++ {
			k = k>>1 | a.Bit(c+1)<<2
			set(c, k)
		}
		set(last, k>>1|a.Bit(0)<<2)
		a, a1 = a1, a

		output(strings.Replace(strings.Replace(
			fmt.Sprintf("%0*b", cells, a), "0", " ", -1), "1", "#", -1))

	}
}


package main

import (
	"testing"
	"unicode"
)

func TestShowRange16(t *testing.T) {
	// Test case for lowercase letters
	lowerRanges := unicode.Lower.R16
	showRange16("Test Lower case 16-bit code points:", lowerRanges)

	// Test case for uppercase letters
	upperRanges := unicode.Upper.R16
	showRange16("Test Upper case 16-bit code points:", upperRanges)
}

func TestShowRange32(t *testing.T) {
	// Test case for lowercase letters
	lowerRanges := unicode.Lower.R32
	showRange32("Test Lower case 32-bit code points:", lowerRanges)

	// Test case for uppercase letters
	upperRanges := unicode.Upper.R32
	showRange32("Test Upper case 32-bit code points:", upperRanges)
}

func TestMainOutput(t *testing.T) {
	// Capture the output of the main function
	main()

	// Since we can't capture stdout in a straightforward way in Go,
	// we can only ensure that the main function runs without errors.
	// This test is more of a sanity check.
}

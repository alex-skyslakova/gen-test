package main

import (
	"bytes"
	"fmt"
	"testing"
)

func captureOutput(f func()) string {
	var buf bytes.Buffer
	fmt.SetOutput(&buf)
	defer func() {
		fmt.SetOutput(nil) // Reset to default output
	}()
	f()
	return buf.String()
}


func TestCistercianNumerals(t *testing.T) {
	initDraw()
	testCases := []struct {
		input    int
		expected string
	}{
		{0, generateExpectedOutput(0)},
		{1, generateExpectedOutput(1)},
		{20, generateExpectedOutput(20)},
		{300, generateExpectedOutput(300)},
		{4000, generateExpectedOutput(4000)},
		{5555, generateExpectedOutput(5555)},
		{6789, generateExpectedOutput(6789)},
		{9999, generateExpectedOutput(9999)},  // Added test case for 9999
		{1234, generateExpectedOutput(1234)}, // another added test case for 1234
	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("Input_%d", tc.input), func(t *testing.T) {
			initN()
			thousands := tc.input / 1000
			tc.input %= 1000
			hundreds := tc.input / 100
			tc.input %= 100
			tens := tc.input / 10
			ones := tc.input % 10
			if thousands > 0 {
				draw[thousands*1000]()
			}
			if hundreds > 0 {
				draw[hundreds*100]()
			}
			if tens > 0 {
				draw[tens*10]()
			}
			if ones > 0 {
				draw[ones]()
			}

			actual := captureOutput(printNumeral)

			if actual != tc.expected {
				t.Errorf("For input %d:\nExpected:\n%s\nGot:\n%s", tc.input, tc.expected, actual)
			}
		})

	}
}



func generateExpectedOutput(num int) string {
	initN()
	thousands := num / 1000
	num %= 1000
	hundreds := num / 100
	num %= 100
	tens := num / 10
	ones := num % 10

	if thousands > 0 {
		draw[thousands*1000]()
	}
	if hundreds > 0 {
		draw[hundreds*100]()
	}
	if tens > 0 {
		draw[tens*10]()
	}
	if ones > 0 {
		draw[ones]()
	}
	return captureOutput(printNumeral)

}


package main

import (
	"bytes"
	"testing"
)

func captureOutput(f func()) string {
	var buf bytes.Buffer
	fmt.SetOutput(&buf)
	defer fmt.SetOutput(nil)
	f()
	return buf.String()
}

func TestCistercianNumerals(t *testing.T) {
	tests := []struct {
		number   int
		expected string
	}{
		{0, "x\n"},
		{1, "x x x x x x x x x x x x x x x\n"},
		{20, "x x x x x x x x x x x x x x x\n"},
		{300, "x x x x x x x x x x x x x x x\n"},
		{4000, "x x x x x x x x x x x x x x x\n"},
		{5555, "x x x x x x x x x x x x x x x\n"},
		{6789, "x x x x x x x x x x x x x x x\n"},
		{9999, "x x x x x x x x x x x x x x x\n"},
	}

	for _, tt := range tests {
		t.Run(fmt.Sprintf("Number %d", tt.number), func(t *testing.T) {
			initDraw()
			initN()
			thousands := tt.number / 1000
			tt.number %= 1000
			hundreds := tt.number / 100
			tt.number %= 100
			tens := tt.number / 10
			ones := tt.number % 10
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

			output := captureOutput(printNumeral)
			if output != tt.expected {
				t.Errorf("expected %q, got %q", tt.expected, output)
			}
		})
	}
}

package main

import (
	"testing"
)

// TestEnc tests the enc function for encoding binary to Gray code.
func TestEnc(t *testing.T) {
	tests := []struct {
		binary int
		gray   int
	}{
		{0, 0},
		{1, 1},
		{2, 3},
		{3, 2},
		{4, 6},
		{5, 7},
		{6, 5},
		{7, 4},
		{8, 12},
		{9, 13},
		{10, 15},
		{11, 14},
		{12, 10},
		{13, 11},
		{14, 9},
		{15, 8},
		{16, 24},
		{17, 25},
		{18, 27},
		{19, 26},
		{20, 30},
		{21, 31},
		{22, 29},
		{23, 28},
		{24, 20},
		{25, 21},
		{26, 23},
		{27, 22},
		{28, 18},
		{29, 19},
		{30, 17},
		{31, 16},
	}

	for _, tt := range tests {
		t.Run("", func(t *testing.T) {
			if got := enc(tt.binary); got != tt.gray {
				t.Errorf("enc(%d) = %d; want %d", tt.binary, got, tt.gray)
			}
		})
	}
}

// TestDec tests the dec function for decoding Gray code to binary.
func TestDec(t *testing.T) {
	tests := []struct {
		gray   int
		binary int
	}{
		{0, 0},
		{1, 1},
		{3, 2},
		{2, 3},
		{6, 4},
		{7, 5},
		{5, 6},
		{4, 7},
		{12, 8},
		{13, 9},
		{15, 10},
		{14, 11},
		{10, 12},
		{11, 13},
		{9, 14},
		{8, 15},
		{24, 16},
		{25, 17},
		{27, 18},
		{26, 19},
		{30, 20},
		{31, 21},
		{29, 22},
		{28, 23},
		{20, 24},
		{21, 25},
		{23, 26},
		{22, 27},
		{18, 28},
		{19, 29},
		{17, 30},
		{16, 31},
	}

	for _, tt := range tests {
		t.Run("", func(t *testing.T) {
			if got := dec(tt.gray); got != tt.binary {
				t.Errorf("dec(%d) = %d; want %d", tt.gray, got, tt.binary)
			}
		})
	}
}

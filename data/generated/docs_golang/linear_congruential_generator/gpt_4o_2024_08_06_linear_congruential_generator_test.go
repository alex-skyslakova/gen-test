package main

import (
	"testing"
)

// Test the BSD linear congruential generator
func TestBSDLCG(t *testing.T) {
	tests := []struct {
		seed     uint32
		expected []uint32
	}{
		{0, []uint32{12345, 1406932606, 654583775, 1449466924, 229283573}},
		{1, []uint32{1103527590, 377401575, 662824084, 1147902781, 1012239690}},
	}

	for _, test := range tests {
		bsd := lcg(1103515245, 12345, 1<<31, test.seed)
		for i, exp := range test.expected {
			if result := bsd(); result != exp {
				t.Errorf("BSD LCG with seed %d, iteration %d: expected %d, got %d", test.seed, i, exp, result)
			}
		}
	}
}

// Test the Microsoft linear congruential generator
func TestMSGLCG(t *testing.T) {
	tests := []struct {
		seed     uint32
		expected []uint32
	}{
		{0, []uint32{38, 497, 11606, 12803, 19850}},
		{1, []uint32{4, 21016, 29211, 24171, 7336}},
	}

	for _, test := range tests {
		msf := msg(test.seed)
		for i, exp := range test.expected {
			if result := msf(); result != exp {
				t.Errorf("Microsoft LCG with seed %d, iteration %d: expected %d, got %d", test.seed, i, exp, result)
			}
		}
	}
}

package main

import (
	"testing"
)

func TestHalve(t *testing.T) {
	tests := []struct {
		input    int
		expected int
	}{
		{1, 0},
		{2, 1},
		{3, 1},
		{4, 2},
		{5, 2},
		{6, 3},
		{7, 3},
		{8, 4},
		{9, 4},
		{10, 5},
	}

	for _, test := range tests {
		result := halve(test.input)
		if result != test.expected {
			t.Errorf("halve(%d) = %d; expected %d", test.input, result, test.expected)
		}
	}
}

func TestDouble(t *testing.T) {
	tests := []struct {
		input    int
		expected int
	}{
		{1, 2},
		{2, 4},
		{3, 6},
		{4, 8},
		{5, 10},
		{6, 12},
		{7, 14},
		{8, 16},
		{9, 18},
		{10, 20},
	}

	for _, test := range tests {
		result := double(test.input)
		if result != test.expected {
			t.Errorf("double(%d) = %d; expected %d", test.input, result, test.expected)
		}
	}
}

func TestIsEven(t *testing.T) {
	tests := []struct {
		input    int
		expected bool
	}{
		{1, false},
		{2, true},
		{3, false},
		{4, true},
		{5, false},
		{6, true},
		{7, false},
		{8, true},
		{9, false},
		{10, true},
	}

	for _, test := range tests {
		result := isEven(test.input)
		if result != test.expected {
			t.Errorf("isEven(%d) = %v; expected %v", test.input, result, test.expected)
		}
	}
}

func TestEthMulti(t *testing.T) {
	tests := []struct {
		i        int
		j        int
		expected int
	}{
		{17, 34, 578},
		{1, 1, 1},
		{2, 2, 4},
		{3, 3, 9},
		{4, 4, 16},
		{5, 5, 25},
		{6, 6, 36},
		{7, 7, 49},
		{8, 8, 64},
		{9, 9, 81},
		{10, 10, 100},
	}

	for _, test := range tests {
		result := ethMulti(test.i, test.j)
		if result != test.expected {
			t.Errorf("ethMulti(%d, %d) = %d; expected %d", test.i, test.j, result, test.expected)
		}
	}
}

package main

import (
	"fmt"
	"testing"
)

func TestProcess(t *testing.T) {
	tests := []struct {
		j        int
		initial  int
		expected int
	}{
		{j: 5, initial: 1, expected: 5},
		{j: -3, initial: 1, expected: -3},
		{j: 0, initial: 1, expected: 1}, // j is 0, so prod should remain unchanged
		{j: 1<<27 + 1, initial: 1, expected: 1}, // abs(prod) >= 2^27, so prod should remain unchanged
	}

	for _, tt := range tests {
		prod := tt.initial
		sum := 0
		j := tt.j

		process := func() {
			sum += abs(j)
			if abs(prod) < (1<<27) && j != 0 {
				prod *= j
			}
		}

		process()

		if prod != tt.expected {
			t.Errorf("Expected prod to be %d, but got %d for j = %d", tt.expected, prod, j)
		}
	}
}

func TestMainLoop(t *testing.T) {
	prod := 1
	sum := 0
	const (
		x     = 5
		y     = -5
		z     = -2
		one   = 1
		three = 3
		seven = 7
	)
	p := pow(11, x)
	var j int

	process := func() {
		sum += abs(j)
		if abs(prod) < (1<<27) && j != 0 {
			prod *= j
		}
	}

	expectedSum := 348173
	expectedProd := -793618560

	for j = -three; j <= pow(3, 3); j += three {
		process()
	}
	for j = -seven; j <= seven; j += x {
		process()
	}
	for j = 555; j <= 550-y; j++ {
		process()
	}
	for j = 22; j >= -28; j -= three {
		process()
	}
	for j = 1927; j <= 1939; j++ {
		process()
	}
	for j = x; j >= y; j -= -z {
		process()
	}
	for j = p; j <= p+one; j++ {
		process()
	}

	if sum != expectedSum {
		t.Errorf("Expected sum to be %d, but got %d", expectedSum, sum)
	}
	if prod != expectedProd {
		t.Errorf("Expected prod to be %d, but got %d", expectedProd, prod)
	}
}

func TestCommatize(t *testing.T) {
	tests := []struct {
		n        int
		expected string
	}{
		{n: 1234567, expected: " 1,234,567"},
		{n: -1234567, expected: "-1,234,567"},
		{n: 0, expected: " 0"},
		{n: 123, expected: " 123"},
		{n: -123, expected: "-123"},
	}

	for _, tt := range tests {
		result := commatize(tt.n)
		if result != tt.expected {
			t.Errorf("Expected commatize(%d) to be %s, but got %s", tt.n, tt.expected, result)
		}
	}
}

func TestPow(t *testing.T) {
	tests := []struct {
		n        int
		e        uint
		expected int
	}{
		{n: 2, e: 3, expected: 8},
		{n: 10, e: 0, expected: 1},
		{n: 5, e: 2, expected: 25},
		{n: 3, e: 4, expected: 81},
	}

	for _, tt := range tests {
		result := pow(tt.n, tt.e)
		if result != tt.expected {
			t.Errorf("Expected pow(%d, %d) to be %d, but got %d", tt.n, tt.e, tt.expected, result)
		}
	}
}

func TestAbs(t *testing.T) {
	tests := []struct {
		n        int
		expected int
	}{
		{n: 5, expected: 5},
		{n: -5, expected: 5},
		{n: 0, expected: 0},
	}

	for _, tt := range tests {
		result := abs(tt.n)
		if result != tt.expected {
			t.Errorf("Expected abs(%d) to be %d, but got %d", tt.n, tt.expected, result)
		}
	}
}

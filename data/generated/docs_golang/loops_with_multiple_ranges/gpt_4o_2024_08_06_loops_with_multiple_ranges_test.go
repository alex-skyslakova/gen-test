package main

import (
	"testing"
)

// TestPow tests the pow function
func TestPow(t *testing.T) {
	tests := []struct {
		base     int
		exponent uint
		expected int
	}{
		{2, 3, 8},
		{3, 3, 27},
		{5, 0, 1},
		{11, 5, 161051},
	}

	for _, test := range tests {
		result := pow(test.base, test.exponent)
		if result != test.expected {
			t.Errorf("pow(%d, %d) = %d; want %d", test.base, test.exponent, result, test.expected)
		}
	}
}

// TestAbs tests the abs function
func TestAbs(t *testing.T) {
	tests := []struct {
		input    int
		expected int
	}{
		{5, 5},
		{-5, 5},
		{0, 0},
	}

	for _, test := range tests {
		result := abs(test.input)
		if result != test.expected {
			t.Errorf("abs(%d) = %d; want %d", test.input, result, test.expected)
		}
	}
}

// TestCommatize tests the commatize function
func TestCommatize(t *testing.T) {
	tests := []struct {
		input    int
		expected string
	}{
		{1000, " 1,000"},
		{-1000, "-1,000"},
		{123456789, " 123,456,789"},
		{0, " 0"},
	}

	for _, test := range tests {
		result := commatize(test.input)
		if result != test.expected {
			t.Errorf("commatize(%d) = %s; want %s", test.input, result, test.expected)
		}
	}
}

// TestMainFunction tests the main function logic
func TestMainFunction(t *testing.T) {
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

	expectedSum := 124, 255
	expectedProd := 1, 307, 674, 368, 000

	if sum != expectedSum {
		t.Errorf("sum = %d; want %d", sum, expectedSum)
	}

	if prod != expectedProd {
		t.Errorf("prod = %d; want %d", prod, expectedProd)
	}
}

package main

import (
	"testing"
)

// Helper function to create a copy of a 2D slice
func copyMatrix(m [][]int) [][]int {
	copy := make([][]int, len(m))
	for i := range m {
		copy[i] = make([]int, len(m[i]))
		copy(copy[i], m[i])
	}
	return copy
}

// Test the flipRow function
func TestFlipRow(t *testing.T) {
	matrix := [][]int{
		{0, 1, 0},
		{1, 0, 1},
		{0, 0, 1},
	}

	expected := [][]int{
		{1, 0, 1},
		{1, 0, 1},
		{0, 0, 1},
	}

	result := flipRow(copyMatrix(matrix), 1)

	for i := range expected {
		for j := range expected[i] {
			if result[i][j] != expected[i][j] {
				t.Errorf("flipRow failed at (%d, %d): expected %d, got %d", i, j, expected[i][j], result[i][j])
			}
		}
	}
}

// Test the flipCol function
func TestFlipCol(t *testing.T) {
	matrix := [][]int{
		{0, 1, 0},
		{1, 0, 1},
		{0, 0, 1},
	}

	expected := [][]int{
		{0, 0, 0},
		{1, 1, 1},
		{0, 1, 1},
	}

	result := flipCol(copyMatrix(matrix), 2)

	for i := range expected {
		for j := range expected[i] {
			if result[i][j] != expected[i][j] {
				t.Errorf("flipCol failed at (%d, %d): expected %d, got %d", i, j, expected[i][j], result[i][j])
			}
		}
	}
}

// Test the compareSlices function
func TestCompareSlices(t *testing.T) {
	matrix1 := [][]int{
		{0, 1, 0},
		{1, 0, 1},
		{0, 0, 1},
	}

	matrix2 := [][]int{
		{0, 1, 0},
		{1, 0, 1},
		{0, 0, 1},
	}

	matrix3 := [][]int{
		{1, 1, 0},
		{1, 0, 1},
		{0, 0, 1},
	}

	if !compareSlices(matrix1, matrix2) {
		t.Errorf("compareSlices failed: expected true, got false")
	}

	if compareSlices(matrix1, matrix3) {
		t.Errorf("compareSlices failed: expected false, got true")
	}
}

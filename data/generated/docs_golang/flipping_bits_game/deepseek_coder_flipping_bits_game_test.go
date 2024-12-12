package main

import (
	"testing"
)

func TestFlipRow(t *testing.T) {
	tests := []struct {
		name     string
		input    [][]int
		row      int
		expected [][]int
	}{
		{
			name: "Flip first row",
			input: [][]int{
				{1, 0, 1},
				{0, 1, 0},
				{1, 1, 1},
			},
			row: 1,
			expected: [][]int{
				{0, 1, 0},
				{0, 1, 0},
				{1, 1, 1},
			},
		},
		{
			name: "Flip second row",
			input: [][]int{
				{1, 0, 1},
				{0, 1, 0},
				{1, 1, 1},
			},
			row: 2,
			expected: [][]int{
				{1, 0, 1},
				{1, 0, 1},
				{1, 1, 1},
			},
		},
		{
			name: "Flip third row",
			input: [][]int{
				{1, 0, 1},
				{0, 1, 0},
				{1, 1, 1},
			},
			row: 3,
			expected: [][]int{
				{1, 0, 1},
				{0, 1, 0},
				{0, 0, 0},
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := flipRow(tt.input, tt.row)
			if !compareSlices(result, tt.expected) {
				t.Errorf("Expected %v, but got %v", tt.expected, result)
			}
		})
	}
}

func TestFlipCol(t *testing.T) {
	tests := []struct {
		name     string
		input    [][]int
		col      int
		expected [][]int
	}{
		{
			name: "Flip first column",
			input: [][]int{
				{1, 0, 1},
				{0, 1, 0},
				{1, 1, 1},
			},
			col: 1,
			expected: [][]int{
				{0, 0, 1},
				{1, 1, 0},
				{0, 1, 1},
			},
		},
		{
			name: "Flip second column",
			input: [][]int{
				{1, 0, 1},
				{0, 1, 0},
				{1, 1, 1},
			},
			col: 2,
			expected: [][]int{
				{1, 1, 1},
				{0, 0, 0},
				{1, 0, 1},
			},
		},
		{
			name: "Flip third column",
			input: [][]int{
				{1, 0, 1},
				{0, 1, 0},
				{1, 1, 1},
			},
			col: 3,
			expected: [][]int{
				{1, 0, 0},
				{0, 1, 1},
				{1, 1, 0},
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := flipCol(tt.input, tt.col)
			if !compareSlices(result, tt.expected) {
				t.Errorf("Expected %v, but got %v", tt.expected, result)
			}
		})
	}
}

func TestCompareSlices(t *testing.T) {
	tests := []struct {
		name     string
		input1   [][]int
		input2   [][]int
		expected bool
	}{
		{
			name: "Equal slices",
			input1: [][]int{
				{1, 0, 1},
				{0, 1, 0},
				{1, 1, 1},
			},
			input2: [][]int{
				{1, 0, 1},
				{0, 1, 0},
				{1, 1, 1},
			},
			expected: true,
		},
		{
			name: "Different slices",
			input1: [][]int{
				{1, 0, 1},
				{0, 1, 0},
				{1, 1, 1},
			},
			input2: [][]int{
				{1, 0, 1},
				{0, 1, 0},
				{1, 1, 0},
			},
			expected: false,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := compareSlices(tt.input1, tt.input2)
			if result != tt.expected {
				t.Errorf("Expected %v, but got %v", tt.expected, result)
			}
		})
	}
}

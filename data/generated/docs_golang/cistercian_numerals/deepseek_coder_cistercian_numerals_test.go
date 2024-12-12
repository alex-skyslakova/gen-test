package main

import (
	"testing"
)

func TestCistercianNumerals(t *testing.T) {
	tests := []struct {
		name     string
		number   int
		expected [][]string
	}{
		{
			name:   "Test 0",
			number: 0,
			expected: [][]string{
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
			},
		},
		{
			name:   "Test 1",
			number: 1,
			expected: [][]string{
				{"x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x"},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
			},
		},
		{
			name:   "Test 20",
			number: 20,
			expected: [][]string{
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{"x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x"},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
			},
		},
		{
			name:   "Test 300",
			number: 300,
			expected: [][]string{
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
			},
		},
		{
			name:   "Test 4000",
			number: 4000,
			expected: [][]string{
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
			},
		},
		{
			name:   "Test 5555",
			number: 5555,
			expected: [][]string{
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
			},
		},
		{
			name:   "Test 6789",
			number: 6789,
			expected: [][]string{
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
			},
		},
		{
			name:   "Test 9999",
			number: 9999,
			expected: [][]string{
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", "x", " ", " ", " ", " ", " "},
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			initDraw()
			initN()

			thousands := tt.number / 1000
			number := tt.number % 1000
			hundreds := number / 100
			number %= 100
			tens := number / 10
			ones := number % 10

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

			for i := 0; i < 15; i++ {
				for j := 0; j < 11; j++ {
					if n[i][j] != tt.expected[i][j] {
						t.Errorf("Expected n[%d][%d] = %s, but got %s", i, j, tt.expected[i][j], n[i][j])
					}
				}
			}
		})
	}
}
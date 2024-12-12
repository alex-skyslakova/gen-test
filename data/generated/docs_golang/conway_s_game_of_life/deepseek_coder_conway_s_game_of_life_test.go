package main

import (
	"testing"
)

func TestField_Next(t *testing.T) {
	tests := []struct {
		name     string
		initial  [][]bool
		x, y     int
		expected bool
	}{
		{
			name: "Lonely - Live cell with 0 live neighbors dies",
			initial: [][]bool{
				{false, false, false},
				{false, true, false},
				{false, false, false},
			},
			x:        1,
			y:        1,
			expected: false,
		},
		{
			name: "Lonely - Live cell with 1 live neighbor dies",
			initial: [][]bool{
				{false, true, false},
				{false, true, false},
				{false, false, false},
			},
			x:        1,
			y:        1,
			expected: false,
		},
		{
			name: "Overcrowded - Live cell with 4 live neighbors dies",
			initial: [][]bool{
				{true, true, true},
				{true, true, false},
				{false, false, false},
			},
			x:        1,
			y:        1,
			expected: false,
		},
		{
			name: "Lives - Live cell with 2 live neighbors lives",
			initial: [][]bool{
				{false, true, false},
				{false, true, true},
				{false, false, false},
			},
			x:        1,
			y:        1,
			expected: true,
		},
		{
			name: "Lives - Live cell with 3 live neighbors lives",
			initial: [][]bool{
				{false, true, false},
				{true, true, true},
				{false, false, false},
			},
			x:        1,
			y:        1,
			expected: true,
		},
		{
			name: "Birth - Dead cell with 3 live neighbors becomes live",
			initial: [][]bool{
				{false, true, false},
				{true, false, true},
				{false, false, false},
			},
			x:        1,
			y:        1,
			expected: true,
		},
		{
			name: "Barren - Dead cell with 2 live neighbors remains dead",
			initial: [][]bool{
				{false, true, false},
				{true, false, false},
				{false, false, false},
			},
			x:        1,
			y:        1,
			expected: false,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			field := NewField(3, 3)
			for y := 0; y < 3; y++ {
				for x := 0; x < 3; x++ {
					field.Set(x, y, tt.initial[y][x])
				}
			}
			actual := field.Next(tt.x, tt.y)
			if actual != tt.expected {
				t.Errorf("expected %v, got %v", tt.expected, actual)
			}
		})
	}
}

func TestLife_Step(t *testing.T) {
	tests := []struct {
		name     string
		initial  [][]bool
		expected [][]bool
	}{
		{
			name: "Blinker - Generation 1 to 2",
			initial: [][]bool{
				{false, false, false, false, false},
				{false, false, true, false, false},
				{false, false, true, false, false},
				{false, false, true, false, false},
				{false, false, false, false, false},
			},
			expected: [][]bool{
				{false, false, false, false, false},
				{false, false, false, false, false},
				{false, true, true, true, false},
				{false, false, false, false, false},
				{false, false, false, false, false},
			},
		},
		{
			name: "Blinker - Generation 2 to 3",
			initial: [][]bool{
				{false, false, false, false, false},
				{false, false, false, false, false},
				{false, true, true, true, false},
				{false, false, false, false, false},
				{false, false, false, false, false},
			},
			expected: [][]bool{
				{false, false, false, false, false},
				{false, false, true, false, false},
				{false, false, true, false, false},
				{false, false, true, false, false},
				{false, false, false, false, false},
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			life := &Life{
				w: 5,
				h: 5,
				a: NewField(5, 5),
				b: NewField(5, 5),
			}
			for y := 0; y < 5; y++ {
				for x := 0; x < 5; x++ {
					life.a.Set(x, y, tt.initial[y][x])
				}
			}
			life.Step()
			for y := 0; y < 5; y++ {
				for x := 0; x < 5; x++ {
					if life.a.State(x, y) != tt.expected[y][x] {
						t.Errorf("expected %v at (%d, %d), got %v", tt.expected[y][x], x, y, life.a.State(x, y))
					}
				}
			}
		})
	}
}

package main

import (
	"testing"
)

func TestField_Next(t *testing.T) {
	tests := []struct {
		name     string
		field    Field
		x, y     int
		expected bool
	}{
		{
			name: "Lonely cell dies",
			field: func() Field {
				f := NewField(3, 3)
				f.Set(1, 1, true)
				return f
			}(),
			x:        1,
			y:        1,
			expected: false,
		},
		{
			name: "Overcrowded cell dies",
			field: func() Field {
				f := NewField(3, 3)
				f.Set(0, 0, true)
				f.Set(0, 1, true)
				f.Set(1, 0, true)
				f.Set(1, 1, true)
				f.Set(2, 2, true)
				return f
			}(),
			x:        1,
			y:        1,
			expected: false,
		},
		{
			name: "Cell lives with two neighbours",
			field: func() Field {
				f := NewField(3, 3)
				f.Set(0, 1, true)
				f.Set(1, 1, true)
				f.Set(1, 0, true)
				return f
			}(),
			x:        1,
			y:        1,
			expected: true,
		},
				{
			name: "Cell lives with three neighbours",
			field: func() Field {
				f := NewField(3, 3)
				f.Set(0, 1, true)
				f.Set(1, 1, true)
				f.Set(1, 0, true)
				f.Set(2,1,true)
				return f
			}(),
			x:        1,
			y:        1,
			expected: true,
		},
		{
			name: "Dead cell becomes alive with three neighbours",
			field: func() Field {
				f := NewField(3, 3)
				f.Set(0, 1, true)
				f.Set(1, 0, true)
				f.Set(2, 1, true)
				return f
			}(),
			x:        1,
			y:        1,
			expected: true,
		},
		{
			name: "Dead cell stays dead with less then three neighbours",
			field: func() Field {
				f := NewField(3, 3)
				f.Set(0, 1, true)
				f.Set(1, 0, true)
				return f
			}(),
			x:        1,
			y:        1,
			expected: false,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			actual := tt.field.Next(tt.x, tt.y)

			if actual != tt.expected {
				t.Errorf("Expected %t, got %t", tt.expected, actual)
			}
		})
	}
}


func TestLife_Step_Blinker(t *testing.T) {
	l := NewLife(3, 3)
	l.a.Set(0, 1, true)
	l.a.Set(1, 1, true)
	l.a.Set(2, 1, true)

	l.Step()

	if !l.a.State(1, 0) || !l.a.State(1, 1) || !l.a.State(1, 2) ||
		l.a.State(0, 1) || l.a.State(2, 1) {
		t.Error("Blinker didn't flip vertically")
	}

	l.Step()

		if !l.a.State(0, 1) || !l.a.State(1, 1) || !l.a.State(2, 1) ||
		l.a.State(1, 0) || l.a.State(1, 2) {
		t.Error("Blinker didn't flip horizontally")
	}


}


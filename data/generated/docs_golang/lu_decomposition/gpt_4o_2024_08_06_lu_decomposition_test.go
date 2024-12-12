package main

import (
	"reflect"
	"testing"
)

func TestLUDecomposition(t *testing.T) {
	tests := []struct {
		name     string
		input    matrix
		expected struct {
			L matrix
			U matrix
			P matrix
		}
	}{
		{
			name: "Example 1",
			input: matrix{
				{1, 3, 5},
				{2, 4, 7},
				{1, 1, 0},
			},
			expected: struct {
				L matrix
				U matrix
				P matrix
			}{
				L: matrix{
					{1.00000, 0.00000, 0.00000},
					{0.50000, 1.00000, 0.00000},
					{0.50000, -1.00000, 1.00000},
				},
				U: matrix{
					{2.00000, 4.00000, 7.00000},
					{0.00000, 1.00000, 1.50000},
					{0.00000, 0.00000, -2.00000},
				},
				P: matrix{
					{0, 1, 0},
					{1, 0, 0},
					{0, 0, 1},
				},
			},
		},
		{
			name: "Example 2",
			input: matrix{
				{11, 9, 24, 2},
				{1, 5, 2, 6},
				{3, 17, 18, 1},
				{2, 5, 7, 1},
			},
			expected: struct {
				L matrix
				U matrix
				P matrix
			}{
				L: matrix{
					{1.00000, 0.00000, 0.00000, 0.00000},
					{0.27273, 1.00000, 0.00000, 0.00000},
					{0.09091, 0.28750, 1.00000, 0.00000},
					{0.18182, 0.23125, 0.00360, 1.00000},
				},
				U: matrix{
					{11.00000, 9.00000, 24.00000, 2.00000},
					{0.00000, 14.54545, 11.45455, 0.45455},
					{0.00000, 0.00000, -3.47500, 5.68750},
					{0.00000, 0.00000, 0.00000, 0.51079},
				},
				P: matrix{
					{1, 0, 0, 0},
					{0, 0, 1, 0},
					{0, 1, 0, 0},
					{0, 0, 0, 1},
				},
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			L, U, P := tt.input.lu()
			if !reflect.DeepEqual(L, tt.expected.L) {
				t.Errorf("L matrix = %v, expected %v", L, tt.expected.L)
			}
			if !reflect.DeepEqual(U, tt.expected.U) {
				t.Errorf("U matrix = %v, expected %v", U, tt.expected.U)
			}
			if !reflect.DeepEqual(P, tt.expected.P) {
				t.Errorf("P matrix = %v, expected %v", P, tt.expected.P)
			}
		})
	}
}

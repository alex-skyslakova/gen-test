package main

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestLUDecomposition(t *testing.T) {
	testCases := []struct {
		name string
		a    matrix
		l    matrix
		u    matrix
		p    matrix
	}{
		{
			name: "Example 1",
			a: matrix{
				{1, 3, 5},
				{2, 4, 7},
				{1, 1, 0},
			},
			l: matrix{
				{1.00000, 0.00000, 0.00000},
				{0.50000, 1.00000, 0.00000},
				{0.50000, -1.00000, 1.00000},
			},
			u: matrix{
				{2.00000, 4.00000, 7.00000},
				{0.00000, 1.00000, 1.50000},
				{0.00000, 0.00000, -2.00000},
			},
			p: matrix{
				{0, 1, 0},
				{1, 0, 0},
				{0, 0, 1},
			},
		},
		{
			name: "Example 2",
			a: matrix{
				{11, 9, 24, 2},
				{1, 5, 2, 6},
				{3, 17, 18, 1},
				{2, 5, 7, 1},
			},
			l: matrix{
				{1.00000, 0.00000, 0.00000, 0.00000},
				{0.27273, 1.00000, 0.00000, 0.00000},
				{0.09091, 0.28750, 1.00000, 0.00000},
				{0.18182, 0.23125, 0.00360, 1.00000},
			},
			u: matrix{
				{11.00000, 9.00000, 24.00000, 2.00000},
				{0.00000, 14.54545, 11.45455, 0.45455},
				{0.00000, 0.00000, -3.47500, 5.68750},
				{0.00000, 0.00000, 0.00000, 0.51079},
			},
			p: matrix{
				{1, 0, 0, 0},
				{0, 0, 1, 0},
				{0, 1, 0, 0},
				{0, 0, 0, 1},
			},
		},

		{
			name: "Singular Matrix",
			a: matrix{
				{1, 2},
				{2, 4},
			},
			l: matrix{}, //  Expect a panic due to division by zero.  The test checks for that.
			u: matrix{},
			p: matrix{},
		},
		{
			name: "1x1 Matrix",
			a:    matrix{{5}},
			l:    matrix{{1}},
			u:    matrix{{5}},
			p:    matrix{{1}},
		},
		{
			name: "Zero Matrix 2x2",
			a:    matrix{{0, 0}, {0, 0}},
			l:    matrix{{1, 0}, {0, 1}},
			u:    matrix{{0, 0}, {0, 0}},
			p:    matrix{{1, 0}, {0, 1}},
		},

	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			if tc.name == "Singular Matrix" {
				assert.Panics(t, func() { tc.a.lu() })
				return
			}

			l, u, p := tc.a.lu()

			assert.InDeltaSlice(t, tc.l, l, 0.00001)
			assert.InDeltaSlice(t, tc.u, u, 0.00001)
			assert.Equal(t, tc.p, p)
		})
	}
}


func TestPivotize(t *testing.T) {
	a := matrix{
		{1, 3, 5},
		{2, 4, 7},
		{1, 1, 0},
	}

	expectedP := matrix{
		{0, 1, 0},
		{1, 0, 0},
		{0, 0, 1},
	}
	p := a.pivotize()
	assert.Equal(t, expectedP, p)
}


func TestMul(t *testing.T) {
	m1 := matrix{
		{1, 2},
		{3, 4},
	}
	m2 := matrix{
		{5, 6},
		{7, 8},
	}
	expected := matrix{
		{19, 22},
		{43, 50},
	}
	result := m1.mul(m2)
	assert.Equal(t, expected, result)

}



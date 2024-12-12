package main

import (
	"reflect"
	"testing"
)

func TestKronecker(t *testing.T) {
	testCases := []struct {
		m1     uintMatrix
		m2     uintMatrix
		expect uintMatrix
	}{
		{
			m1: uintMatrix{
				{1, 2},
				{3, 4},
			},
			m2: uintMatrix{
				{0, 5},
				{6, 7},
			},
			expect: uintMatrix{
				{0, 5, 0, 10},
				{6, 7, 12, 14},
				{0, 15, 0, 20},
				{18, 21, 24, 28},
			},
		},
		{
			m1: uintMatrix{
				{0, 1, 0},
				{1, 1, 1},
				{0, 1, 0},
			},
			m2: uintMatrix{
				{1, 1, 1, 1},
				{1, 0, 0, 1},
				{1, 1, 1, 1},
			},
			expect: uintMatrix{
				{0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
				{0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
				{0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1},
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
				{0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
				{0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
			},
		},
		{ // Test with empty matrices
			m1:     uintMatrix{},
			m2:     uintMatrix{},
			expect: uintMatrix{},
		},
		{ // Test with one empty matrix
			m1:     uintMatrix{},
			m2:     uintMatrix{{1, 2}, {3, 4}},
			expect: uintMatrix{},
		},
		{ // Test with one empty matrix
			m1:     uintMatrix{{1, 2}, {3, 4}},
			m2:     uintMatrix{},
			expect: uintMatrix{},
		},

		{
			m1: uintMatrix{{1}},
			m2: uintMatrix{{2}},
			expect: uintMatrix{{2}},
		},
	}

	for _, tc := range testCases {
		result := kronecker(tc.m1, tc.m2)
		if !reflect.DeepEqual(result, tc.expect) {
			t.Errorf("For m1=%v and m2=%v, expected %v but got %v", tc.m1, tc.m2, tc.expect, result)
		}
	}
}


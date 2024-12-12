package main

import (
	"reflect"
	"testing"
)

func TestKroneckerProduct(t *testing.T) {
	// Test case 1
	m1 := uintMatrix{
		{1, 2},
		{3, 4},
	}
	m2 := uintMatrix{
		{0, 5},
		{6, 7},
	}
	expected1 := uintMatrix{
		{0, 5, 0, 10},
		{6, 7, 12, 14},
		{0, 15, 0, 20},
		{18, 21, 24, 28},
	}
	result1 := kronecker(m1, m2)
	if !reflect.DeepEqual(result1, expected1) {
		t.Errorf("Test case 1 failed. Expected:\n%v\nGot:\n%v", expected1, result1)
	}

	// Test case 2
	m3 := uintMatrix{
		{0, 1, 0},
		{1, 1, 1},
		{0, 1, 0},
	}
	m4 := uintMatrix{
		{1, 1, 1, 1},
		{1, 0, 0, 1},
		{1, 1, 1, 1},
	}
	expected2 := uintMatrix{
		{0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
		{0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
		{0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
		{0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
		{0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
	}
	result2 := kronecker(m3, m4)
	if !reflect.DeepEqual(result2, expected2) {
		t.Errorf("Test case 2 failed. Expected:\n%v\nGot:\n%v", expected2, result2)
	}
}

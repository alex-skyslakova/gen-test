package main

import (
	"testing"
	"reflect"
)

func TestLUDecomposition(t *testing.T) {
	// Test case 1
	a1 := matrix{
		{1, 3, 5},
		{2, 4, 7},
		{1, 1, 0},
	}
	expectedL1 := matrix{
		{1.00000, 0.00000, 0.00000},
		{0.50000, 1.00000, 0.00000},
		{0.50000, -1.00000, 1.00000},
	}
	expectedU1 := matrix{
		{2.00000, 4.00000, 7.00000},
		{0.00000, 1.00000, 1.50000},
		{0.00000, 0.00000, -2.00000},
	}
	expectedP1 := matrix{
		{0, 1, 0},
		{1, 0, 0},
		{0, 0, 1},
	}

	l1, u1, p1 := a1.lu()
	if !reflect.DeepEqual(l1, expectedL1) {
		t.Errorf("Test case 1: L matrix is incorrect.\nExpected:\n%v\nGot:\n%v", expectedL1, l1)
	}
	if !reflect.DeepEqual(u1, expectedU1) {
		t.Errorf("Test case 1: U matrix is incorrect.\nExpected:\n%v\nGot:\n%v", expectedU1, u1)
	}
	if !reflect.DeepEqual(p1, expectedP1) {
		t.Errorf("Test case 1: P matrix is incorrect.\nExpected:\n%v\nGot:\n%v", expectedP1, p1)
	}

	// Test case 2
	a2 := matrix{
		{11, 9, 24, 2},
		{1, 5, 2, 6},
		{3, 17, 18, 1},
		{2, 5, 7, 1},
	}
	expectedL2 := matrix{
		{1.00000, 0.00000, 0.00000, 0.00000},
		{0.27273, 1.00000, 0.00000, 0.00000},
		{0.09091, 0.28750, 1.00000, 0.00000},
		{0.18182, 0.23125, 0.00360, 1.00000},
	}
	expectedU2 := matrix{
		{11.00000, 9.00000, 24.00000, 2.00000},
		{0.00000, 14.54545, 11.45455, 0.45455},
		{0.00000, 0.00000, -3.47500, 5.68750},
		{0.00000, 0.00000, 0.00000, 0.51079},
	}
	expectedP2 := matrix{
		{1, 0, 0, 0},
		{0, 0, 1, 0},
		{0, 1, 0, 0},
		{0, 0, 0, 1},
	}

	l2, u2, p2 := a2.lu()
	if !reflect.DeepEqual(l2, expectedL2) {
		t.Errorf("Test case 2: L matrix is incorrect.\nExpected:\n%v\nGot:\n%v", expectedL2, l2)
	}
	if !reflect.DeepEqual(u2, expectedU2) {
		t.Errorf("Test case 2: U matrix is incorrect.\nExpected:\n%v\nGot:\n%v", expectedU2, u2)
	}
	if !reflect.DeepEqual(p2, expectedP2) {
		t.Errorf("Test case 2: P matrix is incorrect.\nExpected:\n%v\nGot:\n%v", expectedP2, p2)
	}
}

package main

import (
	"testing"
	"reflect"
)

func TestDoors(t *testing.T) {
	expected := [100]bool{}
	for i := 0; i < 100; i++ {
		if isPerfectSquare(i + 1) {
			expected[i] = true
		}
	}

	doors := [100]bool{}

	for pass := 1; pass <= 100; pass++ {
		for door := pass - 1; door < 100; door += pass {
			doors[door] = !doors[door]
		}
	}

	if !reflect.DeepEqual(doors, expected) {
		t.Errorf("Expected doors state to be %v, but got %v", expected, doors)
	}
}

func isPerfectSquare(n int) bool {
	root := int(float64(n) + 0.5)
	return root*root == n
}

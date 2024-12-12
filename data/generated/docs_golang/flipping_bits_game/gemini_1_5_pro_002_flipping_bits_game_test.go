package main

import (
	"testing"
)

func TestFlipRow(t *testing.T) {
	m := [][]int{{0, 0, 0}, {1, 1, 1}, {0, 1, 0}}
	expected := [][]int{{0, 0, 0}, {0, 0, 0}, {0, 1, 0}}
	result := flipRow(m, 2)
	if !compareSlices(result, expected) {
		t.Errorf("flipRow failed. Expected: %v, Got: %v", expected, result)
	}
}

func TestFlipCol(t *testing.T) {
	m := [][]int{{0, 0, 0}, {1, 1, 1}, {0, 1, 0}}
	expected := [][]int{{0, 0, 1}, {1, 1, 0}, {0, 1, 1}}

	result := flipCol(m, 3)
	if !compareSlices(result, expected) {
		t.Errorf("flipCol failed. Expected: %v, Got: %v", expected, result)
	}
}


func TestCompareSlices(t *testing.T) {
	m1 := [][]int{{0, 0, 0}, {1, 1, 1}, {0, 1, 0}}
	m2 := [][]int{{0, 0, 0}, {1, 1, 1}, {0, 1, 0}}
	m3 := [][]int{{0, 0, 1}, {1, 1, 1}, {0, 1, 0}}

	if !compareSlices(m1, m2) {
		t.Error("compareSlices failed. Expected true, got false")
	}

	if compareSlices(m1, m3) {
		t.Error("compareSlices failed. Expected false, got true")
	}
}

// Although main() could be theoretically tested by providing mock input to fmt.Scanf
// and capturing output from fmt.Printf, it's highly complex and not practical
// due to the interactive nature of the game loop.  Focusing on testable functions is
// a better approach for unit testing.  Integration tests could be used to verify the
// overall game flow if needed. Therefore for main() it returns:

//None



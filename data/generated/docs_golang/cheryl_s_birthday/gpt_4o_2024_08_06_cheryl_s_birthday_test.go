package main

import (
	"testing"
)

func TestMonthUniqueIn(t *testing.T) {
	choices := []birthday{
		{5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
		{7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
	}

	tests := []struct {
		bd       birthday
		expected bool
	}{
		{birthday{5, 15}, false},
		{birthday{6, 17}, false},
		{birthday{7, 14}, false},
		{birthday{8, 14}, false},
	}

	for _, test := range tests {
		if result := test.bd.monthUniqueIn(choices); result != test.expected {
			t.Errorf("monthUniqueIn(%v) = %v; want %v", test.bd, result, test.expected)
		}
	}
}

func TestDayUniqueIn(t *testing.T) {
	choices := []birthday{
		{5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
		{7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
	}

	tests := []struct {
		bd       birthday
		expected bool
	}{
		{birthday{5, 15}, true},
		{birthday{5, 16}, true},
		{birthday{5, 19}, true},
		{birthday{6, 17}, true},
		{birthday{6, 18}, true},
		{birthday{7, 14}, false},
		{birthday{7, 16}, true},
		{birthday{8, 14}, false},
		{birthday{8, 15}, true},
		{birthday{8, 17}, true},
	}

	for _, test := range tests {
		if result := test.bd.dayUniqueIn(choices); result != test.expected {
			t.Errorf("dayUniqueIn(%v) = %v; want %v", test.bd, result, test.expected)
		}
	}
}

func TestMonthWithUniqueDayIn(t *testing.T) {
	choices := []birthday{
		{5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
		{7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
	}

	tests := []struct {
		bd       birthday
		expected bool
	}{
		{birthday{5, 15}, true},
		{birthday{6, 17}, true},
		{birthday{7, 14}, false},
		{birthday{8, 14}, false},
	}

	for _, test := range tests {
		if result := test.bd.monthWithUniqueDayIn(choices); result != test.expected {
			t.Errorf("monthWithUniqueDayIn(%v) = %v; want %v", test.bd, result, test.expected)
		}
	}
}

func TestMainLogic(t *testing.T) {
	choices := []birthday{
		{5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
		{7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
	}

	// Step 1: Filter out months with unique days
	var filtered []birthday
	for _, bd := range choices {
		if !bd.monthUniqueIn(choices) {
			filtered = append(filtered, bd)
		}
	}

	expectedFiltered := []birthday{
		{5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
	}

	if len(filtered) != len(expectedFiltered) {
		t.Fatalf("Expected filtered length %d, got %d", len(expectedFiltered), len(filtered))
	}

	// Step 2: Filter out months with unique days
	var filtered2 []birthday
	for _, bd := range filtered {
		if !bd.monthWithUniqueDayIn(filtered) {
			filtered2 = append(filtered2, bd)
		}
	}

	expectedFiltered2 := []birthday{
		{7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
	}

	if len(filtered2) != len(expectedFiltered2) {
		t.Fatalf("Expected filtered2 length %d, got %d", len(expectedFiltered2), len(filtered2))
	}

	// Step 3: Bernard knows the answer, so the day must be unique
	var filtered3 []birthday
	for _, bd := range filtered2 {
		if bd.dayUniqueIn(filtered2) {
			filtered3 = append(filtered3, bd)
		}
	}

	expectedFiltered3 := []birthday{
		{7, 16}, {8, 15}, {8, 17},
	}

	if len(filtered3) != len(expectedFiltered3) {
		t.Fatalf("Expected filtered3 length %d, got %d", len(expectedFiltered3), len(filtered3))
	}

	// Step 4: Albert knows the answer, so the month must be unique
	var filtered4 []birthday
	for _, bd := range filtered3 {
		if bd.monthUniqueIn(filtered3) {
			filtered4 = append(filtered4, bd)
		}
	}

	expectedFiltered4 := []birthday{
		{7, 16},
	}

	if len(filtered4) != len(expectedFiltered4) {
		t.Fatalf("Expected filtered4 length %d, got %d", len(expectedFiltered4), len(filtered4))
	}

	if filtered4[0] != (birthday{7, 16}) {
		t.Errorf("Expected Cheryl's birthday to be July 16, got %v", filtered4[0])
	}
}

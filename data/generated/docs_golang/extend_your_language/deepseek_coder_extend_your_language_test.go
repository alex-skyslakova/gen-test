package main

import (
	"testing"
)

func TestIf2(t *testing.T) {
	var tests = []struct {
		cond1, cond2 bool
		expected     string
	}{
		{true, true, "a = 1 and b = 3"},
		{true, false, "a = 1 and b <> 3"},
		{false, true, "a <> 1 and b = 3"},
		{false, false, "a <> 1 and b <> 3"},
	}

	for _, tt := range tests {
		t.Run(tt.expected, func(t *testing.T) {
			var result string
			if2(tt.cond1, tt.cond2, func() {
				result = "a = 1 and b = 3"
			}).else1(func() {
				result = "a = 1 and b <> 3"
			}).else2(func() {
				result = "a <> 1 and b = 3"
			}).else0(func() {
				result = "a <> 1 and b <> 3"
			})

			if result != tt.expected {
				t.Errorf("got %s, want %s", result, tt.expected)
			}
		})
	}
}

func TestIf2OmitElse(t *testing.T) {
	var result string
	if2(true, false, func() {
		result = "a = 1 and b = 3"
	}).else0(func() {
		result = "a <> 1 and b <> 3"
	})

	if result != "" {
		t.Errorf("expected no result, got %s", result)
	}
}

func TestIf2OutOfOrder(t *testing.T) {
	var result string
	if2(false, true, func() {
		result = "a = 1 and b = 3"
	}).else0(func() {
		result = "a <> 1 and b <> 3"
	}).else2(func() {
		result = "a <> 1 and b = 3"
	})

	if result != "a <> 1 and b = 3" {
		t.Errorf("got %s, want a <> 1 and b = 3", result)
	}
}

package main

import (
	"reflect"
	"testing"
)

type assoc map[string]interface{}

func merge(base, update assoc) assoc {
	result := make(assoc)
	for k, v := range base {
		result[k] = v
	}
	for k, v := range update {
		result[k] = v
	}
	return result
}

func TestMerge(t *testing.T) {
	tests := []struct {
		base     assoc
		update   assoc
		expected assoc
	}{
		{
			base:     assoc{"name": "Rocket Skates", "price": 12.75, "color": "yellow"},
			update:   assoc{"price": 15.25, "color": "red", "year": 1974},
			expected: assoc{"name": "Rocket Skates", "price": 15.25, "color": "red", "year": 1974},
		},
		{
			base:     assoc{"name": "Rocket Skates", "price": 12.75},
			update:   assoc{"color": "red", "year": 1974},
			expected: assoc{"name": "Rocket Skates", "price": 12.75, "color": "red", "year": 1974},
		},
		{
			base:     assoc{},
			update:   assoc{"price": 15.25, "color": "red", "year": 1974},
			expected: assoc{"price": 15.25, "color": "red", "year": 1974},
		},
		{
			base:     assoc{"name": "Rocket Skates", "price": 12.75, "color": "yellow"},
			update:   assoc{},
			expected: assoc{"name": "Rocket Skates", "price": 12.75, "color": "yellow"},
		},
		{
			base:     assoc{},
			update:   assoc{},
			expected: assoc{},
		},
	}

	for _, test := range tests {
		result := merge(test.base, test.update)
		if !reflect.DeepEqual(result, test.expected) {
			t.Errorf("merge(%v, %v) = %v; expected %v", test.base, test.update, result, test.expected)
		}
	}
}

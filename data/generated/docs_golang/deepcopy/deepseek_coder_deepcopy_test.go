package main

import (
	"reflect"
	"testing"
)

func TestDeepCopy(t *testing.T) {
	// Test case 1: Basic deep copy
	t.Run("BasicDeepCopy", func(t *testing.T) {
		c1 := &cds{1, "one", []byte("unit"), map[int]bool{1: true}}
		c2 := c1.deepcopy()

		// Check that c1 and c2 are different
		if c1 == c2 {
			t.Errorf("Expected c1 and c2 to be different pointers, but they are the same")
		}

		// Check that the contents are the same
		if !reflect.DeepEqual(c1, c2) {
			t.Errorf("Expected c1 and c2 to be deep equal, but they are not")
		}

		// Modify c1 and check that c2 is unaffected
		c1.i = 0
		c1.s = "nil"
		copy(c1.b, "zero")
		c1.m[1] = false

		if c2.i != 1 || c2.s != "one" || string(c2.b) != "unit" || c2.m[1] != true {
			t.Errorf("Expected c2 to remain unchanged, but it was modified")
		}
	})

	// Test case 2: Deep copy with empty fields
	t.Run("EmptyFieldsDeepCopy", func(t *testing.T) {
		c1 := &cds{0, "", []byte{}, map[int]bool{}}
		c2 := c1.deepcopy()

		// Check that c1 and c2 are different
		if c1 == c2 {
			t.Errorf("Expected c1 and c2 to be different pointers, but they are the same")
		}

		// Check that the contents are the same
		if !reflect.DeepEqual(c1, c2) {
			t.Errorf("Expected c1 and c2 to be deep equal, but they are not")
		}

		// Modify c1 and check that c2 is unaffected
		c1.i = 1
		c1.s = "one"
		c1.b = []byte("unit")
		c1.m[1] = true

		if c2.i != 0 || c2.s != "" || len(c2.b) != 0 || len(c2.m) != 0 {
			t.Errorf("Expected c2 to remain unchanged, but it was modified")
		}
	})

	// Test case 3: Deep copy with nil fields
	t.Run("NilFieldsDeepCopy", func(t *testing.T) {
		c1 := &cds{0, "", nil, nil}
		c2 := c1.deepcopy()

		// Check that c1 and c2 are different
		if c1 == c2 {
			t.Errorf("Expected c1 and c2 to be different pointers, but they are the same")
		}

		// Check that the contents are the same
		if !reflect.DeepEqual(c1, c2) {
			t.Errorf("Expected c1 and c2 to be deep equal, but they are not")
		}

		// Modify c1 and check that c2 is unaffected
		c1.i = 1
		c1.s = "one"
		c1.b = []byte("unit")
		c1.m = map[int]bool{1: true}

		if c2.i != 0 || c2.s != "" || c2.b != nil || c2.m != nil {
			t.Errorf("Expected c2 to remain unchanged, but it was modified")
		}
	})
}

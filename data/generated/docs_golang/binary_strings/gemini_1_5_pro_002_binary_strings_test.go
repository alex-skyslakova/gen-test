package main

import (
	"bytes"
	"reflect"
	"testing"
)

func TestBinaryStringOperations(t *testing.T) {
	// String creation and assignment
	b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
	var c []byte
	c = b
	if !bytes.Equal(b, c) {
		t.Errorf("String assignment failed. Expected %v, got %v", b, c)
	}

	// String comparison
	if !bytes.Equal(b, c) {
		t.Error("String comparison failed. Expected equal")
	}
	if bytes.Compare(b, []byte{'a'}) >= 0 && bytes.Compare(b, []byte{'z'}) <= 0  {
		
	} else {
		t.Errorf("bytes.Compare incorrect")
	}

	// String cloning and copying
	d := make([]byte, len(b))
	copy(d, b)
	d[1] = 'a'
	if bytes.Equal(b, d) {
		t.Error("String cloning failed. Expected different slices")
	}

	// Check if a string is empty
	var empty []byte
	if len(empty) != 0 {
		t.Error("Empty string check failed")
	}

	// Append a byte to a string
	z := append(b, 'z')
	expectedAppend := []byte{'b', 'i', 'n', 'a', 'r', 'y', 'z'}

	if !bytes.Equal(z, expectedAppend) {
		t.Errorf("Append failed. Expected %v, got %v", expectedAppend, z)
	}


	// Extract a substring from a string
	sub := b[1:3]
	expectedSub := []byte{'i', 'n'}
	if !bytes.Equal(sub, expectedSub) {
		t.Errorf("Substring extraction failed. Expected %v, got %v", expectedSub, sub)
	}

	// Replace every occurrence of a byte (or a string) in a string with another string
	f := bytes.Replace(d, []byte{'n'}, []byte{'m'}, -1)
	expectedReplace := []byte{'b', 'a', 'm', 'a', 'r', 'y'}
	if !bytes.Equal(f, expectedReplace) {
		t.Errorf("Replace failed. Expected %s, got %s", expectedReplace, f)
	}


	// Join strings
	rem := append(append([]byte{}, b[:1]...), b[3:]...)
	expectedJoin := []byte{'b', 'a', 'r', 'y'}
	if !reflect.DeepEqual(rem, expectedJoin) {
		t.Errorf("Join failed. Expected %v, got %v", expectedJoin, rem)
	}

    // Test Replace All occurrences
    testReplace := []byte{'a', 'b', 'c', 'b', 'a'}
    replacedBytes := bytes.ReplaceAll(testReplace, []byte{'b'}, []byte{'x'})
    expectedReplaced := []byte{'a', 'x', 'c', 'x', 'a'}

    if !bytes.Equal(replacedBytes, expectedReplaced) {
        t.Errorf("Replace All failed. Expected %v, got %v", expectedReplaced, replacedBytes)
    }
}

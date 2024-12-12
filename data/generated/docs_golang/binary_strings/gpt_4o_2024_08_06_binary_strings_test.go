package main

import (
	"bytes"
	"testing"
)

func TestStringCreationAndDestruction(t *testing.T) {
	b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
	expected := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
	if !bytes.Equal(b, expected) {
		t.Errorf("Expected %v, got %v", expected, b)
	}
}

func TestStringAssignment(t *testing.T) {
	b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
	var c []byte
	c = b
	if !bytes.Equal(c, b) {
		t.Errorf("Expected %v, got %v", b, c)
	}
}

func TestStringComparison(t *testing.T) {
	b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
	c := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
	if !bytes.Equal(b, c) {
		t.Errorf("Expected %v to be equal to %v", b, c)
	}
}

func TestStringCloningAndCopying(t *testing.T) {
	b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
	d := make([]byte, len(b))
	copy(d, b)
	d[1] = 'a'
	d[4] = 'n'
	if bytes.Equal(b, d) {
		t.Errorf("Expected %v to be different from %v", b, d)
	}
}

func TestCheckIfStringIsEmpty(t *testing.T) {
	b := []byte{}
	if len(b) != 0 {
		t.Errorf("Expected string to be empty")
	}
}

func TestAppendByteToString(t *testing.T) {
	b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
	z := append(b, 'z')
	expected := []byte{'b', 'i', 'n', 'a', 'r', 'y', 'z'}
	if !bytes.Equal(z, expected) {
		t.Errorf("Expected %v, got %v", expected, z)
	}
}

func TestExtractSubstringFromString(t *testing.T) {
	b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
	sub := b[1:3]
	expected := []byte{'i', 'n'}
	if !bytes.Equal(sub, expected) {
		t.Errorf("Expected %v, got %v", expected, sub)
	}
}

func TestReplaceByteInString(t *testing.T) {
	d := []byte{'b', 'a', 'n', 'a', 'r', 'y'}
	f := bytes.Replace(d, []byte{'n'}, []byte{'m'}, -1)
	expected := []byte{'b', 'a', 'm', 'a', 'r', 'y'}
	if !bytes.Equal(f, expected) {
		t.Errorf("Expected %v, got %v", expected, f)
	}
}

func TestJoinStrings(t *testing.T) {
	b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
	rem := append(append([]byte{}, b[:1]...), b[3:]...)
	expected := []byte{'b', 'a', 'r', 'y'}
	if !bytes.Equal(rem, expected) {
		t.Errorf("Expected %v, got %v", expected, rem)
	}
}

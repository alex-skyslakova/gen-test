package main

import (
    "bytes"
    "testing"
)

func TestStringCreationAndDestruction(t *testing.T) {
    b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
    if len(b) != 6 {
        t.Errorf("Expected length of 6, got %d", len(b))
    }
}

func TestStringAssignment(t *testing.T) {
    b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
    var c []byte
    c = b
    if !bytes.Equal(b, c) {
        t.Errorf("Expected b and c to be equal")
    }
}

func TestStringComparison(t *testing.T) {
    b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
    c := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
    if !bytes.Equal(b, c) {
        t.Errorf("Expected b and c to be equal")
    }
}

func TestStringCloningAndCopying(t *testing.T) {
    b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
    d := make([]byte, len(b))
    copy(d, b)
    if !bytes.Equal(b, d) {
        t.Errorf("Expected b and d to be equal")
    }
    d[1] = 'a'
    d[4] = 'n'
    if bytes.Equal(b, d) {
        t.Errorf("Expected b and d to be different after modification")
    }
}

func TestCheckIfStringIsEmpty(t *testing.T) {
    b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
    if len(b) == 0 {
        t.Errorf("Expected b to be non-empty")
    }
    empty := []byte{}
    if len(empty) != 0 {
        t.Errorf("Expected empty to be empty")
    }
}

func TestAppendByteToString(t *testing.T) {
    b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
    z := append(b, 'z')
    if len(z) != 7 {
        t.Errorf("Expected length of 7, got %d", len(z))
    }
    if z[6] != 'z' {
        t.Errorf("Expected last byte to be 'z', got %c", z[6])
    }
}

func TestExtractSubstring(t *testing.T) {
    b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
    sub := b[1:3]
    if !bytes.Equal(sub, []byte{'i', 'n'}) {
        t.Errorf("Expected substring 'in', got %s", sub)
    }
}

func TestReplaceByteInString(t *testing.T) {
    d := []byte{'b', 'a', 'n', 'a', 'n', 'a'}
    f := bytes.Replace(d, []byte{'n'}, []byte{'m'}, -1)
    if !bytes.Equal(f, []byte{'b', 'a', 'm', 'a', 'm', 'a'}) {
        t.Errorf("Expected 'bamama', got %s", f)
    }
}

func TestJoinStrings(t *testing.T) {
    b := []byte{'b', 'i', 'n', 'a', 'r', 'y'}
    rem := append(append([]byte{}, b[:1]...), b[3:]...)
    if !bytes.Equal(rem, []byte{'b', 'a', 'r', 'y'}) {
        t.Errorf("Expected 'bary', got %s", rem)
    }
}

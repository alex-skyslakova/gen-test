package main

import (
	"testing"
)

func TestEmptyList(t *testing.T) {
	dll := &dlList{}
	if dll.String() != "<nil>" {
		t.Errorf("Expected empty list string representation to be <nil>, got %s", dll.String())
	}
}

func TestInsertTail(t *testing.T) {
	dll := &dlList{}
	a := &dlNode{string: "A"}
	dll.insertTail(a)
	if dll.String() != "[A]" {
		t.Errorf("Expected list to be [A], got %s", dll.String())
	}
	if dll.head != a {
		t.Errorf("Expected head to be a, got %v", dll.head)
	}
	if dll.tail != a {
		t.Errorf("Expected tail to be a, got %v", dll.tail)
	}
	if a.next != nil {
		t.Errorf("Expected a.next to be nil, got %v", a.next)
	}
	if a.prev != nil {
		t.Errorf("Expected a.prev to be nil, got %v", a.prev)
	}

	b := &dlNode{string: "B"}
	dll.insertTail(b)
	if dll.String() != "[A B]" {
		t.Errorf("Expected list to be [A B], got %s", dll.String())
	}
	if dll.head != a {
		t.Errorf("Expected head to be a, got %v", dll.head)
	}
	if dll.tail != b {
		t.Errorf("Expected tail to be b, got %v", dll.tail)
	}
	if a.next != b {
		t.Errorf("Expected a.next to be b, got %v", a.next)
	}
	if a.prev != nil {
		t.Errorf("Expected a.prev to be nil, got %v", a.prev)
	}
	if b.next != nil {
		t.Errorf("Expected b.next to be nil, got %v", b.next)
	}
	if b.prev != a {
		t.Errorf("Expected b.prev to be a, got %v", b.prev)
	}
}

func TestInsertAfter(t *testing.T) {
	dll := &dlList{}
	a := &dlNode{string: "A"}
	dll.insertTail(a)
	b := &dlNode{string: "B"}
	dll.insertTail(b)

	c := &dlNode{string: "C"}
	dll.insertAfter(a, c)

	if dll.String() != "[A C B]" {
		t.Errorf("Expected list to be [A C B], got %s", dll.String())
	}

    if a.next != c {
        t.Errorf("Expected a.next to be c, got %v", a.next)
    }
    if c.prev != a {
        t.Errorf("Expected c.prev to be a, got %v", c.prev)
    }
    if c.next != b {
        t.Errorf("Expected c.next to be b, got %v", c.next)
    }
    if b.prev != c {
        t.Errorf("Expected b.prev to be c, got %v", b.prev)
    }

	dll = &dlList{}
	a = &dlNode{string: "A"}
	dll.insertTail(a)
	c = &dlNode{string: "C"}
	dll.insertAfter(a, c)

	if dll.String() != "[A C]" {
		t.Errorf("Expected list to be [A C], got %s", dll.String())
	}
    if dll.tail != c {
        t.Errorf("Expected tail to be c, got %v", dll.tail)
    }



}

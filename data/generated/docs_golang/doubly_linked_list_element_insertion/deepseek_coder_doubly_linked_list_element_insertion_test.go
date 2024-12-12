package main

import (
    "testing"
)

func TestInsertTail(t *testing.T) {
    dll := &dlList{}

    // Test inserting into an empty list
    a := &dlNode{string: "A"}
    dll.insertTail(a)
    if dll.head != a || dll.tail != a || a.prev != nil || a.next != nil {
        t.Errorf("InsertTail into empty list failed")
    }

    // Test inserting into a non-empty list
    b := &dlNode{string: "B"}
    dll.insertTail(b)
    if dll.head != a || dll.tail != b || a.next != b || b.prev != a || b.next != nil {
        t.Errorf("InsertTail into non-empty list failed")
    }
}

func TestInsertAfter(t *testing.T) {
    dll := &dlList{}

    // Test inserting after an element in a list with one element
    a := &dlNode{string: "A"}
    dll.insertTail(a)
    c := &dlNode{string: "C"}
    dll.insertAfter(a, c)
    if dll.head != a || dll.tail != c || a.next != c || c.prev != a || c.next != nil {
        t.Errorf("InsertAfter in a list with one element failed")
    }

    // Test inserting after an element in a list with multiple elements
    b := &dlNode{string: "B"}
    dll.insertTail(b)
    d := &dlNode{string: "D"}
    dll.insertAfter(c, d)
    if dll.head != a || dll.tail != b || a.next != c || c.prev != a || c.next != d || d.prev != c || d.next != b || b.prev != d || b.next != nil {
        t.Errorf("InsertAfter in a list with multiple elements failed")
    }

    // Test inserting after the tail element
    e := &dlNode{string: "E"}
    dll.insertAfter(b, e)
    if dll.head != a || dll.tail != e || a.next != c || c.prev != a || c.next != d || d.prev != c || d.next != b || b.prev != d || b.next != e || e.prev != b || e.next != nil {
        t.Errorf("InsertAfter after the tail element failed")
    }
}

func TestString(t *testing.T) {
    dll := &dlList{}

    // Test String method on an empty list
    if dll.String() != "<nil>" {
        t.Errorf("String method on empty list failed")
    }

    // Test String method on a list with one element
    a := &dlNode{string: "A"}
    dll.insertTail(a)
    if dll.String() != "[A]" {
        t.Errorf("String method on list with one element failed")
    }

    // Test String method on a list with multiple elements
    b := &dlNode{string: "B"}
    dll.insertTail(b)
    c := &dlNode{string: "C"}
    dll.insertTail(c)
    if dll.String() != "[A B C]" {
        t.Errorf("String method on list with multiple elements failed")
    }
}

package main

import (
	"testing"
)

func TestInsertTail(t *testing.T) {
	dll := &dlList{}
	nodeA := &dlNode{string: "A"}
	nodeB := &dlNode{string: "B"}

	dll.insertTail(nodeA)
	if dll.head != nodeA || dll.tail != nodeA {
		t.Errorf("Failed to insert node A as tail. Head: %v, Tail: %v", dll.head, dll.tail)
	}

	dll.insertTail(nodeB)
	if dll.tail != nodeB || dll.head.next != nodeB || nodeB.prev != nodeA {
		t.Errorf("Failed to insert node B as tail. Head: %v, Tail: %v, NodeB Prev: %v", dll.head, dll.tail, nodeB.prev)
	}
}

func TestInsertAfter(t *testing.T) {
	dll := &dlList{}
	nodeA := &dlNode{string: "A"}
	nodeB := &dlNode{string: "B"}
	nodeC := &dlNode{string: "C"}

	dll.insertTail(nodeA)
	dll.insertTail(nodeB)

	dll.insertAfter(nodeA, nodeC)
	if nodeA.next != nodeC || nodeC.prev != nodeA || nodeC.next != nodeB || nodeB.prev != nodeC {
		t.Errorf("Failed to insert node C after node A. NodeA Next: %v, NodeC Prev: %v, NodeC Next: %v, NodeB Prev: %v", nodeA.next, nodeC.prev, nodeC.next, nodeB.prev)
	}

	if dll.tail != nodeB {
		t.Errorf("Tail should be node B. Got: %v", dll.tail)
	}

	dll.insertAfter(nodeB, &dlNode{string: "D"})
	if dll.tail.string != "D" {
		t.Errorf("Tail should be node D. Got: %v", dll.tail)
	}
}

func TestString(t *testing.T) {
	dll := &dlList{}
	if dll.String() != "<nil>" {
		t.Errorf("Expected empty list string representation to be '<nil>'. Got: %s", dll.String())
	}

	dll.insertTail(&dlNode{string: "A"})
	dll.insertTail(&dlNode{string: "B"})
	dll.insertTail(&dlNode{string: "C"})

	expected := "[A B C]"
	if dll.String() != expected {
		t.Errorf("Expected list string representation to be '%s'. Got: %s", expected, dll.String())
	}
}

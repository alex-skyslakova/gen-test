package main

import (
	"testing"
)

func TestInsertTail(t *testing.T) {
	list := &dlList{}
	nodeA := &dlNode{string: "A"}
	nodeB := &dlNode{string: "B"}

	list.insertTail(nodeA)
	if list.head != nodeA || list.tail != nodeA {
		t.Errorf("InsertTail failed: head or tail not set correctly after first insertion")
	}

	list.insertTail(nodeB)
	if list.tail != nodeB {
		t.Errorf("InsertTail failed: tail not updated correctly after second insertion")
	}
	if list.head.next != nodeB || nodeB.prev != nodeA {
		t.Errorf("InsertTail failed: nodes not linked correctly")
	}
}

func TestInsertAfter(t *testing.T) {
	list := &dlList{}
	nodeA := &dlNode{string: "A"}
	nodeB := &dlNode{string: "B"}
	nodeC := &dlNode{string: "C"}

	list.insertTail(nodeA)
	list.insertTail(nodeB)

	list.insertAfter(nodeA, nodeC)

	if nodeA.next != nodeC || nodeC.prev != nodeA {
		t.Errorf("InsertAfter failed: nodeC not inserted correctly after nodeA")
	}
	if nodeC.next != nodeB || nodeB.prev != nodeC {
		t.Errorf("InsertAfter failed: nodeB not linked correctly after nodeC insertion")
	}
}

func TestString(t *testing.T) {
	list := &dlList{}
	if list.String() != "<nil>" {
		t.Errorf("String failed: expected '<nil>', got %s", list.String())
	}

	list.insertTail(&dlNode{string: "A"})
	list.insertTail(&dlNode{string: "B"})
	list.insertTail(&dlNode{string: "C"})

	expected := "[A B C]"
	if list.String() != expected {
		t.Errorf("String failed: expected %s, got %s", expected, list.String())
	}
}

func TestTraverseFromEndToBeginning(t *testing.T) {
	list := &dlList{}
	nodeA := &dlNode{string: "A"}
	nodeB := &dlNode{string: "B"}
	nodeC := &dlNode{string: "C"}

	list.insertTail(nodeA)
	list.insertTail(nodeB)
	list.insertTail(nodeC)

	expected := []string{"C", "B", "A"}
	index := 0
	for p := list.tail; p != nil; p = p.prev {
		if p.string != expected[index] {
			t.Errorf("TraverseFromEndToBeginning failed: expected %s, got %s", expected[index], p.string)
		}
		index++
	}
}

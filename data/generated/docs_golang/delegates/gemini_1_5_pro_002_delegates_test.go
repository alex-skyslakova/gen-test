package main

import (
	"testing"
)

func TestDelegator_NoDelegate(t *testing.T) {
	a := Delegator{}
	expected := "default implementation"
	actual := a.operation()
	if actual != expected {
		t.Errorf("Expected %s, got %s", expected, actual)
	}
}

func TestDelegator_DelegateWithoutThing(t *testing.T) {
	a := Delegator{delegate: "A delegate may be any object"}
	expected := "default implementation"
	actual := a.operation()
	if actual != expected {
		t.Errorf("Expected %s, got %s", expected, actual)
	}
}

func TestDelegator_DelegateWithThing(t *testing.T) {
	var d Delegate
	a := Delegator{delegate: d}
	expected := "delegate implementation"
	actual := a.operation()
	if actual != expected {
		t.Errorf("Expected %s, got %s", expected, actual)
	}
}

// Test with a different type implementing Thingable
type AnotherDelegate struct{}

func (AnotherDelegate) thing() string {
	return "another delegate implementation"
}

func TestDelegator_AnotherDelegateWithThing(t *testing.T) {
	var d AnotherDelegate
	a := Delegator{delegate: d}
	expected := "another delegate implementation"
	actual := a.operation()
	if actual != expected {
		t.Errorf("Expected %s, got %s", expected, actual)
	}
}


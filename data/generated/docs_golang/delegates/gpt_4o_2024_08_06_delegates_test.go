package main

import (
	"testing"
)

type MockDelegateWithoutThing struct{}

func TestDelegatorWithoutDelegate(t *testing.T) {
	delegator := Delegator{}
	expected := "default implementation"
	result := delegator.operation()
	if result != expected {
		t.Errorf("Expected %s, but got %s", expected, result)
	}
}

func TestDelegatorWithDelegateWithoutThing(t *testing.T) {
	delegator := Delegator{delegate: MockDelegateWithoutThing{}}
	expected := "default implementation"
	result := delegator.operation()
	if result != expected {
		t.Errorf("Expected %s, but got %s", expected, result)
	}
}

func TestDelegatorWithDelegateWithThing(t *testing.T) {
	var delegate Delegate
	delegator := Delegator{delegate: delegate}
	expected := "delegate implementation"
	result := delegator.operation()
	if result != expected {
		t.Errorf("Expected %s, but got %s", expected, result)
	}
}

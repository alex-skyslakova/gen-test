package main

import (
    "testing"
)

// TestDelegatorWithoutDelegate tests the operation method without a delegate.
func TestDelegatorWithoutDelegate(t *testing.T) {
    delegator := Delegator{}
    result := delegator.operation()
    if result != "default implementation" {
        t.Errorf("Expected 'default implementation', got '%s'", result)
    }
}

// TestDelegatorWithNonThingableDelegate tests the operation method with a delegate that does not implement "thing".
func TestDelegatorWithNonThingableDelegate(t *testing.T) {
    delegator := Delegator{delegate: "A delegate may be any object"}
    result := delegator.operation()
    if result != "default implementation" {
        t.Errorf("Expected 'default implementation', got '%s'", result)
    }
}

// TestDelegatorWithThingableDelegate tests the operation method with a delegate that implements "thing".
func TestDelegatorWithThingableDelegate(t *testing.T) {
    var delegate Delegate
    delegator := Delegator{delegate: delegate}
    result := delegator.operation()
    if result != "delegate implementation" {
        t.Errorf("Expected 'delegate implementation', got '%s'", result)
    }
}

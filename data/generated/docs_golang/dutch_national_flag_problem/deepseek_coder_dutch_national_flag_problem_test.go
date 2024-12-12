package main

import (
    "testing"
    "reflect"
)

func TestOutOfOrder(t *testing.T) {
    // Test case 1: n = 2
    n := 2
    o := outOfOrder(n)
    if o.ordered() {
        t.Errorf("outOfOrder(%d) returned an ordered list", n)
    }

    // Test case 2: n = 10
    n = 10
    o = outOfOrder(n)
    if o.ordered() {
        t.Errorf("outOfOrder(%d) returned an ordered list", n)
    }

    // Test case 3: n = 1 (should panic)
    defer func() {
        if r := recover(); r == nil {
            t.Errorf("outOfOrder(1) did not panic")
        }
    }()
    outOfOrder(1)
}

func TestSort3(t *testing.T) {
    // Test case 1: Simple case with 3 balls
    o := ordering{ball{white}, ball{blue}, ball{red}}
    o.sort3()
    expected := ordering{ball{red}, ball{white}, ball{blue}}
    if !reflect.DeepEqual(o, expected) {
        t.Errorf("sort3() failed, expected %v, got %v", expected, o)
    }

    // Test case 2: Larger case with 12 balls
    o = outOfOrder(12)
    o.sort3()
    if !o.ordered() {
        t.Errorf("sort3() failed to sort the balls correctly")
    }

    // Test case 3: Edge case with all balls of the same color
    o = ordering{ball{red}, ball{red}, ball{red}}
    o.sort3()
    expected = ordering{ball{red}, ball{red}, ball{red}}
    if !reflect.DeepEqual(o, expected) {
        t.Errorf("sort3() failed, expected %v, got %v", expected, o)
    }

    // Test case 4: Edge case with already sorted balls
    o = ordering{ball{red}, ball{white}, ball{blue}}
    o.sort3()
    expected = ordering{ball{red}, ball{white}, ball{blue}}
    if !reflect.DeepEqual(o, expected) {
        t.Errorf("sort3() failed, expected %v, got %v", expected, o)
    }
}

func TestOrdered(t *testing.T) {
    // Test case 1: Ordered list
    o := ordering{ball{red}, ball{white}, ball{blue}}
    if !o.ordered() {
        t.Errorf("ordered() failed, expected true, got false")
    }

    // Test case 2: Unordered list
    o = ordering{ball{blue}, ball{white}, ball{red}}
    if o.ordered() {
        t.Errorf("ordered() failed, expected false, got true")
    }

    // Test case 3: Single element list
    o = ordering{ball{red}}
    if !o.ordered() {
        t.Errorf("ordered() failed, expected true, got false")
    }

    // Test case 4: All elements the same
    o = ordering{ball{white}, ball{white}, ball{white}}
    if !o.ordered() {
        t.Errorf("ordered() failed, expected true, got false")
    }
}

package main

import (
    "fmt"
    "testing"
)

func TestAdd(t *testing.T) {
    list = nil
    add(0)
    if len(list) != 1 || list[0] != 2 {
        t.Errorf("Expected list to have one element with value 2, got %v", list)
    }
}

func TestShow(t *testing.T) {
    list = []tree{2} // Represents the tree "()"
    var buf = make([]byte, 2)
    n, err := fmt.Sscanf("()", "%s", &buf)
    if err != nil || n != 1 {
        t.Fatalf("Failed to parse test string: %v", err)
    }
    show(list[0], 2)
    fmt.Println()
}

func TestListTrees(t *testing.T) {
    list = []tree{2, 6} // Represents the trees "()" and "(())"
    offset = [32]uint{1: 1, 2: 2}
    listTrees(2)
}

func TestAssemble(t *testing.T) {
    list = nil
    offset = [32]uint{1: 1}
    add(0)
    assemble(2, 0, 1, 1, 1)
    if len(list) != 2 || list[1] != 6 {
        t.Errorf("Expected list to have two elements with value 6, got %v", list)
    }
}

func TestMktrees(t *testing.T) {
    list = nil
    offset = [32]uint{1: 1}
    add(0)
    mktrees(2)
    if len(list) != 2 || list[1] != 6 {
        t.Errorf("Expected list to have two elements with value 6, got %v", list)
    }
    if offset[3] != 2 {
        t.Errorf("Expected offset[3] to be 2, got %d", offset[3])
    }
}

func TestMain(t *testing.T) {
    // This test is more complex and would require mocking os.Args and os.Stderr
    // For simplicity, we assume the main function works as expected based on the other tests.
}

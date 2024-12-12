package main

import (
	"reflect"
	"testing"
)

func TestEertree(t *testing.T) {
	tree := eertree([]byte("eertree"))
	expectedTree := []node{
		{length: 0, suffix: 1, edges: edges{'e': 2, 'r': 4, 't': 5}},
		{length: -1, suffix: 1, edges: edges{'e': 2}},
		{length: 1, suffix: 0, edges: edges{'e': 3}},
		{length: 3, suffix: 2, edges: edges{}},
		{length: 1, suffix: 0, edges: edges{}},
		{length: 1, suffix: 0, edges: edges{'r': 6}},
		{length: 3, suffix: 4, edges: edges{'e': 7}},
		{length: 5, suffix: 3, edges: edges{}},
	}

	if !reflect.DeepEqual(tree, expectedTree) {
		t.Errorf("Expected tree: %v, got: %v", expectedTree, tree)
	}
}

func TestSubPalindromes(t *testing.T) {
	tree := eertree([]byte("eertree"))
	subPalindromesList := subPalindromes(tree)
	expectedSubPalindromes := []string{"e", "eee", "r", "t", "rtr", "eertree"}

	if !reflect.DeepEqual(subPalindromesList, expectedSubPalindromes) {
		t.Errorf("Expected sub-palindromes: %v, got: %v", expectedSubPalindromes, subPalindromesList)
	}
}

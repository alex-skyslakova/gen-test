package main

import (
	"reflect"
	"testing"
)

func TestEertree(t *testing.T) {
	tests := []struct {
		input string
		want  []node
	}{
		{"", []node{
			{length: 0, suffix: 1, edges: edges{}},
			{length: -1, suffix: 1, edges: edges{}},
		}},
		{"a", []node{
			{length: 0, suffix: 1, edges: edges{'a': 2}},
			{length: -1, suffix: 1, edges: edges{}},
			{length: 1, suffix: 0, edges: edges{}},
		}},
		{"aa", []node{
			{length: 0, suffix: 1, edges: edges{'a': 2}},
			{length: -1, suffix: 1, edges: edges{}},
			{length: 1, suffix: 0, edges: edges{'a': 3}},
			{length: 2, suffix: 2, edges: edges{}},
		}},
		{"aba", []node{
			{length: 0, suffix: 1, edges: edges{'a': 2, 'b': 4}},
			{length: -1, suffix: 1, edges: edges{}},
			{length: 1, suffix: 0, edges: edges{'b': 4}},
			{length: 3, suffix: 2, edges: edges{}},
			{length: 2, suffix: 0, edges: edges{}},

		}},
		{"eertree", []node{
			{length: 0, suffix: 1, edges: edges{'e': 2, 'r': 5, 't': 7}},
			{length: -1, suffix: 1, edges: edges{}},
			{length: 1, suffix: 0, edges: edges{'e': 3, 'r': 5}},
			{length: 2, suffix: 2, edges: edges{'r': 6}},
			{length: 4, suffix: 0, edges: edges{}},
			{length: 2, suffix: 0, edges: edges{'t': 7}},
			{length: 3, suffix: 2, edges: edges{'e': 8}},
			{length: 2, suffix: 0, edges: edges{'r': 9, 'e': 10}},
			{length: 5, suffix: 2, edges: edges{}},
			{length: 3, suffix: 5, edges: edges{}},
			{length: 1, suffix: 0, edges: edges{}},

		}},

	}
	for _, tt := range tests {
		t.Run(tt.input, func(t *testing.T) {
			if got := eertree([]byte(tt.input)); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("eertree() = %v, want %v", got, tt.want)
			}
		})
	}
}



func TestSubPalindromes(t *testing.T) {
	tests := []struct {
		input []node
		want  []string
	}{
		{[]node{
			{length: 0, suffix: 1, edges: edges{}},
			{length: -1, suffix: 1, edges: edges{}},
		}, []string{}},
		{[]node{
			{length: 0, suffix: 1, edges: edges{'a': 2}},
			{length: -1, suffix: 1, edges: edges{}},
			{length: 1, suffix: 0, edges: edges{}},
		}, []string{"a"}},
		{[]node{
			{length: 0, suffix: 1, edges: edges{'a': 2}},
			{length: -1, suffix: 1, edges: edges{}},
			{length: 1, suffix: 0, edges: edges{'a': 3}},
			{length: 2, suffix: 2, edges: edges{}},
		}, []string{"a", "aaa"}},
		{[]node{
			{length: 0, suffix: 1, edges: edges{'a': 2, 'b': 4}},
			{length: -1, suffix: 1, edges: edges{}},
			{length: 1, suffix: 0, edges: edges{'b': 3}},
			{length: 3, suffix: 2, edges: edges{}},
			{length: 2, suffix: 0, edges: edges{}},

		}, []string{"a", "aba", "b"}},
		{[]node{
			{length: 0, suffix: 1, edges: edges{'e': 2, 'r': 5, 't': 7}},
			{length: -1, suffix: 1, edges: edges{}},
			{length: 1, suffix: 0, edges: edges{'e': 3, 'r': 6}},
			{length: 2, suffix: 2, edges: edges{'r': 4}},
			{length: 4, suffix: 0, edges: edges{}},
			{length: 2, suffix: 0, edges: edges{'t': 7}},
			{length: 3, suffix: 2, edges: edges{}},
			{length: 2, suffix: 0, edges: edges{'r': 8, 'e': 9}},
			{length: 3, suffix: 5, edges: edges{}},
			{length: 1, suffix: 0, edges: edges{}},

		}, []string{"e", "ee", "eerree", "r", "rr", "t", "trrt", "e", "e"}},
	}
	for _, tt := range tests {
		t.Run(tt.input[0].edges.(edges).string(), func(t *testing.T) {
			if got := subPalindromes(tt.input); !reflect.DeepEqual(got, tt.want) {

				t.Errorf("subPalindromes() = %v, want %v", got, tt.want)
			}
		})
	}
}

// Helper function for test readability
func (e edges) string() string {
	if len(e) == 0 {
		return ""
	}
	var s string
	for k := range e {
		s += string(k)
	}
	return s
}


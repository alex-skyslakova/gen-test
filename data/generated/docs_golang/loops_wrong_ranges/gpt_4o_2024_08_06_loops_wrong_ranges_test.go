package main

import (
	"reflect"
	"testing"
)

type S struct {
	start, stop, incr int
	comment           string
}

var examples = []S{
	{-2, 2, 1, "Normal"},
	{-2, 2, 0, "Zero increment"},
	{-2, 2, -1, "Increments away from stop value"},
	{-2, 2, 10, "First increment is beyond stop value"},
	{2, -2, 1, "Start more than stop: positive increment"},
	{2, 2, 1, "Start equal stop: positive increment"},
	{2, 2, -1, "Start equal stop: negative increment"},
	{2, 2, 0, "Start equal stop: zero increment"},
	{0, 0, 0, "Start equal stop equal zero: zero increment"},
}

func sequence(s S, limit int) []int {
	var seq []int
	for i, c := s.start, 0; i <= s.stop && c < limit; i, c = i+s.incr, c+1 {
		seq = append(seq, i)
	}
	return seq
}

func TestSequence(t *testing.T) {
	tests := []struct {
		input    S
		limit    int
		expected []int
	}{
		{S{-2, 2, 1, "Normal"}, 10, []int{-2, -1, 0, 1, 2}},
		{S{-2, 2, 0, "Zero increment"}, 10, []int{}},
		{S{-2, 2, -1, "Increments away from stop value"}, 10, []int{}},
		{S{-2, 2, 10, "First increment is beyond stop value"}, 10, []int{-2}},
		{S{2, -2, 1, "Start more than stop: positive increment"}, 10, []int{}},
		{S{2, 2, 1, "Start equal stop: positive increment"}, 10, []int{2}},
		{S{2, 2, -1, "Start equal stop: negative increment"}, 10, []int{2}},
		{S{2, 2, 0, "Start equal stop: zero increment"}, 10, []int{}},
		{S{0, 0, 0, "Start equal stop equal zero: zero increment"}, 10, []int{}},
	}

	for _, test := range tests {
		t.Run(test.input.comment, func(t *testing.T) {
			result := sequence(test.input, test.limit)
			if !reflect.DeepEqual(result, test.expected) {
				t.Errorf("For %s, expected %v, got %v", test.input.comment, test.expected, result)
			}
		})
	}
}

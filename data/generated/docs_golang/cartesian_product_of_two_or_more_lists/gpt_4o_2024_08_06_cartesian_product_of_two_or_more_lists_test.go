package main

import (
	"reflect"
	"testing"
)

type pair [2]int

func cart2(a, b []int) []pair {
	p := make([]pair, len(a)*len(b))
	i := 0
	for _, a := range a {
		for _, b := range b {
			p[i] = pair{a, b}
			i++
		}
	}
	return p
}

func cartN(lists ...[]int) [][]int {
	if len(lists) == 0 {
		return [][]int{}
	}

	result := [][]int{{}}
	for _, list := range lists {
		var newResult [][]int
		for _, x := range result {
			for _, y := range list {
				newResult = append(newResult, append(append([]int{}, x...), y))
			}
		}
		result = newResult
	}
	return result
}

func TestCart2(t *testing.T) {
	tests := []struct {
		a, b     []int
		expected []pair
	}{
		{[]int{1, 2}, []int{3, 4}, []pair{{1, 3}, {1, 4}, {2, 3}, {2, 4}}},
		{[]int{3, 4}, []int{1, 2}, []pair{{3, 1}, {3, 2}, {4, 1}, {4, 2}}},
		{[]int{1, 2}, nil, []pair{}},
		{nil, []int{1, 2}, []pair{}},
	}

	for _, test := range tests {
		result := cart2(test.a, test.b)
		if !reflect.DeepEqual(result, test.expected) {
			t.Errorf("cart2(%v, %v) = %v; expected %v", test.a, test.b, result, test.expected)
		}
	}
}

func TestCartN(t *testing.T) {
	tests := []struct {
		lists    [][]int
		expected [][]int
	}{
		{
			[][]int{{1776, 1789}, {7, 12}, {4, 14, 23}, {0, 1}},
			[][]int{
				{1776, 7, 4, 0}, {1776, 7, 4, 1}, {1776, 7, 14, 0}, {1776, 7, 14, 1}, {1776, 7, 23, 0}, {1776, 7, 23, 1},
				{1776, 12, 4, 0}, {1776, 12, 4, 1}, {1776, 12, 14, 0}, {1776, 12, 14, 1}, {1776, 12, 23, 0}, {1776, 12, 23, 1},
				{1789, 7, 4, 0}, {1789, 7, 4, 1}, {1789, 7, 14, 0}, {1789, 7, 14, 1}, {1789, 7, 23, 0}, {1789, 7, 23, 1},
				{1789, 12, 4, 0}, {1789, 12, 4, 1}, {1789, 12, 14, 0}, {1789, 12, 14, 1}, {1789, 12, 23, 0}, {1789, 12, 23, 1},
			},
		},
		{
			[][]int{{1, 2, 3}, {30}, {500, 100}},
			[][]int{
				{1, 30, 500}, {1, 30, 100},
				{2, 30, 500}, {2, 30, 100},
				{3, 30, 500}, {3, 30, 100},
			},
		},
		{
			[][]int{{1, 2, 3}, {}, {500, 100}},
			[][]int{},
		},
	}

	for _, test := range tests {
		result := cartN(test.lists...)
		if !reflect.DeepEqual(result, test.expected) {
			t.Errorf("cartN(%v) = %v; expected %v", test.lists, result, test.expected)
		}
	}
}

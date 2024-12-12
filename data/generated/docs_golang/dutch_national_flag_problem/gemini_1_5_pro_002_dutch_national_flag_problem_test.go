package main

import (
	"fmt"
	"math/rand"
	"testing"
)

func TestOutOfOrder(t *testing.T) {
	for n := 2; n < 20; n++ {
		r := outOfOrder(n)
		if len(r) != n {
			t.Errorf("expected length %d, got %d", n, len(r))
		}
		if r.ordered() {
			t.Errorf("expected out of order, got ordered")
		}
		for _, b := range r {
			if b.color < 0 || b.color >= nColors {
				t.Errorf("invalid color: %d", b.color)
			}
		}
	}

	defer func() {
		if r := recover(); r == nil {
			t.Errorf("The code did not panic")
		}
	}()
	outOfOrder(1)

}

func TestOrderingOrdered(t *testing.T) {
	testCases := []struct {
		input    ordering
		expected bool
	}{
		{ordering{{red}, {white}, {blue}}, true},
		{ordering{{red}, {red}, {white}, {blue}}, true},
		{ordering{{white}, {blue}}, true},
		{ordering{{red}, {white}, {blue}, {red}}, false},
		{ordering{{blue}, {white}, {red}}, false},
		{ordering{{white}, {red}}, false},
		{ordering{}, true},
		{ordering{{red}}, true},
	}

	for _, tc := range testCases {
		actual := tc.input.ordered()
		if actual != tc.expected {
			t.Errorf("For input %v, expected %v, got %v", tc.input, tc.expected, actual)
		}
	}
}

func TestSort3(t *testing.T) {
	testCases := []ordering{
		{
			{color: white}, {color: red}, {color: blue}, {color: white}, {color: red}, {color: blue},
		},
		{
			{color: blue}, {color: white}, {color: red},
		},
		{
			{color: red}, {color: red}, {color: red},
		},
		{
			{color: white}, {color: white}, {color: white},
		},
		{
			{color: blue}, {color: blue}, {color: blue},
		},
		{},
		{{color: red}},
		{{color: white}},
		{{color: blue}},

		{{color: red}, {color: white}},
		{{color: white}, {color: blue}},
		{{color: blue}, {color: white}},
	}
	for _, tc := range testCases {
		tc.sort3()
		if !tc.ordered() {
			t.Errorf("Expected ordered array, got %v", tc)
		}
	}
}

func generateTestArray(size int) ordering {
	r := make(ordering, size)
	for i := 0; i < size; i++ {
		r[i].color = rand.Intn(nColors)
	}

	return r
}

func TestSort3Random(t *testing.T) {

	for i := 0; i < 1000; i++ {
		size := rand.Intn(100) + 1 // Generate random sizes between 1 and 100
		arr := generateTestArray(size)

		arr.sort3()

		if !arr.ordered() {
			t.Errorf("Expected sorted array for input %v, got %v", arr, arr)
		}
	}
}

// added for testing purposes only
func (o ordering) print() {
	for _, b := range o {
		switch b.color {
		case red:
			fmt.Print("r")
		case white:
			fmt.Print("w")
		case blue:
			fmt.Print("b")
		}
	}
	fmt.Println()
}


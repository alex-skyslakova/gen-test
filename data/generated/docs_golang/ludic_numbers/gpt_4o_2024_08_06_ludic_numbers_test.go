package main

import (
	"testing"
	"reflect"
)

func TestLudicFirst25(t *testing.T) {
	expected := []uint32{1, 2, 3, 5, 7, 11, 13, 17, 23, 25, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89}
	result := Ludic(25, -1)
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("Expected first 25 ludic numbers to be %v, but got %v", expected, result)
	}
}

func TestLudicBelow1000(t *testing.T) {
	expectedCount := 177
	result := len(Ludic(-1, 1000))
	if result != expectedCount {
		t.Errorf("Expected number of ludic numbers below 1000 to be %d, but got %d", expectedCount, result)
	}
}

func TestLudic2000To2005(t *testing.T) {
	expected := []uint32{1213, 1217, 1223, 1225, 1229, 1231}
	result := Ludic(2005, -1)[1999:]
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("Expected ludic numbers from 2000 to 2005 to be %v, but got %v", expected, result)
	}
}

func TestLudicTripletsBelow250(t *testing.T) {
	expectedTriplets := [][3]uint32{
		{1, 3, 7},
		{3, 5, 11},
		{7, 9, 15},
		{13, 15, 21},
		{21, 23, 29},
		{31, 33, 39},
		{43, 45, 51},
		{55, 57, 63},
		{61, 63, 69},
		{73, 75, 81},
		{79, 81, 87},
		{97, 99, 105},
		{103, 105, 111},
		{109, 111, 117},
		{127, 129, 135},
		{151, 153, 159},
		{157, 159, 165},
		{163, 165, 171},
		{181, 183, 189},
		{193, 195, 201},
		{199, 201, 207},
		{211, 213, 219},
		{217, 219, 225},
		{223, 225, 231},
	}

	x := Ludic(-1, 250)
	var triplets [][3]uint32
	for i, v := range x[:len(x)-2] {
		if has(x[i+1:], v+2) && has(x[i+2:], v+6) {
			triplets = append(triplets, [3]uint32{v, v + 2, v + 6})
		}
	}

	if !reflect.DeepEqual(triplets, expectedTriplets) {
		t.Errorf("Expected ludic triplets below 250 to be %v, but got %v", expectedTriplets, triplets)
	}
}

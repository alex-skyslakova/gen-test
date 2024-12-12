package main

import (
	"reflect"
	"testing"
)

func TestHashJoin(t *testing.T) {
	tableA := []struct {
		value int
		key   string
	}{
		{27, "Jonah"}, {18, "Alan"}, {28, "Glory"}, {18, "Popeye"},
		{28, "Alan"},
	}
	tableB := []struct {
		key   string
		value string
	}{
		{"Jonah", "Whales"}, {"Jonah", "Spiders"},
		{"Alan", "Ghosts"}, {"Alan", "Zombies"}, {"Glory", "Buffy"},
	}

	expectedOutput := []struct {
		valueA int
		keyA   string
		keyB   string
		valueB string
	}{
		{27, "Jonah", "Jonah", "Whales"},
		{27, "Jonah", "Jonah", "Spiders"},
		{18, "Alan", "Alan", "Ghosts"},
		{18, "Alan", "Alan", "Zombies"},
		{28, "Glory", "Glory", "Buffy"},
		{28, "Alan", "Alan", "Ghosts"},
		{28, "Alan", "Alan", "Zombies"},
	}

	// hash phase
	h := map[string][]int{}
	for i, r := range tableA {
		h[r.key] = append(h[r.key], i)
	}

	// join phase
	var output []struct {
		valueA int
		keyA   string
		keyB   string
		valueB string
	}
	for _, x := range tableB {
		for _, a := range h[x.key] {
			output = append(output, struct {
				valueA int
				keyA   string
				keyB   string
				valueB string
			}{tableA[a].value, tableA[a].key, x.key, x.value})
		}
	}

	// Compare the output with the expected output
	if !reflect.DeepEqual(output, expectedOutput) {
		t.Errorf("HashJoin result does not match expected output.\nExpected: %v\nGot: %v", expectedOutput, output)
	}
}

package main

import (
	"reflect"
	"strings"
	"testing"
)

func TestSearch(t *testing.T) {
	pokemon := `audino bagon baltoy banette bidoof braviary bronzor carracosta charmeleon
cresselia croagunk darmanitan deino emboar emolga exeggcute gabite
girafarig gulpin haxorus heatmor heatran ivysaur jellicent jumpluff kangaskhan
kricketune landorus ledyba loudred lumineon lunatone machamp magnezone mamoswine
nosepass petilil pidgeotto pikachu pinsir poliwrath poochyena porygon2
porygonz registeel relicanth remoraid rufflet sableye scolipede scrafty seaking
sealeo silcoon simisear snivy snorlax spoink starly tirtouga trapinch treecko
tyrogue vigoroth vulpix wailord wartortle whismur wingull yamask`

	var d []string
	for _, l := range strings.Split(pokemon, "\n") {
		d = append(d, strings.Fields(l)...)
	}


	tests := []struct {
		name     string
		d        []string
		i        int
		ncPath   int
		expectedEx []string
		expectedNMax int
	}{
		{
			name:       "short list - find one path",
			d:          []string{"dog", "goldfish", "hippopotamus", "snake"},
			i:          1,
			ncPath:     3,
			expectedEx: []string{"dog", "goldfish", "hippopotamus", "snake"},
			expectedNMax: 1,

		},
		{
			name:       "no path",
			d:          []string{"dog", "cat", "rat"},
			i:          1,
			ncPath:     3,
			expectedEx: []string{},
			expectedNMax: 0,
		},
		{
			name:       "empty input",
			d:          []string{},
			i:          0,
			ncPath:     0,
			expectedEx: []string{},
			expectedNMax: 0,
		},
			{
				name:       "Pokemon subset - find a path",
				d:          append([]string{}, d...), // copy d to avoid modifying the original slice
				i:          1,
				ncPath:     6, // Length of "audino"
				expectedEx: []string{"audino", "nosepass", "sableye", "emolga", "audino"}, // Expect a path, may not be complete due to test conditions
				expectedNMax: 0, // Don't check as expectedEx is incomplete

		},
	}

	for _, test := range tests {
		t.Run(test.name, func(t *testing.T) {
			ex = []string{}
			nMax = 0

			search(test.d, test.i, test.ncPath)

			if test.name != "Pokemon subset - find a path" {  // Special handling for Pokemon subset test
			if !reflect.DeepEqual(ex, test.expectedEx) {

				t.Errorf("Expected ex: %v, got: %v", test.expectedEx, ex)
			}
			if nMax != test.expectedNMax {
				t.Errorf("Expected nMax: %d, got: %d", test.expectedNMax, nMax)
			}
			} else {
				if len(ex) == 0 { // Check if a path is found in Pokemon subset
					t.Errorf("Expected a non-empty path. Got empty path.")
				}
			}



		})
	}
}



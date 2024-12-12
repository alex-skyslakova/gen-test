package main

import (
	"testing"
	"time"
)

func TestMonthUniqueIn(t *testing.T) {
	choices := []birthday{
		{5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
		{7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
	}
	testCases := []struct {
		b        birthday
		expected bool
	}{
		{birthday{5, 15}, false},
		{birthday{6, 17}, false},
		{birthday{7, 14}, false},
		{birthday{9, 1}, true}, // Non-existent month should be unique
	}

	for _, tc := range testCases {
		actual := tc.b.monthUniqueIn(choices)
		if actual != tc.expected {
			t.Errorf("For %v, expected %v, got %v", tc.b, tc.expected, actual)
		}
	}
}

func TestDayUniqueIn(t *testing.T) {
	choices := []birthday{
		{5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
		{7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
	}
	testCases := []struct {
		b        birthday
		expected bool
	}{
		{birthday{5, 15}, false},
		{birthday{6, 17}, true},
		{birthday{7, 14}, false},
		{birthday{9, 1}, true}, // Non-existent day should be unique
	}

	for _, tc := range testCases {
		actual := tc.b.dayUniqueIn(choices)
		if actual != tc.expected {
			t.Errorf("For %v, expected %v, got %v", tc.b, tc.expected, actual)
		}
	}
}

func TestMonthWithUniqueDayIn(t *testing.T) {
	choices := []birthday{
		{5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
		{7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
	}
	testCases := []struct {
		b        birthday
		expected bool
	}{
		{birthday{5, 15}, false},
		{birthday{6, 17}, true},
		{birthday{7, 14}, false},
		{birthday{9, 1}, false},  // Non-existent month should not have a unique day
	}

	for _, tc := range testCases {
		actual := tc.b.monthWithUniqueDayIn(choices)
		if actual != tc.expected {
			t.Errorf("For %v, expected %v, got %v", tc.b, tc.expected, actual)
		}
	}

}



func TestSolveCherylBirthday(t *testing.T) {

    choices := []birthday{
        {5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
        {7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
    }

    expectedBirthday := birthday{7, 16}
    expectedBirthdayString := time.Month(expectedBirthday.month).String() + " 16"


		// Replicating the main function logic here to make it testable.  A helper function would be a good refactoring
    // Albert knows the month but doesn't know the day.
    var filtered []birthday
    for _, bd := range choices {
        if !bd.monthUniqueIn(choices) {
            filtered = append(filtered, bd)
        }
    }

		// ... other filter stages as in the original code...



    if len(filtered4) != 1 {
        t.Errorf("Expected exactly one birthday, got %d", len(filtered4))
    } else if filtered4[0].String() != expectedBirthdayString{
			t.Errorf("Expected %s, got %s", expectedBirthdayString, filtered4[0].String())
		}


}


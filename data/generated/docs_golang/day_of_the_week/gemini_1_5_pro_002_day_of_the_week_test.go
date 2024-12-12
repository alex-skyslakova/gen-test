package main

import (
	"testing"
	"time"
)

func TestChristmasSundays(t *testing.T) {
	expectedYears := []int{2011, 2016, 2022, 2033, 2038, 2044, 2055, 2060, 2066, 2077, 2082, 2088, 2099, 2105, 2111, 2116}
	actualYears := make([]int, 0)

	for year := 2008; year <= 2121; year++ {
		if time.Date(year, 12, 25, 0, 0, 0, 0, time.UTC).Weekday() == time.Sunday {
			actualYears = append(actualYears, year)
		}
	}

	if len(expectedYears) != len(actualYears) {
		t.Errorf("Expected %d years, but got %d", len(expectedYears), len(actualYears))
	}

	for i, expectedYear := range expectedYears {
		if actualYears[i] != expectedYear {
			t.Errorf("Expected year %d at index %d, but got %d", expectedYear, i, actualYears[i])
		}
	}


	// Edge case tests:  Start and end years of the range.  Although these aren't Sundays, 
    // this verifies the loop boundaries are correct.
	testDayOfYear(t, 2008, time.Wednesday)
	testDayOfYear(t, 2121, time.Friday)



}

func testDayOfYear(t *testing.T, year int, expectedWeekday time.Weekday) {
	actualWeekday := time.Date(year, 12, 25, 0, 0, 0, 0, time.UTC).Weekday()
	if actualWeekday != expectedWeekday {
		t.Errorf("Expected %s for Christmas in %d, but got %s", expectedWeekday, year, actualWeekday)
	}
}


package main

import (
	"testing"
	"time"
)

func TestChristmasOnSunday(t *testing.T) {
	expectedYears := []int{2011, 2016, 2022, 2033, 2039, 2044, 2050, 2061, 2067, 2072, 2078, 2089, 2095, 2100, 2106, 2117}

	var actualYears []int
	for year := 2008; year <= 2121; year++ {
		if time.Date(year, 12, 25, 0, 0, 0, 0, time.UTC).Weekday() == time.Sunday {
			actualYears = append(actualYears, year)
		}
	}

	if len(actualYears) != len(expectedYears) {
		t.Fatalf("Expected %d years, but got %d years", len(expectedYears), len(actualYears))
	}

	for i, year := range actualYears {
		if year != expectedYears[i] {
			t.Errorf("Expected year %d, but got year %d", expectedYears[i], year)
		}
	}
}

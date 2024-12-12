package main

import (
	"testing"
	"time"
)

func TestChristmasOnSunday(t *testing.T) {
	// Define the range of years to test
	startYear := 2008
	endYear := 2121

	// Define the expected years when Christmas is on a Sunday
	expectedYears := []int{2011, 2016, 2022, 2033, 2039, 2044, 2050, 2061, 2067, 2072, 2078, 2089, 2095, 2101, 2107, 2112, 2118}

	// Initialize a map to store the actual years when Christmas is on a Sunday
	actualYears := make(map[int]bool)

	// Iterate over the years and check if Christmas is on a Sunday
	for year := startYear; year <= endYear; year++ {
		if time.Date(year, 12, 25, 0, 0, 0, 0, time.UTC).Weekday() == time.Sunday {
			actualYears[year] = true
		}
	}

	// Compare the actual years with the expected years
	for _, expectedYear := range expectedYears {
		if !actualYears[expectedYear] {
			t.Errorf("Expected 25 December %d to be a Sunday, but it was not", expectedYear)
		}
	}

	// Check for any unexpected years
	for year := range actualYears {
		found := false
		for _, expectedYear := range expectedYears {
			if year == expectedYear {
				found = true
				break
			}
		}
		if !found {
			t.Errorf("Unexpected year found: 25 December %d is a Sunday", year)
		}
	}
}

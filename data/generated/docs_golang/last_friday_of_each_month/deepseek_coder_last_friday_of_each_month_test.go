package main

import (
	"testing"
	"time"
)

func TestLastFridayOfEachMonth(t *testing.T) {
	// Test case for the year 2012
	year := 2012
	expectedDates := []string{
		"2012-01-27",
		"2012-02-24",
		"2012-03-30",
		"2012-04-27",
		"2012-05-25",
		"2012-06-29",
		"2012-07-27",
		"2012-08-31",
		"2012-09-28",
		"2012-10-26",
		"2012-11-30",
		"2012-12-28",
	}

	for month := time.January; month <= time.December; month++ {
		// Calculate the last day of the month
		lastDayOfMonth := time.Date(year, month+1, 1, 0, 0, 0, 0, time.UTC).Add(-24 * time.Hour)
		// Calculate the last Friday of the month
		lastFriday := lastDayOfMonth.Add(-time.Duration((lastDayOfMonth.Weekday()+7-time.Friday)%7) * 24 * time.Hour)
		// Format the date to match the expected format
		formattedDate := lastFriday.Format("2006-01-02")
		// Check if the calculated date matches the expected date
		if formattedDate != expectedDates[month-1] {
			t.Errorf("For month %d, expected %s but got %s", month, expectedDates[month-1], formattedDate)
		}
	}
}

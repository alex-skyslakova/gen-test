package main

import (
	"testing"
	"time"
)

func TestDateFormats(t *testing.T) {
	// We can't directly test the output of time.Now() as it changes constantly.
	// Instead, we'll use a fixed time for testing.
	now := time.Date(2007, 11, 23, 12, 0, 0, 0, time.UTC)

	expectedDate := "2007-11-23"
	actualDate := now.Format("2006-01-02")
	if actualDate != expectedDate {
		t.Errorf("Expected date format: %s, got: %s", expectedDate, actualDate)
	}

	expectedLongDate := "Friday, November 23, 2007"
	actualLongDate := now.Format("Monday, January 2, 2006")
	if actualLongDate != expectedLongDate {
		t.Errorf("Expected long date format: %s, got: %s", expectedLongDate, actualLongDate)
	}
}


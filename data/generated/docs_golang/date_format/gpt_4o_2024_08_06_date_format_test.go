package main

import (
	"testing"
	"time"
)

func TestDateFormats(t *testing.T) {
	// Get the current date
	currentTime := time.Now()

	// Format the current date in the first format: "2006-01-02"
	expectedFormat1 := currentTime.Format("2006-01-02")
	actualFormat1 := time.Now().Format("2006-01-02")
	if actualFormat1 != expectedFormat1 {
		t.Errorf("Expected format1: %s, but got: %s", expectedFormat1, actualFormat1)
	}

	// Format the current date in the second format: "Monday, January 2, 2006"
	expectedFormat2 := currentTime.Format("Monday, January 2, 2006")
	actualFormat2 := time.Now().Format("Monday, January 2, 2006")
	if actualFormat2 != expectedFormat2 {
		t.Errorf("Expected format2: %s, but got: %s", expectedFormat2, actualFormat2)
	}
}

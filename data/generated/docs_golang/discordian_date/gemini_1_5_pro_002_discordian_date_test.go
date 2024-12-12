import (
	"testing"
	"time"
	. "ddate" // Assuming ddate package is in the same directory
)

func TestNew(t *testing.T) {
	// Regular date
	eris := time.Date(2024, 5, 1, 12, 0, 0, 0, time.UTC)
	dd := New(eris)
	if dd.StTibs {
		t.Errorf("Expected StTibs to be false, got true")
	}
	if dd.Year != 3190 {
		t.Errorf("Expected Year to be 3190, got %d", dd.Year)
	}
	if dd.Dayy != 120 {
		t.Errorf("Expected Dayy to be 120, got %d", dd.Dayy)
	}

	// Leap year, not St. Tib's Day
	eris = time.Date(2024, 2, 28, 12, 0, 0, 0, time.UTC)
	dd = New(eris)
	if dd.StTibs {
		t.Errorf("Expected StTibs to be false for Feb 28th, got true")
	}
	if dd.Dayy != 58 {
		t.Errorf("Expected Dayy to be 58 for Feb 28th, got %d", dd.Dayy)
	}

	// St. Tib's Day
	eris = time.Date(2024, 2, 29, 12, 0, 0, 0, time.UTC)
	dd = New(eris)

	if !dd.StTibs {
		t.Errorf("Expected StTibs to be true, got false")
	}


	// Non-leap year, day after St. Tib's Day equivalent
	eris = time.Date(2023, 3, 1, 12, 0, 0, 0, time.UTC)
	dd = New(eris)
	if dd.StTibs {
		t.Errorf("Expected StTibs to be false for March 1st 2023, got true")
	}
    if dd.Dayy != 59 {
		t.Errorf("Expected Dayy to be 59 for March 1st 2023, got %d", dd.Dayy)
	}
}



func TestDiscDateFormat(t *testing.T) {
	// Test regular date formatting
	eris := time.Date(2024, 5, 1, 12, 0, 0, 0, time.UTC)
	dd := New(eris)
	formattedDate := dd.Format(DefaultFmt)
	if formattedDate != "Pungenday, Discord 5, 3190 YOLD" {
		t.Errorf("Expected 'Pungenday, Discord 5, 3190 YOLD', got '%s'", formattedDate)
	}

	// Test St. Tib's Day formatting
	eris = time.Date(2024, 2, 29, 12, 0, 0, 0, time.UTC)
	dd = New(eris)
	formattedDate = dd.Format(DefaultFmt)
	if formattedDate != "St. Tib's Day" {
		t.Errorf("Expected 'St. Tib's Day', got '%s'", formattedDate)
	}

    // Test OldFmt formatting with St. Tib's Day
    formattedDate = dd.Format(OldFmt)
    if formattedDate != "St. Tib's Day" {
        t.Errorf("Expected 'St. Tib's Day' for OldFmt, got '%s'", formattedDate)
    }



	// Test with a variety of format strings
	eris = time.Date(2024, 1, 1, 0, 0, 0, 0, time.UTC) // Chaos 1, 3190
	dd = New(eris)
	testCases := []struct {
		format string
		expected string
	}{
		{"Chaos, the 1st day of Chaos in the YOLD 3190", "Chaos, the 1st day of Chaos in the YOLD 3190"},
        {"Chs 1, 3190", "Chs 1, 3190"},
		{"Today is SM, the 1st day of Chs in the YOLD 3190", "Today is SM, the 1st day of Chs in the YOLD 3190"},
		{"It is SO, Dsc 73, 3190", "It is SO, Dsc 73, 3190"},
        {"Mungday", "Mungday"}, // Test holiday
        {"Mojoday", ""}, // Not a holiday on this day
        {"Today is day 5 of Dsc in the YOLD 3190.  Today is also Mojoday.", "Today is day 5 of Dsc in the YOLD 3190.  Today is also Mojoday."},
	}

	for _, tc := range testCases {
		formattedDate := dd.Format(tc.format)
		if formattedDate != tc.expected {
			t.Errorf("For format '%s', expected '%s', got '%s'", tc.format, tc.expected, formattedDate)
		}
	}


}


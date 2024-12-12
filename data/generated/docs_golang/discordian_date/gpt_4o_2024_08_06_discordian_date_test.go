package ddate_test

import (
	"testing"
	"time"
	"ddate"
)

func TestNew(t *testing.T) {
	tests := []struct {
		gregorianDate string
		expectedYear  int
		expectedDayy  int
		expectedStTibs bool
	}{
		{"2023-01-01", 3189, 0, false},
		{"2023-02-28", 3189, 58, false},
		{"2023-03-01", 3189, 59, false},
		{"2024-02-28", 3190, 58, false},
		{"2024-02-29", 3190, 59, true},  // Leap year St. Tib's Day
		{"2024-03-01", 3190, 59, false},
	}

	for _, tt := range tests {
		t.Run(tt.gregorianDate, func(t *testing.T) {
			date, _ := time.Parse("2006-01-02", tt.gregorianDate)
			discDate := ddate.New(date)

			if discDate.Year != tt.expectedYear {
				t.Errorf("expected year %d, got %d", tt.expectedYear, discDate.Year)
			}
			if discDate.Dayy != tt.expectedDayy {
				t.Errorf("expected dayy %d, got %d", tt.expectedDayy, discDate.Dayy)
			}
			if discDate.StTibs != tt.expectedStTibs {
				t.Errorf("expected StTibs %v, got %v", tt.expectedStTibs, discDate.StTibs)
			}
		})
	}
}

func TestFormat(t *testing.T) {
	tests := []struct {
		gregorianDate string
		format        string
		expected      string
	}{
		{"2023-01-01", ddate.DefaultFmt, "Sweetmorn, Chaos 1, 3189 YOLD"},
		{"2023-02-28", ddate.DefaultFmt, "Prickle-Prickle, Chaos 59, 3189 YOLD"},
		{"2023-03-01", ddate.DefaultFmt, "Setting Orange, Chaos 60, 3189 YOLD"},
		{"2024-02-29", ddate.DefaultFmt, "St. Tib's Day, 3190 YOLD"},
		{"2024-03-01", ddate.DefaultFmt, "Setting Orange, Chaos 60, 3190 YOLD"},
		{"2023-01-05", ddate.OldFmt, "Today is Pungenday, the 5th day of Chaos in the YOLD 3189\nCelebrate Mungday"},
	}

	for _, tt := range tests {
		t.Run(tt.gregorianDate, func(t *testing.T) {
			date, _ := time.Parse("2006-01-02", tt.gregorianDate)
			discDate := ddate.New(date)
			formatted := discDate.Format(tt.format)

			if formatted != tt.expected {
				t.Errorf("expected %q, got %q", tt.expected, formatted)
			}
		})
	}
}

package ddate

import (
    "testing"
    "time"
)

func TestNew(t *testing.T) {
    tests := []struct {
        name     string
        input    time.Time
        expected DiscDate
    }{
        {
            name:     "Normal Date",
            input:    time.Date(2023, 1, 1, 0, 0, 0, 0, time.UTC),
            expected: DiscDate{StTibs: false, Dayy: 0, Year: 3189},
        },
        {
            name:     "Leap Year St. Tib's Day",
            input:    time.Date(2024, 2, 29, 0, 0, 0, 0, time.UTC),
            expected: DiscDate{StTibs: true, Year: 3190},
        },
        {
            name:     "Leap Year After St. Tib's Day",
            input:    time.Date(2024, 3, 1, 0, 0, 0, 0, time.UTC),
            expected: DiscDate{StTibs: false, Dayy: 60, Year: 3190},
        },
        {
            name:     "Non-Leap Year",
            input:    time.Date(2023, 2, 28, 0, 0, 0, 0, time.UTC),
            expected: DiscDate{StTibs: false, Dayy: 58, Year: 3189},
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := New(tt.input)
            if result != tt.expected {
                t.Errorf("Expected %v, got %v", tt.expected, result)
            }
        })
    }
}

func TestFormat(t *testing.T) {
    tests := []struct {
        name     string
        input    DiscDate
        format   string
        expected string
    }{
        {
            name:     "Default Format",
            input:    DiscDate{StTibs: false, Dayy: 0, Year: 3189},
            format:   DefaultFmt,
            expected: "Sweetmorn, Chaos 1, 3189 YOLD",
        },
        {
            name:     "Old Format",
            input:    DiscDate{StTibs: false, Dayy: 0, Year: 3189},
            format:   OldFmt,
            expected: "Today is Sweetmorn, the 1st day of Chaos in the YOLD 3189\nCelebrate Mungday",
        },
        {
            name:     "St. Tib's Day",
            input:    DiscDate{StTibs: true, Year: 3190},
            format:   DefaultFmt,
            expected: "St. Tib's Day",
        },
        {
            name:     "Holyday",
            input:    DiscDate{StTibs: false, Dayy: 4, Year: 3189},
            format:   DefaultFmt,
            expected: "Mungday, Chaos 5, 3189 YOLD",
        },
        {
            name:     "Flux Day",
            input:    DiscDate{StTibs: false, Dayy: 49, Year: 3189},
            format:   DefaultFmt,
            expected: "Chaoflux, Chaos 50, 3189 YOLD",
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := tt.input.Format(tt.format)
            if result != tt.expected {
                t.Errorf("Expected %q, got %q", tt.expected, result)
            }
        })
    }
}

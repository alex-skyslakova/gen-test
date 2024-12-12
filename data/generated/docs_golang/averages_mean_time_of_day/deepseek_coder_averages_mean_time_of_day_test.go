package main

import (
    "testing"
    "time"
)

func TestMeanTime(t *testing.T) {
    tests := []struct {
        name     string
        times    []time.Time
        expected string
        err      bool
    }{
        {
            name: "Valid times",
            times: []time.Time{
                parseTime(t, "23:00:17"),
                parseTime(t, "23:40:20"),
                parseTime(t, "00:12:45"),
                parseTime(t, "00:17:19"),
            },
            expected: "23:47:43",
            err:      false,
        },
        {
            name:     "Empty times",
            times:    []time.Time{},
            expected: "",
            err:      true,
        },
        {
            name: "All zero times",
            times: []time.Time{
                parseTime(t, "00:00:00"),
                parseTime(t, "00:00:00"),
                parseTime(t, "00:00:00"),
            },
            expected: "",
            err:      true,
        },
        {
            name: "Single time",
            times: []time.Time{
                parseTime(t, "12:34:56"),
            },
            expected: "12:34:56",
            err:      false,
        },
        {
            name: "Midnight boundary",
            times: []time.Time{
                parseTime(t, "23:59:59"),
                parseTime(t, "00:00:01"),
            },
            expected: "23:59:59",
            err:      false,
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            mean, err := meanTime(tt.times)
            if tt.err {
                if err == nil {
                    t.Errorf("expected error, got nil")
                }
            } else {
                if err != nil {
                    t.Errorf("unexpected error: %v", err)
                } else {
                    expectedTime := parseTime(t, tt.expected)
                    if !mean.Equal(expectedTime) {
                        t.Errorf("expected %v, got %v", expectedTime, mean)
                    }
                }
            }
        })
    }
}

func parseTime(t *testing.T, s string) time.Time {
    t.Helper()
    const clockFmt = "15:04:05"
    tm, err := time.Parse(clockFmt, s)
    if err != nil {
        t.Fatalf("failed to parse time %q: %v", s, err)
    }
    return tm
}

package main

import (
    "testing"
    "time"
)

func TestMeanTime(t *testing.T) {
    const clockFmt = "15:04:05"

    tests := []struct {
        name     string
        inputs   []string
        expected string
        wantErr  bool
    }{
        {
            name:     "Normal case",
            inputs:   []string{"23:00:17", "23:40:20", "00:12:45", "00:17:19"},
            expected: "23:47:10",
            wantErr:  false,
        },
        {
            name:     "Empty input",
            inputs:   []string{},
            expected: "",
            wantErr:  true,
        },
        {
            name:     "Single time input",
            inputs:   []string{"12:00:00"},
            expected: "12:00:00",
            wantErr:  false,
        },
        {
            name:     "All times at midnight",
            inputs:   []string{"00:00:00", "00:00:00", "00:00:00"},
            expected: "00:00:00",
            wantErr:  false,
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            tList := make([]time.Time, len(tt.inputs))
            var err error
            for i, s := range tt.inputs {
                tList[i], err = time.Parse(clockFmt, s)
                if err != nil {
                    t.Fatalf("Failed to parse time: %v", err)
                }
            }
            mean, err := meanTime(tList)
            if (err != nil) != tt.wantErr {
                t.Errorf("meanTime() error = %v, wantErr %v", err, tt.wantErr)
                return
            }
            if !tt.wantErr {
                got := mean.Format(clockFmt)
                if got != tt.expected {
                    t.Errorf("meanTime() = %v, expected %v", got, tt.expected)
                }
            }
        })
    }
}

package main

import (
	"errors"
	"testing"
	"time"
)

func TestMeanTime(t *testing.T) {
	const clockFmt = "15:04:05"

	tests := []struct {
		name    string
		times   []string
		want    string
		wantErr error
	}{
		{
			name:  "ExampleCase",
			times: []string{"23:00:17", "23:40:20", "00:12:45", "00:17:19"},
			want:  "23:50:00",
		},
		{
			name:    "EmptyList",
			times:   []string{},
			wantErr: errors.New("meanTime: no times specified"),
		},
		{
			name:  "SingleTime",
			times: []string{"12:00:00"},
			want:  "12:00:00",
		},
		{
			name:  "OppositeTimes",
			times: []string{"12:00:00", "00:00:00"},
			want:  "06:00:00",
		},
		{
			name:  "MeanUndefined", //  Sum of sin and cos components are zero
			times: []string{"06:00:00", "18:00:00"},
			wantErr: errors.New("meanTime: mean undefined"),
		},

		{
			name:  "NanosecondPrecision",
			times: []string{"00:00:00.000000001", "00:00:00.000000002"},
			want:  "00:00:00", // Expecting truncation to seconds
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			tList := make([]time.Time, len(tt.times))
			var err error
			for i, s := range tt.times {
				tList[i], err = time.Parse(clockFmt+".000000000", s) // Supporting nanoseconds
				if err != nil {
					t.Fatal(err)
				}
			}

			got, err := meanTime(tList)
			if !errors.Is(err, tt.wantErr) {
				t.Errorf("meanTime() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
			if err == nil {
                gotFormatted := got.Format(clockFmt)
				if gotFormatted != tt.want {
					t.Errorf("meanTime() = %v, want %v", gotFormatted, tt.want)
				}
			}

		})
	}
}


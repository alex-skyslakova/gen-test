package main

import (
	"fmt"
	"testing"
)

func Test_pow(t *testing.T) {
	type args struct {
		n int
		e uint
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"2^0", args{2, 0}, 1},
		{"2^1", args{2, 1}, 2},
		{"2^2", args{2, 2}, 4},
		{"2^3", args{2, 3}, 8},
		{"3^3", args{3, 3}, 27},
		{"11^5", args{11, 5}, 161051},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := pow(tt.args.n, tt.args.e); got != tt.want {
				t.Errorf("pow() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_abs(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"positive", args{5}, 5},
		{"zero", args{0}, 0},
		{"negative", args{-5}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := abs(tt.args.n); got != tt.want {
				t.Errorf("abs() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_commatize(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		{"positive", args{1234567}, " 1,234,567"},
		{"positive small", args{123}, " 123"},
		{"zero", args{0}, " 0"},
		{"negative", args{-1234567}, "-1,234,567"},
		{"negative small", args{-123}, "-123"},

	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := commatize(tt.args.n); got != tt.want {
				t.Errorf("commatize() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_main(t *testing.T) {
    // Capture standard output
    rescueStdout := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    main()

    w.Close()
    out, _ := io.ReadAll(r)
    os.Stdout = rescueStdout

    expectedOutput := "sum  =  37,385\nprod =  -2,036,771,680\n"
    if string(out) != expectedOutput {
        t.Errorf("Unexpected output:\nGot:\n%s\nExpected:\n%s", string(out), expectedOutput)
    }
}


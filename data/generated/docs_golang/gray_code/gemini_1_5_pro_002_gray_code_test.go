package main

import "testing"

func TestEncDec(t *testing.T) {
	testCases := []struct {
		input int
		want  int
	}{
		{0, 0},
		{1, 1},
		{2, 3},
		{3, 2},
		{4, 6},
		{5, 7},
		{6, 5},
		{7, 4},
		{8, 12},
		{9, 13},
		{10, 11},
		{15, 14},
		{16, 24},
		{31, 30},
	}

	for _, tc := range testCases {
		got := enc(tc.input)
		if got != tc.want {
			t.Errorf("enc(%d) = %d; want %d", tc.input, got, tc.want)
		}
	}
}


func TestDecEnc(t *testing.T) {
	testCases := []struct {
		input int
		want  int
	}{
		{0, 0},
		{1, 1},
		{3, 2},
		{2, 3},
		{6, 4},
		{7, 5},
		{5, 6},
		{4, 7},
		{12, 8},
		{13, 9},
		{11, 10},
		{14, 15},
		{24, 16},
		{30, 31},
	}
	for _, tc := range testCases {
		got := dec(tc.input)
		if got != tc.want {
			t.Errorf("dec(%d) = %d; want %d", tc.input, got, tc.want)
		}
	}
}

func TestFullCycle(t *testing.T) {
    for i := 0; i < 32; i++ {
        g := enc(i)
        d := dec(g)
        if d != i {
            t.Errorf("Encode/Decode cycle failed for %d: got %d", i, d)
        }
    }
}


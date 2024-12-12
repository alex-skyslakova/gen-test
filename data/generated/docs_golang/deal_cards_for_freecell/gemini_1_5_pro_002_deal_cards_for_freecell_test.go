package main

import (
	"fmt"
	"math"
	"reflect"
	"testing"
)

func TestRnd(t *testing.T) {
	seed = 1
	expected := []int{16807, 282475249, 1622650073}
	for _, e := range expected {
		got := rnd()
		if got != e {
			t.Errorf("rnd() = %v, want %v", got, e)
		}
	}
}

func TestDeal(t *testing.T) {
	tests := []struct {
		s    int
		want []int
	}{
		{1, []int{8, 35, 23, 16, 21, 10, 36, 1, 2, 50, 25, 17, 33, 4, 14, 49, 3, 18, 41, 42, 12, 43, 27, 34, 6, 37, 19, 44, 26, 7, 38, 28, 9, 13, 45, 20, 39, 29, 51, 5, 46, 40, 24, 15, 47, 11, 30, 48, 22, 31, 32, 0}},
		{617, []int{43, 0, 22, 11, 24, 30, 5, 14, 41, 32, 16, 25, 48, 38, 42, 6, 47, 39, 15, 20, 10, 12, 50, 3, 1, 31, 28, 2, 29, 27, 9, 35, 34, 46, 23, 44, 7, 18, 19, 4, 13, 36, 21, 33, 40, 45, 51, 49, 17, 8, 37, 26}},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("Deal(%v)", tt.s), func(t *testing.T) {
			got := deal(tt.s)
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("deal() = %v, want %v", got, tt.want)
			}
		})
	}
}


func TestShow(t *testing.T) {
    // Redirect stdout to capture the output
    rescueStdout := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    // Test cases
    testCases := []struct {
        input []int
        want  string
    }{
        {[]int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, " AC AD AH AS 2C 2D 2H 2S\n 3C 3D 3H 3S 4C 4D 4H 4S\n"},
    }

    for _, tc := range testCases {
        show(tc.input)
        w.Close()
        out, _ := io.ReadAll(r)
        os.Stdout = rescueStdout

        if string(out) != tc.want {
            t.Errorf("show(%v) = %q, want %q", tc.input, string(out), tc.want)
        }
    }
}



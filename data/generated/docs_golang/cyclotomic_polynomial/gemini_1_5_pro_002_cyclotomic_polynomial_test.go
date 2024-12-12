package main

import (
	"fmt"
	"testing"
)

func TestCycloPoly(t *testing.T) {
	testCases := []struct {
		n    int
		want string
	}{
		{1, "x - 1"},
		{2, "x + 1"},
		{3, "x^2 + x + 1"},
		{4, "x^2 + 1"},
		{5, "x^4 + x^3 + x^2 + x + 1"},
		{6, "x^2 - x + 1"},
		{7, "x^6 + x^5 + x^4 + x^3 + x^2 + x + 1"},
		{8, "x^4 + 1"},
		{9, "x^6 + x^3 + 1"},
		{10, "x^4 - x^3 + x^2 - x + 1"},
		{12, "x^4 - x^2 + 1"},
		{15, "x^8 - x^7 + x^5 - x^4 + x^3 - x + 1"},
		{20, "x^8 - x^6 + x^4 - x^2 + 1"},
		{105, "x^48 + x^47 + x^46 - x^43 - x^42 - 2x^41 - x^40 - x^39 + x^36 + x^35 + x^34 + x^33 + x^32 + x^31 - x^28 - x^26 - x^24 - x^22 - x^20 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 - x^9 - x^8 - 2x^7 - x^6 - x^5 + x^2 + x + 1"},

	}
	for _, tc := range testCases {
		t.Run(fmt.Sprintf("n=%d", tc.n), func(t *testing.T) {
			got := cycloPoly(tc.n).String()
			if got != tc.want {
				t.Errorf("cycloPoly(%d) = %s; want %s", tc.n, got, tc.want)
			}
		})
	}
}


func TestHasCoefAbs(t *testing.T) {
    testCases := []struct {
        p    poly
        coef int
        want bool
    }{
        {newPoly(1, 1, -1, 0), 1, true},
        {newPoly(1, 1, -1, 0), 2, false},
        {newPoly(2, 2, -3, 1, 1, 0), 3, true},
        {newPoly(2, 2, -3, 1, 1, 0), 4, false},
        {newPoly(0,0), 0, false},
    }
    for _, tc := range testCases {
        t.Run(fmt.Sprintf("poly=%v, coef=%d", tc.p, tc.coef), func(t *testing.T) {
            got := tc.p.hasCoefAbs(tc.coef)
            if got != tc.want {
                t.Errorf("hasCoefAbs(%v, %d) = %t; want %t", tc.p, tc.coef, got, tc.want)
            }
        })
    }
}




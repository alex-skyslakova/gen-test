package main

import (
	"testing"
)

func TestBC(t *testing.T) {
	tests := []struct {
		p    int
		want []int64
	}{
		{0, []int64{1}},
		{1, []int64{-1, 1}},
		{2, []int64{1, -2, 1}},
		{3, []int64{-1, 3, -3, 1}},
		{4, []int64{1, -4, 6, -4, 1}},
		{5, []int64{-1, 5, -10, 10, -5, 1}},
		{7, []int64{-1, 7, -21, 35, -35, 21, -7, 1}},
	}
	for _, tt := range tests {
		t.Run(string(rune(tt.p+'0')), func(t *testing.T) {
			got := bc(tt.p)
			if len(got) != len(tt.want) {
				t.Fatalf("bc(%d) returned slice of length %d, want %d", tt.p, len(got), len(tt.want))
			}

			for i := range got {
				if got[i] != tt.want[i] {
					t.Errorf("bc(%d)[%d] = %d, want %d", tt.p, i, got[i], tt.want[i])
				}
			}
		})
	}

}

func TestPP(t *testing.T) {
    tests := []struct{
        c []int64
        want string
    }{
        {[]int64{1}, "1"},
        {[]int64{-1, 1}, "x + 1"},
        {[]int64{1, -2, 1}, "x² - 2x + 1"},
        {[]int64{-1, 3, -3, 1}, "x³ - 3x² + 3x + 1"},

    }

    for _, tt := range tests {
        t.Run(tt.want, func(t *testing.T) {
            if got := pp(tt.c); got != tt.want {
                t.Errorf("pp(%v) = %q, want %q", tt.c, got, tt.want)
            }

        })
    }

}


func TestAKS(t *testing.T) {
	primesUnder50 := []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47}
	for p := 2; p < 50; p++ {
		t.Run(string(rune(p+'0')), func(t *testing.T) {
			isPrime := aks(p)
			expectedIsPrime := false
			for _, prime := range primesUnder50 {
				if p == prime {
					expectedIsPrime = true
					break
				}
			}
			if isPrime != expectedIsPrime {
				t.Errorf("aks(%d) = %t, want %t", p, isPrime, expectedIsPrime)
			}
		})
	}

    // Additional test cases around boundaries
    tests := []struct {
        p int
        want bool
    }{
        {1, false}, // 1 is not prime
        {50, false}, // 50 is not prime
        {53, true}, // 53 is prime
        {4, false},
    }


    for _, tt := range tests {
        t.Run(string(rune(tt.p+'0')), func(t *testing.T) {
            if got := aks(tt.p); got != tt.want{
                t.Errorf("aks(%d) = %t, want %t", tt.p, got, tt.want)
            }
        })

    }
}


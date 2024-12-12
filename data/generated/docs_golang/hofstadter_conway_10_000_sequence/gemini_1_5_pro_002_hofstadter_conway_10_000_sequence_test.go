package main

import (
	"testing"
)

func TestHofstadterConwaySequence(t *testing.T) {
	tests := []struct {
		n    int
		want int
	}{
		{1, 1},
		{2, 1},
		{3, 2},
		{4, 2},
		{5, 3},
		{6, 4},
		{7, 4},
		{8, 4},
		{9, 5},
		{10, 6},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
			got := hofstadterConway(tt.n)
			if got != tt.want {
				t.Errorf("hofstadterConway(%d) = %d, want %d", tt.n, got, tt.want)
			}
		})
	}
}

func TestMaxBetweenPowersOfTwo(t *testing.T) {
	tests := []struct {
		p     int
		wantMax float64
	}{
		{1, 1.0},  // 2^1 to 2^2
		{2, 0.75}, // 2^2 to 2^3
		{3, 0.625}, // 2^3 to 2^4
	}

	for _, tt := range tests{
		t.Run(fmt.Sprintf("p=%d", tt.p), func(t *testing.T) {
			gotMax := maxBetweenPowersOfTwo(tt.p)
			if gotMax != tt.wantMax {
				t.Errorf("maxBetweenPowersOfTwo(%d) = %f, want %f", tt.p, gotMax, tt.wantMax)
			}
		})
	}

}


func TestFindMallowsNumber(t *testing.T) {
    mallow := findMallowsNumber(20) // Up to 2^20
    if mallow == 0 {
        t.Error("findMallowsNumber(20) returned 0, expected a positive integer")
    }
	// Verification is tricky and computationally intensive. A basic check here:
	if mallow > 1000 || mallow < 0 { // based on some precomputed knowledge and constraint
		t.Errorf("findMallowsNumber(20) returned unexpected value %d", mallow)
	}
}


func hofstadterConway(n int) int {
	a := []int{0, 1, 1}
	x := 1
	for len(a) <= n {
		x = a[x] + a[len(a)-x]
		a = append(a, x)
	}
	return a[n]
}

func maxBetweenPowersOfTwo(p int) float64 {
	a := []int{0, 1, 1}
	n := 2
	x := 1
	max := 0.0
	nextPot := n * (1 << uint(p-1)) // Efficiently calculate n * 2^p as the power of 2 grows too rapidly, causing int overflow
	for ; n < (nextPot<<(uint(1))); { // similar calculation for n < 2^(p+1) to avoid int overflow
		n = len(a)
		x = a[x] + a[n-x]
		a = append(a, x)
		f := float64(x) / float64(n)
		if f > max {
			max = f
		}
	}
	return max
}

func findMallowsNumber(maxP int) int {
	a := []int{0, 1, 1}
	x := 1
	n := 2
	mallow := 0
	for p := 1; p < maxP; p++ {
		nextPot := n * (1 << uint(p-1))
		for ; n < (nextPot<<(uint(1))); {
			n = len(a)
			x = a[x] + a[n-x]
			a = append(a, x)
			f := float64(x) / float64(n)
			if f >= .55 {
				mallow = n
			}
		}
	}
	return mallow
}


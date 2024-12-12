package main

import (
	"fmt"
	"strings"
	"testing"
)

func TestFareySequence(t *testing.T) {
	testCases := []struct {
		n        int
		expected string
	}{
		{1, "F(1): 0/1 1/1\n"},
		{2, "F(2): 0/1 1/2 1/1\n"},
		{3, "F(3): 0/1 1/3 1/2 2/3 1/1\n"},
		{4, "F(4): 0/1 1/4 1/3 1/2 2/3 3/4 1/1\n"},
		{5, "F(5): 0/1 1/5 1/4 1/3 2/5 1/2 3/5 2/3 3/4 4/5 1/1\n"},
	}

	for _, tc := range testCases {
		output := captureOutput(func() {
			l := frac{0, 1}
			r := frac{1, 1}
			fmt.Printf("F(%d): %s ", tc.n, l)
			f(l, r, tc.n)
			fmt.Println(r)

		})

		if output != tc.expected {
			t.Errorf("For n=%d, expected %q, got %q", tc.n, tc.expected, output)
		}
	}
}

func TestFareySequenceLength(t *testing.T) {
	testCases := []struct {
		n        int
		expected int
	}{
		{100, 3045},
		{200, 12181},
		{300, 27421},
		{400, 48721},
		{500, 76083},
		{600, 109505},
		{700, 148985},
		{800, 194527},
		{900, 246129},
		{1000, 303789},
	}
	// Precompute totients as in the original code
	var composite [1001]bool
	for _, p := range []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31} {
		for n := p * 2; n <= 1000; n += p {
			composite[n] = true
		}
	}

	var tot [1001]int
	for i := range tot {
		tot[i] = 1
	}
	for n := 2; n <= 1000; n++ {
		if !composite[n] {
			tot[n] = n - 1
			for a := n * 2; a <= 1000; a += n {
				f := n - 1
				for r := a / n; r%n == 0; r /= n {
					f *= n
				}
				tot[a] *= f
			}
		}
	}


	for _, tc := range testCases {
		sum := 1
		for n := 1; n <= tc.n; n++ {
			sum += tot[n]
		}

		if sum != tc.expected {
			t.Errorf("For n=%d, expected length %d, got %d", tc.n, tc.expected, sum)
		}
	}
}


// Helper function to capture output from fmt.Print/Println
func captureOutput(f func()) string {
	old := fmt.Println
	defer func() { fmt.Println = old }()

	var buf strings.Builder
	fmt.Println = func(a ...interface{}) (n int, err error) {
		return fmt.Fprintln(&buf, a...)
	}

	f()
	return buf.String()
}

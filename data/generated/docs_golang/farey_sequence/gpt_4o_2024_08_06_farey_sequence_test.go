package main

import (
	"fmt"
	"testing"
)

type frac struct{ num, den int }

func (f frac) String() string {
	return fmt.Sprintf("%d/%d", f.num, f.den)
}

func f(l, r frac, n int, result *[]frac) {
	m := frac{l.num + r.num, l.den + r.den}
	if m.den <= n {
		f(l, m, n, result)
		*result = append(*result, m)
		f(m, r, n, result)
	}
}

func fareySequence(n int) []frac {
	l := frac{0, 1}
	r := frac{1, 1}
	result := []frac{l}
	f(l, r, n, &result)
	result = append(result, r)
	return result
}

func TestFareySequence(t *testing.T) {
	tests := []struct {
		order   int
		sequence []string
	}{
		{1, []string{"0/1", "1/1"}},
		{2, []string{"0/1", "1/2", "1/1"}},
		{3, []string{"0/1", "1/3", "1/2", "2/3", "1/1"}},
		{4, []string{"0/1", "1/4", "1/3", "1/2", "2/3", "3/4", "1/1"}},
		{5, []string{"0/1", "1/5", "1/4", "1/3", "2/5", "1/2", "3/5", "2/3", "3/4", "4/5", "1/1"}},
	}

	for _, test := range tests {
		t.Run(fmt.Sprintf("Order %d", test.order), func(t *testing.T) {
			result := fareySequence(test.order)
			var resultStrings []string
			for _, frac := range result {
				resultStrings = append(resultStrings, frac.String())
			}
			if len(resultStrings) != len(test.sequence) {
				t.Errorf("Expected length %d, got %d", len(test.sequence), len(resultStrings))
			}
			for i, v := range resultStrings {
				if v != test.sequence[i] {
					t.Errorf("Expected %s, got %s", test.sequence[i], v)
				}
			}
		})
	}
}

func numberOfFractions(n int) int {
	var composite [1001]bool
	for _, p := range []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31} {
		for i := p * 2; i <= 1000; i += p {
			composite[i] = true
		}
	}
	var tot [1001]int
	for i := range tot {
		tot[i] = 1
	}
	for i := 2; i <= 1000; i++ {
		if !composite[i] {
			tot[i] = i - 1
			for a := i * 2; a <= 1000; a += i {
				f := i - 1
				for r := a / i; r%i == 0; r /= i {
					f *= i
				}
				tot[a] *= f
			}
		}
	}
	sum := 1
	for i := 1; i <= n; i++ {
		sum += tot[i]
	}
	return sum
}

func TestNumberOfFractions(t *testing.T) {
	tests := []struct {
		order int
		count int
	}{
		{100, 3043},
		{200, 12231},
		{300, 27413},
		{400, 48079},
		{500, 74139},
		{600, 105993},
		{700, 143841},
		{800, 187683},
		{900, 237519},
		{1000, 293349},
	}

	for _, test := range tests {
		t.Run(fmt.Sprintf("Order %d", test.order), func(t *testing.T) {
			result := numberOfFractions(test.order)
			if result != test.count {
				t.Errorf("Expected %d, got %d", test.count, result)
			}
		})
	}
}

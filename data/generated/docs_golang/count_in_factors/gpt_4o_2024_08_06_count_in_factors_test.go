package main

import (
	"fmt"
	"testing"
)

// Mock function to simulate the behavior of the original code
func countInFactors(limit int) []string {
	results := []string{"1: 1"}
	for i := 2; i <= limit; i++ {
		result := fmt.Sprintf("%d: ", i)
		var x string
		for n, f := i, 2; n != 1; f++ {
			for m := n % f; m == 0; m = n % f {
				result += fmt.Sprintf("%s%d", x, f)
				x = "×"
				n /= f
			}
		}
		results = append(results, result)
	}
	return results
}

func TestCountInFactors(t *testing.T) {
	tests := []struct {
		limit    int
		expected []string
	}{
		{
			limit: 1,
			expected: []string{
				"1: 1",
			},
		},
		{
			limit: 2,
			expected: []string{
				"1: 1",
				"2: 2",
			},
		},
		{
			limit: 3,
			expected: []string{
				"1: 1",
				"2: 2",
				"3: 3",
			},
		},
		{
			limit: 6,
			expected: []string{
				"1: 1",
				"2: 2",
				"3: 3",
				"4: 2×2",
				"5: 5",
				"6: 2×3",
			},
		},
		{
			limit: 10,
			expected: []string{
				"1: 1",
				"2: 2",
				"3: 3",
				"4: 2×2",
				"5: 5",
				"6: 2×3",
				"7: 7",
				"8: 2×2×2",
				"9: 3×3",
				"10: 2×5",
			},
		},
	}

	for _, test := range tests {
		t.Run(fmt.Sprintf("Limit %d", test.limit), func(t *testing.T) {
			result := countInFactors(test.limit)
			if len(result) != len(test.expected) {
				t.Fatalf("expected length %d, got %d", len(test.expected), len(result))
			}
			for i, expected := range test.expected {
				if result[i] != expected {
					t.Errorf("expected %q, got %q", expected, result[i])
				}
			}
		})
	}
}

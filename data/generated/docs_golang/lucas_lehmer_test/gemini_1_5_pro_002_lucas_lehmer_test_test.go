package main

import (
	"math/big"
	"testing"
)

func TestLLTest(t *testing.T) {
	knownPrimes := []uint{3, 5, 7, 13, 17, 19, 31, 61, 89, 107, 127}
	knownComposites := []uint{11, 23, 29, 37, 41, 43, 47, 53, 59, 67, 71, 73, 79, 97, 101, 103, 109, 113}

	testCases := []struct {
		name     string
		input    []uint
		expected []uint
	}{
		{
			name:     "Known Primes",
			input:    knownPrimes,
			expected: knownPrimes,
		},
		{
			name:     "Known Composites",
			input:    knownComposites,
			expected: []uint{}, // No Mersenne primes expected from these inputs
		},
		{
			name:     "Empty Input",
			input:    []uint{},
			expected: []uint{},
		},
		{
			name:     "Mixed Input",
			input:    append(knownPrimes, knownComposites...),
			expected: knownPrimes,
		},


	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			results := []uint{}
			var s, m big.Int
			one := big.NewInt(1)
			two := big.NewInt(2)

			for _, p := range tc.input {
				m.Sub(m.Lsh(one, p), one)
				s.SetInt64(4)
				for i := uint(2); i < p; i++ {
					s.Mod(s.Sub(s.Mul(&s, &s), two), &m)
				}
				if s.BitLen() == 0 {
					results = append(results, p)
				}
			}

			if !slicesEqual(results, tc.expected) {
                t.Errorf("For input %v, expected %v but got %v", tc.input, tc.expected, results)
            }

		})
	}


}
func slicesEqual(a, b []uint) bool {
    if len(a) != len(b) {
        return false
    }
    for i, v := range a {
        if v != b[i] {
            return false
        }
    }
    return true
}




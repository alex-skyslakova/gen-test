package main

import (
	"math/big"
	"testing"
)

func TestLeftFactorial(t *testing.T) {
	testCases := []struct {
		n    int64
		want *big.Int
	}{
		{0, big.NewInt(0)},
		{1, big.NewInt(1)},
		{2, big.NewInt(2)},
		{3, big.NewInt(4)},
		{4, big.NewInt(10)},
		{5, big.NewInt(34)},
		{10, big.NewInt(409114)},
		{20, big.NewInt(25852016738884976640000)},
		{110, big.NewInt(0).SetBytes([]byte{
			0x27, 0x42, 0x69, 0x92, 0x19, 0x75, 0x2c, 0x0a, 0x10, 0x39, 0xb1, 0xb0, 0x49, 0x21, 0x72, 0x8e,
			0x3b, 0x2a, 0xed, 0x89, 0x41, 0x20, 0x4a, 0x79, 0x9e, 0x35, 0xd0, 0x4f, 0x29, 0x91, 0x15, 0x25,
			0x77, 0x03, 0x85, 0x93, 0x58, 0xd8, 0x06, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
		})},
	}

	for _, tc := range testCases {
		t.Run(string(rune(tc.n)), func(t *testing.T) {
			got := leftFactorial(tc.n)
			if got.Cmp(tc.want) != 0 {
				t.Errorf("leftFactorial(%d) = %v, want %v", tc.n, got, tc.want)
			}
		})
	}
}


func TestLeftFactorialLength(t *testing.T) {
	testCases := []struct {
		n    int64
		want int
	}{
		{1000, 2568},
		{2000, 5134},
		{3000, 7701},
		{4000, 10267},
		{5000, 12834},
		{6000, 15400},
		{7000, 17967},
		{8000, 20533},
		{9000, 23100},
		{10000, 25666},
	}

	for _, tc := range testCases {
		t.Run(string(rune(tc.n)), func(t *testing.T) {
			got := len(leftFactorial(tc.n).String())
			if got != tc.want {
				t.Errorf("len(leftFactorial(%d)) = %d, want %d", tc.n, got, tc.want)
			}
		})
	}
}

func leftFactorial(n int64) *big.Int {
	one := big.NewInt(1)
	f := big.NewInt(1)
	l := big.NewInt(0) 
	for k := int64(0); k < n; k++ {
		l.Add(l, f)
		f.Mul(f, big.NewInt(k+1))
	}
	return l
}


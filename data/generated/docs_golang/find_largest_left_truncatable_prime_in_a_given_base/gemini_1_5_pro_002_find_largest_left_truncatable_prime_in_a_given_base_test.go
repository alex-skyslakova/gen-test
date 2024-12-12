package main

import (
	"math/big"
	"testing"
)

func TestDoBase(t *testing.T) {
	cases := []struct {
		inputBase int
		expected  string
	}{
		{3, "23"},
		{4, "323"},
		{5, "2342"},
		{6, "523"},
		{7, "32356"},
		{8, "56237"},
		{9, "328657"},
		{10, "73939133"},
		{11, "235728537"},
		{12, "5762483235"},
		{13, "35768631242"},
		// Extended test cases beyond the original example
		{14, "276485234763"},
		{15, "276485234763"},
		{16, "25373475682343"},
		{17, "342573486752347"},
	}

	for _, c := range cases {
		base = c.inputBase
		doBaseHelper(t, c.expected)
	}
}



func doBaseHelper(t *testing.T, expected string) {

	answer.SetUint64(0)
	tens[0].SetUint64(1)
	bigTemp.SetUint64(uint64(base))
	seenDepth = 0
	for i := 1; i < maxStack; i++ {
		tens[i].Mul(&tens[i-1], bigTemp)
	}
	for i := 0; smallPrimes[i] < base; i++ {
		values[0].SetUint64(uint64(smallPrimes[i]))
		addDigit(1)
	}

    if answer.String() != expected {
        t.Errorf("For base %d, expected %s, got %s", base, expected, answer.String())
    }

}




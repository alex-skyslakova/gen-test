package main

import "testing"

func TestPfacSum(t *testing.T) {
	testCases := []struct {
		input int
		want  int
	}{
		{1, 0},
		{2, 1},
		{4, 3},
		{6, 6},
		{10, 8},
		{12, 16},
		{28, 28},
		{220, 284},
		{284, 220},
	}

	for _, tc := range testCases {
		got := pfacSum(tc.input)
		if got != tc.want {
			t.Errorf("pfacSum(%d) = %d; want %d", tc.input, got, tc.want)
		}
	}
}


func TestAmicablePairs(t *testing.T) {
	expectedPairs := [][]int{
		{220, 284},
		{1184, 1210},
		{2620, 2924},
		{5020, 5564},
		{6232, 6368},
		{10744, 10856},
		{12285, 14595},
		{17296, 18416},
	}

	var foundPairs [][]int

	var a [20000]int
	for i := 1; i < 20000; i++ {
		a[i] = pfacSum(i)
	}

	for n := 2; n < 19999; n++ {
		m := a[n]
		if m > n && m < 20000 && n == a[m] {
			foundPairs = append(foundPairs, []int{n, m})
		}
	}

	if len(foundPairs) != len(expectedPairs) {
		t.Errorf("Expected %d amicable pairs, but found %d", len(expectedPairs), len(foundPairs))
	}

	for _, expectedPair := range expectedPairs {
		found := false
		for _, foundPair := range foundPairs {
			if (foundPair[0] == expectedPair[0] && foundPair[1] == expectedPair[1]) || (foundPair[0] == expectedPair[1] && foundPair[1] == expectedPair[0]) {
				found = true
				break
			}
		}
		if !found {
			t.Errorf("Missing amicable pair: %v", expectedPair)
		}
	}

}


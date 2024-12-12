package main

import (
	"sort"
	"testing"
)

func TestContains(t *testing.T) {
	testCases := []struct {
		arr    []int
		val    int
		expect bool
	}{
		{[]int{1, 2, 3}, 2, true},
		{[]int{1, 2, 3}, 4, false},
		{[]int{}, 1, false},
	}

	for _, tc := range testCases {
		result := contains(tc.arr, tc.val)
		if result != tc.expect {
			t.Errorf("contains(%v, %d) = %t; want %t", tc.arr, tc.val, result, tc.expect)
		}
	}
}

func TestGCD(t *testing.T) {
	testCases := []struct {
		a, b int
		want int
	}{
		{10, 5, 5},
		{12, 18, 6},
		{7, 13, 1},
	}
	for _, tc := range testCases {
		got := gcd(tc.a, tc.b)
		if got != tc.want {
			t.Errorf("gcd(%d, %d) = %d; want %d", tc.a, tc.b, got, tc.want)
		}
	}
}

func TestAreSame(t *testing.T) {
	testCases := []struct {
		s, t   []int
		expect bool
	}{
		{[]int{1, 2, 3}, []int{3, 2, 1}, true},
		{[]int{1, 2, 3}, []int{1, 2, 4}, false},
		{[]int{1, 2, 3}, []int{1, 2}, false},
	}

	for _, tc := range testCases {
		result := areSame(tc.s, tc.t)
		if result != tc.expect {
			t.Errorf("areSame(%v, %v) = %t; want %t", tc.s, tc.t, result, tc.expect)
		}
	}
}

func TestEKGGeneration(t *testing.T) {
	testCases := []struct {
		start int
		want  []int
	}{
		{2, []int{1, 2, 4, 6, 3, 9, 12, 8, 10, 5}},
        {5, []int{1, 5, 10, 2, 4, 6, 3, 9, 12, 8}},
        {7, []int{1, 7, 14, 2, 4, 6, 3, 9, 12, 8}},
        {9, []int{1, 9, 3, 6, 12, 4, 8, 2, 10, 5}},
        {10, []int{1, 10, 2, 4, 6, 3, 9, 12, 8, 5}},

	}

	const limit = 10

	for _, tc := range testCases {
		var ekg [limit]int
		ekg[0] = 1
		ekg[1] = tc.start
		for n := 2; n < limit; n++ {
			for i := 2; ; i++ {
				if !contains(ekg[:n], i) && gcd(ekg[n-1], i) > 1 {
					ekg[n] = i
					break
				}
			}
		}

        if !areSame(ekg[:limit], tc.want) {
			t.Errorf("EKG(%d) = %v; want %v", tc.start, ekg[:limit], tc.want)
		}
	}
}


func TestConvergence(t *testing.T) {
    const limit = 100
    start1 := 5
    start2 := 7


    var ekg1 [limit]int
    ekg1[0] = 1
    ekg1[1] = start1
    for n := 2; n < limit; n++ {
        for i := 2; ; i++ {
            if !contains(ekg1[:n], i) && gcd(ekg1[n-1], i) > 1 {
                ekg1[n] = i
                break
            }
        }
    }


    var ekg2 [limit]int
    ekg2[0] = 1
    ekg2[1] = start2
    for n := 2; n < limit; n++ {
        for i := 2; ; i++ {
            if !contains(ekg2[:n], i) && gcd(ekg2[n-1], i) > 1 {
                ekg2[n] = i
                break
            }
        }
    }

    convergencePoint := -1
    for i := 2; i < limit; i++ {
        if ekg1[i] == ekg2[i] && areSame(ekg1[:i], ekg2[:i]) {
            convergencePoint = i+1
            break
        }
    }

    if convergencePoint != 11 {
        t.Errorf("EKG(5) and EKG(7) converge at %d; want 11", convergencePoint)
    }
}


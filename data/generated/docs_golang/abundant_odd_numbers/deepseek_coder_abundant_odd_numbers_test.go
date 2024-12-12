package main

import (
    "testing"
)

func TestDivisors(t *testing.T) {
    tests := []struct {
        n      int
        expect []int
    }{
        {1, []int{1}},
        {12, []int{1, 2, 3, 4, 6}},
        {15, []int{1, 3, 5}},
        {28, []int{1, 2, 4, 7, 14}},
    }

    for _, test := range tests {
        result := divisors(test.n)
        if !sliceEqual(result, test.expect) {
            t.Errorf("divisors(%d) = %v; want %v", test.n, result, test.expect)
        }
    }
}

func TestSum(t *testing.T) {
    tests := []struct {
        divs   []int
        expect int
    }{
        {[]int{1}, 1},
        {[]int{1, 2, 3, 4, 6}, 16},
        {[]int{1, 3, 5}, 9},
        {[]int{1, 2, 4, 7, 14}, 28},
    }

    for _, test := range tests {
        result := sum(test.divs)
        if result != test.expect {
            t.Errorf("sum(%v) = %d; want %d", test.divs, result, test.expect)
        }
    }
}

func TestSumStr(t *testing.T) {
    tests := []struct {
        divs   []int
        expect string
    }{
        {[]int{1}, "1"},
        {[]int{1, 2, 3, 4, 6}, "1 + 2 + 3 + 4 + 6"},
        {[]int{1, 3, 5}, "1 + 3 + 5"},
        {[]int{1, 2, 4, 7, 14}, "1 + 2 + 4 + 7 + 14"},
    }

    for _, test := range tests {
        result := sumStr(test.divs)
        if result != test.expect {
            t.Errorf("sumStr(%v) = %s; want %s", test.divs, result, test.expect)
        }
    }
}

func TestAbundantOdd(t *testing.T) {
    tests := []struct {
        searchFrom int
        countFrom  int
        countTo    int
        printOne   bool
        expect     int
    }{
        {1, 0, 1, false, 945},
        {1, 0, 25, false, 957},
        {1, 25, 1000, true, 492975},
        {1e9 + 1, 0, 1, true, 1000000575},
    }

    for _, test := range tests {
        result := abundantOdd(test.searchFrom, test.countFrom, test.countTo, test.printOne)
        if result != test.expect {
            t.Errorf("abundantOdd(%d, %d, %d, %v) = %d; want %d", test.searchFrom, test.countFrom, test.countTo, test.printOne, result, test.expect)
        }
    }
}

func sliceEqual(a, b []int) bool {
    if len(a) != len(b) {
        return false
    }
    for i := range a {
        if a[i] != b[i] {
            return false
        }
    }
    return true
}

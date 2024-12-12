package main

import (
    "testing"
)

func TestIabs(t *testing.T) {
    tests := []struct {
        input    int
        expected int
    }{
        {0, 0},
        {1, 1},
        {-1, 1},
        {10, 10},
        {-10, 10},
    }

    for _, test := range tests {
        result := iabs(test.input)
        if result != test.expected {
            t.Errorf("iabs(%d) = %d; expected %d", test.input, result, test.expected)
        }
    }
}

func TestTermMul(t *testing.T) {
    tests := []struct {
        t1       term
        t2       term
        expected term
    }{
        {term{1, 1}, term{1, 1}, term{1, 2}},
        {term{2, 3}, term{3, 4}, term{6, 7}},
        {term{-1, 2}, term{1, 3}, term{-1, 5}},
    }

    for _, test := range tests {
        result := test.t1.mul(test.t2)
        if result != test.expected {
            t.Errorf("(%v).mul(%v) = %v; expected %v", test.t1, test.t2, result, test.expected)
        }
    }
}

func TestTermAdd(t *testing.T) {
    tests := []struct {
        t1       term
        t2       term
        expected term
    }{
        {term{1, 1}, term{1, 1}, term{2, 1}},
        {term{2, 3}, term{3, 3}, term{5, 3}},
        {term{-1, 2}, term{1, 2}, term{0, 2}},
    }

    for _, test := range tests {
        result := test.t1.add(test.t2)
        if result != test.expected {
            t.Errorf("(%v).add(%v) = %v; expected %v", test.t1, test.t2, result, test.expected)
        }
    }
}

func TestTermNegate(t *testing.T) {
    tests := []struct {
        input    term
        expected term
    }{
        {term{1, 1}, term{-1, 1}},
        {term{-2, 3}, term{2, 3}},
        {term{0, 2}, term{0, 2}},
    }

    for _, test := range tests {
        result := test.input.negate()
        if result != test.expected {
            t.Errorf("(%v).negate() = %v; expected %v", test.input, result, test.expected)
        }
    }
}

func TestNewPoly(t *testing.T) {
    tests := []struct {
        values   []int
        expected poly
    }{
        {[]int{}, poly{[]term{term{0, 0}}}},
        {[]int{1, 1}, poly{[]term{term{1, 1}}}},
        {[]int{1, 1, 2, 2}, poly{[]term{term{1, 1}, term{2, 2}}}},
    }

    for _, test := range tests {
        result := newPoly(test.values...)
        if len(result.terms) != len(test.expected.terms) {
            t.Errorf("newPoly(%v) = %v; expected %v", test.values, result, test.expected)
        } else {
            for i := range result.terms {
                if result.terms[i] != test.expected.terms[i] {
                    t.Errorf("newPoly(%v) = %v; expected %v", test.values, result, test.expected)
                    break
                }
            }
        }
    }
}

func TestPolyHasCoefAbs(t *testing.T) {
    tests := []struct {
        p        poly
        coef     int
        expected bool
    }{
        {poly{[]term{term{1, 1}}}, 1, true},
        {poly{[]term{term{1, 1}, term{-1, 2}}}, 1, true},
        {poly{[]term{term{2, 1}, term{-2, 2}}}, 2, true},
        {poly{[]term{term{2, 1}, term{-2, 2}}}, 3, false},
    }

    for _, test := range tests {
        result := test.p.hasCoefAbs(test.coef)
        if result != test.expected {
            t.Errorf("(%v).hasCoefAbs(%d) = %v; expected %v", test.p, test.coef, result, test.expected)
        }
    }
}

func TestPolyAdd(t *testing.T) {
    tests := []struct {
        p1       poly
        p2       poly
        expected poly
    }{
        {poly{[]term{term{1, 1}}}, poly{[]term{term{1, 1}}}, poly{[]term{term{2, 1}}}},
        {poly{[]term{term{1, 1}, term{2, 2}}}, poly{[]term{term{1, 1}, term{3, 3}}}, poly{[]term{term{2, 1}, term{2, 2}, term{3, 3}}}},
        {poly{[]term{term{1, 1}, term{-1, 1}}}, poly{[]term{term{1, 1}, term{3, 3}}}, poly{[]term{term{3, 3}}}},
    }

    for _, test := range tests {
        result := test.p1.add(test.p2)
        if len(result.terms) != len(test.expected.terms) {
            t.Errorf("(%v).add(%v) = %v; expected %v", test.p1, test.p2, result, test.expected)
        } else {
            for i := range result.terms {
                if result.terms[i] != test.expected.terms[i] {
                    t.Errorf("(%v).add(%v) = %v; expected %v", test.p1, test.p2, result, test.expected)
                    break
                }
            }
        }
    }
}

func TestPolyAddTerm(t *testing.T) {
    tests := []struct {
        p        poly
        term     term
        expected poly
    }{
        {poly{[]term{term{1, 1}}}, term{1, 1}, poly{[]term{term{2, 1}}}},
        {poly{[]term{term{1, 1}, term{2, 2}}}, term{1, 1}, poly{[]term{term{2, 1}, term{2, 2}}}},
        {poly{[]term{term{1, 1}, term{2, 2}}}, term{-1, 1}, poly{[]term{term{2, 2}}}},
    }

    for _, test := range tests {
        result := test.p.addTerm(test.term)
        if len(result.terms) != len(test.expected.terms) {
            t.Errorf("(%v).addTerm(%v) = %v; expected %v", test.p, test.term, result, test.expected)
        } else {
            for i := range result.terms {
                if result.terms[i] != test.expected.terms[i] {
                    t.Errorf("(%v).addTerm(%v) = %v; expected %v", test.p, test.term, result, test.expected)
                    break
                }
            }
        }
    }
}

func TestPolyMulTerm(t *testing.T) {
    tests := []struct {
        p        poly
        term     term
        expected poly
    }{
        {poly{[]term{term{1, 1}}}, term{2, 1}, poly{[]term{term{2, 2}}}},
        {poly{[]term{term{1, 1}, term{2, 2}}}, term{2, 1}, poly{[]term{term{2, 2}, term{4, 3}}}},
        {poly{[]term{term{1, 1}, term{2, 2}}}, term{-1, 1}, poly{[]term{term{-1, 2}, term{-2, 3}}}},
    }

    for _, test := range tests {
        result := test.p.mulTerm(test.term)
        if len(result.terms) != len(test.expected.terms) {
            t.Errorf("(%v).mulTerm(%v) = %v; expected %v", test.p, test.term, result, test.expected)
        } else {
            for i := range result.terms {
                if result.terms[i] != test.expected.terms[i] {
                    t.Errorf("(%v).mulTerm(%v) = %v; expected %v", test.p, test.term, result, test.expected)
                    break
                }
            }
        }
    }
}

func TestPolyDiv(t *testing.T) {
    tests := []struct {
        p1       poly
        p2       poly
        expected poly
    }{
        {poly{[]term{term{1, 1}, term{-1, 0}}}, poly{[]term{term{1, 1}, term{-1, 0}}}, poly{[]term{term{1, 0}}}},
        {poly{[]term{term{1, 2}, term{-1, 0}}}, poly{[]term{term{1, 1}, term{-1, 0}}}, poly{[]term{term{1, 1}}}},
    }

    for _, test := range tests {
        result := test.p1.div(test.p2)
        if len(result.terms) != len(test.expected.terms) {
            t.Errorf("(%v).div(%v) = %v; expected %v", test.p1, test.p2, result, test.expected)
        } else {
            for i := range result.terms {
                if result.terms[i] != test.expected.terms[i] {
                    t.Errorf("(%v).div(%v) = %v; expected %v", test.p1, test.p2, result, test.expected)
                    break
                }
            }
        }
    }
}

func TestPolyLeadingCoef(t *testing.T) {
    tests := []struct {
        p        poly
        expected int
    }{
        {poly{[]term{term{1, 1}}}, 1},
        {poly{[]term{term{2, 2}, term{1, 1}}}, 2},
        {poly{[]term{term{-1, 1}, term{2, 2}}}, -1},
    }

    for _, test := range tests {
        result := test.p.leadingCoef()
        if result != test.expected {
            t.Errorf("(%v).leadingCoef() = %d; expected %d", test.p, result, test.expected)
        }
    }
}

func TestPolyDegree(t *testing.T) {
    tests := []struct {
        p        poly
        expected int
    }{
        {poly{[]term{term{1, 1}}}, 1},
        {poly{[]term{term{2, 2}, term{1, 1}}}, 2},
        {poly{[]term{term{-1, 1}, term{2, 2}}}, 2},
    }

    for _, test := range tests {
        result := test.p.degree()
        if result != test.expected {
            t.Errorf("(%v).degree() = %d; expected %d", test.p, result, test.expected)
        }
    }
}

func TestPolyTidy(t *testing.T) {
    tests := []struct {
        p        poly
        expected poly
    }{
        {poly{[]term{term{1, 1}, term{0, 2}}}, poly{[]term{term{1, 1}}}},
        {poly{[]term{term{1, 1}, term{2, 2}, term{0, 3}}}, poly{[]term{term{1, 1}, term{2, 2}}}},
        {poly{[]term{term{0, 1}, term{0, 2}}}, poly{[]term{term{0, 0}}}},
    }

    for _, test := range tests {
        result := test.p.tidy()
        if len(result.terms) != len(test.expected.terms) {
            t.Errorf("(%v).tidy() = %v; expected %v", test.p, result, test.expected)
        } else {
            for i := range result.terms {
                if result.terms[i] != test.expected.terms[i] {
                    t.Errorf("(%v).tidy() = %v; expected %v", test.p, result, test.expected)
                    break
                }
            }
        }
    }
}

func TestGetDivisors(t *testing.T) {
    tests := []struct {
        n        int
        expected []int
    }{
        {1, []int{1}},
        {2, []int{1, 2}},
        {3, []int{1, 3}},
        {4, []int{1, 2, 4}},
        {6, []int{1, 2, 3, 6}},
    }

    for _, test := range tests {
        result := getDivisors(test.n)
        if len(result) != len(test.expected) {
            t.Errorf("getDivisors(%d) = %v; expected %v", test.n, result, test.expected)
        } else {
            for i := range result {
                if result[i] != test.expected[i] {
                    t.Errorf("getDivisors(%d) = %v; expected %v", test.n, result, test.expected)
                    break
                }
            }
        }
    }
}

func TestGetFactors(t *testing.T) {
    tests := []struct {
        n        int
        expected map[int]int
    }{
        {1, map[int]int{}},
        {2, map[int]int{2: 1}},
        {3, map[int]int{3: 1}},
        {4, map[int]int{2: 2}},
        {6, map[int]int{2: 1, 3: 1}},
    }

    for _, test := range tests {
        result := getFactors(test.n)
        if len(result) != len(test.expected) {
            t.Errorf("getFactors(%d) = %v; expected %v", test.n, result, test.expected)
        } else {
            for k, v := range result {
                if v != test.expected[k] {
                    t.Errorf("getFactors(%d) = %v; expected %v", test.n, result, test.expected)
                    break
                }
            }
        }
    }
}

func TestCycloPoly(t *testing.T) {
    tests := []struct {
        n        int
        expected poly
    }{
        {1, poly{[]term{term{1, 1}, term{-1, 0}}}},
        {2, poly{[]term{term{1, 1}, term{1, 0}}}},
        {3, poly{[]term{term{1, 2}, term{1, 1}, term{1, 0}}}},
        {4, poly{[]term{term{1, 2}, term{1, 0}}}},
    }

    for _, test := range tests {
        result := cycloPoly(test.n)
        if len(result.terms) != len(test.expected.terms) {
            t.Errorf("cycloPoly(%d) = %v; expected %v", test.n, result, test.expected)
        } else {
            for i := range result.terms {
                if result.terms[i] != test.expected.terms[i] {
                    t.Errorf("cycloPoly(%d) = %v; expected %v", test.n, result, test.expected)
                    break
                }
            }
        }
    }
}

package main

import (
	"fmt"
	"testing"
)

func TestDinesmansMultipleDwellingProblem(t *testing.T) {
	// Backup original values
	originalTenants := tenants
	originalBottom := bottom
	originalTop := top
	originalRules := rules

	defer func() {
		tenants = originalTenants
		bottom = originalBottom
		top = originalTop
		rules = originalRules
	}()

	// Test case 1: Original problem
	tenants = []string{"Baker", "Cooper", "Fletcher", "Miller", "Smith"}
	bottom = 1
	top = 5
	rules = []func(assignments) bool{
		func(a assignments) bool {
			floor, assigned := a["Baker"]
			return !assigned || floor != top
		},
		func(a assignments) bool {
			floor, assigned := a["Cooper"]
			return !assigned || floor != bottom
		},
		func(a assignments) bool {
			floor, assigned := a["Fletcher"]
			return !assigned || (floor != top && floor != bottom)
		},
		func(a assignments) bool {
			if m, assigned := a["Miller"]; assigned {
				c, assigned := a["Cooper"]
				return !assigned || m > c
			}
			return true
		},
		func(a assignments) bool {
			if s, assigned := a["Smith"]; assigned {
				if f, assigned := a["Fletcher"]; assigned {
					d := s - f
					return d*d > 1
				}
			}
			return true
		},
		func(a assignments) bool {
			if f, assigned := a["Fletcher"]; assigned {
				if c, assigned := a["Cooper"]; assigned {
					d := f - c
					return d*d > 1
				}
			}
			return true
		},
	}

	expectedSolution := assignments{"Baker": 3, "Cooper": 2, "Fletcher": 4, "Miller": 5, "Smith": 1}
	actualSolution := solve()

    if !compareSolutions(expectedSolution, actualSolution) {
        t.Errorf("Test case 1 failed. Expected: %v, Got: %v", expectedSolution, actualSolution)
    }


	// Test case 2: No solution
	rules = append(rules, func(a assignments) bool { return false })
	if solve() != nil {
		t.Error("Test case 2 failed. Expected no solution.")
	}

	// Test case 3: Different tenants and floors
	tenants = []string{"A", "B", "C"}
	bottom = 10
	top = 12
	rules = []func(assignments) bool{
		func(a assignments) bool {
			floor, assigned := a["A"]
			return !assigned || floor != bottom
		},
		func(a assignments) bool {
			floor, assigned := a["B"]
			return !assigned || floor != top
		},
	}

    expectedSolution = assignments{"A": 11, "B": 12, "C": 10}
	actualSolution = solve()
	if !compareSolutions(expectedSolution, actualSolution) {
        t.Errorf("Test case 3 failed. Expected: %v, Got: %v", expectedSolution, actualSolution)
    }

}

// Helper function to solve the problem and return the solution
func solve() assignments {
	a := assignments{}
	var occ [top + 1]bool
	var df func([]string) bool
	df = func(u []string) bool {
		// ... (same as original df function)
        // This implementation is directly copied from original code.
        if len(u) == 0 {
			return true
		}
		tn := u[0]
		u = u[1:]
	f:
		for f := bottom; f <= top; f++ {
			if !occ[f] {
				a[tn] = f
				for _, r := range rules {
					if !r(a) {
						delete(a, tn)
						continue f
					}
				}
				occ[f] = true
				if df(u) {
					return true
				}
				occ[f] = false
				delete(a, tn)
			}
		}
		return false
	}

	if !df(tenants) {
		return nil
	}
	return a
}


func compareSolutions(expected, actual assignments) bool {
    if expected == nil && actual == nil {
        return true
    }
    if expected == nil || actual == nil {
        return false
    }
    if len(expected) != len(actual) {
        return false
    }
    for k, v := range expected {
        if actual[k] != v {
            return false
        }
    }
    return true
}



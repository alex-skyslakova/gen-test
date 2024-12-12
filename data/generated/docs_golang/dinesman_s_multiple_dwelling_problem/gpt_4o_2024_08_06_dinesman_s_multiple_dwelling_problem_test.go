package main

import (
	"testing"
)

// Test the rule that Baker does not live on the top floor
func TestBakerNotTopFloor(t *testing.T) {
	a := assignments{"Baker": top}
	if rules[0](a) {
		t.Errorf("Baker should not live on the top floor")
	}
	a = assignments{"Baker": top - 1}
	if !rules[0](a) {
		t.Errorf("Baker can live on floor %d", top-1)
	}
}

// Test the rule that Cooper does not live on the bottom floor
func TestCooperNotBottomFloor(t *testing.T) {
	a := assignments{"Cooper": bottom}
	if rules[1](a) {
		t.Errorf("Cooper should not live on the bottom floor")
	}
	a = assignments{"Cooper": bottom + 1}
	if !rules[1](a) {
		t.Errorf("Cooper can live on floor %d", bottom+1)
	}
}

// Test the rule that Fletcher does not live on either the top or the bottom floor
func TestFletcherNotTopOrBottomFloor(t *testing.T) {
	a := assignments{"Fletcher": top}
	if rules[2](a) {
		t.Errorf("Fletcher should not live on the top floor")
	}
	a = assignments{"Fletcher": bottom}
	if rules[2](a) {
		t.Errorf("Fletcher should not live on the bottom floor")
	}
	a = assignments{"Fletcher": bottom + 1}
	if !rules[2](a) {
		t.Errorf("Fletcher can live on floor %d", bottom+1)
	}
}

// Test the rule that Miller lives on a higher floor than does Cooper
func TestMillerHigherThanCooper(t *testing.T) {
	a := assignments{"Miller": 3, "Cooper": 4}
	if rules[3](a) {
		t.Errorf("Miller should live on a higher floor than Cooper")
	}
	a = assignments{"Miller": 5, "Cooper": 4}
	if !rules[3](a) {
		t.Errorf("Miller can live on a higher floor than Cooper")
	}
}

// Test the rule that Smith does not live on a floor adjacent to Fletcher's
func TestSmithNotAdjacentToFletcher(t *testing.T) {
	a := assignments{"Smith": 3, "Fletcher": 4}
	if rules[4](a) {
		t.Errorf("Smith should not live on a floor adjacent to Fletcher's")
	}
	a = assignments{"Smith": 3, "Fletcher": 5}
	if !rules[4](a) {
		t.Errorf("Smith can live on floor %d when Fletcher is on floor %d", 3, 5)
	}
}

// Test the rule that Fletcher does not live on a floor adjacent to Cooper's
func TestFletcherNotAdjacentToCooper(t *testing.T) {
	a := assignments{"Fletcher": 2, "Cooper": 3}
	if rules[5](a) {
		t.Errorf("Fletcher should not live on a floor adjacent to Cooper's")
	}
	a = assignments{"Fletcher": 2, "Cooper": 4}
	if !rules[5](a) {
		t.Errorf("Fletcher can live on floor %d when Cooper is on floor %d", 2, 4)
	}
}

// Test the overall solution
func TestSolution(t *testing.T) {
	a := assignments{}
	var occ [top + 1]bool
	var df func([]string) bool
	df = func(u []string) bool {
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
		t.Fatalf("no solution found")
	}

	expected := map[string]int{
		"Baker":    3,
		"Cooper":   2,
		"Fletcher": 4,
		"Miller":   5,
		"Smith":    1,
	}

	for tenant, floor := range expected {
		if a[tenant] != floor {
			t.Errorf("Expected %s to live on floor %d, but got %d", tenant, floor, a[tenant])
		}
	}
}

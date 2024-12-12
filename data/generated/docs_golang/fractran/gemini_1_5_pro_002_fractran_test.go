import (
	"math/big"
	"strings"
	"testing"
)

func TestCompile(t *testing.T) {
	tests := []struct {
		input    string
		expected []big.Rat
		ok       bool
	}{
		{"17/91 78/85", []big.Rat{*big.NewRat(17, 91), *big.NewRat(78, 85)}, true},
		{"1/2 3/4 5/6", []big.Rat{*big.NewRat(1, 2), *big.NewRat(3, 4), *big.NewRat(5, 6)}, true},
		{"1/2 invalid/fraction", nil, false},
		{"", []big.Rat{}, true},
		{"1/0", nil, false}, // Test division by zero in SetString
	}

	for _, test := range tests {
		result, ok := compile(test.input)
		if ok != test.ok {
			t.Errorf("compile(%q) returned ok=%t, want %t", test.input, ok, test.ok)
		}
		if ok && !equalRatSlices(result, test.expected) {
			t.Errorf("compile(%q) returned %v, want %v", test.input, result, test.expected)
		}

	}
}

func equalRatSlices(a, b []big.Rat) bool {
	if len(a) != len(b) {
		return false
	}
	for i := range a {
		if a[i].Cmp(&b[i]) != 0 {
			return false
		}
	}
	return true
}

func TestExec(t *testing.T) {
	// Capturing standard output is complex and might not be essential for this specific test.
	// Instead, we can focus on the core logic by checking the final state of 'n'
	// after a limited number of steps.


	// Example based on Conway's prime program (first few steps):
	program := []big.Rat{
		*big.NewRat(17, 91),
		*big.NewRat(78, 85),
		*big.NewRat(19, 51),
		*big.NewRat(23, 38),
		*big.NewRat(29, 33),
		*big.NewRat(77, 29),
		*big.NewRat(95, 23),
		*big.NewRat(77, 19),
		*big.NewRat(1, 17),
		*big.NewRat(11, 13),
		*big.NewRat(13, 11),
		*big.NewRat(15, 14),
		*big.NewRat(15, 2),
		*big.NewRat(55, 1),
	}

	n := big.NewInt(2)
	limit := 2

	exec(program, n, limit)  // Execute for 2 steps
	want := big.NewInt(825) // Expected value after 2 steps (2->15->825)
	if n.Cmp(want) != 0 {
		t.Errorf("After 2 steps, n=%v, want %v", n, want)
	}



	// Another example (no fractions apply):
	program2 := []big.Rat{*big.NewRat(2, 3), *big.NewRat(3, 5)}
	n2 := big.NewInt(7)
	limit2 := 5

	exec(program2, n2, limit2)
	want2 := big.NewInt(7)
	if n2.Cmp(want2) != 0 {
		t.Errorf("n should remain unchanged, but got %v", n2)
	}



	// Test with empty program
	program3 := []big.Rat{}
	n3 := big.NewInt(10)
	limit3 := 5

	exec(program3, n3, limit3)
	want3 := big.NewInt(10)
	if n3.Cmp(want3) != 0 {
		t.Errorf("n should remain unchanged, but got %v", n3)
	}

}


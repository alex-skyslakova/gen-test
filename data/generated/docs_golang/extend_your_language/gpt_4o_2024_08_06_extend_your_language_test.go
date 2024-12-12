package main

import (
	"bytes"
	"fmt"
	"testing"
)

type F func()

type If2 struct {
	cond1, cond2 bool
}

func (i If2) else1(f F) If2 {
	if i.cond1 && !i.cond2 {
		f()
	}
	return i
}

func (i If2) else2(f F) If2 {
	if i.cond2 && !i.cond1 {
		f()
	}
	return i
}

func (i If2) else0(f F) If2 {
	if !i.cond1 && !i.cond2 {
		f()
	}
	return i
}

func if2(cond1, cond2 bool, f F) If2 {
	if cond1 && cond2 {
		f()
	}
	return If2{cond1, cond2}
}

func TestIf2(t *testing.T) {
	tests := []struct {
		cond1, cond2 bool
		expected     string
	}{
		{true, true, "both conditions are true\n"},
		{true, false, "first condition is true\n"},
		{false, true, "second condition is true\n"},
		{false, false, "no condition is true\n"},
	}

	for _, test := range tests {
		var buf bytes.Buffer
		fmtFunc := func(msg string) F {
			return func() {
				fmt.Fprintln(&buf, msg)
			}
		}

		if2(test.cond1, test.cond2, fmtFunc("both conditions are true")).
			else1(fmtFunc("first condition is true")).
			else2(fmtFunc("second condition is true")).
			else0(fmtFunc("no condition is true"))

		if buf.String() != test.expected {
			t.Errorf("for cond1=%v, cond2=%v, expected %q, got %q", test.cond1, test.cond2, test.expected, buf.String())
		}
	}
}

func TestIf2OmittedElseClauses(t *testing.T) {
	var buf bytes.Buffer
	fmtFunc := func(msg string) F {
		return func() {
			fmt.Fprintln(&buf, msg)
		}
	}

	// Test with omitted else2 and else0
	if2(true, false, fmtFunc("both conditions are true")).
		else1(fmtFunc("first condition is true"))

	expected := "first condition is true\n"
	if buf.String() != expected {
		t.Errorf("expected %q, got %q", expected, buf.String())
	}
}

func TestIf2OutOfOrderElseClauses(t *testing.T) {
	var buf bytes.Buffer
	fmtFunc := func(msg string) F {
		return func() {
			fmt.Fprintln(&buf, msg)
		}
	}

	// Test with out of order else clauses
	if2(false, false, fmtFunc("both conditions are true")).
		else0(fmtFunc("no condition is true")).
		else1(fmtFunc("first condition is true"))

	expected := "no condition is true\n"
	if buf.String() != expected {
		t.Errorf("expected %q, got %q", expected, buf.String())
	}
}

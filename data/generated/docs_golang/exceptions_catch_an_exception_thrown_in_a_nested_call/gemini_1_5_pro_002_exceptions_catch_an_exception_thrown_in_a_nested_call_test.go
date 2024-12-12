package main

import (
	"fmt"
	"runtime"
	"strings"
	"testing"
)

// Define mock functions for testing
type mockBazCall struct {
	callCount int
	panicOnCall int
	panicType string
}

func (m *mockBazCall) baz() {
	m.callCount++
	switch m.panicOnCall {
	case m.callCount:
		switch m.panicType {
		case "U0":
			panic(exception{"U0", func() {}})
		case "U1":
			panic(exception{"U1", func() {}})
		}
	}
}

func TestFoo(t *testing.T) {
	cs = make([]uintptr, 10) // Reset cs for consistent test output

	traceOrig := trace
	defer func() { trace = traceOrig }()

	var traceOutput []string
	trace = func(s string) {
		nc := runtime.Callers(2, cs)
		f := runtime.FuncForPC(cs[0])
		traceOutput = append(traceOutput, fmt.Sprintf("%s: %s", strings.Repeat("  ", nc-3)+f.Name()[5:], s))
	}

    //Test with U0 raised by baz on first call
	mbc := &mockBazCall{panicOnCall: 1, panicType: "U0"}
	baz = mbc.baz


	u0 = exception{"U0", func() {}}
	u1 = exception{"U1", func() {}}

	defer func() {
		if r := recover(); r != nil {
			t.Errorf("Unexpected panic: %v", r)
		}
	}()

	foo()

    expected := []string{
    "main: start",
    "  foo: start",
    "    try: start",
    "      bar: start",
    "        baz: start",
    "      try: Panic mode!",
    "      try: handling exception",
    "      try: panic over",
    "    try: complete",
    "    try: start",
    "      bar: start",
    "        baz: start",
    "      bar: complete",
    "    try: complete",
    "  foo: complete",
    "main: complete",
}
		if !slicesEqual(traceOutput, expected) {
			t.Errorf("Trace mismatch.\nGot:\n%s\nExpected:\n%s", strings.Join(traceOutput, "\n"), strings.Join(expected, "\n"))
		}


	//Test with U1 raised by baz on second call
	traceOutput = nil
	mbc.callCount = 0
	mbc.panicOnCall = 2
	mbc.panicType = "U1"



	defer func() {
		if r := recover(); r == nil {
			t.Errorf("Expected a panic but none occurred")
		}
	}()

	func() {
		defer func() {
			if r := recover(); r == nil {
				t.Error("Expected a panic")
			}

		}()
		foo()

	}()

}

func slicesEqual(a, b []string) bool {
	if len(a) != len(b) {
		return false
	}
	for i, v := range a {
		if v != b[i] {
			return false
		}
	}
	return true
}


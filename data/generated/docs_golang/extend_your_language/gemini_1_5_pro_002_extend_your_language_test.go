package main

import (
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
		a, b     int
		expected string
	}{
		{1, 3, "a = 1 and b = 3"},
		{1, 0, "a = 1 and b <> 3"},
		{0, 3, "a <> 1 and b = 3"},
		{0, 0, "a <> 1 and b <> 3"},
	}

	for _, tt := range tests {
		t.Run(fmt.Sprintf("a=%d, b=%d", tt.a, tt.b), func(t *testing.T) {
			var result string
			if2(tt.a == 1, tt.b == 3, func() {
				result = "a = 1 and b = 3"
			}).else1(func() {
				result = "a = 1 and b <> 3"
			}).else2(func() {
				result = "a <> 1 and b = 3"
			}).else0(func() {
				result = "a <> 1 and b <> 3"
			})

			if result != tt.expected {
				t.Errorf("expected %q, got %q", tt.expected, result)
			}
		})
	}

    t.Run("omit else clauses", func(t *testing.T) {
        var result string
        if2(true, true, func() {
            result = "both true"
        })
        if result != "both true" {
            t.Errorf("expected both true, got %s", result)
        }

        result = ""
        if2(false, false, func() {
            result = "both false"
        })
        if result != "" {
            t.Errorf("expected empty, got %s", result)
        }
    })

	t.Run("out of order", func(t *testing.T) {
		var result string
		if2(1 == 1, 0 == 3, func() {
			result = "a = 1 and b = 3"
		}).else0(func() {
			result = "a <> 1 and b <> 3"
		}).else1(func() {
			result = "a = 1 and b <> 3"
		})
        if result != "a = 1 and b <> 3" {
            t.Errorf("expected 'a = 1 and b <> 3', got %s", result)
        }
	})


}


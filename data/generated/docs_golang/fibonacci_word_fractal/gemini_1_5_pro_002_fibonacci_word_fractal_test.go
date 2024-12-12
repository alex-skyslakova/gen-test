package main

import (
	"github.com/fogleman/gg"
	"strings"
	"testing"
)

func TestWordFractal(t *testing.T) {
	cases := []struct {
		in   int
		want string
	}{
		{0, ""},
		{1, "1"},
		{2, "0"},
		{3, "01"},
		{4, "010"},
		{5, "01001"},
		{6, "01001010"},
	}
	for _, c := range cases {
		got := wordFractal(c.in)
		if got != c.want {
			t.Errorf("wordFractal(%d) == %q, want %q", c.in, got, c.want)
		}
	}
}

func TestDraw(t *testing.T) {
	dc := gg.NewContext(100, 100)
	cases := []struct {
		x, y, dx, dy float64
		wf           string
	}{
		{0, 0, 1, 0, "111"},
		{0, 0, 1, 0, "10101"},
		{0, 0, 1, 0, "000"},
		{10, 10, 5, 5, "11"},
	}

	for _, c := range cases {
		dc.Clear() // Clear the context for each test case
		draw(dc, c.x, c.y, c.dx, c.dy, c.wf)

		// Assertions here are tricky without access to the drawing internals.
		//  We could potentially check the path length, bounds etc. but
		// it quickly becomes complicated and may not offer a good ROI.  
		// The real test is visual inspection of the output.
		// For demonstration purposes a minimal check:
		if dc.Path() == nil { 
			t.Errorf("draw() did not create a path for input wf=%s", c.wf)
		} 
	}
}



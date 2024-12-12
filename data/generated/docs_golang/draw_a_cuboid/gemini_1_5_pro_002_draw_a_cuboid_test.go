package main

import (
	"bytes"
	"fmt"
	"strings"
	"testing"
)

func TestCuboid(t *testing.T) {
	testCases := []struct {
		dx, dy, dz int
		expected   string
	}{
		{2, 3, 4, `cuboid 2 3 4:
+-----------------+ |
/ |               | |
/ |               | |
/ |               | |
+-----------------+|
| |               | |
| |               | |
| |               | |
| |               | |
| |               | |
| |               | |
| |               | |
| |               | |
| |               | |
| |               | |
| |               | |
| +---------------+-
| /               |/
| /               |/
| /               |/
+-----------------+
`},
		{1, 1, 1, `cuboid 1 1 1:
+---------+ |
/ |       | |
+---------+|
| |       | |
| |       | |
| +-------+-
| /       |/
+---------+
`},
		{6, 2, 1, `cuboid 6 2 1:
+-----------------------------------------------+ |
/ |                                               | |
/ |                                               | |
+-----------------------------------------------+|
| |                                               | |
| |                                               | |
| +----------------------------------------------+-
| /                                               |/
| /                                               |/
+-----------------------------------------------+
`},
        // Add more test cases as needed, including edge cases
        {0, 0, 0, `cuboid 0 0 0:
++ |
++|
| +-
+
`},
		{0, 1, 0, `cuboid 0 1 0:
++ |
/ |
++|
+
`},
		{1, 0, 0, `cuboid 1 0 0:
+---------++
+---------+
`},
	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("%dx%dx%d", tc.dx, tc.dy, tc.dz), func(t *testing.T) {
			var buf bytes.Buffer
			fmt.Fprintf(&buf, "cuboid %d %d %d:\n", tc.dx, tc.dy, tc.dz) // mimic initial printf

			cubLineTest := func(n, dx, dy int, cde string) {
				fmt.Fprintf(&buf, "%*s", n+1, cde[:1])
				for d := 9*dx - 1; d > 0; d-- {
					fmt.Fprint(&buf, cde[1:2])
				}
				fmt.Fprint(&buf, cde[:1])
				fmt.Fprintf(&buf, "%*s\n", dy+1, cde[2:])

			}

			cubLine = cubLineTest
			cuboid(tc.dx, tc.dy, tc.dz)
			actual := buf.String()

			if actual != tc.expected {
				t.Errorf("For dimensions %d %d %d:\nExpected:\n%s\nGot:\n%s", tc.dx, tc.dy, tc.dz, tc.expected, actual)
				diff := getDiff(tc.expected, actual)
				t.Logf("Diff:\n%s", diff)
			}
		})
	}
}

func getDiff(expected, actual string) string {
	expectedLines := strings.Split(expected, "\n")
	actualLines := strings.Split(actual, "\n")

	diff := ""
	for i := 0; i < len(expectedLines) || i < len(actualLines); i++ {
		if i < len(expectedLines) && i < len(actualLines) {
			if expectedLines[i] != actualLines[i] {
				diff += fmt.Sprintf("Line %d:\nExpected: %s\nActual:   %s\n", i+1, expectedLines[i], actualLines[i])
			}
		} else if i < len(expectedLines) {
			diff += fmt.Sprintf("Line %d (missing in actual):\nExpected: %s\n", i+1, expectedLines[i])

		} else {
			diff += fmt.Sprintf("Line %d (extra in actual):\nActual:   %s\n", i+1, actualLines[i])
		}
	}

	return diff

}

// this is only done so the tests can redirect output. In normal usage you wouldn't want to modify the original function
var cubLine = func(n, dx, dy int, cde string) {
	fmt.Printf("%*s", n+1, cde[:1])
	for d := 9*dx - 1; d > 0; d-- {
		fmt.Print(cde[1:2])
	}
	fmt.Print(cde[:1])
	fmt.Printf("%*s\n", dy+1, cde[2:])
}



package main

import (
	"bytes"
	"testing"
)

func TestCuboid(t *testing.T) {
	tests := []struct {
		dx, dy, dz int
		expected   string
	}{
		{
			dx: 2, dy: 3, dz: 4,
			expected: `cuboid 2 3 4:
   +--------+
  /        /|
 /        / |
+--------+  |
|        |  |
|        |  |
|        |  |
|        |  |
|        |  |
|        |  +
|        | /
|        |/
+--------+
`,
		},
		{
			dx: 1, dy: 1, dz: 1,
			expected: `cuboid 1 1 1:
 +-+
/ /|
+-+ |
| | +
| |/
+-+
`,
		},
		{
			dx: 6, dy: 2, dz: 1,
			expected: `cuboid 6 2 1:
   +-------------------------+
  /                         /|
 /                         / |
+-------------------------+  |
|                         |  +
|                         | /
+-------------------------+
`,
		},
	}

	for _, tt := range tests {
		t.Run("", func(t *testing.T) {
			var buf bytes.Buffer
			fmt.Printf = func(format string, args ...interface{}) (n int, err error) {
				return buf.WriteString(fmt.Sprintf(format, args...))
			}
			defer func() { fmt.Printf = fmt.Printf }() // Restore original fmt.Printf after test

			cuboid(tt.dx, tt.dy, tt.dz)

			if buf.String() != tt.expected {
				t.Errorf("expected:\n%s\ngot:\n%s", tt.expected, buf.String())
			}
		})
	}
}

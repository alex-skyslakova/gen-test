import (
	"testing"
)

func TestLinearCombo(t *testing.T) {
	testCases := []struct {
		input    []int
		expected string
	}{
		{[]int{1, 2, 3}, "e(1) + 2*e(2) + 3*e(3)"},
		{[]int{0, 1, 2, 3}, "e(2) + 2*e(3) + 3*e(4)"},
		{[]int{1, 0, 3, 4}, "e(1) + 3*e(3) + 4*e(4)"},
		{[]int{1, 2, 0}, "e(1) + 2*e(2)"},
		{[]int{0, 0, 0}, "0"},
		{[]int{0}, "0"},
		{[]int{1, 1, 1}, "e(1) + e(2) + e(3)"},
		{[]int{-1, -1, -1}, "-e(1) - e(2) - e(3)"},
		{[]int{-1, -2, 0, -3}, "-e(1) - 2*e(2) - 3*e(4)"},
		{[]int{-1}, "-e(1)"},
		{[]int{}, "0"}, // Added test case for empty input
		{[]int{0, 0, 1}, "e(3)"}, // Added test case for trailing zero
		{[]int{5, -3, 0, 1}, "5*e(1) - 3*e(2) + e(4)"}, // Added test case for mixed signs and zero

	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("%v", tc.input), func(t *testing.T) {
			actual := linearCombo(tc.input)
			if actual != tc.expected {
				t.Errorf("For input %v, expected %q but got %q", tc.input, tc.expected, actual)
			}
		})
	}
}


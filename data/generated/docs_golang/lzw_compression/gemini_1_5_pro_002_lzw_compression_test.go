import (
	"fmt"
	"strings"
	"testing"
)

func TestCompress(t *testing.T) {
	testCases := []struct {
		input    string
		expected []int
	}{
		{"", []int{}},
		{"A", []int{65}},
		{"AB", []int{65, 66}},
		{"ABA", []int{65, 66, 256}},
		{"ABABABA", []int{65, 66, 256, 257}},
		{"TOBEORNOTTOBEORTOBEORNOT", []int{84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 263}},
	}

	for _, tc := range testCases {
		t.Run(tc.input, func(t *testing.T) {
			actual := compress(tc.input)
			if !equalSlices(actual, tc.expected) {
				t.Errorf("compress(%s) = %v; want %v", tc.input, actual, tc.expected)
			}
		})
	}
}

func TestDecompress(t *testing.T) {
	testCases := []struct {
		input    []int
		expected string
		err      error
	}{
		{[]int{}, "", nil},
		{[]int{65}, "A", nil},
		{[]int{65, 66}, "AB", nil},
		{[]int{65, 66, 256}, "ABA", nil},
		{[]int{65, 66, 256, 257}, "ABABABA", nil},
		{[]int{84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 263}, "TOBEORNOTTOBEORTOBEORNOT", nil},
		{[]int{65, 256, 257}, "ABAAB", nil},
		{[]int{65, 66, 258}, "", BadSymbolError(258)}, // Invalid input
	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("%v", tc.input), func(t *testing.T) {
			actual, err := decompress(tc.input)
			if !equalErrors(err, tc.err) {
				t.Errorf("decompress(%v) returned error %v; want %v", tc.input, err, tc.err)
			}
			if actual != tc.expected {
				t.Errorf("decompress(%v) = %s; want %s", tc.input, actual, tc.expected)
			}
		})
	}

}

func equalSlices(a, b []int) bool {
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


func equalErrors(a, b error) bool {
	if a == nil && b == nil {
		return true
	}
	if a == nil || b == nil {
		return false
	}
	return a.Error() == b.Error()
}

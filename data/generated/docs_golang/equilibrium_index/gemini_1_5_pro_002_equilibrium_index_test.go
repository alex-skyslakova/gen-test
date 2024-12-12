import (
	"reflect"
	"testing"
)

func TestEx(t *testing.T) {
	testCases := []struct {
		input    []int32
		expected []int
	}{
		{[]int32{-7, 1, 5, 2, -4, 3, 0}, []int{3, 6}},
		{[]int32{1, 2, 3, 4, 5}, []int{}},
		{[]int32{0, 0, 0, 0, 0}, []int{0, 1, 2, 3, 4}},
		{[]int32{1, -1, 1, -1, 1, -1}, []int{0, 1, 2, 3, 4, 5}},
		{[]int32{}, []int{}},
		{[]int32{1}, []int{0}},
        {[]int32{1, 1, -1, 1}, []int{1}},
	}

	for _, tc := range testCases {
		actual := ex(tc.input)
		if !reflect.DeepEqual(actual, tc.expected) {
			t.Errorf("For input %v, expected %v, but got %v", tc.input, tc.expected, actual)
		}
	}
}


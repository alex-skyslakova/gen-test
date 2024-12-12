import (
	"math/big"
	"testing"
)

func TestLi(t *testing.T) {
	testCases := []struct {
		input    []int
		expected *big.Int
	}{
		{[]int{1, 34, 3, 98, 9, 76, 45, 4}, big.NewInt(998764543431)},
		{[]int{54, 546, 548, 60}, big.NewInt(6054854654)},
		{[]int{}, big.NewInt(0)},                // Empty input
		{[]int{0}, big.NewInt(0)},               // Single zero
		{[]int{0, 0}, big.NewInt(0)},            // Multiple zeros
		{[]int{10, 1}, big.NewInt(110)},          // Test for correct ordering with leading zeros
                {[]int{9,91}, big.NewInt(991)},
                {[]int{121,12}, big.NewInt(12121)}, // Test case from discussion
                {[]int{54, 546, 548, 60, 5480}, big.NewInt(60548548054654)}, // Longer number test case



	}

	for _, tc := range testCases {
		actual := li(tc.input...)
		if actual.Cmp(tc.expected) != 0 {
			t.Errorf("For input %v, expected %v but got %v", tc.input, tc.expected, actual)
		}
	}
}


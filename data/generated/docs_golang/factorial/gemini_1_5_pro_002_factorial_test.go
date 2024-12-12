import (
	"math/big"
	"testing"
)

func TestFactorial(t *testing.T) {
	tests := []struct {
		input    int64
		expected *big.Int
	}{
		{0, big.NewInt(1)},
		{1, big.NewInt(1)},
		{2, big.NewInt(2)},
		{5, big.NewInt(120)},
		{10, big.NewInt(3628800)},
		{-1, nil}, // Test negative input
	}

	for _, test := range tests {
		result := factorial(test.input)

		if test.expected == nil {
			if result != nil {
				t.Errorf("For input %d, expected nil, but got %v", test.input, result)
			}
			continue
		}
        
		if result.Cmp(test.expected) != 0 {
			t.Errorf("For input %d, expected %v, but got %v", test.input, test.expected, result)
		}
	}
}


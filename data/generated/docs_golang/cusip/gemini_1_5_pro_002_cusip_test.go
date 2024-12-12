import "testing"

func TestIsCusip(t *testing.T) {
	testCases := []struct {
		cusip    string
		expected bool
	}{
		{"037833100", true},
		{"17275R102", true},
		{"38259P508", true},
		{"594918104", true},
		{"68389X106", false},
		{"68389X105", true},
		{"123456789", false}, // Invalid CUSIP - incorrect length and check digit
		{"12345678A", false}, // Invalid CUSIP - incorrect check digit (should be numeric)
		{"*", false},          // Invalid CUSIP - incorrect length
		{"12345678", false}, // Invalid CUSIP - incorrect length
		{"1234567890", false}, // Invalid CUSIP - incorrect length
        {"03783310@", false}, // Invalid CUSIP - incorrect check digit
        {"03783*105", true}, // Valid CUSIP with special chars
        {"03783@101", true}, // Valid CUSIP with special chars
        {"03783#109", true}, // Valid CUSIP with special chars


	}

	for _, tc := range testCases {
		t.Run(tc.cusip, func(t *testing.T) {
			actual := isCusip(tc.cusip)
			if actual != tc.expected {
				t.Errorf("isCusip(%q) = %v; expected %v", tc.cusip, actual, tc.expected)
			}
		})
	}
}


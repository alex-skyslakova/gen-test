package main

import "testing"

func TestEgyptianDivide(t *testing.T) {
	testCases := []struct {
		dividend  int
		divisor   int
		quotient  int
		remainder int
	}{
		{580, 34, 17, 2},
		{10, 2, 5, 0},
		{10, 3, 3, 1},
		{1, 2, 0, 1},
		{0, 1, 0, 0},
		{100, 1, 100, 0},
		{7, 3, 2, 1},
		{27, 5, 5, 2},
		{1024, 2, 512, 0},
		{1025, 2, 512, 1},
        {5000, 23, 217, 9},


	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("dividend=%d, divisor=%d", tc.dividend, tc.divisor), func(t *testing.T) {
			quotient, remainder := egyptianDivide(tc.dividend, tc.divisor)
			if quotient != tc.quotient || remainder != tc.remainder {
				t.Errorf("Expected quotient=%d, remainder=%d, got quotient=%d, remainder=%d", tc.quotient, tc.remainder, quotient, remainder)
			}
		})
	}
}

func TestEgyptianDividePanic(t *testing.T) {
    
    defer func() {
        if r := recover(); r == nil {
            t.Errorf("The code did not panic")
        }
    }()
    
    egyptianDivide(5,0)
}

func TestEgyptianDividePanicNegative(t *testing.T) {
    
    defer func() {
        if r := recover(); r == nil {
            t.Errorf("The code did not panic")
        }
    }()
    
    egyptianDivide(-5,5)
}



package main

import "testing"

func TestXor(t *testing.T) {
	testCases := []struct {
		a, b, expected byte
	}{
		{0, 0, 0},
		{0, 1, 1},
		{1, 0, 1},
		{1, 1, 0},
	}
	for _, tc := range testCases {
		result := xor(tc.a, tc.b)
		if result != tc.expected {
			t.Errorf("xor(%d, %d) = %d; want %d", tc.a, tc.b, result, tc.expected)
		}
	}
}


func TestHA(t *testing.T) {
	testCases := []struct {
		a, b, expectedS, expectedC byte
	}{
		{0, 0, 0, 0},
		{0, 1, 1, 0},
		{1, 0, 1, 0},
		{1, 1, 0, 1},
	}
	for _, tc := range testCases {
		s, c := ha(tc.a, tc.b)
		if s != tc.expectedS || c != tc.expectedC {
			t.Errorf("ha(%d, %d) = %d, %d; want %d, %d", tc.a, tc.b, s, c, tc.expectedS, tc.expectedC)
		}
	}
}



func TestFA(t *testing.T) {
	testCases := []struct {
		a, b, c0, expectedS, expectedC1 byte
	}{
		{0, 0, 0, 0, 0},
		{0, 0, 1, 1, 0},
		{0, 1, 0, 1, 0},
		{0, 1, 1, 0, 1},
		{1, 0, 0, 1, 0},
		{1, 0, 1, 0, 1},
		{1, 1, 0, 0, 1},
		{1, 1, 1, 1, 1},
	}
	for _, tc := range testCases {
		s, c1 := fa(tc.a, tc.b, tc.c0)
		if s != tc.expectedS || c1 != tc.expectedC1 {
			t.Errorf("fa(%d, %d, %d) = %d, %d; want %d, %d", tc.a, tc.b, tc.c0, s, c1, tc.expectedS, tc.expectedC1)
		}
	}
}



func TestAdd4(t *testing.T) {
	testCases := []struct {
		a3, a2, a1, a0, b3, b2, b1, b0 byte
		expectedV, expectedS3, expectedS2, expectedS1, expectedS0 byte
	}{
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
        {1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1}, // 10 + 9 = 19
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, // 15 + 15 = 30
		{1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0},

	}
	for _, tc := range testCases {
		v, s3, s2, s1, s0 := add4(tc.a3, tc.a2, tc.a1, tc.a0, tc.b3, tc.b2, tc.b1, tc.b0)
		if v != tc.expectedV || s3 != tc.expectedS3 || s2 != tc.expectedS2 || s1 != tc.expectedS1 || s0 != tc.expectedS0 {
			t.Errorf("add4(%d, %d, %d, %d, %d, %d, %d, %d) = %d, %d, %d, %d, %d; want %d, %d, %d, %d, %d", tc.a3, tc.a2, tc.a1, tc.a0, tc.b3, tc.b2, tc.b1, tc.b0, v, s3, s2, s1, s0, tc.expectedV, tc.expectedS3, tc.expectedS2, tc.expectedS1, tc.expectedS0)
		}
	}
}


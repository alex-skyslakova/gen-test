package main

import (
    "testing"
)

func TestXor(t *testing.T) {
    tests := []struct {
        a, b, expected byte
    }{
        {0, 0, 0},
        {0, 1, 1},
        {1, 0, 1},
        {1, 1, 0},
    }

    for _, test := range tests {
        result := xor(test.a, test.b)
        if result != test.expected {
            t.Errorf("xor(%d, %d) = %d; expected %d", test.a, test.b, result, test.expected)
        }
    }
}

func TestHa(t *testing.T) {
    tests := []struct {
        a, b, expectedS, expectedC byte
    }{
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {1, 0, 1, 0},
        {1, 1, 0, 1},
    }

    for _, test := range tests {
        s, c := ha(test.a, test.b)
        if s != test.expectedS || c != test.expectedC {
            t.Errorf("ha(%d, %d) = (%d, %d); expected (%d, %d)", test.a, test.b, s, c, test.expectedS, test.expectedC)
        }
    }
}

func TestFa(t *testing.T) {
    tests := []struct {
        a, b, c0, expectedS, expectedC1 byte
    }{
        {0, 0, 0, 0, 0},
        {0, 1, 0, 1, 0},
        {1, 0, 0, 1, 0},
        {1, 1, 0, 0, 1},
        {0, 0, 1, 1, 0},
        {0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1},
        {1, 1, 1, 1, 1},
    }

    for _, test := range tests {
        s, c1 := fa(test.a, test.b, test.c0)
        if s != test.expectedS || c1 != test.expectedC1 {
            t.Errorf("fa(%d, %d, %d) = (%d, %d); expected (%d, %d)", test.a, test.b, test.c0, s, c1, test.expectedS, test.expectedC1)
        }
    }
}

func TestAdd4(t *testing.T) {
    tests := []struct {
        a3, a2, a1, a0, b3, b2, b1, b0, expectedV, expectedS3, expectedS2, expectedS1, expectedS0 byte
    }{
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
        {1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1}, // 10 + 9 = 19 (1 0 0 1 1)
        {1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0}, // 15 + 1 = 16 (1 0 0 0 0)
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, // 15 + 15 = 30 (1 1 1 1 0)
    }

    for _, test := range tests {
        v, s3, s2, s1, s0 := add4(test.a3, test.a2, test.a1, test.a0, test.b3, test.b2, test.b1, test.b0)
        if v != test.expectedV || s3 != test.expectedS3 || s2 != test.expectedS2 || s1 != test.expectedS1 || s0 != test.expectedS0 {
            t.Errorf("add4(%d%d%d%d, %d%d%d%d) = (%d, %d, %d, %d, %d); expected (%d, %d, %d, %d, %d)",
                test.a3, test.a2, test.a1, test.a0, test.b3, test.b2, test.b1, test.b0,
                v, s3, s2, s1, s0,
                test.expectedV, test.expectedS3, test.expectedS2, test.expectedS1, test.expectedS0)
        }
    }
}

import (
	"testing"
)

func TestLCG(t *testing.T) {
	testCases := []struct {
		seed uint32
		a    uint32
		c    uint32
		m    uint32
		expected []uint32
	}{
		{0, 1103515245, 12345, 1 << 31, []uint32{12345, 1406932606, 1610373733, 1074837787, 1197282452}},
		{1, 1103515245, 12345, 1 << 31, []uint32{1103515246, 2519785903,  340437317, 2526672411, 1509197491}},
	}

	for _, tc := range testCases {
		gen := lcg(tc.a, tc.c, tc.m, tc.seed)
		for i, expected := range tc.expected {
			got := gen()
			if got != expected {
				t.Errorf("For seed %d, iteration %d: expected %d, got %d", tc.seed, i, expected, got)
			}
		}
	}
}


func TestMSG(t *testing.T) {
	testCases := []struct {
		seed uint32
		expected []uint32
	}{
		{0, []uint32{0, 7, 15, 22, 30}},
		{1, []uint32{5, 12, 20, 27, 35}},
	}

	for _, tc := range testCases {
		gen := msg(tc.seed)
		for i, expected := range tc.expected {
			got := gen()
			if got != expected {
				t.Errorf("For seed %d, iteration %d: expected %d, got %d", tc.seed, i, expected, got)
			}
		}
	}
}


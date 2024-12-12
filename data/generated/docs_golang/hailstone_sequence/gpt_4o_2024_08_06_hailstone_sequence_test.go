package main

import (
	"testing"
)

func TestHailstoneSequence(t *testing.T) {
	tests := []struct {
		start int
		want  []int
	}{
		{1, []int{1}},
		{2, []int{2, 1}},
		{3, []int{3, 10, 5, 16, 8, 4, 2, 1}},
		{4, []int{4, 2, 1}},
		{5, []int{5, 16, 8, 4, 2, 1}},
		{6, []int{6, 3, 10, 5, 16, 8, 4, 2, 1}},
		{7, []int{7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1}},
		{27, []int{27, 82, 41, 124, 62, 31, 94, 47, 142, 71, 214, 107, 322, 161, 484, 242, 121, 364, 182, 91, 274, 137, 412, 206, 103, 310, 155, 466, 233, 700, 350, 175, 526, 263, 790, 395, 1186, 593, 1780, 890, 445, 1336, 668, 334, 167, 502, 251, 754, 377, 1132, 566, 283, 850, 425, 1276, 638, 319, 958, 479, 1438, 719, 2158, 1079, 3238, 1619, 4858, 2429, 7288, 3644, 1822, 911, 2734, 1367, 4102, 2051, 6154, 3077, 9232, 4616, 2308, 1154, 577, 1732, 866, 433, 1300, 650, 325, 976, 488, 244, 122, 61, 184, 92, 46, 23, 70, 35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1}},
	}

	for _, tt := range tests {
		t.Run("", func(t *testing.T) {
			got := hs(tt.start, nil)
			if len(got) != len(tt.want) {
				t.Errorf("hs(%d) = %v, want %v", tt.start, got, tt.want)
			}
			for i := range got {
				if got[i] != tt.want[i] {
					t.Errorf("hs(%d) = %v, want %v", tt.start, got, tt.want)
					break
				}
			}
		})
	}
}

func TestLongestHailstoneSequenceUnder100000(t *testing.T) {
	var maxN, maxLen int
	var seq []int
	for n := 1; n < 100000; n++ {
		seq = hs(n, seq)
		if len(seq) > maxLen {
			maxN = n
			maxLen = len(seq)
		}
	}

	if maxN != 77031 || maxLen != 351 {
		t.Errorf("Longest sequence under 100000 is hs(%d) with %d elements, got hs(%d) with %d elements", 77031, 351, maxN, maxLen)
	}
}

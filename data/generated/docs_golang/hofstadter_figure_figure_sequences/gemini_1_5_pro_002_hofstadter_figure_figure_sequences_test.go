import "testing"

func TestFfr(t *testing.T) {
	tests := []struct {
		n    int
		want int
	}{
		{1, 1},
		{2, 3},
		{3, 7},
		{4, 12},
		{5, 18},
		{6, 26},
		{7, 35},
		{8, 45},
		{9, 56},
		{10, 69},
		{100, 1385},  // Larger value test
		{1000, 171765}, // Even larger value test
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
			if got := ffr(tt.n); got != tt.want {
				t.Errorf("ffr(%d) = %d; want %d", tt.n, got, tt.want)
			}
		})
	}
}

func TestFfs(t *testing.T) {
	tests := []struct {
		n    int
		want int
	}{
		{1, 2},
		{2, 4},
		{3, 5},
		{4, 6},
		{5, 8},
		{6, 9},
		{7, 10},
		{8, 11},
		{9, 13},
		{10, 14},
		{100, 154},  // Larger value test
		{1000, 167846}, // Even larger value test
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
			if got := ffs(tt.n); got != tt.want {
				t.Errorf("ffs(%d) = %d; want %d", tt.n, got, tt.want)
			}
		})
	}
}

func TestCombinedSequence(t *testing.T) {
	found := [1001]bool{}
	for n := 1; n <= 40; n++ {
		found[ffr(n)] = true
	}
	for n := 1; n <= 960; n++ {
		found[ffs(n)] = true
	}
	for i := 1; i <= 1000; i++ {
		if !found[i] {
			t.Errorf("Number %d not found in combined sequence", i)
		}
	}
}


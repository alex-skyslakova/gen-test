import "testing"

func TestSieve(t *testing.T) {
	tests := []struct {
		limit int
		want  []int
	}{
		{10, []int{3, 5, 7}},
		{20, []int{3, 5, 7, 11, 13, 17, 19}},
		{2, []int{}},
		{1, []int{}},
		{0, []int{}},

	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("limit=%d", tt.limit), func(t *testing.T) {
			got := sieve(tt.limit)
			if !equalSlices(got, tt.want) {
				t.Errorf("sieve(%d) = %v, want %v", tt.limit, got, tt.want)
			}

		})

	}
}
func equalSlices(a, b []int) bool {
	if len(a) != len(b) {
		return false
	}
	for i, v := range a {
		if v != b[i] {
			return false
		}
	}
	return true
}


func TestFindPeriod(t *testing.T) {
	tests := []struct {
		n    int
		want int
	}{
		{7, 6},
		{11, 2},
		{13, 6},
		{17, 16},
		{19, 18},

		{2, 1},
		{3, 1},
		{5, 1},

	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
			if got := findPeriod(tt.n); got != tt.want {
				t.Errorf("findPeriod(%d) = %v, want %v", tt.n, got, tt.want)
			}
		})
	}
}


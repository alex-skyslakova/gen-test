import (
	"testing"
)

func TestJaro(t *testing.T) {
	testCases := []struct {
		str1 string
		str2 string
		want float64
	}{
		{"", "", 1.0},
		{"", "a", 0.0},
		{"a", "", 0.0},
		{"MARTHA", "MARHTA", 0.9444444444444445},
		{"DIXON", "DICKSONX", 0.7666666666666666},
		{"JELLYFISH", "SMELLYFISH", 0.8962962962962964},
		{"CRATE", "TRACE", 0.7333333333333334},
		{"DwAyNe", "DuAnE", 0.8222222222222223},
		{"a", "a", 1},
		{"ab", "ab", 1},
		{"abc", "acb", 0.9444444444444445},
        {"aa", "aa", 1},
        {"aaa", "aba", 0.6666666666666666},
        {"abcde", "edcba", 0.0},


	}

	for _, tc := range testCases {
		got := jaro(tc.str1, tc.str2)
		if got != tc.want {
			t.Errorf("jaro(%q, %q) = %f; want %f", tc.str1, tc.str2, got, tc.want)
		}
	}
}


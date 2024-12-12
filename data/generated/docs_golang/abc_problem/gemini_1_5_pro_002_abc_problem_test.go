import (
	"strings"
	"testing"
)

func TestNewSpeller(t *testing.T) {
	blocks := "BO XK DQ CP NA GT RE TG QD FS JW HU VI AN OB ER FS LY PC ZM"
	sp := newSpeller(blocks)

	testCases := []struct {
		word string
		want bool
	}{
		{"A", true},
		{"BARK", true},
		{"BOOK", false},
		{"TREAT", true},
		{"COMMON", false},
		{"SQUAD", true},
		{"CONFUSE", true},
		{"", true}, // Empty string
		{"a", true}, // Lowercase
		{"bark", true}, // Lowercase
		{"booK", false}, // Mixed case
		{"TreAt", true}, // Mixed case
		{"commOn", false}, // Mixed case
		{"SquAd", true}, // Mixed case
		{"ConFusE", true}, // Mixed case
		{"BOGT", true},  // Uses both letters from a block
                {"BOOB", false}, // Double letter on same block
                {"ZANZIBAR",false}, // Long word impossible to spell
                {"BB", false}, // Repeated letter impossible
                {"AMAZON", true}, // More comprehensive test
	}

	for _, tc := range testCases {
		t.Run(tc.word, func(t *testing.T) {
			got := sp(tc.word)
			if got != tc.want {
				t.Errorf("sp(%q) = %v; want %v", tc.word, got, tc.want)
			}
		})
	}


        // Test with no blocks available
	spEmpty := newSpeller("")
        if spEmpty("A") {
                t.Errorf("sp(\"\"A\") with empty blocks should be false")

        }

        // Test with repeated blocks - check it doesn't overspend
        spRepeated := newSpeller("BO BO BO BO BO BO BO BO BO BO BO BO BO BO BO BO BO BO BO BO")

        if spRepeated("BOBOBOBOBOBO") {
                t.Errorf("spRepeated(\"BOBOBOBOBOBO\") should be false")
        }
        if !spRepeated("BOBOBO") {
                t.Errorf("spRepeated(\"BOBOBO\") should be true")
        }


}




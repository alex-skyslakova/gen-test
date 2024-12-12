import (
	"testing"
)

func TestCz(t *testing.T) {
	tests := []struct {
		year        int
		animal      string
		yinYang     string
		element     string
		stemBranch string
		cycleYear   int
	}{
		{1984, "Rat", "Yang", "Wood", "甲子", 1},
		{1985, "Ox", "Yin", "Wood", "乙丑", 2},
		{1986, "Tiger", "Yang", "Fire", "丙寅", 3},
		{2022, "Tiger", "Yang", "Water", "壬寅", 39},
		{4, "Rat", "Yang", "Wood", "甲子", 1},
		{1935, "Pig", "Yin", "Wood", "乙亥", 32},
		{1938, "Tiger", "Yang", "Earth", "戊寅", 35},
		{1968, "Monkey", "Yang", "Earth", "戊申", 65},
		{1972, "Rat", "Yang", "Water", "壬子", 69},
		{1976, "Dragon", "Yang", "Fire", "丙辰", 73},

		{2044, "Monkey", "Yang", "Wood", "甲申", 61}, // Start of a new cycle
		{2045, "Rooster", "Yin", "Wood", "乙酉", 62},
	}

	for _, tt := range tests {
		t.Run(fmt.Sprintf("Year %d", tt.year), func(t *testing.T) {
			animal, yinYang, element, stemBranch, cycleYear := cz(tt.year)
			if animal != tt.animal {
				t.Errorf("animal mismatch: got %s, want %s", animal, tt.animal)
			}
			if yinYang != tt.yinYang {
				t.Errorf("yinYang mismatch: got %s, want %s", yinYang, tt.yinYang)
			}
			if element != tt.element {
				t.Errorf("element mismatch: got %s, want %s", element, tt.element)
			}
			if stemBranch != tt.stemBranch {
				t.Errorf("stemBranch mismatch: got %s, want %s", stemBranch, tt.stemBranch)
			}
			if cycleYear != tt.cycleYear {

				t.Errorf("cycleYear mismatch: got %d, want %d", cycleYear, tt.cycleYear)
			}
		})
	}
}


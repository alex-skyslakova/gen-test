import (
	"math/big"
	"testing"
)

func TestDC_String(t *testing.T) {
	tests := []struct {
		name string
		dc   DC
		want string
	}{
		{"Positive", 12345, "123.45"},
		{"Zero", 0, "0.00"},
		{"Negative", -12345, "-123.45"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := tt.dc.String(); got != tt.want {
				t.Errorf("DC.String() = %v, want %v", got, tt.want)
			}
		})
	}
}

func TestDC_Extend(t *testing.T) {
	tests := []struct {
		name string
		dc   DC
		n    int
		want DC
	}{
		{"Positive", 123, 2, 246},
		{"Zero", 0, 100, 0},
		{"Negative", -123, 2, -246},
		{"LargeNumber", 550, 4000000000000000, 2200000000000000000},

	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := tt.dc.Extend(tt.n); got != tt.want {
				t.Errorf("DC.Extend() = %v, want %v", got, tt.want)
			}
		})
	}
}

func TestParseDC(t *testing.T) {
	tests := []struct {
		name    string
		s       string
		wantDc  DC
		wantOk bool
	}{
		{"Valid", "5.50", 550, true},
		{"Zero", "0.00", 0, true},
		{"Negative", "-1.23", -123, true},
		{"Invalid", "abc", 0, false},
		{"FractionalCents", "1.234", 0, false},

	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			gotDc, gotOk := ParseDC(tt.s)
			if gotDc != tt.wantDc {
				t.Errorf("ParseDC() gotDc = %v, want %v", gotDc, tt.wantDc)
			}
			if gotOk != tt.wantOk {
				t.Errorf("ParseDC() gotOk = %v, want %v", gotOk, tt.wantOk)
			}
		})
	}
}


func TestTR_SetString(t *testing.T) {
	tests := []struct {
		name    string
		s       string
		wantTr  TR
		wantOk bool
	}{
		{"Valid", "0.0765", TR{big.NewRat(765, 10000)}, true},
		{"Zero", "0", TR{big.NewRat(0, 1)}, true},
		{"Invalid", "abc", TR{}, false},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			tr := NewTR()
			gotTr, gotOk := tr.SetString(tt.s)
			if gotOk != tt.wantOk {
				t.Errorf("TR.SetString() got1 = %v, want %v", gotTr, tt.wantTr)
			}
			if !gotTr.Rat.Equal(tt.wantTr.Rat) {
				t.Errorf("TR.SetString() gotOk = %v, want %v", gotOk, tt.wantOk)

			}
		})
	}
}

func TestTR_Tax(t *testing.T) {
	tests := []struct {
		name string
		tr   TR
		dc   DC
		want DC
	}{
		{"Standard", TR{big.NewRat(765, 10000)}, 2200000000000000572, 168300000000000044},
		{"ZeroRate", TR{big.NewRat(0, 1)}, 1000, 0},
		{"ZeroValue", TR{big.NewRat(765, 10000)}, 0, 0},
		{"RoundingUp", TR{big.NewRat(50, 100)}, 150, 75},
		{"RoundingDown", TR{big.NewRat(50, 100)}, 149, 75},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := tt.tr.Tax(tt.dc); got != tt.want {
				t.Errorf("TR.Tax() = %v, want %v", got, tt.want)
			}
		})
	}
}


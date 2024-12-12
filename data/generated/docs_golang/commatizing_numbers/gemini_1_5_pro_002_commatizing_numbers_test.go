package main

import (
	"testing"
)

func TestCommatize(t *testing.T) {
	tests := []struct {
		input      string
		startIndex int
		period     int
		sep        string
		expected   string
	}{
		{"123456789.123456789", 0, 2, "*", "12*34*56*78*9.12*34*56*78*9"},
		{".123456789", 0, 3, "-", ".123-456-789"},
		{"57256.1D-4", 0, 4, "__", "57256.1D-4"},
		{"pi=3.14159265358979323846264338327950288419716939937510582097494459231", 0, 5, " ", "pi=3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510 58209 74944 59231"},
		{"The author has two Z$100000000000000 Zimbabwe notes (100 trillion).", 0, 3, ".", "The author has two Z$10.000.000.000.000 Zimbabwe notes (100 trillion)."},
		{"-in Aus$+1411.8millions", 0, 3, ",", "-in Aus$+1,411.8millions"},
		{"===US$0017440 millions=== (in 2000 dollars)", 0, 3, ",", "===US$001,744,0 millions=== (in 2000 dollars)"},
		{"123.e8000 is pretty big.", 0, 3, ",", "123.e8000 is pretty big."},
		{"The land area of the earth is 57268900(29% of the surface) square miles.", 0, 3, ",", "The land area of the earth is 57,268,900(29% of the surface) square miles."},
		{"Ain't no numbers in this here words, nohow, no way, Jose.", 0, 3, ",", "Ain't no numbers in this here words, nohow, no way, Jose."},
		{"James was never known as 0000000007", 0, 3, ",", "James was never known as 0000000007"},
		{"Arthur Eddington wrote: I believe there are 15747724136275002577605653961181555468044717914527116709366231425076185631031296 protons in the universe.", 0, 3, ",", "Arthur Eddington wrote: I believe there are 157,477,241,362,750,025,776,056,539,611,815,554,680,447,179,145,271,167,093,662,314,250,761,856,310,312,96 protons in the universe."},
		{"   $-140000±100 millions.", 0, 3, ",", "   $-140,000±100 millions."},
		{"6/9/1946 was a good year for some.", 0, 3, ",", "6/9/1946 was a good year for some."},
        //Edge cases
        {"", 0, 3, ",", ""},
        {"1", 0, 3, ",", "1"},
        {"1234", 0, 3, ",", "1,234"},
		{"1234567", 0, 0, ",", "1234567"}, //Invalid period
		{"1234567", -1, 3, ",", "1234567"}, // Invalid startIndex
		{"1234567", 10, 3, ",", "1234567"}, // Invalid startIndex
		{"1234567", 0, 3, "", "1234567"}, // Invalid sep

	}

	for _, test := range tests {
		actual := commatize(test.input, test.startIndex, test.period, test.sep)
		if actual != test.expected {
			t.Errorf("commatize(%s, %d, %d, %s) = %s; want %s", test.input, test.startIndex, test.period, test.sep, actual, test.expected)
		}
	}
}


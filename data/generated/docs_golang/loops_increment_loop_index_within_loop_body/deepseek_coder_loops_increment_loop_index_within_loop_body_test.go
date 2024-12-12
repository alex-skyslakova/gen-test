package main

import (
	"fmt"
	"testing"
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

func TestIsPrime(t *testing.T) {
	tests := []struct {
		n    uint64
		want bool
	}{
		{2, true},
		{3, true},
		{4, false},
		{5, true},
		{6, false},
		{7, true},
		{8, false},
		{9, false},
		{10, false},
		{11, true},
		{12, false},
		{13, true},
		{14, false},
		{15, false},
		{16, false},
		{17, true},
		{18, false},
		{19, true},
		{20, false},
		{21, false},
		{22, false},
		{23, true},
		{24, false},
		{25, false},
		{26, false},
		{27, false},
		{28, false},
		{29, true},
		{30, false},
		{31, true},
		{32, false},
		{33, false},
		{34, false},
		{35, false},
		{36, false},
		{37, true},
		{38, false},
		{39, false},
		{40, false},
		{41, true},
		{42, false},
		{43, true},
		{44, false},
		{45, false},
		{46, false},
		{47, true},
		{48, false},
		{49, false},
		{50, false},
	}

	for _, tt := range tests {
		t.Run(fmt.Sprintf("isPrime(%d)", tt.n), func(t *testing.T) {
			if got := isPrime(tt.n); got != tt.want {
				t.Errorf("isPrime(%d) = %v, want %v", tt.n, got, tt.want)
			}
		})
	}
}

func TestMainLoop(t *testing.T) {
	// Capture the output of the main function
	oldStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

	main()

	w.Close()
	out, _ := ioutil.ReadAll(r)
	os.Stdout = oldStdout

	// Convert the output to a string
	output := string(out)

	// Check if the output contains exactly 42 lines
	lines := strings.Split(output, "\n")
	if len(lines) != 43 { // 42 lines + 1 empty line at the end
		t.Errorf("Expected 42 lines of output, got %d", len(lines)-1)
	}

	// Check if each line is correctly formatted
	p := message.NewPrinter(language.English)
	for i, line := range lines[:len(lines)-1] { // Skip the last empty line
		var n int
		var prime uint64
		fmt.Sscanf(line, "n = %d  %d", &n, &prime)
		if n != i+1 {
			t.Errorf("Expected n = %d, got n = %d", i+1, n)
		}
		if !isPrime(prime) {
			t.Errorf("Expected prime number, got %d", prime)
		}
		expectedPrime := p.Sprintf("%d", prime)
		if !strings.Contains(line, expectedPrime) {
			t.Errorf("Expected formatted prime %s, got %s", expectedPrime, line)
		}
	}
}

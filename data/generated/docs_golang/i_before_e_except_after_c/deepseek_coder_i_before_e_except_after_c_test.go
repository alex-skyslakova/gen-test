package main

import (
	"testing"
)

func TestCheck(t *testing.T) {
	tests := []struct {
		a        int
		b        int
		expected bool
	}{
		{10, 3, true},  // a is more than twice b
		{5, 2, true},   // a is exactly twice b
		{4, 2, false},  // a is less than twice b but more than b
		{1, 2, false},  // a is less than b
		{0, 0, false},  // both are zero
		{10, 0, true},  // b is zero
	}

	for _, test := range tests {
		result := check(test.a, test.b, "Test case")
		if result != test.expected {
			t.Errorf("check(%d, %d) = %v; expected %v", test.a, test.b, result, test.expected)
		}
	}
}

func TestMainFunction(t *testing.T) {
	// Mock the file reading and scanning
	oldOpen := os.Open
	defer func() { os.Open = oldOpen }()

	os.Open = func(name string) (*os.File, error) {
		return nil, nil
	}

	// Mock the bufio.Scanner to return specific words
	oldNewScanner := bufio.NewScanner
	defer func() { bufio.NewScanner = oldNewScanner }()

	bufio.NewScanner = func(f *os.File) *bufio.Scanner {
		scanner := &bufio.Scanner{}
		scanner.Scan = func() bool { return true }
		scanner.Text = func() string { return "ie" }
		return scanner
	}

	// Capture the output to check if the function behaves as expected
	oldPrintln := fmt.Println
	defer func() { fmt.Println = oldPrintln }()

	var output []string
	fmt.Println = func(a ...interface{}) (n int, err error) {
		output = append(output, a[0].(string))
		return 0, nil
	}

	main()

	expectedOutput := []string{
		`"I before E when not preceded by C" is plausible (1 vs 0).`,
		`"E before I when preceded by C" is implausible (0 vs 0).`,
		"One or both implausable.",
		`"I before E, except after C" is implausable.`,
	}

	for i, out := range output {
		if out != expectedOutput[i] {
			t.Errorf("Output mismatch at index %d: got %q, expected %q", i, out, expectedOutput[i])
		}
	}
}

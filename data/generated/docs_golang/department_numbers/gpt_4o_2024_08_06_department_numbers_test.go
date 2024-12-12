package main

import (
	"bytes"
	"testing"
)

func TestDepartmentNumbers(t *testing.T) {
	expectedOutput := "Police  Sanitation  Fire\n" +
		"------  ----------  ----\n" +
		" 2         3         7\n" +
		" 2         5         5\n" +
		" 2         7         3\n" +
		" 4         1         7\n" +
		" 4         3         5\n" +
		" 4         5         3\n" +
		" 4         7         1\n" +
		" 6         1         5\n" +
		" 6         5         1\n" +
		"\n9 valid combinations\n"

	var buf bytes.Buffer
	mainOutput := func() {
		old := fmt.Printf
		fmt.Printf = func(format string, a ...interface{}) (n int, err error) {
			return buf.WriteString(fmt.Sprintf(format, a...))
		}
		defer func() { fmt.Printf = old }()
		main()
	}

	mainOutput()

	if buf.String() != expectedOutput {
		t.Errorf("Output did not match expected.\nGot:\n%s\nExpected:\n%s", buf.String(), expectedOutput)
	}
}

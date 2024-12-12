package main

import (
	"fmt"
	"io"
	"os"
	"strings"
	"testing"
)

func TestDepartmentCombinations(t *testing.T) {
	rescueStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

	main()

	w.Close()
	out, _ := io.ReadAll(r)
	os.Stdout = rescueStdout

	expectedOutput := `Police  Sanitation  Fire
------  ----------  ----
  2         3         7
  2         7         3
  4         1         7
  4         7         1
  6         1         5
  6         5         1
6 valid combinations
`

	if strings.TrimSpace(string(out)) != strings.TrimSpace(expectedOutput) {
		t.Errorf("Incorrect output:\nExpected:\n%s\nGot:\n%s", expectedOutput, out)
	}
}


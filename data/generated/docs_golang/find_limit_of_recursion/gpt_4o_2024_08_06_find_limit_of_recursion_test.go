package main

import (
	"os/exec"
	"strings"
	"testing"
)

func TestFindLimitOfRecursion(t *testing.T) {
	tests := []struct {
		stackSize int
		expectErr bool
	}{
		{stackSize: 0, expectErr: true},   // Default stack size, expect stack overflow
		{stackSize: 1024 * 1024, expectErr: true}, // Small stack size, expect stack overflow
		{stackSize: 10 * 1024 * 1024, expectErr: false}, // Larger stack size, may not overflow
	}

	for _, test := range tests {
		t.Run(strings.Join([]string{"stackSize", string(test.stackSize)}, "_"), func(t *testing.T) {
			cmd := exec.Command("go", "run", "find_limit_of_recursion.go", "-stack", string(test.stackSize))
			output, err := cmd.CombinedOutput()

			if test.expectErr && err == nil {
				t.Errorf("Expected an error for stack size %d, but got none. Output: %s", test.stackSize, output)
			} else if !test.expectErr && err != nil {
				t.Errorf("Did not expect an error for stack size %d, but got one. Error: %v, Output: %s", test.stackSize, err, output)
			}
		})
	}
}

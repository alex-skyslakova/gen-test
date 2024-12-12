package main

import (
	"os"
	"os/exec"
	"strconv"
	"strings"
	"testing"
	"time"
)

func TestLastFridays(t *testing.T) {
	testCases := []struct {
		year int
		expectedOutput string
	}{
		{2012, "2012-01-27\n2012-02-24\n2012-03-30\n2012-04-27\n2012-05-25\n2012-06-29\n2012-07-27\n2012-08-31\n2012-09-28\n2012-10-26\n2012-11-30\n2012-12-28\n"},
		{2023, "2023-01-27\n2023-02-24\n2023-03-31\n2023-04-28\n2023-05-26\n2023-06-30\n2023-07-28\n2023-08-25\n2023-09-29\n2023-10-27\n2023-11-24\n2023-12-29\n"},
		{2024, "2024-01-26\n2024-02-23\n2024-03-29\n2024-04-26\n2024-05-31\n2024-06-28\n2024-07-26\n2024-08-30\n2024-09-27\n2024-10-25\n2024-11-29\n2024-12-27\n"}, // Leap year
	}

	for _, tc := range testCases {
		t.Run(strconv.Itoa(tc.year), func(t *testing.T) {
			cmd := exec.Command("go", "run", "main.go", strconv.Itoa(tc.year)) // Assuming main.go is the file name
			output, err := cmd.CombinedOutput()
			if err != nil {
				t.Fatalf("Error executing command: %v\nOutput:\n%s", err, output)
			}
			actualOutput := string(output)

			if actualOutput != tc.expectedOutput {
				t.Errorf("For year %d, expected:\n%s\nGot:\n%s", tc.year, tc.expectedOutput, actualOutput)
			}
		})
	}

	t.Run("No Year Provided", func(t *testing.T) {
		cmd := exec.Command("go", "run", "main.go") // Assuming main.go is the file name
		output, err := cmd.CombinedOutput()

		if err != nil {
			t.Fatalf("Error executing command: %v", err)

		}

		currentYear := time.Now().Year()
		expectedOutputPrefix := strconv.Itoa(currentYear) + "-"
		actualOutput := string(output)

		if !strings.HasPrefix(actualOutput, expectedOutputPrefix) {

			t.Errorf("Expected output to start with current year (%d), but got: %s", currentYear, actualOutput)
		}
	})


	t.Run("Invalid Year", func(t *testing.T) {
			cmd := exec.Command("go", "run", "main.go", "invalid") 
			output, err := cmd.CombinedOutput()
			if err == nil {
				t.Error("Expected an error for invalid year input, but got none")
			}
		})


}

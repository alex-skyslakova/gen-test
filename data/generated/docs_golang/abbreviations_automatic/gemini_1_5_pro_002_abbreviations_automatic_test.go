package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
	"testing"
)

func TestDistinctStrings(t *testing.T) {
	testCases := []struct {
		input    []string
		expected []string
	}{
		{[]string{"a", "b", "c", "a", "b", "d"}, []string{"a", "b", "c", "d"}},
		{[]string{"a", "a", "a"}, []string{"a"}},
		{[]string{}, []string{}},
	}
	for _, tc := range testCases {
		actual := distinctStrings(tc.input)
		if len(actual) != len(tc.expected) {
			t.Errorf("For input %v, expected %v but got %v", tc.input, tc.expected, actual)
		} else {
			for i := range actual {
				if actual[i] != tc.expected[i] {
					t.Errorf("For input %v, expected %v but got %v", tc.input, tc.expected, actual)
					break
				}
			}
		}
	}
}


func TestTakeRunes(t *testing.T) {
	testCases := []struct {
		s        string
		n        int
		expected string
	}{
		{"hello", 3, "hel"},
		{"hello", 5, "hello"},
		{"hello", 0, ""},
		{"hello", 10, "hello"},
		{"", 3, ""},
	}
	for _, tc := range testCases {
		actual := takeRunes(tc.s, tc.n)
		if actual != tc.expected {
			t.Errorf("For s=%s, n=%d, expected %s but got %s", tc.s, tc.n, tc.expected, actual)
		}
	}
}


func TestMainFunction(t *testing.T) {
	// Create a temporary file with test data
	tmpFile, err := os.CreateTemp("", "days_of_week.txt")
	if err != nil {
		t.Fatal(err)
	}
	defer os.Remove(tmpFile.Name()) // Clean up the temporary file

	testData := `Sunday Monday Tuesday Wednesday Thursday Friday Saturday
Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag
E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë
Ehud Segno Maksegno Erob Hamus Arbe Kedame
Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit
InvalidLine
`
	_, err = tmpFile.WriteString(testData)
	if err != nil {
		t.Fatal(err)
	}
	tmpFile.Close()

	// Redirect stdout to capture output
	oldStdout := os.Stdout
	r, w, err := os.Pipe()
	if err != nil {
		t.Fatal(err)
	}
	os.Stdout = w

	// Run the main function with the temporary file
	os.Args = []string{"", tmpFile.Name()}
	main()

	w.Close()
	os.Stdout = oldStdout

	// Read and check the output
	var output []string
	scanner := bufio.NewScanner(r)
	for scanner.Scan() {
		output = append(output, scanner.Text())
	}

	expectedOutput := []string{
		" 3  Sunday Monday Tuesday Wednesday Thursday Friday Saturday",
		" 3  Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag",
		" 3  E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë",
		" 2  Ehud Segno Maksegno Erob Hamus Arbe Kedame",
		" 2  Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit",
		"There aren't 7 days in line 6",
	}


	for i, line := range expectedOutput {
		if i >= len(output) || output[i] !=  line{
			t.Errorf("Line %d mismatch:\nExpected: %s\nGot:      %s", i+1, line, output[i])
			fmt.Println(output)
			break // Stop after the first mismatch
		}
	}

	if len(output) > len(expectedOutput){
		t.Errorf("Extra output lines detected")
	}

}

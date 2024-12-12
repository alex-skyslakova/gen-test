package main

import (
    "bufio"
    "fmt"
    "os"
    "strings"
    "testing"
)

func TestDistinctStrings(t *testing.T) {
    tests := []struct {
        input    []string
        expected []string
    }{
        {[]string{}, []string{}},
        {[]string{"a", "b", "c"}, []string{"a", "b", "c"}},
        {[]string{"a", "a", "b", "b", "c"}, []string{"a", "b", "c"}},
        {[]string{"a", "b", "a", "c", "b"}, []string{"a", "b", "c"}},
    }

    for _, test := range tests {
        result := distinctStrings(test.input)
        if !equal(result, test.expected) {
            t.Errorf("distinctStrings(%v) = %v; expected %v", test.input, result, test.expected)
        }
    }
}

func TestTakeRunes(t *testing.T) {
    tests := []struct {
        input    string
        n        int
        expected string
    }{
        {"", 0, ""},
        {"abc", 0, ""},
        {"abc", 1, "a"},
        {"abc", 2, "ab"},
        {"abc", 3, "abc"},
        {"abc", 4, "abc"},
    }

    for _, test := range tests {
        result := takeRunes(test.input, test.n)
        if result != test.expected {
            t.Errorf("takeRunes(%q, %d) = %q; expected %q", test.input, test.n, result, test.expected)
        }
    }
}

func TestMainFunction(t *testing.T) {
    // Create a temporary file with test data
    tempFile, err := os.CreateTemp("", "days_of_week.txt")
    if err != nil {
        t.Fatalf("Failed to create temporary file: %v", err)
    }
    defer os.Remove(tempFile.Name())

    testData := []string{
        "Sunday Monday Tuesday Wednesday Thursday Friday Saturday",
        "Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag",
        "E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë",
        "Ehud Segno Maksegno Erob Hamus Arbe Kedame",
        "Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit",
        "",
    }

    for _, line := range testData {
        _, err := tempFile.WriteString(line + "\n")
        if err != nil {
            t.Fatalf("Failed to write to temporary file: %v", err)
        }
    }
    tempFile.Close()

    // Redirect stdout to capture the output
    oldStdout := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    // Run the main function
    main()

    // Restore stdout
    w.Close()
    os.Stdout = oldStdout

    // Read the captured output
    output := make([]byte, 1024)
    n, _ := r.Read(output)
    capturedOutput := string(output[:n])

    // Define the expected output
    expectedOutput := ` 3  Sunday Monday Tuesday Wednesday Thursday Friday Saturday
 3  Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag
 3  E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë
 3  Ehud Segno Maksegno Erob Hamus Arbe Kedame
 3  Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit

`

    // Compare the captured output with the expected output
    if capturedOutput != expectedOutput {
        t.Errorf("Unexpected output.\nExpected:\n%s\nGot:\n%s", expectedOutput, capturedOutput)
    }
}

func equal(a, b []string) bool {
    if len(a) != len(b) {
        return false
    }
    for i := range a {
        if a[i] != b[i] {
            return false
        }
    }
    return true
}

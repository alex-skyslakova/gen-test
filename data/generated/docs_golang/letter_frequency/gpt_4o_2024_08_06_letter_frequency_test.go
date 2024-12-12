package main

import (
    "io/ioutil"
    "os"
    "testing"
    "unicode"
)

func TestLetterFrequency(t *testing.T) {
    // Create a temporary file for testing
    tmpfile, err := ioutil.TempFile("", "testfile")
    if err != nil {
        t.Fatal(err)
    }
    defer os.Remove(tmpfile.Name()) // Clean up

    // Write test data to the temporary file
    content := "Hello, World! 123\nHello again!"
    if _, err := tmpfile.Write([]byte(content)); err != nil {
        t.Fatal(err)
    }
    if err := tmpfile.Close(); err != nil {
        t.Fatal(err)
    }

    // Override the file constant with the temporary file name
    file = tmpfile.Name()

    // Run the main function to test
    main()

    // Expected results
    expected := map[rune]int{
        'H': 2, 'e': 2, 'l': 5, 'o': 2, 'W': 1, 'r': 1, 'd': 1, 'a': 2, 'g': 1, 'i': 1, 'n': 1,
    }

    // Read the file again to check the results
    bs, err := ioutil.ReadFile(file)
    if err != nil {
        t.Fatal(err)
    }

    m := make(map[rune]int)
    for _, r := range string(bs) {
        if unicode.IsLetter(r) {
            m[r]++
        }
    }

    // Compare the expected and actual results
    for k, v := range expected {
        if m[k] != v {
            t.Errorf("expected %d occurrences of %c, got %d", v, k, m[k])
        }
    }
}

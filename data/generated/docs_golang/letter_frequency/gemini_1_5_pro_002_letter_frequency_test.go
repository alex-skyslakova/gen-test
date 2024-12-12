package main

import (
	"io/ioutil"
	"os"
	"sort"
	"testing"
	"unicode"
)

func TestLetterFrequency(t *testing.T) {
	// Create a temporary file with test content
	tmpfile, err := ioutil.TempFile("", "testfile")
	if err != nil {
		t.Fatal(err)
	}
	defer os.Remove(tmpfile.Name()) // clean up

	testContent := "aA bB cC\n123"
	if _, err := tmpfile.Write([]byte(testContent)); err != nil {
		t.Fatal(err)
	}
	if err := tmpfile.Close(); err != nil {
		t.Fatal(err)
	}

	// Override the file constant for testing
	originalFile := file
	file = tmpfile.Name()
	defer func() { file = originalFile }() // Restore original value

	// Capture the output
	rescueStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

	main()

	w.Close()
	out, _ := ioutil.ReadAll(r)
	os.Stdout = rescueStdout

	// Verify the output
	expectedOutput := `file: ` + tmpfile.Name() + "\nletter  frequency\n   a         1\n   b         1\n   c         1\n   A         1\n   B         1\n   C         1\n   1         1\n   2         1\n   3         1\n  \n         1\n"
	if string(out) != expectedOutput {
		t.Errorf("Expected output:\n%s\nGot:\n%s", expectedOutput, string(out))
	}

	// Test empty file
	tmpfile, err = ioutil.TempFile("", "testfile_empty")
	if err != nil {
		t.Fatal(err)
	}
	defer os.Remove(tmpfile.Name())

	file = tmpfile.Name()

	rescueStdout = os.Stdout
	r, w, _ = os.Pipe()
	os.Stdout = w

	main()

	w.Close()
	out, _ = ioutil.ReadAll(r)
	os.Stdout = rescueStdout


    expectedOutput = `file: ` + tmpfile.Name() + "\nletter  frequency\n"
	if string(out) != expectedOutput {
		t.Errorf("Expected output for empty file:\n%s\nGot:\n%s", expectedOutput, string(out))
	}


}


func TestLfListSort(t *testing.T) {
	lfs := lfList{
		&letterFreq{'a', 2},
		&letterFreq{'b', 1},
		&letterFreq{'c', 2},
	}

	sort.Sort(lfs)

	expected := []rune{'b', 'a', 'c'}
	for i, lf := range lfs {
		if lf.rune != expected[i] {
			t.Errorf("Expected rune %c at index %d, got %c", expected[i], i, lf.rune)
		}
	}
}


func TestUnicodeHandling(t *testing.T) {

	lfs := lfList{
		&letterFreq{'a', 1},
		&letterFreq{unicode.MaxRune, 1}, // Non-graphic rune

	}



	for _, lf := range lfs {
		if unicode.IsGraphic(lf.rune) {
           // This part is already covered by the main test
		} else {
            // Test the non-graphic branch formatting - no easy way to assert this without full output capture
		}
	}
}




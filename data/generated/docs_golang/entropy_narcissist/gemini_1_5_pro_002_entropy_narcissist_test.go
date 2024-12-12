package main

import (
	"io/ioutil"
	"os"
	"runtime"
	"testing"
)

func TestEntropy(t *testing.T) {
	// Test case 1: Empty file
	tmpfile, err := ioutil.TempFile("", "test")
	if err != nil {
		t.Fatal(err)
	}
	defer os.Remove(tmpfile.Name())
	if err := tmpfile.Close(); err != nil {
		t.Fatal(err)
	}

	e := entropy(tmpfile.Name())
	if e != 0 {
		t.Errorf("Expected entropy 0 for empty file, got %f", e)
	}

	// Test case 2: File with all same bytes
	tmpfile2, err := ioutil.TempFile("", "test2")
	if err != nil {
		t.Fatal(err)
	}

	defer os.Remove(tmpfile2.Name())
	if _, err := tmpfile2.Write([]byte{1, 1, 1, 1, 1}); err != nil {
		t.Fatal(err)
	}
	if err := tmpfile2.Close(); err != nil {
		t.Fatal(err)
	}
	e = entropy(tmpfile2.Name())
	if e != 0 {
		t.Errorf("Expected entropy 0 for file with all same bytes, got %f", e)

	}

	// Test case 3: File with diverse bytes
	tmpfile3, err := ioutil.TempFile("", "test3")

	if err != nil {
		t.Fatal(err)
	}
	defer os.Remove(tmpfile3.Name())
	if _, err := tmpfile3.Write([]byte{1, 2, 3, 4, 5}); err != nil {
		t.Fatal(err)
	}
	if err := tmpfile3.Close(); err != nil {
		t.Fatal(err)
	}
	e = entropy(tmpfile3.Name())
	if e != 2.321928 { //Pre-calculated value for this specific input
		t.Errorf("Expected entropy 2.321928 for file with diverse bytes, got %f", e)
	}

	// Test case 4: Non-existent file
	e = entropy("nonexistentfile.txt")
	if e != 0 {
        //Expect log.Fatal to trigger a panic. However, current test design does not allow panic capture for specific functions in a straightforward way.
		t.Errorf("Expected log.Fatal to be called for non-existent file, got entropy %f", e)
	}


}

func TestMain(t *testing.M) {


    // Redirect stdout and stderr to capture log.Fatal output.
    oldStdout := os.Stdout
    oldStderr := os.Stderr

    _, w, _, _ := runtime.Caller(0)

    devNull, _ := os.OpenFile(os.DevNull, os.O_WRONLY, 0666)
    os.Stdout = devNull
    os.Stderr = devNull

    exitCode := t.Run()


    os.Stdout = oldStdout
    os.Stderr = oldStderr
	entropy(w)
    os.Exit(exitCode)


}

package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"testing"
)

func TestMain(t *testing.T) {
	// Create a temporary file to simulate /dev/lp0
	tmpfile, err := ioutil.TempFile("", "lp0")
	if err != nil {
		t.Fatal(err)
	}
	defer os.Remove(tmpfile.Name())

	// Backup original os.Create and restore after test
	oldOsCreate := os.Create
	defer func() { os.Create = oldOsCreate }()

	// Mock os.Create to return our temporary file
	os.Create = func(name string) (*os.File, error) {
		if name == "/dev/lp0" {
			return tmpfile, nil
		}
		return nil, fmt.Errorf("unexpected file name: %s", name)
	}


	// Backup original os.Stdout, redirect output to avoid test pollution, and restore after test
	oldStdout := os.Stdout
	defer func() { os.Stdout = oldStdout }()
	os.Stdout, _ = os.Open(os.DevNull)


	// Call the main function
	main()

	// Rewind the temporary file to read its contents
	_, err = tmpfile.Seek(0, 0)
	if err != nil {
		t.Fatal(err)
	}

	// Read the content of the temporary file
	content, err := ioutil.ReadAll(tmpfile)
	if err != nil {
		t.Fatal(err)
	}

	// Check if the expected output was written to the file
	expected := "Hello World!\n"
	if string(content) != expected {
		t.Errorf("Expected output: %q, got: %q", expected, string(content))
	}
}


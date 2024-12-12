package main

import (
	"bytes"
	"io/ioutil"
	"os"
	"testing"
)

func TestGRepNFiles(t *testing.T) {
	files := []string{"a.txt", "b.txt", "c.txt"}
	for _, f := range files {
		createTestFile(t, f, "Goodbye London!\nThis is a test file.")
		defer os.Remove(f)
	}

	gRepNFiles("Goodbye London!", "Hello New York!", files)

	for _, f := range files {
		expectedContent := "Hello New York!\nThis is a test file."
		checkFileContent(t, f, expectedContent)
	}
}

func TestGRepFile(t *testing.T) {
	fn := "test.txt"
	createTestFile(t, fn, "Goodbye London!\nThis is a test file.")
	defer os.Remove(fn)

	err := gRepFile([]byte("Goodbye London!"), []byte("Hello New York!"), fn)
	if err != nil {
		t.Fatal(err)
	}

	expectedContent := "Hello New York!\nThis is a test file."
	checkFileContent(t, fn, expectedContent)
}

func TestGRepFileNoMatch(t *testing.T) {
	fn := "test.txt"
	createTestFile(t, fn, "This is a test file.")
	defer os.Remove(fn)

	err := gRepFile([]byte("Goodbye London!"), []byte("Hello New York!"), fn)
	if err != nil {
		t.Fatal(err)
	}

	expectedContent := "This is a test file."
	checkFileContent(t, fn, expectedContent)
}


func TestGRepFileEmptyFile(t *testing.T) {
	fn := "test.txt"
	createTestFile(t, fn, "")
	defer os.Remove(fn)

	err := gRepFile([]byte("Goodbye London!"), []byte("Hello New York!"), fn)
	if err != nil {
		t.Fatal(err)
	}

	expectedContent := ""
	checkFileContent(t, fn, expectedContent)
}

func createTestFile(t *testing.T, filename, content string) {
	err := ioutil.WriteFile(filename, []byte(content), 0644)
	if err != nil {
		t.Fatal(err)
	}
}

func checkFileContent(t *testing.T, filename, expectedContent string) {
	content, err := ioutil.ReadFile(filename)
	if err != nil {
		t.Fatal(err)
	}
	if string(content) != expectedContent {
		t.Errorf("File content mismatch. Expected: %q, Got: %q", expectedContent, string(content))
	}
}


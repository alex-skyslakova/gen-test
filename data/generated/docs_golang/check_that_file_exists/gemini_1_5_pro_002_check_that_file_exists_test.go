package main

import (
	"io/ioutil"
	"os"
	"path/filepath"
	"testing"
)

func TestFileAndDirectoryExistence(t *testing.T) {
	// Test in current working directory
	cwd, err := os.Getwd()
	if err != nil {
		t.Fatal(err)
	}

	// Create dummy files and directory
	createTestFile(t, filepath.Join(cwd, "input.txt"))
	createTestFile(t, filepath.Join(cwd, "Abdu'l-Bahá.txt")) // Optional test case
	createTestFile(t, filepath.Join(cwd, "empty.txt"), true) // Optional test case
	createTestDir(t, filepath.Join(cwd, "docs"))


	checkExistence(t, "input.txt")
    checkExistence(t, "Abdu'l-Bahá.txt")
    checkExistence(t, "empty.txt")

	checkExistence(t, "docs", true)



	// Test in root directory (requires appropriate permissions) - Using a temporary directory for root tests
	tmpRoot, err := ioutil.TempDir("", "root_test")
	if err != nil {
		t.Fatal(err)
	}
	defer os.RemoveAll(tmpRoot)


	createTestFile(t, filepath.Join(tmpRoot, "input.txt"))
	createTestDir(t, filepath.Join(tmpRoot, "docs"))

	checkExistence(t, filepath.Join(tmpRoot, "input.txt"))
	checkExistence(t, filepath.Join(tmpRoot, "docs"), true)

}


func createTestFile(t *testing.T, path string, empty ...bool) {
	f, err := os.Create(path)
	if err != nil {
		t.Fatal(err)
	}
	defer f.Close()

    if len(empty) > 0 && empty[0]{
        return // leave the file empty
    }
	if _, err := f.WriteString("test content"); err != nil {
		t.Fatal(err)
	}
}

func createTestDir(t *testing.T, path string) {
	if err := os.Mkdir(path, 0755); err != nil {
		t.Fatal(err)
	}
}

func checkExistence(t *testing.T, path string, isDir ...bool) {

	info, err := os.Stat(path)

    if err != nil {
        t.Errorf("Error checking %s: %v", path, err)
        return
    }
	if len(isDir) > 0 && isDir[0] {
		if !info.IsDir() {
			t.Errorf("Expected %s to be a directory, but it's not.", path)
		}
	} else {
		if info.IsDir() {
			t.Errorf("Expected %s to be a file, but it's a directory.", path)
		}
	}

}

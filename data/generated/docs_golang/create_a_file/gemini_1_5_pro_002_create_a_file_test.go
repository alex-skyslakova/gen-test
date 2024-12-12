package main

import (
	"os"
	"testing"
)

func TestCreateFile(t *testing.T) {
	// Test creating file in current directory
	createFile("output.txt")
	if _, err := os.Stat("output.txt"); os.IsNotExist(err) {
		t.Error("File 'output.txt' not created in current directory")
	}
	os.Remove("output.txt")

	// Test creating file in root directory (requires sudo privileges)
	createFile("/output.txt")
	if _, err := os.Stat("/output.txt"); os.IsNotExist(err) {
		t.Error("File '/output.txt' not created in root directory")
	}
	os.Remove("/output.txt")

}

func TestCreateDir(t *testing.T) {

	// Test creating directory in current directory
	createDir("docs")
	if _, err := os.Stat("docs"); os.IsNotExist(err) {
		t.Error("Directory 'docs' not created in current directory")
	}
	os.RemoveAll("docs")


	// Test creating directory in root directory (requires sudo privileges)
	createDir("/docs")
	if _, err := os.Stat("/docs"); os.IsNotExist(err) {
		t.Error("Directory '/docs' not created in root directory")
	}
	os.RemoveAll("/docs")

}


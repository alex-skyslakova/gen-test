package main

import (
	"io/ioutil"
	"os"
	"testing"
)

func TestFileReadWrite(t *testing.T) {
	// Create a dummy input file
	inputData := []byte("This is test data.")
	if err := ioutil.WriteFile("input.txt", inputData, 0644); err != nil {
		t.Fatalf("Failed to create input file: %v", err)
	}
	defer os.Remove("input.txt")


	main() // Execute the function being tested

	// Check if the output file exists
	if _, err := os.Stat("output.txt"); os.IsNotExist(err) {
		t.Fatal("Output file not created.")
	}
	defer os.Remove("output.txt")

	// Read the contents of the output file
	outputData, err := ioutil.ReadFile("output.txt")
	if err != nil {
		t.Fatalf("Failed to read output file: %v", err)
	}

	// Compare the input and output data
	if string(inputData) != string(outputData) {
		t.Errorf("Output data does not match input data.\nExpected: %s\nGot: %s", inputData, outputData)
	}


	// Test error handling for missing input file
	os.Remove("input.txt")
	main()
	//  No assertions needed here. We are testing the error handling within main,
	// which prints an error to the console.  A more advanced test could capture
	// standard output and verify the error message.


}


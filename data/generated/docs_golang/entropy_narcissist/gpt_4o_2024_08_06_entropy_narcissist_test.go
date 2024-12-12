package main

import (
	"io/ioutil"
	"math"
	"os"
	"testing"
)

func TestEntropy(t *testing.T) {
	// Create temporary files for testing
	srcFile, err := ioutil.TempFile("", "src_test")
	if err != nil {
		t.Fatal(err)
	}
	defer os.Remove(srcFile.Name())

	binFile, err := ioutil.TempFile("", "bin_test")
	if err != nil {
		t.Fatal(err)
	}
	defer os.Remove(binFile.Name())

	// Write known content to the source file
	srcContent := []byte("aaaabbbbccccdddd")
	if _, err := srcFile.Write(srcContent); err != nil {
		t.Fatal(err)
	}
	srcFile.Close()

	// Write known content to the binary file
	binContent := []byte{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
	if _, err := binFile.Write(binContent); err != nil {
		t.Fatal(err)
	}
	binFile.Close()

	tests := []struct {
		file     string
		expected float64
	}{
		{srcFile.Name(), calculateExpectedEntropy(srcContent)},
		{binFile.Name(), calculateExpectedEntropy(binContent)},
	}

	for _, tt := range tests {
		t.Run(tt.file, func(t *testing.T) {
			got := entropy(tt.file)
			if math.Abs(got-tt.expected) > 1e-9 {
				t.Errorf("entropy(%s) = %v; want %v", tt.file, got, tt.expected)
			}
		})
	}
}

func calculateExpectedEntropy(data []byte) float64 {
	var f [256]float64
	for _, b := range data {
		f[b]++
	}
	hm := 0.
	for _, c := range f {
		if c > 0 {
			hm += c * math.Log2(c)
		}
	}
	l := float64(len(data))
	return math.Log2(l) - hm/l
}

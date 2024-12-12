package main

import (
	"fmt"
	"math/big"
	"testing"
)

// TestHammingFirstTwenty tests the first 20 Hamming numbers.
func TestHammingFirstTwenty(t *testing.T) {
	expected := []string{
		"1", "2", "3", "4", "5", "6", "8", "9", "10", "12",
		"15", "16", "18", "20", "24", "25", "27", "30", "32", "36",
	}

	h := hamming(20)
	for i, num := range h {
		if num.String() != expected[i] {
			t.Errorf("Expected %s but got %s at index %d", expected[i], num.String(), i)
		}
	}
}

// TestHamming1691st tests the 1691st Hamming number.
func TestHamming1691st(t *testing.T) {
	expected := "2123366400"
	h := hamming(1691)
	if h[1690].String() != expected {
		t.Errorf("Expected %s but got %s for the 1691st Hamming number", expected, h[1690].String())
	}
}

// TestHammingMillionth tests the one millionth Hamming number.
func TestHammingMillionth(t *testing.T) {
	expected := "519312780448000"
	h := hamming(1e6)
	if h[999999].String() != expected {
		t.Errorf("Expected %s but got %s for the one millionth Hamming number", expected, h[999999].String())
	}
}

func main() {
	fmt.Println("Running tests...")
	testing.M()
}

package main

import (
	"testing"
)

func TestMain(t *testing.T) {
	defer func() {
		if r := recover(); r != nil {
			if r != 42 {
				t.Errorf("Expected panic with 42, but got %v", r)
			}
		} else {
			t.Error("Expected panic, but code did not panic")
		}
	}()

	main()
}

package main

import (
	"testing"
)

func TestValidA58(t *testing.T) {
	tests := []struct {
		address string
		valid   bool
	}{
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i", true},  // Valid address
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62j", false}, // Invalid checksum
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62", false},  // Too short
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62ii", false}, // Too long
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62O", false},  // Invalid character 'O'
		{"", false},                                   // Empty string
	}

	for _, test := range tests {
		ok, err := ValidA58([]byte(test.address))
		if ok != test.valid {
			t.Errorf("ValidA58(%q) = %v, want %v, error: %v", test.address, ok, test.valid, err)
		}
	}
}

func TestSet58(t *testing.T) {
	var a A25
	err := a.Set58([]byte("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"))
	if err != nil {
		t.Errorf("Set58 failed with error: %v", err)
	}

	err = a.Set58([]byte("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62O")) // Invalid character 'O'
	if err == nil {
		t.Error("Set58 should have failed with invalid character")
	}

	err = a.Set58([]byte("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62ii")) // Too long
	if err == nil {
		t.Error("Set58 should have failed with too long address")
	}
}

func TestComputeChecksum(t *testing.T) {
	var a A25
	err := a.Set58([]byte("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"))
	if err != nil {
		t.Fatalf("Set58 failed with error: %v", err)
	}

	expectedChecksum := a.EmbeddedChecksum()
	computedChecksum := a.ComputeChecksum()

	if expectedChecksum != computedChecksum {
		t.Errorf("ComputeChecksum() = %x, want %x", computedChecksum, expectedChecksum)
	}
}

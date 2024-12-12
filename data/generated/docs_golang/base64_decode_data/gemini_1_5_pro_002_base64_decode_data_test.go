package main

import (
	"encoding/base64"
	"reflect"
	"testing"
)

func TestBase64Decode(t *testing.T) {
	testCases := []struct {
		input    string
		expected string
		wantErr  bool
	}{
		{
			input:    "Um9zZXR0YSBDb2RlIEJhc2U2NCBkZWNvZGUgZGF0YSB0YXNr", // Rosetta Code Base64 decode data task
			expected: "Rosetta Code Base64 decode data task",
			wantErr:  false,
		},
		{
			input:    "SGVsbG8gV29ybGQh", // Hello World!
			expected: "Hello World!",
			wantErr:  false,
		},
		{
			input:    "Foobar==", // Incorrect padding will not be tested specifically, it is handled by the base64 library.
			expected: "Foobar",
			wantErr:  false,
		},


		{
			input:    "!!!!", // Invalid input
			expected: "",
			wantErr:  true,
		},

		{
			input:    "", // Empty input
			expected: "",
			wantErr:  false,
		},
	}

	for _, tc := range testCases {
		decoded, err := base64.StdEncoding.DecodeString(tc.input)
		if (err != nil) != tc.wantErr {
			t.Errorf("Input: %s, Expected error: %v, Got error: %v", tc.input, tc.wantErr, err)
		}

		if !tc.wantErr && !reflect.DeepEqual(string(decoded), tc.expected) {
			t.Errorf("Input: %s, Expected: %s, Got: %s", tc.input, tc.expected, string(decoded))
		}
	}
}


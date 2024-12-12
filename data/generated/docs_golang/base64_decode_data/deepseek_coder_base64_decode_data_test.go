package main

import (
    "encoding/base64"
    "testing"
)

func TestBase64Decode(t *testing.T) {
    testCases := []struct {
        name     string
        input    string
        expected string
        shouldErr bool
    }{
        {
            name:     "Valid Base64 Encoding",
            input:    "UmVzb2N0YWRhIENvZGUgQmFzZTY0IGRlY29kZSBkYXRhIHRhc2s=",
            expected: "Rosetta Code Base64 decode data task",
            shouldErr: false,
        },
        {
            name:     "Empty String",
            input:    "",
            expected: "",
            shouldErr: false,
        },
        {
            name:     "Invalid Base64 Encoding",
            input:    "InvalidBase64String",
            expected: "",
            shouldErr: true,
        },
        {
            name:     "Base64 Encoding with Padding",
            input:    "SGVsbG8gd29ybGQ=",
            expected: "Hello world",
            shouldErr: false,
        },
        {
            name:     "Base64 Encoding without Padding",
            input:    "SGVsbG8gd29ybGQ",
            expected: "Hello world",
            shouldErr: false,
        },
        {
            name:     "Base64 Encoding with Extra Padding",
            input:    "SGVsbG8gd29ybGQ===",
            expected: "",
            shouldErr: true,
        },
    }

    for _, tc := range testCases {
        t.Run(tc.name, func(t *testing.T) {
            decoded, err := base64.StdEncoding.DecodeString(tc.input)
            if tc.shouldErr {
                if err == nil {
                    t.Errorf("Expected an error but got none")
                }
            } else {
                if err != nil {
                    t.Errorf("Unexpected error: %v", err)
                }
                if string(decoded) != tc.expected {
                    t.Errorf("Expected %q, got %q", tc.expected, string(decoded))
                }
            }
        })
    }
}

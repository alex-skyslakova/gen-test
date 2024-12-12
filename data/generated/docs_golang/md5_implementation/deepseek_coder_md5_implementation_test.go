package main

import (
    "encoding/hex"
    "testing"
)

func TestMD5(t *testing.T) {
    for _, tc := range testCases {
        result := md5(tc.string)
        expected, _ := hex.DecodeString(tc.hashCode)

        if !bytesEqual(result[:], expected) {
            t.Errorf("MD5(%q) = %x; want %x", tc.string, result, expected)
        }
    }
}

func bytesEqual(a, b []byte) bool {
    if len(a) != len(b) {
        return false
    }
    for i := range a {
        if a[i] != b[i] {
            return false
        }
    }
    return true
}

package main

import (
	"encoding/hex"
	"testing"
)

func TestMD5(t *testing.T) {
	for _, tc := range testCases {
		t.Run(tc.string, func(t *testing.T) {
			result := md5(tc.string)
			resultHex := hex.EncodeToString(result[:])
			if resultHex != tc.hashCode {
				t.Errorf("MD5(%q) = %s; want %s", tc.string, resultHex, tc.hashCode)
			}
		})
	}
}

func BenchmarkMD5(b *testing.B) {
	for i := 0; i < b.N; i++ {
		for _, tc := range testCases {
			md5(tc.string)
		}
	}
}

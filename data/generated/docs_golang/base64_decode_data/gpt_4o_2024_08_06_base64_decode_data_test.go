package main

import (
	"encoding/base64"
	"testing"
)

func TestBase64EncodingDecoding(t *testing.T) {
	originalMessage := "Rosetta Code Base64 decode data task"
	encodedMessage := base64.StdEncoding.EncodeToString([]byte(originalMessage))

	// Test if encoding is correct
	expectedEncodedMessage := "Um9zZXR0YSBDb2RlIEJhc2U2NCBkZWNvZGUgZGF0YSB0YXNr"
	if encodedMessage != expectedEncodedMessage {
		t.Errorf("Encoding failed. Expected: %s, Got: %s", expectedEncodedMessage, encodedMessage)
	}

	// Test if decoding is correct
	decodedMessage, err := base64.StdEncoding.DecodeString(encodedMessage)
	if err != nil {
		t.Errorf("Decoding failed with error: %v", err)
	}

	if string(decodedMessage) != originalMessage {
		t.Errorf("Decoding failed. Expected: %s, Got: %s", originalMessage, string(decodedMessage))
	}
}

func TestBase64DecodingWithIncorrectPadding(t *testing.T) {
	// Test decoding with incorrect padding
	incorrectEncodedMessage := "Um9zZXR0YSBDb2RlIEJhc2U2NCBkZWNvZGUgZGF0YSB0YXNr==="
	_, err := base64.StdEncoding.DecodeString(incorrectEncodedMessage)
	if err == nil {
		t.Error("Expected error for incorrect padding, but got none")
	}
}

func TestBase64DecodingWithInvalidCharacters(t *testing.T) {
	// Test decoding with invalid characters
	invalidEncodedMessage := "Um9zZXR0YSBDb2RlIEJhc2U2NCBkZWNvZGUgZGF0YSB0YXNr$"
	_, err := base64.StdEncoding.DecodeString(invalidEncodedMessage)
	if err == nil {
		t.Error("Expected error for invalid characters, but got none")
	}
}

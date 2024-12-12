package main

import (
	"io/ioutil"
	"net/http"
	"net/http/httptest"
	"strings"
	"testing"
)

func TestHelloWorldWebServer(t *testing.T) {
	// Create a new server using the handler function
	handler := http.HandlerFunc(func(w http.ResponseWriter, req *http.Request) {
		fmt.Fprintln(w, "Goodbye, World!")
	})

	// Create a test server with the handler
	server := httptest.NewServer(handler)
	defer server.Close()

	// Make a request to the test server
	resp, err := http.Get(server.URL)
	if err != nil {
		t.Fatalf("Failed to make request: %v", err)
	}
	defer resp.Body.Close()

	// Check the response status code
	if resp.StatusCode != http.StatusOK {
		t.Errorf("Expected status code %d, got %d", http.StatusOK, resp.StatusCode)
	}

	// Read the response body
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		t.Fatalf("Failed to read response body: %v", err)
	}

	// Check the response body content
	expected := "Goodbye, World!\n"
	if string(body) != expected {
		t.Errorf("Expected response body '%s', got '%s'", expected, string(body))
	}
}

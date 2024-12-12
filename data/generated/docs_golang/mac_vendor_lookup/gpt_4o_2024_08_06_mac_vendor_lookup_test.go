package main

import (
	"net/http"
	"net/http/httptest"
	"testing"
	"io/ioutil"
	"strings"
)

// Mock server to simulate the API responses
func mockServer(response string, statusCode int) *httptest.Server {
	return httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		w.WriteHeader(statusCode)
		w.Write([]byte(response))
	}))
}

func TestMacLookUp(t *testing.T) {
	// Test case 1: Valid MAC address with a known manufacturer
	server := mockServer("Apple, Inc.", http.StatusOK)
	defer server.Close()
	
	originalURL := "http://api.macvendors.com/"
	http.DefaultClient.Transport = &http.Transport{
		Proxy: func(req *http.Request) (*http.Response, error) {
			req.URL.Scheme = "http"
			req.URL.Host = strings.TrimPrefix(server.URL, "http://")
			return nil, nil
		},
	}
	
	result := macLookUp("FC-A1-3E")
	expected := "Apple, Inc."
	if result != expected {
		t.Errorf("Expected %s, got %s", expected, result)
	}

	// Test case 2: Valid MAC address with no known manufacturer
	server = mockServer("", http.StatusOK)
	defer server.Close()
	
	result = macLookUp("00:00:00:00:00:00")
	expected = "N/A"
	if result != expected {
		t.Errorf("Expected %s, got %s", expected, result)
	}

	// Test case 3: API returns an error (e.g., too many requests)
	server = mockServer(`{"errors":{"detail":"Too Many Requests","message":"Please slow down your requests or upgrade your plan at https://macvendors.com"}}`, http.StatusTooManyRequests)
	defer server.Close()
	
	result = macLookUp("FC:FB:FB:01:FA:21")
	if result != "" {
		t.Errorf("Expected null result, got %s", result)
	}

	// Test case 4: Network error (simulate by using an invalid URL)
	http.DefaultClient.Transport = &http.Transport{
		Proxy: func(req *http.Request) (*http.Response, error) {
			return nil, fmt.Errorf("network error")
		},
	}
	
	result = macLookUp("BC:5F:F4")
	if result != "" {
		t.Errorf("Expected null result, got %s", result)
	}
}

package main

import (
	"net/http"
	"net/http/httptest"
	"testing"
	"io/ioutil"
)

func TestMacLookup(t *testing.T) {
	testCases := []struct {
		mac        string
		serverResponse string
		serverStatusCode int
		expected    string
	}{
		{mac: "FC-A1-3E", serverResponse: "TP-LINK TECHNOLOGIES CO.,LTD.", serverStatusCode: 200, expected: "TP-LINK TECHNOLOGIES CO.,LTD."},
		{mac: "FC:FB:FB:01:FA:21", serverResponse: "Private", serverStatusCode: 200, expected: "Private"},
		{mac: "BC:5F:F4", serverResponse: "N/A", serverStatusCode: 200, expected: "N/A"},
		{mac: "INVALID_MAC", serverResponse: "N/A", serverStatusCode: 200, expected: "N/A"},
		{mac: "ERROR_MAC", serverResponse: "", serverStatusCode: 500, expected: ""}, // Simulate API error
		{mac: "RATE_LIMIT_MAC", serverResponse: `{"errors":{"detail":"Too Many Requests","message":"Please slow down your requests"}}`, serverStatusCode: 429, expected: ""}, // Simulate rate limiting
	}

	for _, tc := range testCases {
		server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			w.WriteHeader(tc.serverStatusCode)
			w.Write([]byte(tc.serverResponse))
		}))
		defer server.Close()

		oldGet := http.Get // Store the original http.Get
		defer func() { http.Get = oldGet }() // Restore the original http.Get after the test

        // Mock the http.Get function to use the test server
        http.Get = func(url string) (*http.Response, error) {
            return http.Get(server.URL + "/" + tc.mac)
        }



		result := macLookUp(tc.mac)

        // Need to handle the case where we expect an empty string indicating error. 
        if tc.expected == "" && result != "" {
            t.Errorf("For MAC %s, expected empty string (null) but got %s", tc.mac, result)
        } else if tc.expected != "" && result != tc.expected{
            t.Errorf("For MAC %s, expected %s but got %s", tc.mac, tc.expected, result)
        }
	}
}



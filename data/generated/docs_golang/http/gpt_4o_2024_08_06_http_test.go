package main

import (
    "io"
    "net/http"
    "net/http/httptest"
    "os"
    "testing"
)

// TestMainFunction tests the main function which fetches and prints the content of a URL.
func TestMainFunction(t *testing.T) {
    // Create a test server to mock the HTTP GET request
    server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
        w.WriteHeader(http.StatusOK)
        w.Write([]byte("User-agent: *\nDisallow: /"))
    }))
    defer server.Close()

    // Redirect stdout to capture the output
    originalStdout := os.Stdout
    r, w, _ := os.Pipe()
    os.Stdout = w

    // Modify the URL in the main function to use the test server's URL
    originalURL := "http://rosettacode.org/robots.txt"
    defer func() { http.Get = http.Get }() // Restore original http.Get after test
    http.Get = func(url string) (*http.Response, error) {
        if url == originalURL {
            return http.Get(server.URL)
        }
        return nil, nil
    }

    // Call the main function
    main()

    // Restore stdout and close the pipe
    w.Close()
    os.Stdout = originalStdout

    // Read the captured output
    var buf io.ReadWriter
    io.Copy(buf, r)

    // Check if the output is as expected
    expectedOutput := "User-agent: *\nDisallow: /"
    if buf.String() != expectedOutput {
        t.Errorf("Expected output %q, but got %q", expectedOutput, buf.String())
    }
}

package main

import (
	"bytes"
	"errors"
	"io"
	"log"
	"net/http"
	"net/http/httptest"
	"os"
	"testing"
)

func Test_main(t *testing.T) {
	tests := []struct {
		name       string
		server     *httptest.Server
		wantErr    bool
		wantOutput string
	}{
		{
			name: "successful request",
			server: httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
				w.WriteHeader(http.StatusOK)
				w.Write([]byte("Test content"))
			})),
			wantErr:    false,
			wantOutput: "Test content",
		},
		{
			name: "server error",
			server: httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
				w.WriteHeader(http.StatusInternalServerError)
			})),
			wantErr: true,
		},
		{
			name:    "request error",
			server:  nil, // Simulate a non-existent server
			wantErr: true,
		},

	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			oldStdout := os.Stdout
			r, w, _ := os.Pipe()
			os.Stdout = w

			originalGet := http.Get
			defer func() {
				http.Get = originalGet
				os.Stdout = oldStdout

				if tt.server != nil {
					tt.server.Close()
				}
			}()



			if tt.server != nil {
				http.Get = func(url string) (*http.Response, error) {
					return http.Get(tt.server.URL)
				}
			} else {
				http.Get = func(url string) (*http.Response, error) {
					return nil, errors.New("simulated get error")

				}
			}

			// Suppress log output during the test
			log.SetOutput(io.Discard)

			main()

			w.Close()
			var buf bytes.Buffer
			io.Copy(&buf, r)
			output := buf.String()

			if (err := recover(); (err != nil)) != tt.wantErr {
				t.Errorf("main() error = %v, wantErr %v", err, tt.wantErr)
				return

			}

			if !tt.wantErr && output != tt.wantOutput {
				t.Errorf("Unexpected Output:\nGot:\n%s\nWant:\n%s\n", output, tt.wantOutput)
			}

		})
	}
}

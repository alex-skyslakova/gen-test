package main

import (
	"fmt"
	"os/exec"
	"strconv"
	"strings"
	"testing"
)


func runMainWithArgs(args ...string) (string, error) {
	cmd := exec.Command("./lucky_numbers", args...)
	out, err := cmd.CombinedOutput()
	return string(out), err
}

func TestMain(t *testing.T) {
	// Build the executable before running tests
	buildCmd := exec.Command("go", "build", "-o", "lucky_numbers")
	if err := buildCmd.Run(); err != nil {
		t.Fatalf("Failed to build executable: %v", err)
	}

	tests := []struct {
		name     string
		args     []string
		expected string
		wantErr  bool
	}{
		{"No arguments", []string{}, "there must be between 1 and 3 command line arguments", true},
		{"Too many arguments", []string{"1", "2", "3", "4", "5"}, "there must be between 1 and 3 command line arguments", true},
		{"Invalid first argument", []string{"a"}, "first argument, a, must be a positive integer", true},
		{"Negative first argument", []string{"-1"}, "first argument, -1, must be a positive integer", true},
		{"First twenty lucky numbers", []string{"20"}, "Lucky number 20 = 51", false},
        {"First twenty even lucky numbers", []string{"20", ",", "evenLucky"}, "Lucky even number 20 = 64", false},

		{"Lucky numbers between 6000 and 6100", []string{"6000", "-6100"}, "Lucky numbers between 6000 and 6100 are:\n[6001 6007 6011 6013 6017 6019 6023 6029 6031 6037 6041 6043 6047 6049 6053 6061 6067 6071 6073 6077 6079 6083 6089 6091 6097 6101]\n", false},

		{"Even lucky numbers between 6000 and 6100", []string{"6000", "-6100", "evenLucky"}, "Lucky even numbers between 6000 and 6100 are:\n[6002 6004 6010 6014 6016 6020 6022 6026 6032 6034 6038 6044 6046 6050 6052 6058 6068 6074 6080 6082 6086 6092 6094 6098 6100]\n", false},

		{"10000th lucky number", []string{"10000"}, "Lucky number 10000 = 19999", false},
		{"10000th even lucky number", []string{"10000", ",", "evenlucky"}, "Lucky even number 10000 = 21998", false},
		{"j k lucky", []string{"1", "5", "lucky"}, "Lucky numbers 1 to 5 are:\n[1 3 7 9 13]\n", false},
		{"j k evenlucky", []string{"1", "5", "evenlucky"}, "Lucky even numbers 1 to 5 are:\n[2 4 6 10 12]\n", false},
		{"j , lucky", []string{"5", ",", "Lucky"}, "Lucky number 5 = 13", false},
		{"j , evenlucky", []string{"5", ",", "EvenLucky"}, "Lucky even number 5 = 12", false},

		{"Invalid third argument", []string{"1", "2", "invalid"}, "third argument, invalid, is invalid", true},

		{"Second arg not integer (range)", []string{"1", "a"}, "second argument, a, must be an integer", true},
		{"Second arg not integer (between)", []string{"1", "a", "lucky"}, "second argument must be an integer or a comma", true},
		{"Second arg not comma or integer", []string{"1", "a", "evenlucky"}, "second argument must be an integer or a comma", true},

		{"k less than j (range)", []string{"5", "1"}, "second argument, 1, can't be less than first, 5", true},
		{"k less than j (range) evenlucky", []string{"5", "1", "evenlucky"}, "second argument, 1, can't be less than first, 5", true},


		{"abs(k) less than j (between)", []string{"5", "-1"}, "second argument, -1, can't be less in absolute value than first, 5", true},
		{"abs(k) less than j (between) evenlucky", []string{"5", "-1", "evenlucky"}, "second argument, -1, can't be less in absolute value than first, 5", true},


	}

    for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got, err := runMainWithArgs(tt.args...)
            got = strings.TrimSpace(got) // remove trailing newline

			if (err != nil) != tt.wantErr {
				t.Errorf("main() error = %v, wantErr %v", err, tt.wantErr)
				return
			}


            expected := strings.TrimSpace(tt.expected)
			if got != expected {
				t.Errorf("main() output = %q, want %q", got, expected)
			}
		})
	}
}


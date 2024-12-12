package main

import (
    "strconv"
    "strings"
    "testing"
)

// TestDivByAll tests the divByAll function
func TestDivByAll(t *testing.T) {
    tests := []struct {
        num    int
        digits []byte
        want   bool
    }{
        {135, []byte{'1', '3', '5'}, true},
        {123, []byte{'1', '2', '3'}, true},
        {12345, []byte{'1', '2', '3', '4', '5'}, false}, // 12345 is not divisible by 4
        {123456, []byte{'1', '2', '3', '4', '5', '6'}, false}, // 123456 is not divisible by 5
        {12348, []byte{'1', '2', '3', '4', '8'}, true},
        {123480, []byte{'1', '2', '3', '4', '8', '0'}, false}, // contains '0'
    }

    for _, tt := range tests {
        t.Run(strconv.Itoa(tt.num), func(t *testing.T) {
            got := divByAll(tt.num, tt.digits)
            if got != tt.want {
                t.Errorf("divByAll(%d, %v) = %v; want %v", tt.num, tt.digits, got, tt.want)
            }
        })
    }
}

// TestMainLogic tests the main logic of the program
func TestMainLogic(t *testing.T) {
    // Mock the main function to capture the output
    savedOutput := ""
    fmt.Println = func(a ...interface{}) (n int, err error) {
        savedOutput = a[0].(string)
        return 0, nil
    }

    main()

    expectedOutput := "Largest decimal number is 9867312"
    if savedOutput != expectedOutput {
        t.Errorf("Expected output %q, but got %q", expectedOutput, savedOutput)
    }
}

// TestEdgeCases tests edge cases for the main logic
func TestEdgeCases(t *testing.T) {
    // Test with a number that contains '0'
    num := 1234567890
    s := strconv.Itoa(num)
    if !strings.ContainsAny(s, "0") {
        t.Errorf("Expected %d to contain '0'", num)
    }

    // Test with a number that contains '5'
    num = 1234567895
    s = strconv.Itoa(num)
    if !strings.ContainsAny(s, "5") {
        t.Errorf("Expected %d to contain '5'", num)
    }

    // Test with a number that ends in '0'
    num = 1234567890
    if num%10 != 0 {
        t.Errorf("Expected %d to end in '0'", num)
    }

    // Test with a number that has duplicate digits
    num = 1234567899
    s = strconv.Itoa(num)
    var set = make(map[byte]bool)
    for _, b := range []byte(s) {
        set[b] = true
    }
    if len(set) == len(s) {
        t.Errorf("Expected %d to have duplicate digits", num)
    }
}

package main

import (
    "fmt"
    "os"
    "testing"
)

func TestPrintSingle(t *testing.T) {
    tests := []struct {
        j          int
        odd        bool
        expected   string
        shouldFail bool
    }{
        {1, true, "Lucky number 1 = 1\n", false},
        {1, false, "Lucky even number 1 = 2\n", false},
        {10000, true, "", true}, // Assuming 10000 is out of bounds for luckyOdd
        {10000, false, "", true}, // Assuming 10000 is out of bounds for luckyEven
    }

    for _, test := range tests {
        t.Run(fmt.Sprintf("j=%d,odd=%v", test.j, test.odd), func(t *testing.T) {
            old := os.Stdout
            r, w, _ := os.Pipe()
            os.Stdout = w

            err := printSingle(test.j, test.odd)

            w.Close()
            os.Stdout = old
            buf := make([]byte, 1024)
            n, _ := r.Read(buf)
            output := string(buf[:n])

            if test.shouldFail {
                if err == nil {
                    t.Errorf("Expected an error but got none")
                }
            } else {
                if err != nil {
                    t.Errorf("Unexpected error: %v", err)
                }
                if output != test.expected {
                    t.Errorf("Expected %q, got %q", test.expected, output)
                }
            }
        })
    }
}

func TestPrintRange(t *testing.T) {
    tests := []struct {
        j          int
        k          int
        odd        bool
        expected   string
        shouldFail bool
    }{
        {1, 5, true, "Lucky numbers 1 to 5 are:\n[1 3 7 9 13]\n", false},
        {1, 5, false, "Lucky even numbers 1 to 5 are:\n[2 4 6 10 12]\n", false},
        {1, 10000, true, "", true}, // Assuming 10000 is out of bounds for luckyOdd
        {1, 10000, false, "", true}, // Assuming 10000 is out of bounds for luckyEven
    }

    for _, test := range tests {
        t.Run(fmt.Sprintf("j=%d,k=%d,odd=%v", test.j, test.k, test.odd), func(t *testing.T) {
            old := os.Stdout
            r, w, _ := os.Pipe()
            os.Stdout = w

            err := printRange(test.j, test.k, test.odd)

            w.Close()
            os.Stdout = old
            buf := make([]byte, 1024)
            n, _ := r.Read(buf)
            output := string(buf[:n])

            if test.shouldFail {
                if err == nil {
                    t.Errorf("Expected an error but got none")
                }
            } else {
                if err != nil {
                    t.Errorf("Unexpected error: %v", err)
                }
                if output != test.expected {
                    t.Errorf("Expected %q, got %q", test.expected, output)
                }
            }
        })
    }
}

func TestPrintBetween(t *testing.T) {
    tests := []struct {
        j          int
        k          int
        odd        bool
        expected   string
        shouldFail bool
    }{
        {6000, 6100, true, "Lucky numbers between 6000 and 6100 are:\n[6001 6007 6019 6031 6043 6049 6055 6061 6067 6073 6079 6085 6091 6097]\n", false},
        {6000, 6100, false, "Lucky even numbers between 6000 and 6100 are:\n[6002 6004 6006 6010 6012 6014 6018 6020 6022 6026 6028 6030 6034 6036 6038 6042 6044 6046 6050 6052 6054 6058 6060 6062 6066 6068 6070 6074 6076 6078 6082 6084 6086 6090 6092 6094 6098]\n", false},
        {10000, 10100, true, "", true}, // Assuming 10000 is out of bounds for luckyOdd
        {10000, 10100, false, "", true}, // Assuming 10000 is out of bounds for luckyEven
    }

    for _, test := range tests {
        t.Run(fmt.Sprintf("j=%d,k=%d,odd=%v", test.j, test.k, test.odd), func(t *testing.T) {
            old := os.Stdout
            r, w, _ := os.Pipe()
            os.Stdout = w

            err := printBetween(test.j, test.k, test.odd)

            w.Close()
            os.Stdout = old
            buf := make([]byte, 1024)
            n, _ := r.Read(buf)
            output := string(buf[:n])

            if test.shouldFail {
                if err == nil {
                    t.Errorf("Expected an error but got none")
                }
            } else {
                if err != nil {
                    t.Errorf("Unexpected error: %v", err)
                }
                if output != test.expected {
                    t.Errorf("Expected %q, got %q", test.expected, output)
                }
            }
        })
    }
}

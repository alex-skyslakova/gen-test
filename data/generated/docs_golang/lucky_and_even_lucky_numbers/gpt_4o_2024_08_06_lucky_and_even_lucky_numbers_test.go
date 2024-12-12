package main

import (
    "os/exec"
    "strings"
    "testing"
)

func TestLuckyNumbers(t *testing.T) {
    // Test showing the first twenty lucky numbers
    cmd := exec.Command("go", "run", "lucky_and_even_lucky_numbers.go", "1", "20")
    output, err := cmd.CombinedOutput()
    if err != nil {
        t.Fatalf("Failed to execute command: %s", err)
    }
    expected := "Lucky numbers 1 to 20 are:\n[1 3 7 9 13 15 21 25 31 33 37 43 49 51 63 67 69 73 75 79]"
    if strings.TrimSpace(string(output)) != expected {
        t.Errorf("Expected %s, but got %s", expected, output)
    }

    // Test showing the first twenty even lucky numbers
    cmd = exec.Command("go", "run", "lucky_and_even_lucky_numbers.go", "1", "20", "evenLucky")
    output, err = cmd.CombinedOutput()
    if err != nil {
        t.Fatalf("Failed to execute command: %s", err)
    }
    expected = "Lucky even numbers 1 to 20 are:\n[2 4 6 10 12 18 20 22 26 28 34 36 38 42 44 50 52 54 58 60]"
    if strings.TrimSpace(string(output)) != expected {
        t.Errorf("Expected %s, but got %s", expected, output)
    }

    // Test showing all lucky numbers between 6,000 and 6,100 (inclusive)
    cmd = exec.Command("go", "run", "lucky_and_even_lucky_numbers.go", "6000", "-6100")
    output, err = cmd.CombinedOutput()
    if err != nil {
        t.Fatalf("Failed to execute command: %s", err)
    }
    expected = "Lucky numbers between 6000 and 6100 are:\n[6001 6003 6007 6013 6017 6021 6025 6029 6031 6033 6037 6041 6043 6047 6051 6053 6057 6061 6063 6067 6071 6073 6077 6081 6083 6087 6091 6093 6097 6101]"
    if strings.TrimSpace(string(output)) != expected {
        t.Errorf("Expected %s, but got %s", expected, output)
    }

    // Test showing all even lucky numbers in the same range as above
    cmd = exec.Command("go", "run", "lucky_and_even_lucky_numbers.go", "6000", "-6100", "evenLucky")
    output, err = cmd.CombinedOutput()
    if err != nil {
        t.Fatalf("Failed to execute command: %s", err)
    }
    expected = "Lucky even numbers between 6000 and 6100 are:\n[6002 6004 6006 6010 6012 6018 6020 6022 6026 6028 6034 6036 6038 6042 6044 6050 6052 6054 6058 6060 6066 6068 6070 6074 6076 6082 6084 6086 6090 6092 6098 6100]"
    if strings.TrimSpace(string(output)) != expected {
        t.Errorf("Expected %s, but got %s", expected, output)
    }

    // Test showing the 10,000th lucky number
    cmd = exec.Command("go", "run", "lucky_and_even_lucky_numbers.go", "10000")
    output, err = cmd.CombinedOutput()
    if err != nil {
        t.Fatalf("Failed to execute command: %s", err)
    }
    expected = "Lucky number 10000 = 114083"
    if strings.TrimSpace(string(output)) != expected {
        t.Errorf("Expected %s, but got %s", expected, output)
    }

    // Test showing the 10,000th even lucky number
    cmd = exec.Command("go", "run", "lucky_and_even_lucky_numbers.go", "10000", ",", "evenLucky")
    output, err = cmd.CombinedOutput()
    if err != nil {
        t.Fatalf("Failed to execute command: %s", err)
    }
    expected = "Lucky even number 10000 = 114084"
    if strings.TrimSpace(string(output)) != expected {
        t.Errorf("Expected %s, but got %s", expected, output)
    }
}

func TestCommandLineErrors(t *testing.T) {
    // Test missing arguments
    cmd := exec.Command("go", "run", "lucky_and_even_lucky_numbers.go")
    output, err := cmd.CombinedOutput()
    if err == nil || !strings.Contains(string(output), "there must be between 1 and 3 command line arguments") {
        t.Errorf("Expected error for missing arguments, but got %s", output)
    }

    // Test too many arguments
    cmd = exec.Command("go", "run", "lucky_and_even_lucky_numbers.go", "1", "2", "3", "4")
    output, err = cmd.CombinedOutput()
    if err == nil || !strings.Contains(string(output), "there must be between 1 and 3 command line arguments") {
        t.Errorf("Expected error for too many arguments, but got %s", output)
    }

    // Test illegal number
    cmd = exec.Command("go", "run", "lucky_and_even_lucky_numbers.go", "abc")
    output, err = cmd.CombinedOutput()
    if err == nil || !strings.Contains(string(output), "first argument, abc, must be a positive integer") {
        t.Errorf("Expected error for illegal number, but got %s", output)
    }

    // Test misspelled argument
    cmd = exec.Command("go", "run", "lucky_and_even_lucky_numbers.go", "1", "2", "luckyy")
    output, err = cmd.CombinedOutput()
    if err == nil || !strings.Contains(string(output), "third argument, luckyy, is invalid") {
        t.Errorf("Expected error for misspelled argument, but got %s", output)
    }
}

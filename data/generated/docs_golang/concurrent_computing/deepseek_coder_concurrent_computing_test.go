package main

import (
    "testing"
    "time"
    "golang.org/x/exp/rand"
)

func TestConcurrentComputing(t *testing.T) {
    // Capture the output of the main function
    output := make(chan string, 3)
    oldPrintln := fmt.Println
    fmt.Println = func(a ...interface{}) (n int, err error) {
        output <- a[0].(string)
        return 0, nil
    }
    defer func() { fmt.Println = oldPrintln }()

    // Run the main function
    go main()

    // Collect the output
    words := []string{"Enjoy", "Rosetta", "Code"}
    received := make(map[string]bool)
    for i := 0; i < len(words); i++ {
        select {
        case word := <-output:
            received[word] = true
        case <-time.After(2 * time.Second):
            t.Fatal("Timeout waiting for output")
        }
    }

    // Verify that all words were received
    for _, word := range words {
        if !received[word] {
            t.Errorf("Expected word %q was not received", word)
        }
    }
}

func TestRandomDelay(t *testing.T) {
    seed := uint64(time.Now().UnixNano())
    delays := make([]time.Duration, 3)
    for i := 0; i < 3; i++ {
        r := rand.New(rand.NewSource(seed + uint64(i)))
        delays[i] = time.Duration(r.Int63n(1e9))
    }

    // Ensure that the delays are different
    if delays[0] == delays[1] || delays[1] == delays[2] || delays[0] == delays[2] {
        t.Errorf("Delays should be different: %v", delays)
    }
}

func TestMainOutputOrder(t *testing.T) {
    // Capture the output of the main function
    output := make(chan string, 3)
    oldPrintln := fmt.Println
    fmt.Println = func(a ...interface{}) (n int, err error) {
        output <- a[0].(string)
        return 0, nil
    }
    defer func() { fmt.Println = oldPrintln }()

    // Run the main function
    go main()

    // Collect the output
    words := []string{"Enjoy", "Rosetta", "Code"}
    received := make([]string, 0, 3)
    for i := 0; i < len(words); i++ {
        select {
        case word := <-output:
            received = append(received, word)
        case <-time.After(2 * time.Second):
            t.Fatal("Timeout waiting for output")
        }
    }

    // Verify that the order is random
    if received[0] == received[1] || received[1] == received[2] || received[0] == received[2] {
        t.Errorf("Output order should be random: %v", received)
    }
}

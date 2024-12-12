package main

import (
    "net"
    "testing"
    "time"
)

func TestSingleInstance(t *testing.T) {
    // Test case 1: No other instance running
    go func() {
        main()
    }()

    // Allow some time for the first instance to start
    time.Sleep(1 * time.Second)

    // Test case 2: Attempt to start another instance
    conn, err := net.Dial(lNet, lAddr)
    if err == nil {
        conn.Close()
        t.Error("Expected an error when trying to start another instance, but got none")
    }

    // Allow some time for the first instance to finish
    time.Sleep(11 * time.Second)
}

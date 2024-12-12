package main

import (
    "testing"
    "strings"
    "golang.org/x/exp/rand"
    "time"
)

func TestConcurrentOutput(t *testing.T) {
    words := []string{"Enjoy", "Rosetta", "Code"}
    seed := uint64(time.Now().UnixNano())
    q := make(chan string)
    for i, w := range words {
        go func(w string, seed uint64) {
            r := rand.New(rand.NewSource(seed))
            time.Sleep(time.Duration(r.Int63n(1e9)))
            q <- w
        }(w, seed+uint64(i))
    }

    receivedWords := make([]string, 0, len(words))
    for i := 0; i < len(words); i++ {
        receivedWords = append(receivedWords, <-q)
    }

    // Check if all words are received
    for _, word := range words {
        if !contains(receivedWords, word) {
            t.Errorf("Expected word %s not found in output", word)
        }
    }

    // Check if there are no extra words
    if len(receivedWords) != len(words) {
        t.Errorf("Expected %d words, but got %d", len(words), len(receivedWords))
    }
}

func contains(slice []string, item string) bool {
    for _, s := range slice {
        if s == item {
            return true
        }
    }
    return false
}

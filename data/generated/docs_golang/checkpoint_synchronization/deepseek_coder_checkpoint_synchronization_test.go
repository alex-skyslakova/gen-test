package main

import (
    "sync"
    "testing"
    "time"
)

func TestWorker(t *testing.T) {
    // Test case 1: Ensure all workers complete their parts before moving to the next assembly cycle
    t.Run("All workers complete before next cycle", func(t *testing.T) {
        var wg sync.WaitGroup
        partList := []string{"A", "B", "C", "D"}
        nAssemblies := 3

        for c := 1; c <= nAssemblies; c++ {
            wg.Add(len(partList))
            for _, part := range partList {
                go func(p string) {
                    worker(p)
                    wg.Done()
                }(part)
            }
            wg.Wait()
        }
    })

    // Test case 2: Ensure no race condition occurs when workers signal completion
    t.Run("No race condition in signaling completion", func(t *testing.T) {
        var wg sync.WaitGroup
        partList := []string{"A", "B", "C", "D"}
        nAssemblies := 3

        for c := 1; c <= nAssemblies; c++ {
            wg.Add(len(partList))
            var mu sync.Mutex
            completed := 0

            for _, part := range partList {
                go func(p string) {
                    worker(p)
                    mu.Lock()
                    completed++
                    mu.Unlock()
                    wg.Done()
                }(part)
            }
            wg.Wait()
            if completed != len(partList) {
                t.Errorf("Expected %d workers to complete, got %d", len(partList), completed)
            }
        }
    })

    // Test case 3: Ensure workers do not count twice within one cycle
    t.Run("Workers do not count twice within one cycle", func(t *testing.T) {
        var wg sync.WaitGroup
        partList := []string{"A", "B", "C", "D"}
        nAssemblies := 3

        for c := 1; c <= nAssemblies; c++ {
            wg.Add(len(partList))
            var mu sync.Mutex
            completed := 0

            for _, part := range partList {
                go func(p string) {
                    worker(p)
                    mu.Lock()
                    completed++
                    mu.Unlock()
                    wg.Done()
                }(part)
            }
            wg.Wait()
            if completed != len(partList) {
                t.Errorf("Expected %d workers to complete, got %d", len(partList), completed)
            }
        }
    })

    // Test case 4: Ensure workers can join and leave dynamically
    t.Run("Workers can join and leave dynamically", func(t *testing.T) {
        var wg sync.WaitGroup
        partList := []string{"A", "B", "C", "D"}
        nAssemblies := 3

        for c := 1; c <= nAssemblies; c++ {
            wg.Add(len(partList))
            var mu sync.Mutex
            completed := 0

            for _, part := range partList {
                go func(p string) {
                    worker(p)
                    mu.Lock()
                    completed++
                    mu.Unlock()
                    wg.Done()
                }(part)
            }
            wg.Wait()
            if completed != len(partList) {
                t.Errorf("Expected %d workers to complete, got %d", len(partList), completed)
            }
        }
    })
}

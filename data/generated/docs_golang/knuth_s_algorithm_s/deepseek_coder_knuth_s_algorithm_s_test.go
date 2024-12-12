package main

import (
    "math/rand"
    "testing"
    "time"
)

// TestSOfNCreator tests the sOfNCreator function.
func TestSOfNCreator(t *testing.T) {
    rand.Seed(time.Now().UnixNano())

    // Test case 1: n = 3, items = 0 to 9
    t.Run("n=3, items=0 to 9", func(t *testing.T) {
        n := 3
        sOfN := sOfNCreator(n)
        items := []byte{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}

        for _, item := range items {
            sOfN(item)
        }

        sample := sOfN('9')
        if len(sample) != n {
            t.Errorf("Expected sample size %d, got %d", n, len(sample))
        }

        // Check that all items in the sample are from the input items
        for _, item := range sample {
            found := false
            for _, inputItem := range items {
                if item == inputItem {
                    found = true
                    break
                }
            }
            if !found {
                t.Errorf("Item %c in sample not found in input items", item)
            }
        }
    })

    // Test case 2: n = 5, items = 0 to 9
    t.Run("n=5, items=0 to 9", func(t *testing.T) {
        n := 5
        sOfN := sOfNCreator(n)
        items := []byte{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}

        for _, item := range items {
            sOfN(item)
        }

        sample := sOfN('9')
        if len(sample) != n {
            t.Errorf("Expected sample size %d, got %d", n, len(sample))
        }

        // Check that all items in the sample are from the input items
        for _, item := range sample {
            found := false
            for _, inputItem := range items {
                if item == inputItem {
                    found = true
                    break
                }
            }
            if !found {
                t.Errorf("Item %c in sample not found in input items", item)
            }
        }
    })

    // Test case 3: n = 1, items = 0 to 9
    t.Run("n=1, items=0 to 9", func(t *testing.T) {
        n := 1
        sOfN := sOfNCreator(n)
        items := []byte{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}

        for _, item := range items {
            sOfN(item)
        }

        sample := sOfN('9')
        if len(sample) != n {
            t.Errorf("Expected sample size %d, got %d", n, len(sample))
        }

        // Check that all items in the sample are from the input items
        for _, item := range sample {
            found := false
            for _, inputItem := range items {
                if item == inputItem {
                    found = true
                    break
                }
            }
            if !found {
                t.Errorf("Item %c in sample not found in input items", item)
            }
        }
    })

    // Test case 4: n = 10, items = 0 to 9
    t.Run("n=10, items=0 to 9", func(t *testing.T) {
        n := 10
        sOfN := sOfNCreator(n)
        items := []byte{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}

        for _, item := range items {
            sOfN(item)
        }

        sample := sOfN('9')
        if len(sample) != n {
            t.Errorf("Expected sample size %d, got %d", n, len(sample))
        }

        // Check that all items in the sample are from the input items
        for _, item := range sample {
            found := false
            for _, inputItem := range items {
                if item == inputItem {
                    found = true
                    break
                }
            }
            if !found {
                t.Errorf("Item %c in sample not found in input items", item)
            }
        }
    })
}

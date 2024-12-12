package main

import (
    "bytes"
    "os/exec"
    "strconv"
    "strings"
    "testing"
)

func TestListRootedTrees(t *testing.T) {
    testCases := []struct {
        n        int
        expected []string
    }{
        {
            n: 1,
            expected: []string{
                "()",
            },
        },
        {
            n: 2,
            expected: []string{
                "(())",
            },
        },
        {
            n: 3,
            expected: []string{
                "((()))",
                "(()())",
            },
        },
        {
            n: 4,
            expected: []string{
                "(((())))",
                "((()()))",
                "(()(()))",
                "(()()())",
            },
        },
        {
            n: 5,
            expected: []string{
                "((((()))))",
                "(((()())))",
                "((()(())))",
                "((()()()))",
                "(()((())))",
                "(()(()()))",
                "(()()(()))",
                "(()()()())",
                "(()(()))()",
            },
        },
    }

    for _, tc := range testCases {
        t.Run("n="+strconv.Itoa(tc.n), func(t *testing.T) {
            cmd := exec.Command("./list_rooted_trees", strconv.Itoa(tc.n))
            var out bytes.Buffer
            cmd.Stdout = &out
            err := cmd.Run()
            if err != nil {
                t.Fatalf("Failed to execute command: %v", err)
            }

            output := out.String()
            lines := strings.Split(strings.TrimSpace(output), "\n")

            if len(lines) != len(tc.expected) {
                t.Fatalf("Expected %d trees, got %d", len(tc.expected), len(lines))
            }

            for i, expected := range tc.expected {
                if lines[i] != expected {
                    t.Errorf("Expected tree %d to be %s, got %s", i, expected, lines[i])
                }
            }
        })
    }
}

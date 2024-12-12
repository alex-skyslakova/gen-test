package main

import (
    "testing"
    "unicode"
)

func TestLetterFrequency(t *testing.T) {
    // Test case 1: Empty file
    testLetterFrequency(t, "", map[rune]int{})

    // Test case 2: Single character file
    testLetterFrequency(t, "a", map[rune]int{'a': 1})

    // Test case 3: Multiple characters with repetition
    testLetterFrequency(t, "hello", map[rune]int{'h': 1, 'e': 1, 'l': 2, 'o': 1})

    // Test case 4: Case sensitivity
    testLetterFrequency(t, "Hello", map[rune]int{'H': 1, 'e': 1, 'l': 2, 'o': 1})

    // Test case 5: Special characters and spaces
    testLetterFrequency(t, "Hello, World!", map[rune]int{'H': 1, 'e': 1, 'l': 3, 'o': 2, ',': 1, ' ': 1, 'W': 1, 'r': 1, 'd': 1, '!': 1})

    // Test case 6: Unicode characters
    testLetterFrequency(t, "Привет, мир!", map[rune]int{'П': 1, 'р': 2, 'и': 1, 'в': 1, 'е': 1, 'т': 1, ',': 1, ' ': 1, 'м': 1, '!': 1})

    // Test case 7: Control characters
    testLetterFrequency(t, "\x00\x01\x02\x03", map[rune]int{'\x00': 1, '\x01': 1, '\x02': 1, '\x03': 1})
}

func testLetterFrequency(t *testing.T, input string, expected map[rune]int) {
    m := make(map[rune]int)
    for _, r := range input {
        m[r]++
    }

    if len(m) != len(expected) {
        t.Errorf("Expected %d unique characters, got %d", len(expected), len(m))
    }

    for r, freq := range m {
        if expected[r] != freq {
            t.Errorf("Expected frequency of '%c' to be %d, got %d", r, expected[r], freq)
        }
    }

    // Sort and format output for comparison
    lfs := make(lfList, 0, len(m))
    for l, f := range m {
        lfs = append(lfs, &letterFreq{l, f})
    }
    sort.Sort(lfs)

    // Compare sorted output with expected frequencies
    for i, lf := range lfs {
        if expected[lf.rune] != lf.freq {
            t.Errorf("Expected frequency of '%c' to be %d, got %d", lf.rune, expected[lf.rune], lf.freq)
        }
        if i > 0 && lfs[i-1].freq == lf.freq && lfs[i-1].rune > lf.rune {
            t.Errorf("Sorting error: '%c' should come before '%c'", lfs[i-1].rune, lf.rune)
        }
    }
}

func TestLFListSorting(t *testing.T) {
    lfs := lfList{
        &letterFreq{'a', 2},
        &letterFreq{'b', 1},
        &letterFreq{'c', 3},
        &letterFreq{'d', 2},
    }

    sort.Sort(lfs)

    if lfs[0].rune != 'c' || lfs[1].rune != 'a' || lfs[2].rune != 'd' || lfs[3].rune != 'b' {
        t.Errorf("Sorting failed: %v", lfs)
    }
}

func TestLFListLen(t *testing.T) {
    lfs := lfList{
        &letterFreq{'a', 2},
        &letterFreq{'b', 1},
    }

    if lfs.Len() != 2 {
        t.Errorf("Expected length 2, got %d", lfs.Len())
    }
}

func TestLFListLess(t *testing.T) {
    lfs := lfList{
        &letterFreq{'a', 2},
        &letterFreq{'b', 1},
    }

    if lfs.Less(0, 1) {
        t.Errorf("Expected 'a' to be less than 'b'")
    }
}

func TestLFListSwap(t *testing.T) {
    lfs := lfList{
        &letterFreq{'a', 2},
        &letterFreq{'b', 1},
    }

    lfs.Swap(0, 1)

    if lfs[0].rune != 'b' || lfs[1].rune != 'a' {
        t.Errorf("Swap failed: %v", lfs)
    }
}

func TestUnicodeIsGraphic(t *testing.T) {
    if !unicode.IsGraphic('a') {
        t.Errorf("Expected 'a' to be a graphic character")
    }

    if unicode.IsGraphic('\x00') {
        t.Errorf("Expected control character to not be a graphic character")
    }
}

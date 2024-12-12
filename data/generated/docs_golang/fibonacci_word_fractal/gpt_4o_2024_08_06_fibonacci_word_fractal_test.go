package main

import (
    "testing"
    "github.com/fogleman/gg"
    "strings"
)

// TestWordFractal tests the wordFractal function for various input cases
func TestWordFractal(t *testing.T) {
    tests := []struct {
        input    int
        expected string
    }{
        {0, ""},
        {1, "1"},
        {2, "0"},
        {3, "01"},
        {4, "010"},
        {5, "01001"},
        {6, "01001010"},
    }

    for _, test := range tests {
        result := wordFractal(test.input)
        if result != test.expected {
            t.Errorf("wordFractal(%d) = %s; expected %s", test.input, result, test.expected)
        }
    }
}

// TestDraw tests the draw function by checking if the drawing context is modified correctly
func TestDraw(t *testing.T) {
    dc := gg.NewContext(100, 100)
    dc.SetRGB(0, 0, 0)
    dc.Clear()
    initialImage := dc.Image()

    wf := "010"
    draw(dc, 10, 10, 1, 0, wf)

    modifiedImage := dc.Image()

    if initialImage == modifiedImage {
        t.Error("draw function did not modify the drawing context")
    }
}

// TestMainFunction tests the main function indirectly by checking the output file
func TestMainFunction(t *testing.T) {
    main()

    // Check if the output file is created
    if _, err := os.Stat("fib_wordfractal.png"); os.IsNotExist(err) {
        t.Error("main function did not create the expected output file fib_wordfractal.png")
    }
}

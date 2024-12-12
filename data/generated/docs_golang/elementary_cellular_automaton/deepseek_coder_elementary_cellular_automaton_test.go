package main

import (
    "math/big"
    "testing"
)

func TestElem(t *testing.T) {
    tests := []struct {
        rule        uint
        cells       int
        generations int
        initial     *big.Int
        expected    []string
    }{
        // Test case 1: Rule 90, single 1 in the middle, 20 cells, 9 generations
        {
            rule:        90,
            cells:       20,
            generations: 9,
            initial:     big.NewInt(1).Lsh(big.NewInt(1), 10), // 1 << 10
            expected: []string{
                "          #          ",
                "         # #         ",
                "        #   #        ",
                "       # # # #       ",
                "      #       #      ",
                "     # #     # #     ",
                "    #   #   #   #    ",
                "   # # # # # # # #   ",
                "  #               #  ",
                " # #             # # ",
            },
        },
        // Test case 2: Rule 30, random initial state, 20 cells, 9 generations
        {
            rule:        30,
            cells:       20,
            generations: 9,
            initial:     big.NewInt(1).Rand(rand.New(rand.NewSource(3)), big.NewInt(1).Lsh(big.NewInt(1), 20)),
            expected: []string{
                " # # # # # # # # # # ",
                "  # # # # # # # # #  ",
                " #   # # # # # # # # ",
                "  # #   # # # # # #  ",
                " #   # #   # # # # # ",
                "  # #   # #   # # #  ",
                " #   # #   # #   # # ",
                "  # #   # #   # #   #",
                " #   # #   # #   # # ",
                "  # #   # #   # #   #",
            },
        },
    }

    for _, test := range tests {
        t.Run(fmt.Sprintf("Rule %d, %d cells, %d generations", test.rule, test.cells, test.generations), func(t *testing.T) {
            var output []string
            outputFunc := func() {
                output = append(output, strings.Replace(strings.Replace(
                    fmt.Sprintf("%0*b", test.cells, test.initial), "0", " ", -1), "1", "#", -1))
            }

            elemWithOutput(test.rule, test.cells, test.generations, test.initial, outputFunc)

            if len(output) != len(test.expected) {
                t.Errorf("Expected %d generations, got %d", len(test.expected), len(output))
            }

            for i := 0; i < len(test.expected); i++ {
                if output[i] != test.expected[i] {
                    t.Errorf("Generation %d: expected %q, got %q", i, test.expected[i], output[i])
                }
            }
        })
    }
}

func elemWithOutput(rule uint, cells, generations int, a *big.Int, output func()) {
    output()
    a1 := new(big.Int)
    set := func(cell int, k uint) {
        a1.SetBit(a1, cell, rule>>k&1)
    }
    last := cells - 1
    for r := 0; r < generations; r++ {
        k := a.Bit(last) | a.Bit(0)<<1 | a.Bit(1)<<2
        set(0, k)
        for c := 1; c < last; c++ {
            k = k>>1 | a.Bit(c+1)<<2
            set(c, k)
        }
        set(last, k>>1|a.Bit(0)<<2)
        a, a1 = a1, a
        output()
    }
}

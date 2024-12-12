package main

import (
    "testing"
    "github.com/biogo/biogo/align"
    ab "github.com/biogo/biogo/alphabet"
    "github.com/biogo/biogo/feat"
    "github.com/biogo/biogo/seq/linear"
)

func TestLevenshteinDistanceAlignment(t *testing.T) {
    lc := ab.Must(ab.NewAlphabet("-abcdefghijklmnopqrstuvwxyz",
        feat.Undefined, '-', 0, true))
    nw := make(align.NW, lc.Len())
    for i := range nw {
        r := make([]int, lc.Len())
        nw[i] = r
        for j := range r {
            if j != i {
                r[j] = -1
            }
        }
    }

    testCases := []struct {
        a        string
        b        string
        expected [2]string
    }{
        {
            a: "rosettacode",
            b: "raisethysword",
            expected: [2]string{
                "R-OSETTAC-ODE",
                "RAISETHYSWORD",
            },
        },
        {
            a: "kitten",
            b: "sitting",
            expected: [2]string{
                "KITTEN-",
                "SITTING",
            },
        },
        {
            a: "flaw",
            b: "lawn",
            expected: [2]string{
                "FLAW-",
                "-LAWN",
            },
        },
    }

    for _, tc := range testCases {
        a := &linear.Seq{Seq: ab.BytesToLetters([]byte(tc.a))}
        a.Alpha = lc
        b := &linear.Seq{Seq: ab.BytesToLetters([]byte(tc.b))}
        b.Alpha = lc

        aln, err := nw.Align(a, b)
        if err != nil {
            t.Fatalf("unexpected error: %v", err)
        }

        fa := align.Format(a, b, aln, '-')
        if fa[0] != tc.expected[0] || fa[1] != tc.expected[1] {
            t.Errorf("unexpected alignment for %s and %s: got %s and %s, expected %s and %s",
                tc.a, tc.b, fa[0], fa[1], tc.expected[0], tc.expected[1])
        }
    }
}

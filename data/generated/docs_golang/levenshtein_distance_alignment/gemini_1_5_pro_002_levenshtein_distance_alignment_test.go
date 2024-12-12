package main

import (
	"bytes"
	"fmt"
	"testing"

	"github.com/biogo/biogo/align"
	ab "github.com/biogo/biogo/alphabet"
	"github.com/biogo/biogo/feat"
	"github.com/biogo/biogo/seq/linear"
)
)

func alignStrings(a, b string) (string, string, error) {
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
	seqA := &linear.Seq{Seq: ab.BytesToLetters([]byte(a))}
	seqA.Alpha = lc
	seqB := &linear.Seq{Seq: ab.BytesToLetters([]byte(b))}
	seqB.Alpha = lc
	aln, err := nw.Align(seqA, seqB)
	if err != nil {
		return "", "", err
	}
	fa := align.Format(seqA, seqB, aln, '-')
	return fmt.Sprint(fa[0]), fmt.Sprint(fa[1]), nil

}

func TestAlignStrings(t *testing.T) {
	testCases := []struct {
		a, b        string
		expectedA, expectedB string
	}{
		{"rosettacode", "raisethysword", "rosetta--code", "raisethysword"},
		{"place", "palace", "p-lace", "palace"},
		{"apple", "apples", "apple-", "apples"},
		{"sitting", "kitten", "sittin-g", "-kitten--"},
		{"", "", "", ""},
		{"a", "", "a", "-"},
		{"", "b", "-", "b"},
	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("%s_%s", tc.a, tc.b), func(t *testing.T) {
			actualA, actualB, err := alignStrings(tc.a, tc.b)
			if err != nil {
				t.Fatalf("Unexpected error: %v", err)
			}
			if actualA != tc.expectedA || actualB != tc.expectedB {
				t.Errorf("For inputs '%s' and '%s':\nExpected:\n%s\n%s\nGot:\n%s\n%s", tc.a, tc.b, tc.expectedA, tc.expectedB, actualA, actualB)
			}
		})
	}
}

package main

import (
	"fmt"
	"io"
	"os"
	"strings"
	"testing"
)

func TestFactorization(t *testing.T) {
	testCases := []struct {
		input int
		want  string
	}{
		{1, "1: 1\n"},
		{2, "2: 2\n"},
		{3, "3: 3\n"},
		{4, "4: 2×2\n"},
		{5, "5: 5\n"},
		{6, "6: 2×3\n"},
		{7, "7: 7\n"},
		{8, "8: 2×2×2\n"},
		{9, "9: 3×3\n"},
		{10, "10: 2×5\n"},
		{12, "12: 2×2×3\n"},
		{2144, "2144: 2×2×2×2×2×67\n"},
        {17, "17: 17\n"},
        {35, "35: 5×7\n"},
        {99, "99: 3×3×11\n"},

	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("Input %d", tc.input), func(t *testing.T) {
			old := os.Stdout // keep backup of the real stdout
			r, w, _ := os.Pipe()
			os.Stdout = w

			// Limit the loop to the desired input
			exit := make(chan bool)
			go func() {
				for i := 1; i <= tc.input; i++ {
					fmt.Printf("%d: ", i)
					var x string
					for n, f := i, 2; n != 1; f++ {
						for m := n % f; m == 0; m = n % f {
							fmt.Print(x, f)
							x = "×"
							n /= f
						}
					}
					fmt.Println()
				}
				exit <- true
			}()
			<-exit

			w.Close()
			os.Stdout = old // restoring the real stdout
			out, _ := io.ReadAll(r)
			got := string(out)

			if !strings.Contains(got, tc.want) { // Check if the expected output is present
				t.Errorf("got:\n%s\nwant output to contain:\n%s", got, tc.want)
			}
		})
	}
}


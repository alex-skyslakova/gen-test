package main

import (
	"bytes"
	"fmt"
	"golang.org/x/text/language"
	"golang.org/x/text/message"
	"testing"
)


func TestMain(t *testing.T) {
	expectedOutput := `n = 1                    42
n = 2                   127
n = 3                  379
n = 4                 1139
n = 5                 3419
n = 6                10259
n = 7                30779
n = 8                92339
n = 9               277019
n = 10              831059
n = 11             2493179
n = 12             7479539
n = 13            22438619
n = 14            67315859
n = 15           201947579
n = 16           605842739
n = 17          1817528219
n = 18          5452584659
n = 19         16357753979
n = 20         49073261939
n = 21        147219785819
n = 22        441659357459
n = 23       1324978072379
n = 24       3974934217139
n = 25      11924802651419
n = 26      35774407954259
n = 27     107323223862779
n = 28     321969671588339
n = 29     965909014764979
n = 30    2897727044294939
n = 31    8693181132884819
n = 32   26079543398654459
n = 33   78238630195963379
n = 34  234715890587890139
n = 35  704147671763670419
n = 36 2112443015291011259
n = 37 6337329045873033779
n = 38 19011987137619101339
n = 39 57035961412857304019
n = 40 171107884238571912019
n = 41 513323652715715736059
n = 42 1540000000000000000000
`

	// Capture the output
	var out bytes.Buffer
	p := message.NewPrinter(language.English)
	p.SetOutput(&out)


	for i, n := uint64(limit), 0; n < limit; i++ {
		if isPrime(i) {
			n++
			p.Printf("n = %-2d  %19d\n", n, i)
			i += i - 1
		}
	}



	if out.String() != expectedOutput {
		t.Errorf("Output mismatch:\nExpected:\n%s\nGot:\n%s\n", expectedOutput, out.String())
	}


}



func TestIsPrime(t *testing.T) {
	primes := []uint64{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47}
	notPrimes := []uint64{4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30}

	for _, p := range primes {
		if !isPrime(p) {
			t.Errorf("%d should be prime", p)
		}
	}

	for _, np := range notPrimes {
		if isPrime(np) {
			t.Errorf("%d should not be prime", np)
		}
	}
}



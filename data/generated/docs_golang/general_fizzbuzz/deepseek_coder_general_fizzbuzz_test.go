package main

import (
	"testing"
)

func TestGeneralFizzBuzz(t *testing.T) {
	tests := []struct {
		max    int
		words  map[int]string
		keys   []int
		output string
	}{
		{
			max: 20,
			words: map[int]string{
				3: "Fizz",
				5: "Buzz",
				7: "Baxx",
			},
			keys: []int{3, 5, 7},
			output: `1
2
Fizz
4
Buzz
Fizz
Baxx
8
Fizz
Buzz
11
Fizz
13
Baxx
FizzBuzz
16
17
Fizz
19
Buzz
`,
		},
		{
			max: 10,
			words: map[int]string{
				2: "Fizz",
				3: "Buzz",
				5: "Baxx",
			},
			keys: []int{2, 3, 5},
			output: `1
Fizz
Buzz
Fizz
Baxx
FizzBuzz
7
Fizz
Buzz
FizzBaxx
`,
		},
		{
			max: 5,
			words: map[int]string{
				2: "Fizz",
				3: "Buzz",
				4: "Baxx",
			},
			keys: []int{2, 3, 4},
			output: `1
Fizz
Buzz
FizzBaxx
5
`,
		},
	}

	for _, test := range tests {
		t.Run("", func(t *testing.T) {
			oldStdout := os.Stdout
			r, w, _ := os.Pipe()
			os.Stdout = w

			max := test.max
			words := test.words
			keys := test.keys
			divisible := false
			for i := 1; i <= max; i++ {
				for _, n := range keys {
					if i%n == 0 {
						fmt.Print(words[n])
						divisible = true
					}
				}
				if !divisible {
					fmt.Print(i)
				}
				fmt.Println()
				divisible = false
			}

			w.Close()
			out, _ := ioutil.ReadAll(r)
			os.Stdout = oldStdout

			if string(out) != test.output {
				t.Errorf("Expected output:\n%s\nGot:\n%s", test.output, string(out))
			}
		})
	}
}

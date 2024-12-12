package main

import (
	"bytes"
	"fmt"
	"testing"
)

func TestFizzBuzz(t *testing.T) {
	testCases := []struct {
		max   int
		words map[int]string
		keys  []int
		want  string
	}{
		{
			20,
			map[int]string{
				3: "Fizz",
				5: "Buzz",
				7: "Baxx",
			},
			[]int{3, 5, 7},
			`1
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
			105,
			map[int]string{
				3: "Fizz",
				5: "Buzz",
				7: "Baxx",
			},
			[]int{3, 5, 7},
			`1
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
FizzBaxx
22
23
Fizz
Buzz
26
Fizz
Baxx
29
FizzBuzz
31
32
Fizz
34
BuzzBaxx
Fizz
37
38
Fizz
Buzz
41
FizzBaxx
43
44
FizzBuzz
46
47
Fizz
Baxx
50
Fizz
Buzz
53
Fizz
Baxx
FizzBuzz
58
59
Fizz
61
Buzz
FizzBaxx
64
65
Fizz
67
Buzz
Fizz
71
Baxx
FizzBuzz
74
75
Fizz
77
Buzz
FizzBaxx
80
82
Fizz
83
BuzzBaxx
Fizz
86
87
Fizz
Buzz
89
FizzBaxx
Buzz
92
Fizz
94
Buzz
Fizz
97
Baxx
FizzBuzz
100
101
Fizz
103
BuzzBaxx
FizzBuzzBaxx
`,
		},
		{
			5,
			map[int]string{
				2: "Foo",
				3: "Bar",
			},
			[]int{2, 3},
			`1
Foo
Bar
Foo
5
`,

		},
		{ // Test with empty words map
			10,
			map[int]string{},
			[]int{},
			`1
2
3
4
5
6
7
8
9
10
`,
		},
		{ // Test with single factor and word.
			6,
			map[int]string{
				2: "Even",
			},
			[]int{2},
			`1
Even
3
Even
5
Even
`,
		},
	}

	for _, tc := range testCases {
		var buf bytes.Buffer
		divisible := false

		for i := 1; i <= tc.max; i++ {
			for _, n := range tc.keys {
				if i%n == 0 {
					buf.WriteString(tc.words[n])
					divisible = true
				}
			}
			if !divisible {
				buf.WriteString(fmt.Sprintf("%d", i))
			}
			buf.WriteString("\n")
			divisible = false
		}

		got := buf.String()
		if got != tc.want {
			t.Errorf("FizzBuzz(%d, %v, %v) = %q; want %q", tc.max, tc.words, tc.keys, got, tc.want)
		}
	}

}


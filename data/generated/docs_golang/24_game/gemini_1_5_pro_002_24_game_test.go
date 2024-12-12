package main

import (
	"bytes"
	"fmt"
	"math"
	"strings"
	"testing"
)

func Test24Game(t *testing.T) {
	testCases := []struct {
		inputNumbers string
		rpnExpr     string
		expected    string
	}{
		{"1234", "12+34+*", "correct."},
		{"1234", "12+3*", "invalid expression syntax."},
		{"1234", "12+34+5*", "too many numbers."},
		{"1234", "12+35+*", "wrong numbers."},
		{"1234", "12+34+", "invalid expression syntax."},
		{"1234", "12+34*%", "% invalid.\n"},
		{"1234", "1234", "invalid expression syntax."},
		{"5555", "55+55+*", "correct."},
		{"1238", "13+82-*", "correct."},  // Example where result is 24
		{"1239", "13-92/+", "incorrect. -0.125 != 24"}, // Example where result is not 24
		{"1111", "11+11+", "incorrect. 2 != 24"},       // Simpler incorrect case.
		{"1111", "11+1*", "incorrect. 2 != 24"},       // Very simple example
        {"1234", "12+34+*2+", "too many numbers."}, // Test too many numbers with expression longer than expected
        {"1234", "12+", "invalid expression syntax."},  //Test short input with valid operators and numbers

	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("%s %s", tc.inputNumbers, tc.rpnExpr), func(t *testing.T) {
			reader := strings.NewReader(tc.rpnExpr + "\n")

			var output bytes.Buffer
			n := []rune(tc.inputNumbers)



            //Overriding standard output and input
			fmt.SetOutput(&output)

            defer func(){
                fmt.SetOutput(nil)
                // Restoring the original values is important for other tests
            }()
            //Mocking original function parameters
            fmt.Printf("Your numbers: %c\n", n)

			fmt.Fprint(reader,"") //Simulate user input reading

            fmt.Print("Enter RPN: ") //To match prompt from real main() to keep outputs consistent.
            expr := "" //Declare it before scanning, so scope allows later access.

            fmt.Fscan(reader, &expr)



			stack := make([]float64, 0, 4)
            originalN:= make([]rune,len(n))
            copy(originalN,n)
            nCopy := n[:] //Local copy to modify within the loop without affecting other tests
			for _, r := range expr {
				if r >= '0' && r <= '9' {
					if len(nCopy) == 0 {
						break // Exit loop as in original code
					}
					i := 0
					for nCopy[i] != r {
						i++
						if i == len(nCopy) {
							break // Exit inner loop as in original code
						}
					}

					if i == len(nCopy){
                        continue //Replicate original flow
                    }
                    nCopy = append(nCopy[:i], nCopy[i+1:]...)
					stack = append(stack, float64(r-'0'))
					continue
				}
				if len(stack) < 2 {
					break // Exit loop
				}
				switch r {
				case '+':
					stack[len(stack)-2] += stack[len(stack)-1]
				case '-':
					stack[len(stack)-2] -= stack[len(stack)-1]
				case '*':
					stack[len(stack)-2] *= stack[len(stack)-1]
				case '/':
					stack[len(stack)-2] /= stack[len(stack)-1]
				default:

					break
				}
				stack = stack[:len(stack)-1]
			}

			//Reconstructing output
            resultString := output.String()

            idx := strings.Index(resultString, "Enter RPN: ")
            if idx != -1{
                resultString = resultString[idx + len("Enter RPN: "):]
            }



			idx = strings.Index(resultString, "\n")
            if idx != -1{
                resultString = resultString[:idx]
            }
            n = originalN


			if resultString != tc.expected {
				t.Errorf("expected '%s', got '%s'", tc.expected, resultString)
			}
		})
	}
}


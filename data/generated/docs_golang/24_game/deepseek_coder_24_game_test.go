package main

import (
    "math"
    "testing"
)

func TestExpressionLength(t *testing.T) {
    expr := "1234+*/"
    if len(expr) != 7 {
        t.Errorf("Expected expression length to be 7, got %d", len(expr))
    }
}

func TestInvalidExpressionLength(t *testing.T) {
    expr := "1234+*-"
    if len(expr) == 7 {
        t.Errorf("Expected expression length to be invalid, got %d", len(expr))
    }
}

func TestTooManyNumbers(t *testing.T) {
    expr := "12345+*"
    stack := make([]float64, 0, 4)
    n := []rune{'1', '2', '3', '4'}
    for _, r := range expr {
        if r >= '0' && r <= '9' {
            if len(n) == 0 {
                t.Errorf("Expected too many numbers error, but no error was thrown")
                return
            }
            i := 0
            for n[i] != r {
                i++
                if i == len(n) {
                    t.Errorf("Expected wrong numbers error, but no error was thrown")
                    return
                }
            }
            n = append(n[:i], n[i+1:]...)
            stack = append(stack, float64(r-'0'))
            continue
        }
        if len(stack) < 2 {
            t.Errorf("Expected invalid expression syntax error, but no error was thrown")
            return
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
            t.Errorf("Expected invalid character error, but no error was thrown")
            return
        }
        stack = stack[:len(stack)-1]
    }
}

func TestWrongNumbers(t *testing.T) {
    expr := "1235+*"
    stack := make([]float64, 0, 4)
    n := []rune{'1', '2', '3', '4'}
    for _, r := range expr {
        if r >= '0' && r <= '9' {
            if len(n) == 0 {
                t.Errorf("Expected too many numbers error, but no error was thrown")
                return
            }
            i := 0
            for n[i] != r {
                i++
                if i == len(n) {
                    return // Expected error, test passed
                }
            }
            n = append(n[:i], n[i+1:]...)
            stack = append(stack, float64(r-'0'))
            continue
        }
        if len(stack) < 2 {
            t.Errorf("Expected invalid expression syntax error, but no error was thrown")
            return
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
            t.Errorf("Expected invalid character error, but no error was thrown")
            return
        }
        stack = stack[:len(stack)-1]
    }
    t.Errorf("Expected wrong numbers error, but no error was thrown")
}

func TestInvalidExpressionSyntax(t *testing.T) {
    expr := "12+*"
    stack := make([]float64, 0, 4)
    n := []rune{'1', '2', '3', '4'}
    for _, r := range expr {
        if r >= '0' && r <= '9' {
            if len(n) == 0 {
                t.Errorf("Expected too many numbers error, but no error was thrown")
                return
            }
            i := 0
            for n[i] != r {
                i++
                if i == len(n) {
                    t.Errorf("Expected wrong numbers error, but no error was thrown")
                    return
                }
            }
            n = append(n[:i], n[i+1:]...)
            stack = append(stack, float64(r-'0'))
            continue
        }
        if len(stack) < 2 {
            return // Expected error, test passed
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
            t.Errorf("Expected invalid character error, but no error was thrown")
            return
        }
        stack = stack[:len(stack)-1]
    }
    t.Errorf("Expected invalid expression syntax error, but no error was thrown")
}

func TestInvalidCharacter(t *testing.T) {
    expr := "1234+%*"
    stack := make([]float64, 0, 4)
    n := []rune{'1', '2', '3', '4'}
    for _, r := range expr {
        if r >= '0' && r <= '9' {
            if len(n) == 0 {
                t.Errorf("Expected too many numbers error, but no error was thrown")
                return
            }
            i := 0
            for n[i] != r {
                i++
                if i == len(n) {
                    t.Errorf("Expected wrong numbers error, but no error was thrown")
                    return
                }
            }
            n = append(n[:i], n[i+1:]...)
            stack = append(stack, float64(r-'0'))
            continue
        }
        if len(stack) < 2 {
            t.Errorf("Expected invalid expression syntax error, but no error was thrown")
            return
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
            return // Expected error, test passed
        }
        stack = stack[:len(stack)-1]
    }
    t.Errorf("Expected invalid character error, but no error was thrown")
}

func TestCorrectExpression(t *testing.T) {
    expr := "42*3+"
    stack := make([]float64, 0, 4)
    n := []rune{'4', '2', '3', '1'}
    for _, r := range expr {
        if r >= '0' && r <= '9' {
            if len(n) == 0 {
                t.Errorf("Expected too many numbers error, but no error was thrown")
                return
            }
            i := 0
            for n[i] != r {
                i++
                if i == len(n) {
                    t.Errorf("Expected wrong numbers error, but no error was thrown")
                    return
                }
            }
            n = append(n[:i], n[i+1:]...)
            stack = append(stack, float64(r-'0'))
            continue
        }
        if len(stack) < 2 {
            t.Errorf("Expected invalid expression syntax error, but no error was thrown")
            return
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
            t.Errorf("Expected invalid character error, but no error was thrown")
            return
        }
        stack = stack[:len(stack)-1]
    }
    if math.Abs(stack[0]-24) > 1e-6 {
        t.Errorf("Expected result to be 24, got %f", stack[0])
    }
}

func TestIncorrectExpression(t *testing.T) {
    expr := "42*3-"
    stack := make([]float64, 0, 4)
    n := []rune{'4', '2', '3', '1'}
    for _, r := range expr {
        if r >= '0' && r <= '9' {
            if len(n) == 0 {
                t.Errorf("Expected too many numbers error, but no error was thrown")
                return
            }
            i := 0
            for n[i] != r {
                i++
                if i == len(n) {
                    t.Errorf("Expected wrong numbers error, but no error was thrown")
                    return
                }
            }
            n = append(n[:i], n[i+1:]...)
            stack = append(stack, float64(r-'0'))
            continue
        }
        if len(stack) < 2 {
            t.Errorf("Expected invalid expression syntax error, but no error was thrown")
            return
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
            t.Errorf("Expected invalid character error, but no error was thrown")
            return
        }
        stack = stack[:len(stack)-1]
    }
    if math.Abs(stack[0]-24) <= 1e-6 {
        t.Errorf("Expected result to be different from 24, got %f", stack[0])
    }
}

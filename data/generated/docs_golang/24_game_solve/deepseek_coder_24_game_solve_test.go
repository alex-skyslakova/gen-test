package main

import (
	"fmt"
	"testing"
)

func TestExprEval(t *testing.T) {
	tests := []struct {
		expr     *Expr
		expected frac
	}{
		{
			&Expr{op_num, nil, nil, frac{5, 1}},
			frac{5, 1},
		},
		{
			&Expr{op_add, &Expr{op_num, nil, nil, frac{2, 1}}, &Expr{op_num, nil, nil, frac{3, 1}}, frac{0, 0}},
			frac{5, 1},
		},
		{
			&Expr{op_sub, &Expr{op_num, nil, nil, frac{7, 1}}, &Expr{op_num, nil, nil, frac{4, 1}}, frac{0, 0}},
			frac{3, 1},
		},
		{
			&Expr{op_mul, &Expr{op_num, nil, nil, frac{2, 1}}, &Expr{op_num, nil, nil, frac{3, 1}}, frac{0, 0}},
			frac{6, 1},
		},
		{
			&Expr{op_div, &Expr{op_num, nil, nil, frac{8, 1}}, &Expr{op_num, nil, nil, frac{2, 1}}, frac{0, 0}},
			frac{4, 1},
		},
	}

	for _, tt := range tests {
		t.Run(fmt.Sprintf("expr_eval(%v)", tt.expr), func(t *testing.T) {
			result := expr_eval(tt.expr)
			if result != tt.expected {
				t.Errorf("expected %v, got %v", tt.expected, result)
			}
		})
	}
}

func TestSolve(t *testing.T) {
	tests := []struct {
		cards    []*Expr
		expected bool
	}{
		{
			[]*Expr{
				&Expr{op_num, nil, nil, frac{1, 1}},
				&Expr{op_num, nil, nil, frac{2, 1}},
				&Expr{op_num, nil, nil, frac{3, 1}},
				&Expr{op_num, nil, nil, frac{4, 1}},
			},
			true,
		},
		{
			[]*Expr{
				&Expr{op_num, nil, nil, frac{1, 1}},
				&Expr{op_num, nil, nil, frac{1, 1}},
				&Expr{op_num, nil, nil, frac{1, 1}},
				&Expr{op_num, nil, nil, frac{1, 1}},
			},
			false,
		},
		{
			[]*Expr{
				&Expr{op_num, nil, nil, frac{6, 1}},
				&Expr{op_num, nil, nil, frac{6, 1}},
				&Expr{op_num, nil, nil, frac{6, 1}},
				&Expr{op_num, nil, nil, frac{6, 1}},
			},
			true,
		},
	}

	for _, tt := range tests {
		t.Run(fmt.Sprintf("solve(%v)", tt.cards), func(t *testing.T) {
			result := solve(tt.cards)
			if result != tt.expected {
				t.Errorf("expected %v, got %v", tt.expected, result)
			}
		})
	}
}

func TestString(t *testing.T) {
	tests := []struct {
		expr     *Expr
		expected string
	}{
		{
			&Expr{op_num, nil, nil, frac{5, 1}},
			"5",
		},
		{
			&Expr{op_add, &Expr{op_num, nil, nil, frac{2, 1}}, &Expr{op_num, nil, nil, frac{3, 1}}, frac{0, 0}},
			"2 + 3",
		},
		{
			&Expr{op_sub, &Expr{op_num, nil, nil, frac{7, 1}}, &Expr{op_num, nil, nil, frac{4, 1}}, frac{0, 0}},
			"7 - 4",
		},
		{
			&Expr{op_mul, &Expr{op_num, nil, nil, frac{2, 1}}, &Expr{op_num, nil, nil, frac{3, 1}}, frac{0, 0}},
			"2 * 3",
		},
		{
			&Expr{op_div, &Expr{op_num, nil, nil, frac{8, 1}}, &Expr{op_num, nil, nil, frac{2, 1}}, frac{0, 0}},
			"8 / 2",
		},
	}

	for _, tt := range tests {
		t.Run(fmt.Sprintf("String(%v)", tt.expr), func(t *testing.T) {
			result := tt.expr.String()
			if result != tt.expected {
				t.Errorf("expected %v, got %v", tt.expected, result)
			}
		})
	}
}

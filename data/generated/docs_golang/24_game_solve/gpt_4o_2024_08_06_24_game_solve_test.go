package main

import (
	"testing"
)

func TestExprString(t *testing.T) {
	tests := []struct {
		expr     Expr
		expected string
	}{
		{Expr{op: op_num, value: frac{num: 3}}, "3"},
		{Expr{op: op_add, left: &Expr{op: op_num, value: frac{num: 3}}, right: &Expr{op: op_num, value: frac{num: 4}}}, "3 + 4"},
		{Expr{op: op_sub, left: &Expr{op: op_num, value: frac{num: 7}}, right: &Expr{op: op_num, value: frac{num: 2}}}, "7 - 2"},
		{Expr{op: op_mul, left: &Expr{op: op_num, value: frac{num: 5}}, right: &Expr{op: op_num, value: frac{num: 6}}}, "5 * 6"},
		{Expr{op: op_div, left: &Expr{op: op_num, value: frac{num: 8}}, right: &Expr{op: op_num, value: frac{num: 4}}}, "8 / 4"},
	}

	for _, test := range tests {
		result := test.expr.String()
		if result != test.expected {
			t.Errorf("Expected %s, got %s", test.expected, result)
		}
	}
}

func TestExprEval(t *testing.T) {
	tests := []struct {
		expr     Expr
		expected frac
	}{
		{Expr{op: op_num, value: frac{num: 3, denom: 1}}, frac{num: 3, denom: 1}},
		{Expr{op: op_add, left: &Expr{op: op_num, value: frac{num: 3, denom: 1}}, right: &Expr{op: op_num, value: frac{num: 4, denom: 1}}}, frac{num: 7, denom: 1}},
		{Expr{op: op_sub, left: &Expr{op: op_num, value: frac{num: 7, denom: 1}}, right: &Expr{op: op_num, value: frac{num: 2, denom: 1}}}, frac{num: 5, denom: 1}},
		{Expr{op: op_mul, left: &Expr{op: op_num, value: frac{num: 5, denom: 1}}, right: &Expr{op: op_num, value: frac{num: 6, denom: 1}}}, frac{num: 30, denom: 1}},
		{Expr{op: op_div, left: &Expr{op: op_num, value: frac{num: 8, denom: 1}}, right: &Expr{op: op_num, value: frac{num: 4, denom: 1}}}, frac{num: 2, denom: 1}},
	}

	for _, test := range tests {
		result := expr_eval(&test.expr)
		if result != test.expected {
			t.Errorf("Expected %v, got %v", test.expected, result)
		}
	}
}

func TestSolve(t *testing.T) {
	tests := []struct {
		exprs    []*Expr
		expected bool
	}{
		{
			[]*Expr{
				{op: op_num, value: frac{num: 6, denom: 1}},
				{op: op_num, value: frac{num: 6, denom: 1}},
				{op: op_num, value: frac{num: 6, denom: 1}},
				{op: op_num, value: frac{num: 6, denom: 1}},
			},
			true,
		},
		{
			[]*Expr{
				{op: op_num, value: frac{num: 1, denom: 1}},
				{op: op_num, value: frac{num: 1, denom: 1}},
				{op: op_num, value: frac{num: 1, denom: 1}},
				{op: op_num, value: frac{num: 1, denom: 1}},
			},
			false,
		},
	}

	for _, test := range tests {
		result := solve(test.exprs)
		if result != test.expected {
			t.Errorf("Expected %v, got %v", test.expected, result)
		}
	}
}

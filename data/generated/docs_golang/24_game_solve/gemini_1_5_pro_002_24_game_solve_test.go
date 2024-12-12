package main

import (
	"fmt"
	"testing"
)

func TestExpr_String(t *testing.T) {
	tests := []struct {
		expr *Expr
		want string
	}{
		{&Expr{op_num, nil, nil, frac{5, 1}}, "5"},
		{&Expr{op_add, &Expr{op_num, nil, nil, frac{1, 1}}, &Expr{op_num, nil, nil, frac{2, 1}}, frac{0, 0}}, "1 + 2"},
		{&Expr{op_sub, &Expr{op_add, &Expr{op_num, nil, nil, frac{1, 1}}, &Expr{op_num, nil, nil, frac{2, 1}}, frac{0, 0}}, &Expr{op_num, nil, nil, frac{3, 1}}, frac{0, 0}}, "(1 + 2) - 3"},
		{&Expr{op_mul, &Expr{op_num, nil, nil, frac{1, 1}}, &Expr{op_add, &Expr{op_num, nil, nil, frac{2, 1}}, &Expr{op_num, nil, nil, frac{3, 1}}, frac{0, 0}}, frac{0, 0}}, "1 * (2 + 3)"},
		{&Expr{op_div, &Expr{op_num, nil, nil, frac{6, 1}}, &Expr{op_num, nil, nil, frac{2, 1}}, frac{0, 0}}, "6 / 2"},

	}
	for _, tt := range tests {
		t.Run(tt.want, func(t *testing.T) {
			if got := tt.expr.String(); got != tt.want {
				t.Errorf("Expr.String() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_expr_eval(t *testing.T) {
	tests := []struct {
		x    *Expr
		want frac
	}{
		{&Expr{op_num, nil, nil, frac{5, 1}}, frac{5, 1}},
		{&Expr{op_add, &Expr{op_num, nil, nil, frac{1, 1}}, &Expr{op_num, nil, nil, frac{2, 1}}, frac{0, 0}}, frac{3, 1}},
		{&Expr{op_sub, &Expr{op_num, nil, nil, frac{3, 1}}, &Expr{op_num, nil, nil, frac{2, 1}}, frac{0, 0}}, frac{1, 1}},
		{&Expr{op_mul, &Expr{op_num, nil, nil, frac{2, 1}}, &Expr{op_num, nil, nil, frac{3, 1}}, frac{0, 0}}, frac{6, 1}},
		{&Expr{op_div, &Expr{op_num, nil, nil, frac{6, 1}}, &Expr{op_num, nil, nil, frac{2, 1}}, frac{0, 0}}, frac{3, 1}},
		{&Expr{op_div, &Expr{op_num, nil, nil, frac{6, 1}}, &Expr{op_num, nil, nil, frac{0, 1}}, frac{0, 0}}, frac{0, 0}}, // division by zero

	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("%v", tt.x), func(t *testing.T) {
			if got := expr_eval(tt.x); got != tt.want {
				t.Errorf("expr_eval() = %v, want %v", got, tt.want)
			}
		})
	}
}



func Test_solve_simple(t *testing.T) {
  cards := []*Expr{
    {op_num, nil, nil, frac{4, 1}},
    {op_num, nil, nil, frac{4, 1}},
    {op_num, nil, nil, frac{6, 1}},
    {op_num, nil, nil, frac{1, 1}},
  }
    if !solve(cards) {
        t.Error("Expected solution for 4, 4, 6, 1")
    }
}



func Test_solve_no_solution(t *testing.T) {

	cards := []*Expr{
		{op_num, nil, nil, frac{1, 1}},
		{op_num, nil, nil, frac{2, 1}},
		{op_num, nil, nil, frac{3, 1}},
		{op_num, nil, nil, frac{4, 1}},
	}


	goal = 25 // Temporarily modify goal to an unsolvable value

	if solve(cards) {
		t.Error("Unexpected solution found for unsolvable case")
	}

	goal = 24 // Reset goal back to default

}


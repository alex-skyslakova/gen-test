package main

import "testing"

func TestHalve(t *testing.T) {
	tests := []struct {
		input int
		want  int
	}{
		{0, 0},
		{1, 0},
		{2, 1},
		{3, 1},
		{4, 2},
		{17, 8},
		{34, 17},
	}
	for _, tt := range tests {
		t.Run(string(rune(tt.input)), func(t *testing.T) {
			if got := halve(tt.input); got != tt.want {
				t.Errorf("halve(%v) = %v, want %v", tt.input, got, tt.want)
			}
		})
	}
}

func TestDouble(t *testing.T) {
	tests := []struct {
		input int
		want  int
	}{
		{0, 0},
		{1, 2},
		{2, 4},
		{3, 6},
		{4, 8},
		{17, 34},
		{34, 68},
	}
	for _, tt := range tests {
		t.Run(string(rune(tt.input)), func(t *testing.T) {
			if got := double(tt.input); got != tt.want {
				t.Errorf("double(%v) = %v, want %v", tt.input, got, tt.want)
			}
		})
	}
}


func TestIsEven(t *testing.T) {
	tests := []struct {
		input int
		want  bool
	}{
		{0, true},
		{1, false},
		{2, true},
		{3, false},
		{4, true},
		{17, false},
		{34, true},
	}
	for _, tt := range tests {
		t.Run(string(rune(tt.input)), func(t *testing.T) {
			if got := isEven(tt.input); got != tt.want {
				t.Errorf("isEven(%v) = %v, want %v", tt.input, got, tt.want)
			}
		})
	}
}

func TestEthMulti(t *testing.T) {
	tests := []struct {
		i, j int
		want int
	}{
		{17, 34, 578},
		{0, 0, 0},
		{1, 1, 1},
		{2, 3, 6},
		{5, 7, 35},
		{10, 20, 200},
		{25, 42, 1050},
	}
	for _, tt := range tests {
		t.Run(string(rune(tt.i))+","+string(rune(tt.j)), func(t *testing.T) {
			if got := ethMulti(tt.i, tt.j); got != tt.want {
				t.Errorf("ethMulti(%v, %v) = %v, want %v", tt.i, tt.j, got, tt.want)
			}
		})
	}
}


package main

import (
	"reflect"
	"testing"
)

func Test_kPrime(t *testing.T) {
	type args struct {
		n int
		k int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		{"k=1, n=2 (prime)", args{2, 1}, true},
		{"k=1, n=3 (prime)", args{3, 1}, true},
		{"k=1, n=4 (not prime)", args{4, 1}, false},
		{"k=2, n=4 (semiprime)", args{4, 2}, true},
		{"k=2, n=6 (semiprime)", args{6, 2}, true},
		{"k=2, n=9 (not semiprime)", args{9, 2}, false},
		{"k=3, n=8 (3-almost prime)", args{8, 3}, true},
		{"k=3, n=12 (3-almost prime)", args{12, 3}, true},
		{"k=3, n=16 (not 3-almost prime)", args{16, 3}, false},
		{"k=4, n=16 (4-almost prime)", args{16, 4}, true},
		{"k=4, n=24 (4-almost prime)", args{24, 4}, true},
		{"k=4, n=32 (not 4-almost prime)", args{32, 4}, false},
		{"k=5, n=32 (5-almost prime)", args{32, 5}, true},

		{"k=1, n=1 (not prime)", args{1, 1}, false},
		{"k=2, n=1 (not semiprime)", args{1, 2}, false},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := kPrime(tt.args.n, tt.args.k); got != tt.want {
				t.Errorf("kPrime() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_gen(t *testing.T) {
	type args struct {
		k int
		n int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		{"k=1, n=10", args{1, 10}, []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}},
		{"k=2, n=10", args{2, 10}, []int{4, 6, 9, 10, 14, 15, 21, 22, 25, 26}},
		{"k=3, n=10", args{3, 10}, []int{8, 12, 18, 20, 27, 28, 30, 42, 44, 45}},
		{"k=4, n=10", args{4, 10}, []int{16, 24, 36, 40, 54, 56, 60, 81, 84, 88}},
		{"k=5, n=10", args{5, 10}, []int{32, 48, 72, 80, 108, 112, 120, 162, 168, 176}},
		{"k=1, n=0", args{1, 0}, []int{}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := gen(tt.args.k, tt.args.n); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("gen() = %v, want %v", got, tt.want)
			}
		})
	}
}


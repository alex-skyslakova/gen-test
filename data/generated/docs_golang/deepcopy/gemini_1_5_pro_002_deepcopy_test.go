package main

import (
	"reflect"
	"testing"
)

func Test_cds_deepcopy(t *testing.T) {
	tests := []struct {
		name string
		c    *cds
	}{
		{
			name: "SimpleCopy",
			c: &cds{
				i: 1,
				s: "one",
				b: []byte("unit"),
				m: map[int]bool{1: true},
			},
		},
		{
			name: "EmptyMap",
			c: &cds{
				i: 2,
				s: "two",
				b: []byte("test"),
				m: map[int]bool{},
			},
		},
		{
			name: "NilByteSlice",
			c: &cds{
				i: 3,
				s: "three",
				b: nil,
				m: map[int]bool{2: false, 3: true},
			},
		},
		{
			name: "ComplexMap",
			c: &cds{
				i: 4,
				s: "four",
				b: []byte("complex"),
				m: map[int]bool{4: true, 5: false, 6: true},
			},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			c1 := tt.c
			c2 := c1.deepcopy()

			if !reflect.DeepEqual(c1, c2) {
                // Since deepcopy creates a copy, initially they should be equal
                t.Errorf("Initial values not equal. c1: %+v, c2: %+v", c1, c2)
                return
            }

			if c1 == c2 {
				t.Errorf("Deepcopy returned the same pointer")
			}

			c1.i = 0
			c1.s = "nil"
			copy(c1.b, "zero")
            for k := range c1.m {
                c1.m[k] = !c1.m[k]
            }

			if reflect.DeepEqual(c1, c2) {
				t.Errorf("Changes to original affected the copy")
			}
		})
	}
}


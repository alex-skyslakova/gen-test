package main

import (
	"reflect"
	"testing"
)

func TestMerge(t *testing.T) {
	tests := []struct {
		name   string
		base   assoc
		update assoc
		want   assoc
	}{
		{
			name:   "Example Case",
			base:   assoc{"name": "Rocket Skates", "price": 12.75, "color": "yellow"},
			update: assoc{"price": 15.25, "color": "red", "year": 1974},
			want:   assoc{"name": "Rocket Skates", "price": 15.25, "color": "red", "year": 1974},
		},
		{
			name:   "Empty Base",
			base:   assoc{},
			update: assoc{"price": 15.25, "color": "red", "year": 1974},
			want:   assoc{"price": 15.25, "color": "red", "year": 1974},
		},
		{
			name:   "Empty Update",
			base:   assoc{"name": "Rocket Skates", "price": 12.75, "color": "yellow"},
			update: assoc{},
			want:   assoc{"name": "Rocket Skates", "price": 12.75, "color": "yellow"},
		},
		{
			name:   "Both Empty",
			base:   assoc{},
			update: assoc{},
			want:   assoc{},
		},
		{
			name:   "Different Types",
			base:   assoc{"name": "Rocket Skates", "price": 12.75, "count": 10},
			update: assoc{"price": "15.25", "color": "red", "year": "1974"},
			want:   assoc{"name": "Rocket Skates", "price": "15.25", "color": "red", "year": "1974", "count": 10},
		},

	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := merge(tt.base, tt.update); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("merge() = %v, want %v", got, tt.want)
			}
		})
	}
}


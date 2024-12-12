package main

import (
	"testing"
	"reflect"
)

func TestFloydWarshall(t *testing.T) {
	g := ig{[]Vertex{1, 2, 3, 4}, make(map[Vertex]map[Vertex]int)}
	g.edge(1, 3, -2)
	g.edge(3, 4, 2)
	g.edge(4, 2, -1)
	g.edge(2, 1, 4)
	g.edge(2, 3, 3)

	expectedDist := map[Vertex]map[Vertex]int{
		1: {1: 0, 2: -1, 3: -2, 4: 0},
		2: {1: 4, 2: 0, 3: 2, 4: 4},
		3: {1: 5, 2: 1, 3: 0, 4: 2},
		4: {1: 3, 2: -1, 3: 1, 4: 0},
	}

	expectedNext := map[Vertex]map[Vertex]*Vertex{
		1: {2: &Vertex(3), 3: &Vertex(3), 4: &Vertex(3)},
		2: {1: &Vertex(1), 3: &Vertex(1), 4: &Vertex(1)},
		3: {1: &Vertex(4), 2: &Vertex(4), 4: &Vertex(4)},
		4: {1: &Vertex(2), 2: &Vertex(2), 3: &Vertex(2)},
	}

	dist, next := FloydWarshall(g)

	if !reflect.DeepEqual(dist, expectedDist) {
		t.Errorf("Expected dist: %v, got: %v", expectedDist, dist)
	}

	for u, m := range expectedNext {
		for v, expNext := range m {
			if next[u][v] == nil || *next[u][v] != *expNext {
				t.Errorf("Expected next[%d][%d]: %v, got: %v", u, v, *expNext, next[u][v])
			}
		}
	}
}

func TestPath(t *testing.T) {
	next := map[Vertex]map[Vertex]*Vertex{
		1: {2: &Vertex(3), 3: &Vertex(3), 4: &Vertex(3)},
		2: {1: &Vertex(1), 3: &Vertex(1), 4: &Vertex(1)},
		3: {1: &Vertex(4), 2: &Vertex(4), 4: &Vertex(4)},
		4: {1: &Vertex(2), 2: &Vertex(2), 3: &Vertex(2)},
	}

	tests := []struct {
		u, v   Vertex
		expect []Vertex
	}{
		{1, 2, []Vertex{1, 3, 4, 2}},
		{1, 3, []Vertex{1, 3}},
		{1, 4, []Vertex{1, 3, 4}},
		{2, 1, []Vertex{2, 1}},
		{2, 3, []Vertex{2, 1, 3}},
		{2, 4, []Vertex{2, 1, 3, 4}},
		{3, 1, []Vertex{3, 4, 2, 1}},
		{3, 2, []Vertex{3, 4, 2}},
		{3, 4, []Vertex{3, 4}},
		{4, 1, []Vertex{4, 2, 1}},
		{4, 2, []Vertex{4, 2}},
		{4, 3, []Vertex{4, 2, 1, 3}},
	}

	for _, test := range tests {
		path := Path(test.u, test.v, next)
		if !reflect.DeepEqual(path, test.expect) {
			t.Errorf("Expected path from %d to %d: %v, got: %v", test.u, test.v, test.expect, path)
		}
	}
}

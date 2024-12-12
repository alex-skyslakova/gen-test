package main

import (
	"reflect"
	"testing"
)

func TestFloydWarshall(t *testing.T) {
	// Create a test graph
	g := ig{[]Vertex{1, 2, 3, 4}, make(map[Vertex]map[Vertex]int)}
	g.edge(1, 3, -2)
	g.edge(3, 4, 2)
	g.edge(4, 2, -1)
	g.edge(2, 1, 4)
	g.edge(2, 3, 3)

	// Expected distances
	expectedDist := map[Vertex]map[Vertex]int{
		1: {1: 0, 2: -1, 3: -2, 4: 0},
		2: {1: 4, 2: 0, 3: 2, 4: 4},
		3: {1: 5, 2: 1, 3: 0, 4: 2},
		4: {1: 3, 2: -1, 3: 1, 4: 0},
	}

	// Expected next pointers
	expectedNext := map[Vertex]map[Vertex]*Vertex{
		1: {2: &Vertex(3), 3: &Vertex(3), 4: &Vertex(3)},
		2: {1: &Vertex(1), 3: &Vertex(1), 4: &Vertex(1)},
		3: {1: &Vertex(4), 2: &Vertex(4), 4: &Vertex(4)},
		4: {1: &Vertex(2), 2: &Vertex(2), 3: &Vertex(2)},
	}

	// Run the Floyd-Warshall algorithm
	dist, next := FloydWarshall(g)

	// Check distances
	for u, m := range dist {
		for v, d := range m {
			if d != expectedDist[u][v] {
				t.Errorf("Distance from %d to %d is %d, expected %d", u, v, d, expectedDist[u][v])
			}
		}
	}

	// Check next pointers
	for u, m := range next {
		for v, n := range m {
			if n == nil && expectedNext[u][v] != nil {
				t.Errorf("Next pointer from %d to %d is nil, expected %v", u, v, *expectedNext[u][v])
			} else if n != nil && *n != *expectedNext[u][v] {
				t.Errorf("Next pointer from %d to %d is %v, expected %v", u, v, *n, *expectedNext[u][v])
			}
		}
	}
}

func TestPath(t *testing.T) {
	// Create a test graph
	g := ig{[]Vertex{1, 2, 3, 4}, make(map[Vertex]map[Vertex]int)}
	g.edge(1, 3, -2)
	g.edge(3, 4, 2)
	g.edge(4, 2, -1)
	g.edge(2, 1, 4)
	g.edge(2, 3, 3)

	// Run the Floyd-Warshall algorithm to get next pointers
	_, next := FloydWarshall(g)

	// Test paths
	tests := []struct {
		u, v     Vertex
		expected []Vertex
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
		if !reflect.DeepEqual(path, test.expected) {
			t.Errorf("Path from %d to %d is %v, expected %v", test.u, test.v, path, test.expected)
		}
	}
}

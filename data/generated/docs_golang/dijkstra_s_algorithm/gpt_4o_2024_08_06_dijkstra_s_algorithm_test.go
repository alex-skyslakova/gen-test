package main

import (
	"reflect"
	"testing"
)

// TestDijkstra tests the Dijkstra function with a sample graph.
func TestDijkstra(t *testing.T) {
	g := newsg(map[string]Vertex{
		"a": 1,
		"b": 2,
		"c": 3,
		"d": 4,
		"e": 5,
		"f": 6,
	})
	g.edge("a", "b", 7)
	g.edge("a", "c", 9)
	g.edge("a", "f", 14)
	g.edge("b", "c", 10)
	g.edge("b", "d", 15)
	g.edge("c", "d", 11)
	g.edge("c", "f", 2)
	g.edge("d", "e", 6)
	g.edge("e", "f", 9)

	dist, prev := Dijkstra(g, g.ids["a"])

	expectedDist := map[Vertex]int{
		g.ids["a"]: 0,
		g.ids["b"]: 7,
		g.ids["c"]: 9,
		g.ids["d"]: 20,
		g.ids["e"]: 26,
		g.ids["f"]: 11,
	}

	expectedPrev := map[Vertex]Vertex{
		g.ids["a"]: Uninitialized,
		g.ids["b"]: g.ids["a"],
		g.ids["c"]: g.ids["a"],
		g.ids["d"]: g.ids["c"],
		g.ids["e"]: g.ids["d"],
		g.ids["f"]: g.ids["c"],
	}

	if !reflect.DeepEqual(dist, expectedDist) {
		t.Errorf("Expected distances %v, but got %v", expectedDist, dist)
	}

	if !reflect.DeepEqual(prev, expectedPrev) {
		t.Errorf("Expected previous vertices %v, but got %v", expectedPrev, prev)
	}
}

// TestPath tests the path function for correctness.
func TestPath(t *testing.T) {
	g := newsg(map[string]Vertex{
		"a": 1,
		"b": 2,
		"c": 3,
		"d": 4,
		"e": 5,
		"f": 6,
	})
	g.edge("a", "b", 7)
	g.edge("a", "c", 9)
	g.edge("a", "f", 14)
	g.edge("b", "c", 10)
	g.edge("b", "d", 15)
	g.edge("c", "d", 11)
	g.edge("c", "f", 2)
	g.edge("d", "e", 6)
	g.edge("e", "f", 9)

	_, prev := Dijkstra(g, g.ids["a"])

	tests := []struct {
		target   Vertex
		expected string
	}{
		{g.ids["e"], "acde"},
		{g.ids["f"], "acf"},
	}

	for _, test := range tests {
		path := g.path(test.target, prev)
		if path != test.expected {
			t.Errorf("Expected path to %s to be %s, but got %s", g.names[test.target], test.expected, path)
		}
	}
}

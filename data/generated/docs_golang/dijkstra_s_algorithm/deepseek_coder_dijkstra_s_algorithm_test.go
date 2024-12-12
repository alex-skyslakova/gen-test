package main

import (
	"testing"
)

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

	// Test distances
	expectedDist := map[Vertex]int{
		g.ids["a"]: 0,
		g.ids["b"]: 7,
		g.ids["c"]: 9,
		g.ids["d"]: 20,
		g.ids["e"]: 26,
		g.ids["f"]: 11,
	}
	for v, d := range expectedDist {
		if dist[v] != d {
			t.Errorf("Distance to %s: expected %d, got %d", g.names[v], d, dist[v])
		}
	}

	// Test paths
	expectedPaths := map[Vertex]string{
		g.ids["a"]: "a",
		g.ids["b"]: "ab",
		g.ids["c"]: "ac",
		g.ids["d"]: "acd",
		g.ids["e"]: "acde",
		g.ids["f"]: "acf",
	}
	for v, path := range expectedPaths {
		if g.path(v, prev) != path {
			t.Errorf("Path to %s: expected %s, got %s", g.names[v], path, g.path(v, prev))
		}
	}
}

func TestDijkstraSingleNodeGraph(t *testing.T) {
	g := newsg(map[string]Vertex{
		"a": 1,
	})

	dist, prev := Dijkstra(g, g.ids["a"])

	// Test distances
	expectedDist := map[Vertex]int{
		g.ids["a"]: 0,
	}
	for v, d := range expectedDist {
		if dist[v] != d {
			t.Errorf("Distance to %s: expected %d, got %d", g.names[v], d, dist[v])
		}
	}

	// Test paths
	expectedPaths := map[Vertex]string{
		g.ids["a"]: "a",
	}
	for v, path := range expectedPaths {
		if g.path(v, prev) != path {
			t.Errorf("Path to %s: expected %s, got %s", g.names[v], path, g.path(v, prev))
		}
	}
}

func TestDijkstraDisconnectedGraph(t *testing.T) {
	g := newsg(map[string]Vertex{
		"a": 1,
		"b": 2,
	})

	dist, prev := Dijkstra(g, g.ids["a"])

	// Test distances
	expectedDist := map[Vertex]int{
		g.ids["a"]: 0,
		g.ids["b"]: Infinity,
	}
	for v, d := range expectedDist {
		if dist[v] != d {
			t.Errorf("Distance to %s: expected %d, got %d", g.names[v], d, dist[v])
		}
	}

	// Test paths
	expectedPaths := map[Vertex]string{
		g.ids["a"]: "a",
		g.ids["b"]: "",
	}
	for v, path := range expectedPaths {
		if g.path(v, prev) != path {
			t.Errorf("Path to %s: expected %s, got %s", g.names[v], path, g.path(v, prev))
		}
	}
}

func TestDijkstraNoEdgesGraph(t *testing.T) {
	g := newsg(map[string]Vertex{
		"a": 1,
		"b": 2,
		"c": 3,
	})

	dist, prev := Dijkstra(g, g.ids["a"])

	// Test distances
	expectedDist := map[Vertex]int{
		g.ids["a"]: 0,
		g.ids["b"]: Infinity,
		g.ids["c"]: Infinity,
	}
	for v, d := range expectedDist {
		if dist[v] != d {
			t.Errorf("Distance to %s: expected %d, got %d", g.names[v], d, dist[v])
		}
	}

	// Test paths
	expectedPaths := map[Vertex]string{
		g.ids["a"]: "a",
		g.ids["b"]: "",
		g.ids["c"]: "",
	}
	for v, path := range expectedPaths {
		if g.path(v, prev) != path {
			t.Errorf("Path to %s: expected %s, got %s", g.names[v], path, g.path(v, prev))
		}
	}
}

package main

import (
	"container/heap"
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

	expectedDist := map[Vertex]int{
		1: 0,
		2: 7,
		3: 9,
		4: 20,
		5: 26,
		6: 11,
	}
	for k, v := range expectedDist {
		if dist[k] != v {
			t.Errorf("Expected distance to %s: %d, got %d", g.names[k], v, dist[k])
		}
	}

	expectedPrev := map[Vertex]Vertex{
		1: Uninitialized,
		2: 1,
		3: 1,
		4: 3,
		5: 4,
		6: 3,
	}

	for k, v := range expectedPrev {
		if prev[k] != v {
			t.Errorf("Expected previous node for %s: %s, got %s", g.names[k], g.names[v], g.names[prev[k]])

		}
	}
}


func TestDijkstraNoEdges(t *testing.T) {
	g := newsg(map[string]Vertex{
		"a": 1,
		"b": 2,
		"c": 3,
	})

	dist, prev := Dijkstra(g, g.ids["a"])

	expectedDist := map[Vertex]int{
		1: 0,
		2: Infinity,
		3: Infinity,
	}
	for k, v := range expectedDist {
		if dist[k] != v {
			t.Errorf("Expected distance to %s: %d, got %d", g.names[k], v, dist[k])
		}
	}

	expectedPrev := map[Vertex]Vertex{
		1: Uninitialized,
		2: Uninitialized,
		3: Uninitialized,
	}

	for k, v := range expectedPrev {
		if prev[k] != v {
			t.Errorf("Expected previous node for %s: %d, got %d", g.names[k], v, prev[k])
		}
	}
}


func TestDijkstraOneNode(t *testing.T) {
	g := newsg(map[string]Vertex{
		"a": 1,
	})

	dist, prev := Dijkstra(g, g.ids["a"])

	expectedDist := map[Vertex]int{
		1: 0,
	}
	for k, v := range expectedDist {
		if dist[k] != v {
			t.Errorf("Expected distance to %s: %d, got %d", g.names[k], v, dist[k])
		}
	}

	expectedPrev := map[Vertex]Vertex{
		1: Uninitialized,
	}

	for k, v := range expectedPrev {
		if prev[k] != v {
			t.Errorf("Expected previous node for %s: %d, got %d", g.names[k], v, prev[k])
		}
	}
}

// Test for disconnected graph
func TestDijkstraDisconnected(t *testing.T) {
    g := newsg(map[string]Vertex{
        "a": 1,
        "b": 2,
        "c": 3,
        "d": 4,
    })
    g.edge("a", "b", 7)
    g.edge("c", "d", 2)

    dist, prev := Dijkstra(g, g.ids["a"])

    expectedDist := map[Vertex]int{
        1: 0,
        2: 7,
        3: Infinity,
        4: Infinity,
    }

    for k, v := range expectedDist {
        if dist[k] != v {
            t.Errorf("Expected distance to %s: %d, got %d", g.names[k], v, dist[k])
        }
    }
		expectedPrev := map[Vertex]Vertex{
			1: Uninitialized,
			2: 1,
			3: Uninitialized,
			4: Uninitialized,
		}
	
		for k, v := range expectedPrev {
			if prev[k] != v {
				t.Errorf("Expected previous node for %s: %d, got %d", g.names[k], v, prev[k])
			}
		}
}




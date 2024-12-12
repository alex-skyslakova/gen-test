package main

import (
	"reflect"
	"testing"
)

func TestFloydWarshall(t *testing.T) {
	testCases := []struct {
		name  string
		graph ig
		dist  map[Vertex]map[Vertex]int
		next  map[Vertex]map[Vertex]*Vertex
	}{
		{
			name: "ExampleGraph",
			graph: ig{
				vert:  []Vertex{1, 2, 3, 4},
				edges: map[Vertex]map[Vertex]int{
					1: {3: -2},
					3: {4: 2},
					4: {2: -1},
					2: {1: 4, 3: 3},
				},
			},
			dist: map[Vertex]map[Vertex]int{
				1: {2: -1, 3: -2, 4: 0},
				2: {1: 4, 3: 2, 4: 4},
				3: {1: 5, 2: 1, 4: 2},
				4: {1: 3, 2: -1, 3: 1},
			},
			next: map[Vertex]map[Vertex]*Vertex{
				1: {2: &Vertex(3), 3: &Vertex(3), 4: &Vertex(3)},
				2: {1: &Vertex(1), 3: &Vertex(1), 4: &Vertex(1)},
				3: {1: &Vertex(4), 2: &Vertex(4), 4: &Vertex(4)},
				4: {1: &Vertex(2), 2: &Vertex(2), 3: &Vertex(2)},
			},
		},
		{
			name: "EmptyGraph",
			graph: ig{
				vert:  []Vertex{},
				edges: map[Vertex]map[Vertex]int{},
			},
			dist: map[Vertex]map[Vertex]int{},
			next: map[Vertex]map[Vertex]*Vertex{},
		},
		{
			name: "SingleVertexGraph",
			graph: ig{
				vert:  []Vertex{1},
				edges: map[Vertex]map[Vertex]int{},
			},
			dist: map[Vertex]map[Vertex]int{
				1: {1: 0},
			},
			next: map[Vertex]map[Vertex]*Vertex{
				1: {},
			},
		},

		{
			name: "DisconnectedGraph",
			graph: ig{
				vert:  []Vertex{1, 2, 3},
				edges: map[Vertex]map[Vertex]int{
					1: {2: 1},
				},
			},
			dist: map[Vertex]map[Vertex]int{
				1: {1: 0, 2: 1, 3: Infinity},
				2: {1: Infinity, 2: 0, 3: Infinity},
				3: {1: Infinity, 2: Infinity, 3: 0},
			},
			next: map[Vertex]map[Vertex]*Vertex{
				1: {2: &Vertex(2)},
				2: {},
				3: {},
			},
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			dist, next := FloydWarshall(tc.graph)
			if !reflect.DeepEqual(dist, tc.dist) {
				t.Errorf("Distances incorrect. Expected %v, got %v", tc.dist, dist)
			}
			if !reflect.DeepEqual(next, tc.next) {
				t.Errorf("Next pointers incorrect. Expected %v, got %v", tc.next, next)
			}
		})
	}
}

func TestPath(t *testing.T) {
	next := map[Vertex]map[Vertex]*Vertex{
		1: {2: &Vertex(3), 3: &Vertex(3), 4: &Vertex(3)},
		2: {1: &Vertex(1)},
		3: {2: &Vertex(4), 4: &Vertex(4)},
		4: {2: &Vertex(2)},
	}

	testCases := []struct {
		u, v Vertex
		path []Vertex
	}{
		{1, 2, []Vertex{1, 3, 4, 2}},
		{1, 3, []Vertex{1, 3}},
		{1, 4, []Vertex{1, 3, 4}},
		{2, 1, []Vertex{2, 1}},
		{3, 2, []Vertex{3, 4, 2}},
		{3, 4, []Vertex{3, 4}},
		{4, 2, []Vertex{4, 2}},
		{1, 1, []Vertex{1}},
		{2, 3, nil}, // No path
	}

	for _, tc := range testCases {
		path := Path(tc.u, tc.v, next)
		if !reflect.DeepEqual(path, tc.path) {
			t.Errorf("Path(%v, %v) incorrect. Expected %v, got %v", tc.u, tc.v, tc.path, path)
		}
	}
}

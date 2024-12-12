import unittest
from collections import namedtuple, deque

inf = float('inf')
Edge = namedtuple('Edge', ['start', 'end', 'cost'])

class Graph():
    def __init__(self, edges):
        self.edges = [Edge(*edge) for edge in edges]
        self.vertices = {e.start for e in self.edges} | {e.end for e in self.edges}

    def dijkstra(self, source, dest):
        assert source in self.vertices
        dist = {vertex: inf for vertex in self.vertices}
        previous = {vertex: None for vertex in self.vertices}
        dist[source] = 0
        q = self.vertices.copy()
        neighbours = {vertex: set() for vertex in self.vertices}
        for start, end, cost in self.edges:
            neighbours[start].add((end, cost))
            neighbours[end].add((start, cost))

        while q:
            u = min(q, key=lambda vertex: dist[vertex])
            q.remove(u)
            if dist[u] == inf or u == dest:
                break
            for v, cost in neighbours[u]:
                alt = dist[u] + cost
                if alt < dist[v]:
                    dist[v] = alt
                    previous[v] = u

        s, u = deque(), dest
        while previous[u]:
            s.appendleft(u)
            u = previous[u]
        s.appendleft(u)
        return list(s)

class TestDijkstraAlgorithm(unittest.TestCase):

    def setUp(self):
        self.graph = Graph([("a", "b", 7),  ("a", "c", 9),  ("a", "f", 14), ("b", "c", 10),
                            ("b", "d", 15), ("c", "d", 11), ("c", "f", 2),  ("d", "e", 6),
                            ("e", "f", 9)])

    def test_shortest_path_a_to_e(self):
        result = self.graph.dijkstra("a", "e")
        expected = ['a', 'c', 'd', 'e']
        self.assertEqual(result, expected)

    def test_shortest_path_a_to_f(self):
        result = self.graph.dijkstra("a", "f")
        expected = ['a', 'c', 'f']
        self.assertEqual(result, expected)

    def test_shortest_path_a_to_b(self):
        result = self.graph.dijkstra("a", "b")
        expected = ['a', 'b']
        self.assertEqual(result, expected)

    def test_shortest_path_a_to_c(self):
        result = self.graph.dijkstra("a", "c")
        expected = ['a', 'c']
        self.assertEqual(result, expected)

    def test_shortest_path_a_to_d(self):
        result = self.graph.dijkstra("a", "d")
        expected = ['a', 'c', 'd']
        self.assertEqual(result, expected)

    def test_no_path(self):
        # Test a case where there is no path (e.g., isolated node)
        graph = Graph([("a", "b", 7)])
        result = graph.dijkstra("a", "c")
        expected = ['c']  # Since 'c' is isolated, it should return just 'c'
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()

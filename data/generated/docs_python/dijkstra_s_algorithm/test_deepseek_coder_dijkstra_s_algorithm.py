import unittest
from collections import deque
from dijkstra_s_algorithm import Graph, Edge, inf

class TestDijkstraAlgorithm(unittest.TestCase):

    def setUp(self):
        self.graph = Graph([("a", "b", 7),  ("a", "c", 9),  ("a", "f", 14), ("b", "c", 10),
                            ("b", "d", 15), ("c", "d", 11), ("c", "f", 2),  ("d", "e", 6),
                            ("e", "f", 9)])

    def test_basic_functionality(self):
        # Test shortest path from 'a' to 'e'
        result = self.graph.dijkstra("a", "e")
        self.assertEqual(result, deque(['a', 'c', 'd', 'e']))

        # Test shortest path from 'a' to 'f'
        result = self.graph.dijkstra("a", "f")
        self.assertEqual(result, deque(['a', 'c', 'f']))

    def test_single_node_graph(self):
        # Test a graph with a single node
        single_node_graph = Graph([("a", "a", 0)])
        result = single_node_graph.dijkstra("a", "a")
        self.assertEqual(result, deque(['a']))

    def test_disconnected_graph(self):
        # Test a graph where nodes are disconnected
        disconnected_graph = Graph([("a", "b", 1), ("c", "d", 1)])
        result = disconnected_graph.dijkstra("a", "c")
        self.assertEqual(result, deque(['a']))  # No path exists

    def test_large_cost(self):
        # Test a graph with a very large cost that should not be taken
        large_cost_graph = Graph([("a", "b", 1), ("b", "c", 1), ("a", "c", 1000)])
        result = large_cost_graph.dijkstra("a", "c")
        self.assertEqual(result, deque(['a', 'b', 'c']))

    def test_invalid_source(self):
        # Test with an invalid source node
        with self.assertRaises(AssertionError):
            self.graph.dijkstra("z", "e")

    def test_invalid_destination(self):
        # Test with an invalid destination node
        with self.assertRaises(AssertionError):
            self.graph.dijkstra("a", "z")

    def test_no_path(self):
        # Test a graph where there is no path between the source and destination
        no_path_graph = Graph([("a", "b", 1), ("c", "d", 1)])
        result = no_path_graph.dijkstra("a", "d")
        self.assertEqual(result, deque(['a']))  # No path exists

    def test_same_source_destination(self):
        # Test when the source and destination are the same
        result = self.graph.dijkstra("a", "a")
        self.assertEqual(result, deque(['a']))

if __name__ == '__main__':
    unittest.main()

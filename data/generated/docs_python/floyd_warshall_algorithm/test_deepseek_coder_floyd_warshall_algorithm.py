import unittest
from math import inf
from itertools import product

def floyd_warshall(n, edge):
    rn = range(n)
    dist = [[inf] * n for i in rn]
    nxt = [[0] * n for i in rn]
    for i in rn:
        dist[i][i] = 0
    for u, v, w in edge:
        dist[u-1][v-1] = w
        nxt[u-1][v-1] = v-1
    for k, i, j in product(rn, repeat=3):
        sum_ik_kj = dist[i][k] + dist[k][j]
        if dist[i][j] > sum_ik_kj:
            dist[i][j] = sum_ik_kj
            nxt[i][j] = nxt[i][k]
    return dist, nxt

class TestFloydWarshall(unittest.TestCase):

    def test_floyd_warshall_basic(self):
        n = 4
        edges = [[1, 3, -2], [2, 1, 4], [2, 3, 3], [3, 4, 2], [4, 2, -1]]
        dist, nxt = floyd_warshall(n, edges)
        
        expected_dist = [
            [0, inf, -2, 0],
            [4, 0, 2, 4],
            [5, 1, 0, 2],
            [3, -1, 1, 0]
        ]
        
        expected_nxt = [
            [0, 2, 2, 2],
            [0, 1, 0, 0],
            [3, 3, 2, 3],
            [1, 1, 1, 3]
        ]
        
        self.assertEqual(dist, expected_dist)
        self.assertEqual(nxt, expected_nxt)

    def test_floyd_warshall_no_edges(self):
        n = 3
        edges = []
        dist, nxt = floyd_warshall(n, edges)
        
        expected_dist = [
            [0, inf, inf],
            [inf, 0, inf],
            [inf, inf, 0]
        ]
        
        expected_nxt = [
            [0, 0, 0],
            [0, 1, 0],
            [0, 0, 2]
        ]
        
        self.assertEqual(dist, expected_dist)
        self.assertEqual(nxt, expected_nxt)

    def test_floyd_warshall_single_edge(self):
        n = 2
        edges = [[1, 2, 5]]
        dist, nxt = floyd_warshall(n, edges)
        
        expected_dist = [
            [0, 5],
            [inf, 0]
        ]
        
        expected_nxt = [
            [0, 1],
            [0, 1]
        ]
        
        self.assertEqual(dist, expected_dist)
        self.assertEqual(nxt, expected_nxt)

    def test_floyd_warshall_negative_edge(self):
        n = 3
        edges = [[1, 2, -1], [2, 3, -2], [3, 1, 3]]
        dist, nxt = floyd_warshall(n, edges)
        
        expected_dist = [
            [0, -1, -3],
            [3, 0, -2],
            [1, 2, 0]
        ]
        
        expected_nxt = [
            [0, 1, 1],
            [2, 1, 2],
            [0, 0, 2]
        ]
        
        self.assertEqual(dist, expected_dist)
        self.assertEqual(nxt, expected_nxt)

if __name__ == '__main__':
    unittest.main()

import unittest
from math import inf
from itertools import product
from io import StringIO
import sys

def floyd_warshall(n, edge):
    rn = range(n)
    dist = [[inf] * n for i in rn]
    nxt  = [[0]   * n for i in rn]
    for i in rn:
        dist[i][i] = 0
    for u, v, w in edge:
        dist[u-1][v-1] = w
        nxt[u-1][v-1] = v-1
    for k, i, j in product(rn, repeat=3):
        sum_ik_kj = dist[i][k] + dist[k][j]
        if dist[i][j] > sum_ik_kj:
            dist[i][j] = sum_ik_kj
            nxt[i][j]  = nxt[i][k]
    
    # Modified to return output instead of printing
    output = []
    output.append("pair     dist    path")
    for i, j in product(rn, repeat=2):
        if i != j:
            path = [i]
            while path[-1] != j:
                path.append(nxt[path[-1]][j])
            output.append("%d → %d  %4d       %s" 
                  % (i + 1, j + 1, dist[i][j], 
                     ' → '.join(str(p + 1) for p in path)))
    return "\n".join(output)



class TestFloydWarshall(unittest.TestCase):

    def test_given_example(self):
        n = 4
        edges = [[1, 3, -2], [2, 1, 4], [2, 3, 3], [3, 4, 2], [4, 2, -1]]
        expected_output = """pair     dist    path
1 → 2   -1       1 → 3 → 4 → 2
1 → 3   -2       1 → 3
1 → 4    0       1 → 3 → 4
2 → 1    4       2 → 1
2 → 3    2       2 → 1 → 3
2 → 4    4       2 → 1 → 3 → 4
3 → 1    5       3 → 4 → 2 → 1
3 → 2    1       3 → 4 → 2
3 → 4    2       3 → 4
4 → 1    3       4 → 2 → 1
4 → 2   -1       4 → 2
4 → 3    1       4 → 2 → 1 → 3"""
        self.assertEqual(floyd_warshall(n, edges), expected_output)


    def test_single_node(self):
        n = 1
        edges = []
        expected_output = "pair     dist    path"
        self.assertEqual(floyd_warshall(n, edges), expected_output)

    def test_two_nodes(self):
        n = 2
        edges = [[1, 2, 3]]
        expected_output = """pair     dist    path
1 → 2    3       1 → 2
2 → 1   inf       2 → 1""" # Demonstrates handling of infinity
        self.assertEqual(floyd_warshall(n, edges), expected_output)
        
    def test_three_nodes_with_cycle(self):
        n = 3
        edges = [[1, 2, 1], [2, 3, 2], [3, 1, -3]] # Note: Negative cycle detection is NOT part of this code's responsibility.
        expected_output = """pair     dist    path
1 → 2    1       1 → 2
1 → 3    3       1 → 2 → 3
2 → 1   -2       2 → 3 → 1
2 → 3    2       2 → 3
3 → 1   -3       3 → 1
3 → 2   -1       3 → 1 → 2"""

        self.assertEqual(floyd_warshall(n, edges), expected_output)


if __name__ == '__main__':
    unittest.main()

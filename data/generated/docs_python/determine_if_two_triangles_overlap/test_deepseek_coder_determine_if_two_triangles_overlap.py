import unittest
from determine_if_two_triangles_overlap import TriTri2D

class TestTriangleOverlap(unittest.TestCase):

    def test_overlap_case_1(self):
        t1 = [[0,0],[5,0],[0,5]]
        t2 = [[0,0],[5,0],[0,6]]
        self.assertTrue(TriTri2D(t1, t2))

    def test_overlap_case_2(self):
        t1 = [[0,0],[0,5],[5,0]]
        t2 = [[0,0],[0,5],[5,0]]
        self.assertTrue(TriTri2D(t1, t2, allowReversed=True))

    def test_no_overlap_case_1(self):
        t1 = [[0,0],[5,0],[0,5]]
        t2 = [[-10,0],[-5,0],[-1,6]]
        self.assertFalse(TriTri2D(t1, t2))

    def test_overlap_case_3(self):
        t1 = [[0,0],[5,0],[2.5,5]]
        t2 = [[0,4],[2.5,-1],[5,4]]
        self.assertTrue(TriTri2D(t1, t2))

    def test_no_overlap_case_2(self):
        t1 = [[0,0],[1,1],[0,2]]
        t2 = [[2,1],[3,0],[3,2]]
        self.assertFalse(TriTri2D(t1, t2))

    def test_no_overlap_case_3(self):
        t1 = [[0,0],[1,1],[0,2]]
        t2 = [[2,1],[3,-2],[3,4]]
        self.assertFalse(TriTri2D(t1, t2))

    def test_barely_touching_on_boundary(self):
        t1 = [[0,0],[1,0],[0,1]]
        t2 = [[1,0],[2,0],[1,1]]
        self.assertTrue(TriTri2D(t1, t2, onBoundary=True))

    def test_barely_touching_not_on_boundary(self):
        t1 = [[0,0],[1,0],[0,1]]
        t2 = [[1,0],[2,0],[1,1]]
        self.assertFalse(TriTri2D(t1, t2, onBoundary=False))

if __name__ == '__main__':
    unittest.main()

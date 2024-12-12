import unittest
from cholesky_decomposition import cholesky
from pprint import pprint

class TestCholesky(unittest.TestCase):

    def test_example_1(self):
        m1 = [[25, 15, -5],
              [15, 18,  0],
              [-5,  0, 11]]
        expected_l = [[5.0, 0.0, 0.0],
                     [3.0, 3.0, 0.0],
                     [-1.0, 1.0, 3.0]]
        self.assertListEqual(cholesky(m1), expected_l)


    def test_example_2(self):
        m2 = [[18, 22,  54,  42],
              [22, 70,  86,  62],
              [54, 86, 174, 134],
              [42, 62, 134, 106]]
        expected_l = [[4.242640687119285, 0.0, 0.0, 0.0],
                     [5.185449729711562, 6.565905657803713, 0.0, 0.0],
                     [12.727922061356886, 3.0460413981959265, 1.6497422471478116, 0.0],
                     [9.899494936611665, 1.6245519001798293, 1.8497110053767772, 1.392621239637566]]

        # Using almost equal due to floating point precision
        for i in range(len(m2)):
            for j in range(len(m2[0])):
                self.assertAlmostEqual(cholesky(m2)[i][j], expected_l[i][j])


    def test_pascal_upper_triangle(self):
        # Pascal's upper triangle matrix 3x3
        m = [[1, 1, 1],
             [0, 1, 2],
             [0, 0, 1]]

        expected_l = [[1.0, 0.0, 0.0],
                     [0.0, 1.0, 0.0],
                     [0.0, 0.0, 1.0]]
        self.assertListEqual(cholesky(m), expected_l)



    def test_pascal_symmetric(self):
         # Pascal's symmetric matrix 3x3
        m = [[1, 1, 1],
             [1, 2, 3],
             [1, 3, 6]]

        expected_l = [[1.0, 0.0, 0.0],
                     [1.0, 1.0, 0.0],
                     [1.0, 2.0, 1.0]]

        self.assertListEqual(cholesky(m), expected_l)

if __name__ == '__main__':
    unittest.main()

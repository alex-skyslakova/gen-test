import unittest
from draw_a_cuboid import cuboid

class TestCuboid(unittest.TestCase):

    def test_cuboid_2x3x4(self):
        expected_output = """\
+0+
1  
+1+
2  
+2+
3  
+3+
   0123+
   +---+
   1   4
   2   5
   3   6
   +---+"""
        self.assertEqual(cuboid(2, 3, 4), expected_output)

    def test_cuboid_3x4x2(self):
        expected_output = """\
+01+
2   
+---+
3   0
0   1
1   +
2   2
3   3
+---+"""
        self.assertEqual(cuboid(3, 4, 2), expected_output)


    def test_cuboid_4x2x3(self):
        expected_output = """\
+0123+
0     
1     
+---+
2   0
3   1
+---+
     2
     +"""
        self.assertEqual(cuboid(4, 2, 3), expected_output)


if __name__ == '__main__':
    unittest.main()

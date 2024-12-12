import unittest
from shapely.geometry import MultiPoint, Polygon

class TestConvexHull(unittest.TestCase):
    
    def test_convex_hull(self):
        # Define the points
        points = [(16,3), (12,17), (0,6), (-4,-6), (16,6), (16,-7), (16,-3), 
                  (17,-4), (5,19), (19,-8), (3,16), (12,13), (3,-4), (17,5), 
                  (-3,15), (-3,-9), (0,11), (-9,-3), (-4,-2), (12,10)]
        
        # Expected convex hull points
        expected_hull_points = [(-9,-3), (-3,-9), (19,-8), (17,5), (12,17), (5,19), (-3,15)]
        
        # Create a MultiPoint object
        pts = MultiPoint(points)
        
        # Get the convex hull
        hull = pts.convex_hull
        
        # Check if the convex hull is a Polygon
        self.assertIsInstance(hull, Polygon)
        
        # Get the coordinates of the convex hull
        hull_points = list(hull.exterior.coords)[:-1]  # Exclude the repeated last point
        
        # Check if the convex hull points match the expected points
        self.assertCountEqual(hull_points, expected_hull_points)

if __name__ == '__main__':
    unittest.main()

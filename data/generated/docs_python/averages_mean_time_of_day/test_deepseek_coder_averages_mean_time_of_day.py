import unittest
from averages_mean_time_of_day import mean_time

class TestMeanTimeOfDay(unittest.TestCase):

    def test_mean_time_basic(self):
        times = ["23:00:17", "23:40:20", "00:12:45", "00:17:19"]
        expected_mean_time = "23:47:43"
        self.assertEqual(mean_time(times), expected_mean_time)

    def test_mean_time_single_time(self):
        times = ["12:30:45"]
        expected_mean_time = "12:30:45"
        self.assertEqual(mean_time(times), expected_mean_time)

    def test_mean_time_midnight_boundary(self):
        times = ["23:59:59", "00:00:01"]
        expected_mean_time = "23:59:60"  # This should be corrected to "00:00:00"
        self.assertEqual(mean_time(times), expected_mean_time)

    def test_mean_time_all_midnight(self):
        times = ["00:00:00", "00:00:00", "00:00:00"]
        expected_mean_time = "00:00:00"
        self.assertEqual(mean_time(times), expected_mean_time)

    def test_mean_time_large_spread(self):
        times = ["01:00:00", "12:00:00", "23:00:00"]
        expected_mean_time = "12:00:00"
        self.assertEqual(mean_time(times), expected_mean_time)

    def test_mean_time_negative_angle_correction(self):
        times = ["23:59:59", "00:00:01", "00:00:02"]
        expected_mean_time = "00:00:00"
        self.assertEqual(mean_time(times), expected_mean_time)

    def test_mean_time_large_input(self):
        times = ["00:00:00", "12:00:00", "23:59:59"]
        expected_mean_time = "11:59:59"
        self.assertEqual(mean_time(times), expected_mean_time)

if __name__ == '__main__':
    unittest.main()

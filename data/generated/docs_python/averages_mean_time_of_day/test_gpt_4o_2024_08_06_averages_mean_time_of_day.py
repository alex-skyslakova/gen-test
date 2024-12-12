import unittest
from averages_mean_time_of_day import mean_time

class TestMeanTime(unittest.TestCase):
    
    def test_mean_time_standard_case(self):
        times = ["23:00:17", "23:40:20", "00:12:45", "00:17:19"]
        expected_result = "23:47:10"
        self.assertEqual(mean_time(times), expected_result)

    def test_mean_time_midnight_crossing(self):
        times = ["23:59:59", "00:00:01"]
        expected_result = "00:00:00"
        self.assertEqual(mean_time(times), expected_result)

    def test_mean_time_single_time(self):
        times = ["12:34:56"]
        expected_result = "12:34:56"
        self.assertEqual(mean_time(times), expected_result)

    def test_mean_time_no_times(self):
        times = []
        with self.assertRaises(ZeroDivisionError):
            mean_time(times)

    def test_mean_time_all_same_time(self):
        times = ["06:30:00", "06:30:00", "06:30:00"]
        expected_result = "06:30:00"
        self.assertEqual(mean_time(times), expected_result)

    def test_mean_time_varied_times(self):
        times = ["01:00:00", "13:00:00", "23:00:00"]
        expected_result = "12:59:59"
        self.assertEqual(mean_time(times), expected_result)

if __name__ == '__main__':
    unittest.main()

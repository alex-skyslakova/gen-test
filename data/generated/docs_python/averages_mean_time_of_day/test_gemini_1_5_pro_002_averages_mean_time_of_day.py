import unittest
from cmath import rect, phase
from math import radians, degrees

def mean_angle(deg):
    return degrees(phase(sum(rect(1, radians(d)) for d in deg)/len(deg)))

def mean_time(times):
    t = (time.split(':') for time in times)
    seconds = ((float(s) + int(m) * 60 + int(h) * 3600) 
               for h, m, s in t)
    day = 24 * 60 * 60
    to_angles = [s * 360. / day for s in seconds]
    mean_as_angle = mean_angle(to_angles)
    mean_seconds = mean_as_angle * day / 360.
    if mean_seconds < 0:
        mean_seconds += day
    h, m = divmod(mean_seconds, 3600)
    m, s = divmod(m, 60)
    return '%02i:%02i:%02i' % (h, m, s)


class TestMeanTime(unittest.TestCase):

    def test_given_example(self):
        times = ["23:00:17", "23:40:20", "00:12:45", "00:17:19"]
        expected = "23:37:40"
        self.assertEqual(mean_time(times), expected)

    def test_single_time(self):
        times = ["12:00:00"]
        expected = "12:00:00"
        self.assertEqual(mean_time(times), expected)

    def test_times_spanning_midnight(self):
        times = ["23:59:00", "00:01:00"]
        expected = "00:00:00"
        self.assertEqual(mean_time(times), expected)

    def test_times_all_before_noon(self):
        times = ["01:00:00", "02:00:00", "03:00:00"]
        expected = "02:00:00"
        self.assertEqual(mean_time(times), expected)
    
    def test_times_all_after_noon(self):
        times = ["13:00:00", "14:00:00", "15:00:00"]
        expected = "14:00:00"
        self.assertEqual(mean_time(times), expected)

    def test_empty_time_list(self):
        times = []
        with self.assertRaises(ZeroDivisionError):  # Expecting an error when list is empty
            mean_time(times)

if __name__ == '__main__':
    unittest.main()

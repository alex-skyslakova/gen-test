import unittest

class TestLinearCongruentialGenerators(unittest.TestCase):

    def test_bsd_rand_initial_seed(self):
        bsd = bsd_rand(12345)
        self.assertEqual(bsd(), 1103566280)

    def test_bsd_rand_sequence(self):
        bsd = bsd_rand(12345)
        self.assertEqual(bsd(), 1103566280)
        self.assertEqual(bsd(), 377401575)
        self.assertEqual(bsd(), 662824084)
        self.assertEqual(bsd(), 1147902781)

    def test_bsd_rand_seed_reproducibility(self):
        bsd1 = bsd_rand(12345)
        bsd2 = bsd_rand(12345)
        self.assertEqual(bsd1(), bsd2())
        self.assertEqual(bsd1(), bsd2())
        self.assertEqual(bsd1(), bsd2())

    def test_msvcrt_rand_initial_seed(self):
        msvcrt = msvcrt_rand(12345)
        self.assertEqual(msvcrt(), 38)

    def test_msvcrt_rand_sequence(self):
        msvcrt = msvcrt_rand(12345)
        self.assertEqual(msvcrt(), 38)
        self.assertEqual(msvcrt(), 7719)
        self.assertEqual(msvcrt(), 21238)
        self.assertEqual(msvcrt(), 2437)

    def test_msvcrt_rand_seed_reproducibility(self):
        msvcrt1 = msvcrt_rand(12345)
        msvcrt2 = msvcrt_rand(12345)
        self.assertEqual(msvcrt1(), msvcrt2())
        self.assertEqual(msvcrt1(), msvcrt2())
        self.assertEqual(msvcrt1(), msvcrt2())

if __name__ == '__main__':
    unittest.main()

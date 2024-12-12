import unittest
import io
import sys

try:
    raw_input
except NameError:
    raw_input = input


def stub_stdin(testcase_inst, inputs):
    stdin = sys.stdin

    def cleanup():
        sys.stdin = stdin

    testcase_inst.addCleanup(cleanup)
    sys.stdin = io.StringIO(inputs)


def stub_stdouts(testcase_inst):
    stdout = sys.stdout
    stderr = sys.stderr
    sys.stdout = io.StringIO()
    sys.stderr = io.StringIO()

    def cleanup():
        sys.stdout = stdout
        sys.stderr = stderr
    testcase_inst.addCleanup(cleanup)
    return sys.stdout, sys.stderr


class TestSum(unittest.TestCase):

    def test_positive_numbers(self):
        stub_stdin(self, "2 2")
        out, err = stub_stdouts(self)

        import a_b  # Import the code to be tested

        self.assertEqual(out.getvalue().strip(), "4")


    def test_negative_numbers(self):
        stub_stdin(self, "-3 -2")
        out, err = stub_stdouts(self)

        import a_b

        self.assertEqual(out.getvalue().strip(), "-5")

    def test_mixed_numbers(self):
        stub_stdin(self, "5 -2")
        out, err = stub_stdouts(self)

        import a_b

        self.assertEqual(out.getvalue().strip(), "3")
    
    def test_zero_sum(self):
        stub_stdin(self, "5 -5")
        out, err = stub_stdouts(self)

        import a_b

        self.assertEqual(out.getvalue().strip(), "0")

    def test_boundary_values(self):
        stub_stdin(self, "1000 -1000")
        out, err = stub_stdouts(self)

        import a_b

        self.assertEqual(out.getvalue().strip(), "0")

    def test_large_positive_sum(self):
        stub_stdin(self, "1000 1000")  # technically out of range for problem description but tests code robustness
        out, err = stub_stdouts(self)

        import a_b

        self.assertEqual(out.getvalue().strip(), "2000")


if __name__ == '__main__':
    unittest.main()


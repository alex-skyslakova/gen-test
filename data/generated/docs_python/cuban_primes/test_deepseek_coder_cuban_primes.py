import unittest
from io import StringIO
import sys
import datetime
import math

# Mocking the datetime module to control time for testing purposes
class MockDateTime:
    @staticmethod
    def now():
        return datetime.datetime(2023, 1, 1, 0, 0, 0)

# Mocking the print function to capture output
class Capturing(list):
    def __enter__(self):
        self._stdout = sys.stdout
        sys.stdout = self._stringio = StringIO()
        return self

    def __exit__(self, *args):
        self.extend(self._stringio.getvalue().splitlines())
        del self._stringio    # free up some memory
        sys.stdout = self._stdout

# The function to test
def cuban_primes():
    primes = [3, 5]
    cutOff = 200
    bigUn = 100_000
    chunks = 50
    little = bigUn / chunks
    tn = " cuban prime"
    c = 0
    showEach = True
    u = 0
    v = 1
    st = MockDateTime.now()

    for i in range(1, int(math.pow(2, 20))):
        found = False
        u += 6
        v += u
        mx = int(math.sqrt(v))

        for item in primes:
            if item > mx:
                break
            if v % item == 0:
                found = True
                break

        if not found:
            c += 1
            if showEach:
                z = primes[-1]
                while z <= v - 2:
                    z += 2

                    fnd = False
                    for item in primes:
                        if item > mx:
                            break
                        if z % item == 0:
                            fnd = True
                            break

                    if not fnd:
                        primes.append(z)

                primes.append(v)
                print("{:>11,}".format(v), end='')

                if c % 10 == 0:
                    print("")
                if c == cutOff:
                    showEach = False
                    print("Progress to the {:,}th {}:".format(bigUn, tn), end='')
            if c % little == 0:
                print('.', end='')
            if c == bigUn:
                break

    print("")
    print("The {:,}th{} is {:,}".format(c, tn, v))
    print("Computation time was {} seconds".format((MockDateTime.now() - st).seconds))

# Unit tests
class TestCubanPrimes(unittest.TestCase):
    def test_cuban_primes_output(self):
        with Capturing() as output:
            cuban_primes()

        # Check the first 200 cuban primes output
        first_200_primes = "\n".join(output[:200])
        self.assertIn("The first 200 cuban primes:", first_200_primes)

        # Check the 100,000th cuban prime output
        last_line = output[-1]
        self.assertIn("The 100,000th cuban prime is", last_line)

        # Check the computation time output
        time_line = output[-2]
        self.assertIn("Computation time was", time_line)

if __name__ == '__main__':
    unittest.main()

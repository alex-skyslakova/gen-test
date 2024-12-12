import unittest
import io
import sys
from cuban_primes import * # Import functions/variables from the script

class TestCubanPrimes(unittest.TestCase):

    def test_first_200_primes(self):
        capturedOutput = io.StringIO()
        sys.stdout = capturedOutput
        
        # Reset global variables to their initial state for each test
        global primes, cutOff, bigUn, chunks, little, tn, c, showEach, u, v, st
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
        st = datetime.datetime.now()  # Not relevant for this test, but included for consistency


        # Modify bigUn so that only the first 200 primes are printed
        bigUn = 200 
        little = bigUn/chunks
        main() # Call the main function (you'll need to define this if it doesn't exist)
        sys.stdout = sys.__stdout__

        output = capturedOutput.getvalue()
        self.assertIn("The first 200 cuban primes:", output) # Check if the header is present
        self.assertEqual(output.count('        '), 200) # Check if 200 numbers are printed


    def test_100000th_prime(self):
        capturedOutput = io.StringIO()
        sys.stdout = capturedOutput
        
        # Reset global variables
        global primes, cutOff, bigUn, chunks, little, tn, c, showEach, u, v, st
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
        st = datetime.datetime.now()

        main()
        sys.stdout = sys.__stdout__
        output = capturedOutput.getvalue()

        self.assertIn("The 100,000th cuban prime is 1,852,685,499", output) # Check if the 100,000th prime is correct

if __name__ == '__main__':
    # Define a main function that encapsulates the code logic from the script
    def main():
        global primes, cutOff, bigUn, chunks, little, tn, c, showEach, u, v, st
        for i in range(1, int(math.pow(2,20))):
            found = False
            u += 6
            v += u
            mx = int(math.sqrt(v))

            for item in primes:
                if (item > mx):
                    break
                if (v % item == 0):
                    found = True
                    break

            if (found == 0):
                c += 1
                if (showEach):
                    z = primes[-1]
                    while (z <= v - 2):
                        z += 2

                        fnd = False
                        for item in primes:
                            if (item > mx):
                                break
                            if (z % item == 0):
                                fnd = True
                                break

                        if (not fnd):
                            primes.append(z)

                    primes.append(v)
                    print("{:>11,}".format(v), end='')

                    if (c % 10 == 0):
                        print("");
                    if (c == cutOff):
                        showEach = False
                        print ("Progress to the {:,}th {}:".format(bigUn, tn), end='')
                if (c % little == 0):
                    print('.', end='')
                if (c == bigUn):
                    break

        print("");
        print ("The {:,}th{} is {:,}".format(c, tn, v))
        print("Computation time was {} seconds".format((datetime.datetime.now() - st).seconds))

    unittest.main()


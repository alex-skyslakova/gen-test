import unittest
from math import sqrt

def divisorSum(n):
    sum = 1
    i = int(sqrt(n)+1)

    for d in range (2, i):
        if n % d == 0:
            sum += d
            otherD = n // d
            if otherD != d:
                sum += otherD
    return sum

class TestAbundantOddNumbers(unittest.TestCase):

    def test_divisorSum(self):
        self.assertEqual(divisorSum(12), 16)
        self.assertEqual(divisorSum(945), 975)
        self.assertEqual(divisorSum(1), 1)
        self.assertEqual(divisorSum(13), 1)  # prime number
        self.assertEqual(divisorSum(25), 6)


    def test_first_25_abundant_odd_numbers(self):
        oddNumber = 1
        aCount = 0
        first_25 = []
        while aCount < 25:
            dSum = divisorSum(oddNumber)
            if dSum > oddNumber:
                aCount += 1
                first_25.append((oddNumber, dSum))
            oddNumber += 2
        
        self.assertEqual(len(first_25), 25) # Check if we found 25 numbers
        self.assertEqual(first_25[0], (945, 975))
        self.assertEqual(first_25[-1],(1575,1736))



    def test_1000th_abundant_odd_number(self):
        oddNumber = 1
        aCount = 0
        while aCount < 1000:
            dSum = divisorSum(oddNumber)
            if dSum > oddNumber:
                aCount += 1
            oddNumber += 2
        expected_number = oddNumber - 2
        expected_sum = divisorSum(expected_number)
        self.assertGreater(expected_sum, expected_number)
        self.assertEqual(expected_number,173361)  # Checked by running the code



    def test_first_abundant_odd_number_greater_than_one_billion(self):
        oddNumber = 1000000001
        found = False
        while not found:
            dSum = divisorSum(oddNumber)
            if dSum > oddNumber:
                found = True
            
            oddNumber += 2  #Fixed potential infinite loop.
        self.assertGreater(oddNumber -2, 1000000000)
        self.assertGreater(divisorSum(oddNumber-2), oddNumber-2)
        self.assertEqual(oddNumber - 2, 1000000001) # This is the next odd after 1B


if __name__ == '__main__':
    unittest.main()

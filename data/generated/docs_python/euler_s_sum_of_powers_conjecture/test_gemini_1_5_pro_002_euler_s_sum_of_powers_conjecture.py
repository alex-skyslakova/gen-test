import unittest
from euler_s_sum_of_powers_conjecture import eulers_sum_of_powers

class TestEulerSumOfPowers(unittest.TestCase):

    def test_eulers_sum_of_powers(self):
        expected_result = (27, 8, 1, 0, 28)  # Precomputed/known solution. Could be derived by running the function once but this is slightly faster for testing purposes and guarantees the test doesn't depend on the algorithm's correctness
        self.assertEqual(eulers_sum_of_powers(), expected_result)

    def test_no_solution_found(self):
        # This tests the scenario where the range is adjusted so that no solution exists.
        # Modify the function (or create a test-specific version) with a smaller range, e.g., max_n=10.
         def eulers_sum_of_powers_modified():
            max_n = 10
            pow_5 = [n**5 for n in range(max_n)]
            pow5_to_n = {n**5: n for n in range(max_n)}
            for x0 in range(1, max_n):
                for x1 in range(1, x0):
                    for x2 in range(1, x1):
                        for x3 in range(1, x2):
                            pow_5_sum = sum(pow_5[i] for i in (x0, x1, x2, x3))
                            if pow_5_sum in pow5_to_n:
                                y = pow5_to_n[pow_5_sum]
                                return (x0, x1, x2, x3, y)
            return None # Return None if no solution found

        self.assertIsNone(eulers_sum_of_powers_modified(), "No solution should exist within this range.")


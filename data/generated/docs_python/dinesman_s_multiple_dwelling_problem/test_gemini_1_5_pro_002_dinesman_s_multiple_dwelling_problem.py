import unittest
import re
from itertools import product
from dinesman_s_multiple_dwelling_problem import parse_and_solve

class TestDinesmanPuzzle(unittest.TestCase):

    def setUp(self):
        # Redirect stdout to capture printed output
        import sys
        from io import StringIO
        self.capturedOutput = StringIO()
        sys.stdout = self.capturedOutput


    def tearDown(self):
        import sys
        sys.stdout = sys.__stdout__

    def assert_solution(self, problem_statement, expected_solution):
        parse_and_solve(problem_statement)
        output = self.capturedOutput.getvalue().strip()
        self.assertEqual(output, expected_solution)

    def test_original_problem(self):
        problem_statement = """
        Baker, Cooper, Fletcher, Miller, and Smith live on different floors of an apartment house that contains only five floors.

        Baker does not live on the top floor.
        Cooper does not live on the bottom floor.
        Fletcher does not live on either the top or the bottom floor.
        Miller lives on a higher floor than does Cooper.
        Smith does not live on a floor adjacent to Fletcher's.
        Fletcher does not live on a floor adjacent to Cooper's.

        Where does everyone live?
        """
        expected_solution = """
Floors are numbered from 1 to 5 inclusive.
  Floor 5 is occupied by Miller
  Floor 4 is occupied by Smith
  Floor 3 is occupied by Fletcher
  Floor 2 is occupied by Cooper
  Floor 1 is occupied by Baker"""
        self.assert_solution(problem_statement, expected_solution)


    def test_modified_problem(self):  # Different names and floor count
        problem_statement = """
        Alice, Bob, and Carol live on different floors of an apartment house that contains only three floors.

        Alice does not live on the top floor.
        Bob lives on a higher floor than does Carol.

        Where does everyone live?
        """
        expected_solution = """
Floors are numbered from 1 to 3 inclusive.
  Floor 3 is occupied by Bob
  Floor 2 is occupied by Alice
  Floor 1 is occupied by Carol"""

        self.assert_solution(problem_statement, expected_solution)

    def test_no_solution(self): # Contradictory constraints
        problem_statement = """
        Alice, Bob live on different floors of an apartment house that contains only two floors.

        Alice does not live on the top floor.
        Bob does not live on the bottom floor.
        Alice does not live on the bottom floor. #Added constraint that leads to no solution

        Where does everyone live?
        """
        expected_solution = "No solution found."
        self.assert_solution(problem_statement, expected_solution)


    def test_different_wording(self): # Slightly different phrasing for constraints
        problem_statement = """
        David, Eva, Frank, and Grace live in a house with four floors.

        David does not live on the bottom floor.
        Eva lives on a lower floor than Frank.
        Grace does not live on a floor adjacent to David.
        Grace does not live on either the top or bottom floor.

        Where does everyone live?
        """
        expected_solution = """
Floors are numbered from 1 to 4 inclusive.
  Floor 4 is occupied by Frank
  Floor 3 is occupied by Grace
  Floor 2 is occupied by David
  Floor 1 is occupied by Eva"""
        self.assert_solution(problem_statement, expected_solution)

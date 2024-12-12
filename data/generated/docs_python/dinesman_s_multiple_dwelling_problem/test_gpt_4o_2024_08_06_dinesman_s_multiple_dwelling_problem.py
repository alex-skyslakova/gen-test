import unittest
from dinesman_s_multiple_dwelling_problem import parse_and_solve

class TestDinesmanMultipleDwellingProblem(unittest.TestCase):

    def test_standard_problem(self):
        problem = """
        Baker, Cooper, Fletcher, Miller, and Smith live on different floors of an apartment house that contains only five floors.
        Baker does not live on the top floor.
        Cooper does not live on the bottom floor.
        Fletcher does not live on either the top or the bottom floor.
        Miller lives on a higher floor than does Cooper.
        Smith does not live on a floor adjacent to Fletcher's.
        Fletcher does not live on a floor adjacent to Cooper's.
        Where does everyone live?
        """
        expected_output = (
            "Floors are numbered from 1 to 5 inclusive.\n"
            "  Floor 5 is occupied by Smith\n"
            "  Floor 4 is occupied by Miller\n"
            "  Floor 3 is occupied by Fletcher\n"
            "  Floor 2 is occupied by Cooper\n"
            "  Floor 1 is occupied by Baker\n"
        )
        with self.assertLogs() as log:
            parse_and_solve(problem)
            self.assertIn(expected_output.strip(), log.output[0])

    def test_no_solution(self):
        problem = """
        Baker, Cooper, Fletcher, Miller, and Smith live on different floors of an apartment house that contains only five floors.
        Baker does not live on the top floor.
        Cooper does not live on the bottom floor.
        Fletcher does not live on either the top or the bottom floor.
        Miller lives on a higher floor than does Cooper.
        Smith does not live on a floor adjacent to Fletcher's.
        Fletcher does not live on a floor adjacent to Cooper's.
        Baker lives on the fifth floor.
        Where does everyone live?
        """
        expected_output = "No solution found."
        with self.assertLogs() as log:
            parse_and_solve(problem)
            self.assertIn(expected_output, log.output[0])

    def test_different_floor_count(self):
        problem = """
        Baker, Cooper, Fletcher, Miller, and Smith live on different floors of an apartment house that contains only six floors.
        Baker does not live on the top floor.
        Cooper does not live on the bottom floor.
        Fletcher does not live on either the top or the bottom floor.
        Miller lives on a higher floor than does Cooper.
        Smith does not live on a floor adjacent to Fletcher's.
        Fletcher does not live on a floor adjacent to Cooper's.
        Where does everyone live?
        """
        expected_output = (
            "Floors are numbered from 1 to 6 inclusive.\n"
            "  Floor 6 is occupied by Smith\n"
            "  Floor 5 is occupied by Miller\n"
            "  Floor 4 is occupied by Fletcher\n"
            "  Floor 3 is occupied by Cooper\n"
            "  Floor 2 is occupied by Baker\n"
        )
        with self.assertLogs() as log:
            parse_and_solve(problem)
            self.assertIn(expected_output.strip(), log.output[0])

    def test_minimum_floor_count(self):
        problem = """
        Baker, Cooper, Fletcher, Miller, and Smith live on different floors of an apartment house that contains only two floors.
        Baker does not live on the top floor.
        Cooper does not live on the bottom floor.
        Where does everyone live?
        """
        expected_output = "No solution found."
        with self.assertLogs() as log:
            parse_and_solve(problem)
            self.assertIn(expected_output, log.output[0])

if __name__ == '__main__':
    unittest.main()

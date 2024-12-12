import unittest
from dinesman_s_multiple_dwelling_problem import parse_and_solve

class TestDinesmanProblem(unittest.TestCase):

    def test_default_problem(self):
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
        expected_output = [
            "Floors are numbered from 1 to 5 inclusive.",
            "  Floor 5 is occupied by Miller",
            "  Floor 4 is occupied by Fletcher",
            "  Floor 3 is occupied by Baker",
            "  Floor 2 is occupied by Cooper",
            "  Floor 1 is occupied by Smith"
        ]
        with self.assertLogs(level='INFO') as cm:
            parse_and_solve(problem)
        self.assertEqual(cm.output, [f'INFO:root:{line}' for line in expected_output])

    def test_no_solution(self):
        problem = """
        Baker, Cooper, Fletcher, Miller, and Smith live on different floors of an apartment house that contains only five floors.
        Baker does not live on the top floor.
        Cooper does not live on the bottom floor.
        Fletcher does not live on either the top or the bottom floor.
        Miller lives on a higher floor than does Cooper.
        Smith does not live on a floor adjacent to Fletcher's.
        Fletcher does not live on a floor adjacent to Cooper's.
        Baker lives on the same floor as Cooper.
        Where does everyone live?
        """
        expected_output = "No solution found."
        with self.assertLogs(level='INFO') as cm:
            parse_and_solve(problem)
        self.assertEqual(cm.output, [f'INFO:root:{expected_output}'])

    def test_different_floor_count(self):
        problem = """
        Baker, Cooper, Fletcher, Miller, and Smith live on different floors of an apartment house that contains only four floors.
        Baker does not live on the top floor.
        Cooper does not live on the bottom floor.
        Fletcher does not live on either the top or the bottom floor.
        Miller lives on a higher floor than does Cooper.
        Smith does not live on a floor adjacent to Fletcher's.
        Fletcher does not live on a floor adjacent to Cooper's.
        Where does everyone live?
        """
        expected_output = [
            "Floors are numbered from 1 to 4 inclusive.",
            "  Floor 4 is occupied by Miller",
            "  Floor 3 is occupied by Fletcher",
            "  Floor 2 is occupied by Baker",
            "  Floor 1 is occupied by Cooper"
        ]
        with self.assertLogs(level='INFO') as cm:
            parse_and_solve(problem)
        self.assertEqual(cm.output, [f'INFO:root:{line}' for line in expected_output])

    def test_additional_constraints(self):
        problem = """
        Baker, Cooper, Fletcher, Miller, and Smith live on different floors of an apartment house that contains only five floors.
        Baker does not live on the top floor.
        Cooper does not live on the bottom floor.
        Fletcher does not live on either the top or the bottom floor.
        Miller lives on a higher floor than does Cooper.
        Smith does not live on a floor adjacent to Fletcher's.
        Fletcher does not live on a floor adjacent to Cooper's.
        Baker lives on an even-numbered floor.
        Where does everyone live?
        """
        expected_output = [
            "Floors are numbered from 1 to 5 inclusive.",
            "  Floor 5 is occupied by Miller",
            "  Floor 4 is occupied by Fletcher",
            "  Floor 3 is occupied by Smith",
            "  Floor 2 is occupied by Baker",
            "  Floor 1 is occupied by Cooper"
        ]
        with self.assertLogs(level='INFO') as cm:
            parse_and_solve(problem)
        self.assertEqual(cm.output, [f'INFO:root:{line}' for line in expected_output])

if __name__ == '__main__':
    unittest.main()

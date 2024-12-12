import unittest
import random
from unittest.mock import patch
import sys
from io import StringIO

# Tested code would normally be imported here. 
# For demonstration, functions are directly included below.


def play_random(n):
    # using 0-99 instead of ranges 1-100
    pardoned = 0
    in_drawer = list(range(100))
    sampler = list(range(100))
    for _round in range(n):
        random.shuffle(in_drawer)
        found = False
        for prisoner in range(100):
            found = False
            for reveal in random.sample(sampler, 50):
                card = in_drawer[reveal]
                if card == prisoner:
                    found = True
                    break
            if not found:
                break
        if found:
            pardoned += 1
    return pardoned / n * 100   # %
 
def play_optimal(n):
    # using 0-99 instead of ranges 1-100
    pardoned = 0
    in_drawer = list(range(100))
    for _round in range(n):
        random.shuffle(in_drawer)
        for prisoner in range(100):
            reveal = prisoner
            found = False
            for go in range(50):
                card = in_drawer[reveal]
                if card == prisoner:
                    found = True
                    break
                reveal = card
            if not found:
                break
        if found:
            pardoned += 1
    return pardoned / n * 100   # %



class TestPrisonerProblem(unittest.TestCase):

    def test_play_random_single_round_loss(self):
        with patch('random.shuffle', side_effect=lambda x: x.reverse()):  # Force a losing arrangement
            with patch('random.sample', return_value=list(range(50))):
                self.assertEqual(play_random(1), 0.0)

    def test_play_random_single_round_win(self):
       with patch('random.shuffle', side_effect=lambda x: x): #Do not change the list (it's already ok to win)
           with patch('random.sample', return_value=list(range(50))):
               self.assertEqual(play_random(1), 100.0)

    def test_play_optimal_single_round_loss(self):
        with patch('random.shuffle', side_effect=lambda x: x.reverse()): # Force a losing arrangement
            self.assertEqual(play_optimal(1), 0.0)
    
    def test_play_optimal_single_round_win(self):
        with patch('random.shuffle', side_effect=lambda x: x): #Do not change the list (it's already ok to win)
            self.assertEqual(play_optimal(1), 100.0)



    @patch('sys.stdout', new_callable=StringIO)
    def test_main_output(self, mock_stdout):
        with patch('builtins.input', side_effect=[]):
            with patch('random.shuffle', side_effect = lambda x: x):  # force winning arrangement
                # Import and execute the main section of the code.
                import importlib.util
                spec = importlib.util.spec_from_file_location("100_prisoners", "100_prisoners.py")
                foo = importlib.util.module_from_spec(spec)
                spec.loader.exec_module(foo)


                expected_output = (
                    " Simulation count: 100000\n"
                    " Random play wins: 100.0% of simulations\n"  # Force random to win too, else we need many runs to be sure it is between 0 and 100.
                    "Optimal play wins: 100.0% of simulations\n"
                )

                self.assertEqual(mock_stdout.getvalue(), expected_output)

if __name__ == '__main__':
    unittest.main()

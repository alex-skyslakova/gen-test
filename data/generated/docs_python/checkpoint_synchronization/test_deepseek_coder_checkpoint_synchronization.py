import unittest
from unittest.mock import patch, MagicMock
import threading
import time
import random

# Import the worker function and Barrier from the provided code
from checkpoint_synchronization import worker, barrier

class TestCheckpointSynchronization(unittest.TestCase):

    def setUp(self):
        # Reset the barrier for each test
        self.barrier = threading.Barrier(3)

    def test_worker_completes_tasks(self):
        # Mock the print function to capture output
        with patch('builtins.print') as mock_print:
            # Create threads
            w1 = threading.Thread(target=worker, args=(1, self.barrier))
            w2 = threading.Thread(target=worker, args=(2, self.barrier))
            w3 = threading.Thread(target=worker, args=(3, self.barrier))

            # Start threads
            w1.start()
            w2.start()
            w3.start()

            # Wait for threads to complete
            w1.join()
            w2.join()
            w3.join()

            # Check that all workers completed both tasks
            calls = mock_print.call_args_list
            self.assertEqual(len(calls), 12)  # 3 workers * 2 tasks * 2 prints per task

            # Check that each worker started and exited both tasks
            for i in range(1, 4):
                self.assertIn(f'Starting worker {i} task 1', str(calls))
                self.assertIn(f'Exiting worker{i}', str(calls))
                self.assertIn(f'Starting worker {i} task 2', str(calls))
                self.assertIn(f'Exiting worker{i}', str(calls))

    def test_barrier_synchronization(self):
        # Mock the time.sleep function to speed up the test
        with patch('time.sleep', return_value=None):
            # Mock the print function to capture output
            with patch('builtins.print') as mock_print:
                # Create threads
                w1 = threading.Thread(target=worker, args=(1, self.barrier))
                w2 = threading.Thread(target=worker, args=(2, self.barrier))
                w3 = threading.Thread(target=worker, args=(3, self.barrier))

                # Start threads
                w1.start()
                w2.start()
                w3.start()

                # Wait for threads to complete
                w1.join()
                w2.join()
                w3.join()

                # Check that all workers waited at the barrier before starting task 2
                calls = mock_print.call_args_list
                for i in range(1, 4):
                    self.assertIn(f'Exiting worker{i}', str(calls))
                    self.assertIn(f'Starting worker {i} task 2', str(calls))

    def test_race_condition_free(self):
        # Mock the time.sleep function to speed up the test
        with patch('time.sleep', return_value=None):
            # Mock the print function to capture output
            with patch('builtins.print') as mock_print:
                # Create threads
                w1 = threading.Thread(target=worker, args=(1, self.barrier))
                w2 = threading.Thread(target=worker, args=(2, self.barrier))
                w3 = threading.Thread(target=worker, args=(3, self.barrier))

                # Start threads
                w1.start()
                w2.start()
                w3.start()

                # Wait for threads to complete
                w1.join()
                w2.join()
                w3.join()

                # Check that no worker was counted twice within one cycle
                calls = mock_print.call_args_list
                for i in range(1, 4):
                    self.assertEqual(calls.count(f'Starting worker {i} task 1'), 1)
                    self.assertEqual(calls.count(f'Starting worker {i} task 2'), 1)

if __name__ == '__main__':
    unittest.main()

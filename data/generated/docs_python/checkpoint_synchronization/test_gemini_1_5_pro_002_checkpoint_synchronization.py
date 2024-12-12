import unittest
import threading
import time
import random
from unittest.mock import patch, call
from checkpoint_synchronization import worker, barrier

class TestBarrierSynchronization(unittest.TestCase):

    @patch('checkpoint_synchronization.print')
    def test_barrier_synchronization(self, mock_print):
        # Use a smaller barrier for faster testing
        test_barrier = threading.Barrier(2)

        w1 = threading.Thread(target=worker, args=((1, test_barrier)))
        w2 = threading.Thread(target=worker, args=((2, test_barrier)))

        w1.start()
        w2.start()

        w1.join()
        w2.join()

        # Check if each worker performs both tasks
        expected_calls = [
            call('Starting worker 1 task 1, sleeptime=' + str(w1._args[0][0].sleeptime)),  # Assuming sleeptime is accessible after start
            call('Exiting worker1'),
            call('Starting worker 2 task 1, sleeptime=' + str(w2._args[0][0].sleeptime)),
            call('Exiting worker2'),
            call('Starting worker 1 task 2, sleeptime=' + str(w1._args[0][1].sleeptime)),  # Verify second task sleeptime
            call('Exiting worker1'),
            call('Starting worker 2 task 2, sleeptime=' + str(w2._args[0][1].sleeptime)), # Verify second task sleeptime
            call('Exiting worker2'),
        ]
        mock_print.assert_has_calls(expected_calls, any_order=True)


    @patch('checkpoint_synchronization.print')
    def test_no_premature_completion(self, mock_print):
         # Use a smaller barrier for faster testing
        test_barrier = threading.Barrier(2)
        w1 = threading.Thread(target=worker, args=((1, test_barrier)))
        w2 = threading.Thread(target=worker, args=((2, test_barrier)))

        w1.start()
        w2.start()

        w1.join()
        w2.join()

        # Check if a worker completes task 2 before another finishes task 1.
        task1_complete_w1_index = [i for i, call_ in enumerate(mock_print.mock_calls) if "Exiting worker1" in str(call_) and "task 1" in str(mock_print.mock_calls[i-1])]
        task1_complete_w2_index = [i for i, call_ in enumerate(mock_print.mock_calls) if "Exiting worker2" in str(call_) and "task 1" in str(mock_print.mock_calls[i-1])]

        task2_start_w1_index = [i for i, call_ in enumerate(mock_print.mock_calls) if "Starting worker 1 task 2" in str(call_)]
        task2_start_w2_index = [i for i, call_ in enumerate(mock_print.mock_calls) if "Starting worker 2 task 2" in str(call_)]


        self.assertGreater(task2_start_w1_index[0], task1_complete_w2_index[0], "Worker 1 started task 2 before worker 2 completed task 1")
        self.assertGreater(task2_start_w2_index[0], task1_complete_w1_index[0], "Worker 2 started task 2 before worker 1 completed task 1")



if __name__ == '__main__':
    unittest.main()


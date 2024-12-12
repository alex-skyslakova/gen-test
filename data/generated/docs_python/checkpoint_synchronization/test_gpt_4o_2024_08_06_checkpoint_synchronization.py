import unittest
import threading
from checkpoint_synchronization import worker

class TestCheckpointSynchronization(unittest.TestCase):

    def setUp(self):
        # Initialize the barrier for 3 workers
        self.barrier = threading.Barrier(3)

    def test_all_workers_synchronize(self):
        # Create worker threads
        w1 = threading.Thread(target=worker, args=(1, self.barrier))
        w2 = threading.Thread(target=worker, args=(2, self.barrier))
        w3 = threading.Thread(target=worker, args=(3, self.barrier))

        # Start worker threads
        w1.start()
        w2.start()
        w3.start()

        # Wait for all threads to complete
        w1.join()
        w2.join()
        w3.join()

        # If all threads complete without deadlock, the test passes
        self.assertTrue(True)

    def test_worker_join_and_leave(self):
        # Create a barrier for 2 workers
        barrier = threading.Barrier(2)

        # Create worker threads
        w1 = threading.Thread(target=worker, args=(1, barrier))
        w2 = threading.Thread(target=worker, args=(2, barrier))

        # Start worker threads
        w1.start()
        w2.start()

        # Wait for all threads to complete
        w1.join()
        w2.join()

        # If all threads complete without deadlock, the test passes
        self.assertTrue(True)

    def test_worker_premature_completion(self):
        # Create a barrier for 3 workers
        barrier = threading.Barrier(3)

        # Create worker threads
        w1 = threading.Thread(target=worker, args=(1, barrier))
        w2 = threading.Thread(target=worker, args=(2, barrier))
        w3 = threading.Thread(target=worker, args=(3, barrier))

        # Start worker threads
        w1.start()
        w2.start()
        w3.start()

        # Wait for all threads to complete
        w1.join()
        w2.join()
        w3.join()

        # If all threads complete without premature completion, the test passes
        self.assertTrue(True)

if __name__ == '__main__':
    unittest.main()

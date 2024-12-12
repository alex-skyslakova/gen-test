import unittest
from io import StringIO
import sys
from doubly_linked_list_traversal import List

class TestDoublyLinkedListTraversal(unittest.TestCase):

    def setUp(self):
        # Build the list
        self.tail = self.head = List(10)
        for i in [20, 30, 40]:
            self.tail = self.tail.append(i)

    def test_traverse_forwards(self):
        # Capture the output
        captured_output = StringIO()
        sys.stdout = captured_output

        # Traverse forwards
        node = self.head
        while node is not None:
            print(node.data)
            node = node.next

        # Restore stdout
        sys.stdout = sys.__stdout__

        # Check the output
        self.assertEqual(captured_output.getvalue(), "10\n20\n30\n40\n")

    def test_traverse_backwards(self):
        # Capture the output
        captured_output = StringIO()
        sys.stdout = captured_output

        # Traverse backwards
        node = self.tail
        while node is not None:
            print(node.data)
            node = node.prev

        # Restore stdout
        sys.stdout = sys.__stdout__

        # Check the output
        self.assertEqual(captured_output.getvalue(), "40\n30\n20\n10\n")

if __name__ == '__main__':
    unittest.main()

import unittest

class TestDoublyLinkedListElementInsertion(unittest.TestCase):

    class Node:
        def __init__(self, value):
            self.value = value
            self.next = None
            self.prev = None

    def setUp(self):
        # Create a basic doubly linked list with three nodes
        self.node1 = self.Node(1)
        self.node2 = self.Node(2)
        self.node3 = self.Node(3)

        self.node1.next = self.node2
        self.node2.prev = self.node1
        self.node2.next = self.node3
        self.node3.prev = self.node2

    def test_insert_middle(self):
        # Insert a new node between node1 and node2
        new_node = self.Node(1.5)
        insert(self.node1, new_node)

        # Check the links
        self.assertEqual(self.node1.next, new_node)
        self.assertEqual(new_node.prev, self.node1)
        self.assertEqual(new_node.next, self.node2)
        self.assertEqual(self.node2.prev, new_node)

    def test_insert_end(self):
        # Insert a new node after node3
        new_node = self.Node(4)
        insert(self.node3, new_node)

        # Check the links
        self.assertEqual(self.node3.next, new_node)
        self.assertEqual(new_node.prev, self.node3)
        self.assertIsNone(new_node.next)

    def test_insert_beginning(self):
        # Insert a new node before node1
        new_node = self.Node(0)
        # Create a dummy head node to simulate insertion at the beginning
        dummy_head = self.Node(None)
        dummy_head.next = self.node1
        self.node1.prev = dummy_head

        insert(dummy_head, new_node)

        # Check the links
        self.assertEqual(dummy_head.next, new_node)
        self.assertEqual(new_node.prev, dummy_head)
        self.assertEqual(new_node.next, self.node1)
        self.assertEqual(self.node1.prev, new_node)

    def test_insert_self(self):
        # Attempt to insert a node after itself
        with self.assertRaises(AttributeError):
            insert(self.node2, self.node2)

if __name__ == '__main__':
    unittest.main()

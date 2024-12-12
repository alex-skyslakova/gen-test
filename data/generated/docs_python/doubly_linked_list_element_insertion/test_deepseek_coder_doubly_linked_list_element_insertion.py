import unittest
from doubly_linked_list_element_insertion import insert

class DoublyLinkedListNode:
    def __init__(self, value):
        self.value = value
        self.next = None
        self.prev = None

class TestDoublyLinkedListInsertion(unittest.TestCase):

    def setUp(self):
        # Create a doubly linked list with three nodes: A <-> B <-> C
        self.node_a = DoublyLinkedListNode('A')
        self.node_b = DoublyLinkedListNode('B')
        self.node_c = DoublyLinkedListNode('C')
        
        self.node_a.next = self.node_b
        self.node_b.prev = self.node_a
        self.node_b.next = self.node_c
        self.node_c.prev = self.node_b

    def test_insert_between_two_nodes(self):
        # Insert a new node 'X' between 'A' and 'B'
        new_node = DoublyLinkedListNode('X')
        insert(self.node_a, new_node)

        # Verify the links
        self.assertEqual(self.node_a.next, new_node)
        self.assertEqual(new_node.prev, self.node_a)
        self.assertEqual(new_node.next, self.node_b)
        self.assertEqual(self.node_b.prev, new_node)

    def test_insert_at_end(self):
        # Insert a new node 'X' at the end after 'C'
        new_node = DoublyLinkedListNode('X')
        insert(self.node_c, new_node)

        # Verify the links
        self.assertEqual(self.node_c.next, new_node)
        self.assertEqual(new_node.prev, self.node_c)
        self.assertIsNone(new_node.next)

    def test_insert_into_empty_list(self):
        # Create a new node 'A' and try to insert 'X' after it
        node_a = DoublyLinkedListNode('A')
        new_node = DoublyLinkedListNode('X')
        
        # Insert 'X' after 'A' which is the only node in the list
        insert(node_a, new_node)

        # Verify the links
        self.assertEqual(node_a.next, new_node)
        self.assertEqual(new_node.prev, node_a)
        self.assertIsNone(new_node.next)

    def test_insert_between_two_nodes_with_none_next(self):
        # Create a new node 'A' and 'C' with 'A' pointing to 'C'
        node_a = DoublyLinkedListNode('A')
        node_c = DoublyLinkedListNode('C')
        node_a.next = node_c
        node_c.prev = node_a

        # Insert a new node 'X' between 'A' and 'C'
        new_node = DoublyLinkedListNode('X')
        insert(node_a, new_node)

        # Verify the links
        self.assertEqual(node_a.next, new_node)
        self.assertEqual(new_node.prev, node_a)
        self.assertEqual(new_node.next, node_c)
        self.assertEqual(node_c.prev, new_node)

if __name__ == '__main__':
    unittest.main()

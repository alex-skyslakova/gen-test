import unittest

class TestDoublyLinkedList(unittest.TestCase):

    def setUp(self):
        # Build the list
        self.head = List(10)
        self.tail = self.head
        for i in [20, 30, 40]:
            self.tail = self.tail.append(i)

    def test_forward_traversal(self):
        expected_data = [10, 20, 30, 40]
        node = self.head
        index = 0
        while node is not None:
            self.assertEqual(node.data, expected_data[index])
            node = node.next
            index += 1

    def test_backward_traversal(self):
        expected_data = [40, 30, 20, 10]
        node = self.tail
        index = 0
        while node is not None:
            self.assertEqual(node.data, expected_data[index])
            node = node.prev
            index += 1

    def test_append_element(self):
        new_data = 50
        self.tail = self.tail.append(new_data)
        self.assertEqual(self.tail.data, new_data)
        self.assertIsNone(self.tail.next)
        self.assertEqual(self.tail.prev.data, 40)

if __name__ == '__main__':
    unittest.main()

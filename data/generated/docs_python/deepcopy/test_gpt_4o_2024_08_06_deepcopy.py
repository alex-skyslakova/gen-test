import unittest
import copy

class TestDeepCopy(unittest.TestCase):

    def test_deepcopy_primitive(self):
        # Test deep copy of a primitive data type
        original = 42
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)

    def test_deepcopy_list(self):
        # Test deep copy of a list
        original = [1, 2, [3, 4]]
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        self.assertIsNot(original[2], copied[2])

    def test_deepcopy_dict(self):
        # Test deep copy of a dictionary
        original = {'a': 1, 'b': {'c': 2}}
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        self.assertIsNot(original['b'], copied['b'])

    def test_deepcopy_set(self):
        # Test deep copy of a set
        original = {1, 2, 3}
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)

    def test_deepcopy_tuple(self):
        # Test deep copy of a tuple
        original = (1, 2, [3, 4])
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        self.assertIsNot(original[2], copied[2])

    def test_deepcopy_cyclic(self):
        # Test deep copy of a cyclic structure
        original = []
        original.append(original)
        copied = copy.deepcopy(original)
        self.assertEqual(len(copied), 1)
        self.assertIsNot(original, copied)
        self.assertIs(copied[0], copied)

    def test_deepcopy_custom_object(self):
        # Test deep copy of a custom object
        class CustomObject:
            def __init__(self, value):
                self.value = value

        original = CustomObject(10)
        copied = copy.deepcopy(original)
        self.assertEqual(original.value, copied.value)
        self.assertIsNot(original, copied)

    def test_deepcopy_mutual_reference(self):
        # Test deep copy of mutually referencing objects
        class Node:
            def __init__(self, value):
                self.value = value
                self.next = None

        node1 = Node(1)
        node2 = Node(2)
        node1.next = node2
        node2.next = node1

        copied_node1 = copy.deepcopy(node1)
        copied_node2 = copied_node1.next

        self.assertEqual(copied_node1.value, node1.value)
        self.assertEqual(copied_node2.value, node2.value)
        self.assertIsNot(copied_node1, node1)
        self.assertIsNot(copied_node2, node2)
        self.assertIs(copied_node1.next, copied_node2)
        self.assertIs(copied_node2.next, copied_node1)

if __name__ == '__main__':
    unittest.main()

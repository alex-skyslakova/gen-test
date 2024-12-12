import unittest
import copy

class TestDeepCopy(unittest.TestCase):

    def test_homogeneous_list(self):
        # Test deep copying a homogeneous list
        original = [1, 2, 3]
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        self.assertIsNot(original[0], copied[0])

    def test_heterogeneous_list(self):
        # Test deep copying a heterogeneous list
        original = [1, "two", [3, 4]]
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        self.assertIsNot(original[2], copied[2])
        self.assertIsNot(original[2][0], copied[2][0])

    def test_cyclic_reference(self):
        # Test deep copying a list with a cyclic reference
        original = [1, 2]
        original.append(original)
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        self.assertIsNot(original[2], copied[2])

    def test_nested_cyclic_reference(self):
        # Test deep copying a nested structure with cyclic references
        original = [1, [2, [3, [4, [5, [6, [7, [8, [9, [10, []]]]]]]]]]]
        original[1][1][1][1][1][1][1][1][1][1] = original
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        self.assertIsNot(original[1], copied[1])
        self.assertIsNot(original[1][1], copied[1][1])
        self.assertIsNot(original[1][1][1], copied[1][1][1])
        self.assertIsNot(original[1][1][1][1], copied[1][1][1][1])
        self.assertIsNot(original[1][1][1][1][1], copied[1][1][1][1][1])
        self.assertIsNot(original[1][1][1][1][1][1], copied[1][1][1][1][1][1])
        self.assertIsNot(original[1][1][1][1][1][1][1], copied[1][1][1][1][1][1][1])
        self.assertIsNot(original[1][1][1][1][1][1][1][1], copied[1][1][1][1][1][1][1][1])
        self.assertIsNot(original[1][1][1][1][1][1][1][1][1], copied[1][1][1][1][1][1][1][1][1])
        self.assertIsNot(original[1][1][1][1][1][1][1][1][1][1], copied[1][1][1][1][1][1][1][1][1][1])

    def test_deepcopy_with_custom_object(self):
        # Test deep copying a custom object
        class CustomObject:
            def __init__(self, value):
                self.value = value

        original = CustomObject(42)
        copied = copy.deepcopy(original)
        self.assertEqual(original.value, copied.value)
        self.assertIsNot(original, copied)

    def test_deepcopy_with_custom_object_and_cyclic_reference(self):
        # Test deep copying a custom object with a cyclic reference
        class CustomObject:
            def __init__(self, value):
                self.value = value
                self.ref = None

        original = CustomObject(42)
        original.ref = original
        copied = copy.deepcopy(original)
        self.assertEqual(original.value, copied.value)
        self.assertIsNot(original, copied)
        self.assertIsNot(original.ref, copied.ref)

if __name__ == '__main__':
    unittest.main()

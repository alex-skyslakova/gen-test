import unittest
import copy

class TestDeepCopy(unittest.TestCase):

    def test_simple_list(self):
        original = [1, 2, 3]
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        copied[0] = 4
        self.assertNotEqual(original, copied)

    def test_nested_list(self):
        original = [[1, 2], [3, 4]]
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        copied[0][0] = 5
        self.assertNotEqual(original, copied)

    def test_heterogeneous_list(self):
        original = [1, "string", 3.14, [5, 6]]
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        copied[0] = 7
        copied[1] = "new string"
        copied[2] = 2.71
        copied[3][0] = 8
        self.assertNotEqual(original, copied)

    def test_dictionary(self):
        original = {"a": 1, "b": [2, 3]}
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        copied["a"] = 4
        copied["b"][0] = 5
        self.assertNotEqual(original, copied)

    def test_self_referential_list(self):
        original = [1, 2]
        original.append(original)
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)  # Top-level lists are different
        self.assertIsNot(original[2], copied[2]) # Inner lists are different as well
        self.assertIs(copied[2], copied) # But the copy correctly maintains self-reference


    def test_self_referential_dict(self):
        original = {"a": 1}
        original["b"] = original
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        self.assertIsNot(original["b"], copied["b"])
        self.assertIs(copied["b"], copied)

    def test_mutually_referential_structures(self):
        a = {"data": 1}
        b = {"data": 2, "other": a}
        a["other"] = b
        copied_a = copy.deepcopy(a)

        self.assertEqual(a, copied_a)
        self.assertIsNot(a, copied_a)
        self.assertIsNot(a["other"], copied_a["other"])
        self.assertEqual(b, copied_a["other"])  # Check the nested structure
        self.assertIs(copied_a["other"]["other"], copied_a) # Mutual reference correctly copied

    def test_custom_object(self):
        class MyClass:
            def __init__(self, value):
                self.value = value
            def __eq__(self, other):
                return self.value == other.value

        original = MyClass(10)
        copied = copy.deepcopy(original)
        self.assertEqual(original, copied)
        self.assertIsNot(original, copied)
        copied.value = 20
        self.assertNotEqual(original, copied)

if __name__ == '__main__':
    unittest.main()

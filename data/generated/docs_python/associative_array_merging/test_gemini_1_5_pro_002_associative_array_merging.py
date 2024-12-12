import unittest
from associative_array_merging import base, update

class TestAssociativeArrayMerging(unittest.TestCase):

    def test_merge(self):
        result = {**base, **update}
        expected_result = {
            "name": "Rocket Skates",
            "price": 15.25,
            "color": "red",
            "year": 1974
        }
        self.assertEqual(result, expected_result)

    def test_no_mutation(self):
        original_base = base.copy()
        original_update = update.copy()
        result = {**base, **update}  # Perform the merge
        self.assertEqual(base, original_base) # Check if original dicts are unchanged
        self.assertEqual(update, original_update)

    def test_empty_base(self):
        empty_base = {}
        result = {**empty_base, **update}
        self.assertEqual(result, update)

    def test_empty_update(self):
        empty_update = {}
        result = {**base, **empty_update}
        self.assertEqual(result, base)

    def test_both_empty(self):
        empty_base = {}
        empty_update = {}
        result = {**empty_base, **empty_update}
        self.assertEqual(result, {})

    def test_different_types(self):
         base_diff = {"name": 123, "price": 12.75, "color": True}
         update_diff = {"price": "expensive", "color": 42, "year": [1974]}
         result = {**base_diff, **update_diff}
         expected_result = {
             "name": 123,
             "price": "expensive",
             "color": 42,
             "year": [1974]
         }
         self.assertEqual(result, expected_result)



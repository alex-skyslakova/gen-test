import unittest
from associative_array_merging import base, update, result

class TestAssociativeArrayMerging(unittest.TestCase):

    def test_result_contains_all_keys(self):
        # Check if result contains all keys from both base and update
        expected_keys = set(base.keys()).union(set(update.keys()))
        self.assertEqual(set(result.keys()), expected_keys)

    def test_result_values_from_update(self):
        # Check if result contains values from update where keys overlap
        for key in update:
            self.assertEqual(result[key], update[key])

    def test_result_values_from_base(self):
        # Check if result contains values from base where keys do not overlap
        for key in base:
            if key not in update:
                self.assertEqual(result[key], base[key])

    def test_original_base_not_mutated(self):
        # Ensure the original base dictionary is not mutated
        self.assertEqual(base, {"name": "Rocket Skates", "price": 12.75, "color": "yellow"})

    def test_original_update_not_mutated(self):
        # Ensure the original update dictionary is not mutated
        self.assertEqual(update, {"price": 15.25, "color": "red", "year": 1974})

if __name__ == '__main__':
    unittest.main()

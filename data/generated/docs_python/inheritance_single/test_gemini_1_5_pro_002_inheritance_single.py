import unittest
from inheritance_single import Animal, Dog, Cat, Lab, Collie

class TestInheritance(unittest.TestCase):

    def test_dog_inherits_from_animal(self):
        self.assertTrue(issubclass(Dog, Animal))

    def test_cat_inherits_from_animal(self):
        self.assertTrue(issubclass(Cat, Animal))

    def test_lab_inherits_from_dog(self):
        self.assertTrue(issubclass(Lab, Dog))

    def test_collie_inherits_from_dog(self):
        self.assertTrue(issubclass(Collie, Dog))

    def test_lab_inherits_from_animal(self):
        self.assertTrue(issubclass(Lab, Animal))  # Transitive inheritance

    def test_collie_inherits_from_animal(self):
        self.assertTrue(issubclass(Collie, Animal)) # Transitive inheritance

    def test_cat_does_not_inherit_from_dog(self):
        self.assertFalse(issubclass(Cat, Dog))

    def test_dog_does_not_inherit_from_cat(self):
        self.assertFalse(issubclass(Dog, Cat))


if __name__ == '__main__':
    unittest.main()

import unittest

# Import the classes from the provided code
from inheritance_single import Animal, Dog, Cat, Lab, Collie

class TestInheritance(unittest.TestCase):

    def test_animal_inheritance(self):
        # Test if Animal is a base class
        animal = Animal()
        self.assertIsInstance(animal, Animal)

    def test_dog_inheritance(self):
        # Test if Dog inherits from Animal
        dog = Dog()
        self.assertIsInstance(dog, Animal)
        self.assertIsInstance(dog, Dog)

    def test_cat_inheritance(self):
        # Test if Cat inherits from Animal
        cat = Cat()
        self.assertIsInstance(cat, Animal)
        self.assertIsInstance(cat, Cat)

    def test_lab_inheritance(self):
        # Test if Lab inherits from Dog and Animal
        lab = Lab()
        self.assertIsInstance(lab, Animal)
        self.assertIsInstance(lab, Dog)
        self.assertIsInstance(lab, Lab)

    def test_collie_inheritance(self):
        # Test if Collie inherits from Dog and Animal
        collie = Collie()
        self.assertIsInstance(collie, Animal)
        self.assertIsInstance(collie, Dog)
        self.assertIsInstance(collie, Collie)

if __name__ == '__main__':
    unittest.main()

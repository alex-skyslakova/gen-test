import unittest
from inheritance_single import Animal, Dog, Cat, Lab, Collie

class TestInheritance(unittest.TestCase):

    def test_animal_instance(self):
        animal = Animal()
        self.assertIsInstance(animal, Animal)

    def test_dog_instance(self):
        dog = Dog()
        self.assertIsInstance(dog, Dog)
        self.assertIsInstance(dog, Animal)

    def test_cat_instance(self):
        cat = Cat()
        self.assertIsInstance(cat, Cat)
        self.assertIsInstance(cat, Animal)

    def test_lab_instance(self):
        lab = Lab()
        self.assertIsInstance(lab, Lab)
        self.assertIsInstance(lab, Dog)
        self.assertIsInstance(lab, Animal)

    def test_collie_instance(self):
        collie = Collie()
        self.assertIsInstance(collie, Collie)
        self.assertIsInstance(collie, Dog)
        self.assertIsInstance(collie, Animal)

if __name__ == '__main__':
    unittest.main()

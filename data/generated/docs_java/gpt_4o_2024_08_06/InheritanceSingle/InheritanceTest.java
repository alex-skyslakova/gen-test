import org.junit.Test;
import static org.junit.Assert.assertTrue;

// Base class
class Animal {
    // No functions needed for this task
}

// Second level classes
class Dog extends Animal {
    // No functions needed for this task
}

class Cat extends Animal {
    // No functions needed for this task
}

// Third level classes
class Lab extends Dog {
    // No functions needed for this task
}

class Collie extends Dog {
    // No functions needed for this task
}

public class InheritanceTest {

    @Test
    public void testAnimalInheritance() {
        Animal animal = new Animal();
        assertTrue(animal instanceof Animal);
    }

    @Test
    public void testDogInheritance() {
        Dog dog = new Dog();
        assertTrue(dog instanceof Animal);
        assertTrue(dog instanceof Dog);
    }

    @Test
    public void testCatInheritance() {
        Cat cat = new Cat();
        assertTrue(cat instanceof Animal);
        assertTrue(cat instanceof Cat);
    }

    @Test
    public void testLabInheritance() {
        Lab lab = new Lab();
        assertTrue(lab instanceof Animal);
        assertTrue(lab instanceof Dog);
        assertTrue(lab instanceof Lab);
    }

    @Test
    public void testCollieInheritance() {
        Collie collie = new Collie();
        assertTrue(collie instanceof Animal);
        assertTrue(collie instanceof Dog);
        assertTrue(collie instanceof Collie);
    }
}

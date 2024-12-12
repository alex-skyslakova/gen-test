import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InheritanceSingleTest {

    @Test
    fun testAnimalToString() {
        val animal = Animal()
        assertEquals("animal", animal.toString())
    }

    @Test
    fun testDogToString() {
        val dog = Dog()
        assertEquals("dog", dog.toString())
    }

    @Test
    fun testCatToString() {
        val cat = Cat()
        assertEquals("cat", cat.toString())
    }

    @Test
    fun testLabradorToString() {
        val labrador = Labrador()
        assertEquals("labrador", labrador.toString())
    }

    @Test
    fun testCollieToString() {
        val collie = Collie()
        assertEquals("collie", collie.toString())
    }

    @Test
    fun testCatIsAnimal() {
        val felix: Animal = Cat()
        assertEquals("cat", felix.toString())
    }

    @Test
    fun testDogIsAnimal() {
        val rover: Animal = Dog()
        assertEquals("dog", rover.toString())
    }

    @Test
    fun testLabradorIsDog() {
        val bella: Dog = Labrador()
        assertEquals("labrador", bella.toString())
    }

    @Test
    fun testCollieIsDog() {
        val casey: Dog = Collie()
        assertEquals("collie", casey.toString())
    }
}

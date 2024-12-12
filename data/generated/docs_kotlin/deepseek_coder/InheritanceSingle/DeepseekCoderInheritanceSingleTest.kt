import org.junit.Test
import kotlin.test.assertEquals

class InheritanceTest {

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
    fun testAnimalHierarchy() {
        val felix: Animal = Cat()
        val rover: Animal = Dog()
        val bella: Dog = Labrador()
        val casey: Dog = Collie()

        assertEquals("cat", felix.toString())
        assertEquals("dog", rover.toString())
        assertEquals("labrador", bella.toString())
        assertEquals("collie", casey.toString())
    }
}

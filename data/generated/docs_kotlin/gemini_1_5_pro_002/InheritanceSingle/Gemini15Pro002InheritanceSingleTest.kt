import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class InheritanceTests {

    @Test
    fun testAnimalCreation() {
        val animal = Animal()
        assertEquals("animal", animal.toString())
    }

    @Test
    fun testDogCreation() {
        val dog = Dog()
        assertEquals("dog", dog.toString())
    }

    @Test
    fun testCatCreation() {
        val cat = Cat()
        assertEquals("cat", cat.toString())
    }

    @Test
    fun testLabradorCreation() {
        val lab = Labrador()
        assertEquals("labrador", lab.toString())
    }

    @Test
    fun testCollieCreation() {
        val collie = Collie()
        assertEquals("collie", collie.toString())
    }

    @Test
    fun testAnimalAssignment() {
        val animal: Animal = Cat()
        assertEquals("cat", animal.toString())

        val animal2: Animal = Dog()
        assertEquals("dog", animal2.toString())
    }


    @Test
    fun testDogAssignment() {
        val dog: Dog = Labrador()
        assertEquals("labrador", dog.toString())

        val dog2: Dog = Collie()
        assertEquals("collie", dog2.toString())
    }


    @Test
    fun testIsAnimal(){
        assertTrue(Dog() is Animal)
        assertTrue(Cat() is Animal)
        assertTrue(Labrador() is Animal)
        assertTrue(Collie() is Animal)
    }

    @Test
    fun testIsDog(){
        assertTrue(Labrador() is Dog)
        assertTrue(Collie() is Dog)
    }
}

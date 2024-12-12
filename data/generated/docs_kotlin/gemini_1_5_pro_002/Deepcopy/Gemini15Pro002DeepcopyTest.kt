import org.junit.jupiter.api.Test
import java.io.Serializable
import kotlin.test.*

class DeepCopyTest {

    @Test
    fun testDeepCopyNull() {
        val original: Person? = null
        val copy = deepCopy(original)
        assertNull(copy)
    }

    @Test
    fun testDeepCopySimpleObject() {
        val original = Person("John", 35, 'M', 50000.0, null)
        val copy = deepCopy(original)

        assertNotNull(copy)
        assertNotSame(original, copy)
        assertEquals(original.name, copy.name)
        assertEquals(original.age, copy.age)
        assertEquals(original.sex, copy.sex)
        assertEquals(original.income, copy.income)
        assertNull(copy.partner)
    }


    @Test
    fun testDeepCopyCyclicReference() {
        var original1 = Person("John", 35, 'M', 50000.0, null)
        val original2 = Person("Jane", 32, 'F', 25000.0, original1)
        original1.partner = original2

        val copy1 = deepCopy(original1)
        assertNotNull(copy1)

        val copy2 = copy1!!.partner
        assertNotNull(copy2)


        assertNotSame(original1, copy1)
        assertNotSame(original2, copy2)

        assertEquals(original1.name, copy1.name)
        assertEquals(original1.age, copy1.age)
        assertEquals(original1.sex, copy1.sex)
        assertEquals(original1.income, copy1.income)


        assertEquals(original2.name, copy2.name)
        assertEquals(original2.age, copy2.age)
        assertEquals(original2.sex, copy2.sex)
        assertEquals(original2.income, copy2.income)


        assertSame(copy2, copy1.partner) // Internal references within the copy should be the same
        assertNotSame(original2, copy1.partner) // But not the same as the originals

        original1.age = 40
        assertNotEquals(original1.age, copy1.age)


        original2.income = 60000.0
        assertNotEquals(original2.income, copy2.income)

    }


    @Test
    fun testDeepCopyAfterBreakingCyclicReference() {
        var original1 = Person("John", 35, 'M', 50000.0, null)
        val original2 = Person("Jane", 32, 'F', 25000.0, original1)
        original1.partner = original2

        original1.partner = null // Break cyclic reference

        val copy1 = deepCopy(original1)

        assertNotNull(copy1)
        assertNotSame(original1, copy1)
        assertEquals(original1.name, copy1.name)
        assertEquals(original1.age, copy1.age)
        assertEquals(original1.sex, copy1.sex)
        assertEquals(original1.income, copy1.income)
        assertNull(copy1.partner)
    }

    private fun <T : Serializable> deepCopy(obj: T?): T? { // Included to make the test file self-contained
        if (obj == null) return null
        val baos = ByteArrayOutputStream()
        val oos  = ObjectOutputStream(baos)
        oos.writeObject(obj)
        oos.close()
        val bais = ByteArrayInputStream(baos.toByteArray())
        val ois  = ObjectInputStream(bais)
        @Suppress("unchecked_cast")
        return ois.readObject() as T
    }


}




data class Person(
    val name: String,
    var age: Int,
    val sex: Char,
    var income: Double,
    var partner: Person?
) : Serializable



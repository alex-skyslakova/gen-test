import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DeepCopyTest {

    @Test
    fun `test deep copy of simple object`() {
        val p1 = Person("Alice", 30, 'F', 40000.0, null)
        val p2 = deepCopy(p1)

        assertNotNull(p2)
        assertEquals(p1.name, p2!!.name)
        assertEquals(p1.age, p2.age)
        assertEquals(p1.sex, p2.sex)
        assertEquals(p1.income, p2.income)
        assertNull(p2.partner)
        assertNotSame(p1, p2)
    }

    @Test
    fun `test deep copy of object with partner`() {
        val p1 = Person("Bob", 40, 'M', 60000.0, null)
        val p2 = Person("Carol", 38, 'F', 30000.0, p1)
        p1.partner = p2

        val p1Copy = deepCopy(p1)
        val p2Copy = p1Copy!!.partner

        assertNotNull(p1Copy)
        assertNotNull(p2Copy)

        assertEquals(p1.name, p1Copy.name)
        assertEquals(p1.age, p1Copy.age)
        assertEquals(p1.sex, p1Copy.sex)
        assertEquals(p1.income, p1Copy.income)

        assertEquals(p2.name, p2Copy!!.name)
        assertEquals(p2.age, p2Copy.age)
        assertEquals(p2.sex, p2Copy.sex)
        assertEquals(p2.income, p2Copy.income)

        assertNotSame(p1, p1Copy)
        assertNotSame(p2, p2Copy)
    }

    @Test
    fun `test deep copy with cyclic reference`() {
        val p1 = Person("Dave", 45, 'M', 70000.0, null)
        val p2 = Person("Eve", 42, 'F', 35000.0, p1)
        p1.partner = p2

        val p1Copy = deepCopy(p1)
        val p2Copy = p1Copy!!.partner

        assertNotNull(p1Copy)
        assertNotNull(p2Copy)

        assertEquals(p1.name, p1Copy.name)
        assertEquals(p1.age, p1Copy.age)
        assertEquals(p1.sex, p1Copy.sex)
        assertEquals(p1.income, p1Copy.income)

        assertEquals(p2.name, p2Copy!!.name)
        assertEquals(p2.age, p2Copy.age)
        assertEquals(p2.sex, p2Copy.sex)
        assertEquals(p2.income, p2Copy.income)

        assertNotSame(p1, p1Copy)
        assertNotSame(p2, p2Copy)
    }

    @Test
    fun `test deep copy of null object`() {
        val p1: Person? = null
        val p2 = deepCopy(p1)

        assertNull(p2)
    }
}

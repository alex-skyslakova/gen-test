import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame
import kotlin.test.assertNull

class DeepCopyTest {

    @Test
    fun testDeepCopyWithNull() {
        val original: Person? = null
        val copied = deepCopy(original)
        assertNull(copied)
    }

    @Test
    fun testDeepCopyWithSimplePerson() {
        val original = Person("John", 35, 'M', 50000.0, null)
        val copied = deepCopy(original)

        assertNotSame(original, copied)
        assertEquals(original.name, copied?.name)
        assertEquals(original.age, copied?.age)
        assertEquals(original.sex, copied?.sex)
        assertEquals(original.income, copied?.income)
        assertNull(copied?.partner)
    }

    @Test
    fun testDeepCopyWithPartners() {
        val p1 = Person("John", 35, 'M', 50000.0, null)
        val p2 = Person("Jane", 32, 'F', 25000.0, p1)
        p1.partner = p2

        val copiedP1 = deepCopy(p1)
        val copiedP2 = copiedP1?.partner

        assertNotSame(p1, copiedP1)
        assertNotSame(p2, copiedP2)

        assertEquals(p1.name, copiedP1?.name)
        assertEquals(p1.age, copiedP1?.age)
        assertEquals(p1.sex, copiedP1?.sex)
        assertEquals(p1.income, copiedP1?.income)

        assertEquals(p2.name, copiedP2?.name)
        assertEquals(p2.age, copiedP2?.age)
        assertEquals(p2.sex, copiedP2?.sex)
        assertEquals(p2.income, copiedP2?.income)

        assertNotSame(p1.partner, copiedP1?.partner)
        assertNotSame(p2.partner, copiedP2?.partner)
    }

    @Test
    fun testDeepCopyWithUpdatedValues() {
        var p1 = Person("John", 35, 'M', 50000.0, null)
        val p2 = Person("Jane", 32, 'F', 25000.0, p1)
        p1.partner = p2

        var copiedP1 = deepCopy(p1)
        val copiedP2 = copiedP1?.partner

        // Update original values
        with(p1) {
            age = 37
            income = 55000.0
            partner = null
        }

        copiedP1 = deepCopy(p1)

        assertNotSame(p1, copiedP1)
        assertEquals(p1.name, copiedP1?.name)
        assertEquals(p1.age, copiedP1?.age)
        assertEquals(p1.sex, copiedP1?.sex)
        assertEquals(p1.income, copiedP1?.income)
        assertNull(copiedP1?.partner)
    }
}

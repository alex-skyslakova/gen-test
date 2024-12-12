import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class HickersonTest {

    @Test
    fun testAlmostInteger() {
        // Test cases for n between 1 and 17
        assertTrue(Hickerson.almostInteger(1))
        assertTrue(Hickerson.almostInteger(2))
        assertTrue(Hickerson.almostInteger(3))
        assertTrue(Hickerson.almostInteger(4))
        assertTrue(Hickerson.almostInteger(5))
        assertTrue(Hickerson.almostInteger(6))
        assertTrue(Hickerson.almostInteger(7))
        assertTrue(Hickerson.almostInteger(8))
        assertTrue(Hickerson.almostInteger(9))
        assertTrue(Hickerson.almostInteger(10))
        assertTrue(Hickerson.almostInteger(11))
        assertTrue(Hickerson.almostInteger(12))
        assertTrue(Hickerson.almostInteger(13))
        assertTrue(Hickerson.almostInteger(14))
        assertTrue(Hickerson.almostInteger(15))
        assertTrue(Hickerson.almostInteger(16))
        assertTrue(Hickerson.almostInteger(17))

        // Test cases for n outside the range 1 to 17
        assertFalse(Hickerson.almostInteger(0))
        assertFalse(Hickerson.almostInteger(18))
        assertFalse(Hickerson.almostInteger(100))
    }
}

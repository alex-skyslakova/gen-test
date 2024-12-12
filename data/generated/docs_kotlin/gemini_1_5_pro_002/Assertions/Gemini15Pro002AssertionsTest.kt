import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class AssertionTest {

    @Test
    fun testAssertionPass() {
        val a = 42
        assertEquals(42, a) // Using assertEquals for a passing test
    }

    @Test
    fun testAssertionFail() {
        val a = 42
        assertThrows<AssertionError> {  // Expecting an AssertionError
            assert(a == 43) { "Expected 43 but found $a" }
        }
    }

    @Test
    fun testAssertionFailWithMessage(){
        val a = 42
        val exception = assertThrows<AssertionError> {
            assert(a == 43) {"Expected 43 but found $a"}
        }
        assertEquals("Expected 43 but found $a", exception.message)
    }


}

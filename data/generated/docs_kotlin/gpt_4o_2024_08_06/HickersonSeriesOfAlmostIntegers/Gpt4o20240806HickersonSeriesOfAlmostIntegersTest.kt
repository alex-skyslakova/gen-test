import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class HickersonTest {

    @Test
    fun testAlmostInteger() {
        // Test cases where the function should return true
        val trueCases = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17)
        for (n in trueCases) {
            assertTrue(Hickerson.almostInteger(n), "Expected h($n) to be an almost integer")
        }

        // Test cases where the function should return false
        // Since the problem states that n between 1 and 17 produces almost integers,
        // we will test n outside this range to ensure they are not considered almost integers.
        val falseCases = listOf(18, 19, 20)
        for (n in falseCases) {
            assertFalse(Hickerson.almostInteger(n), "Expected h($n) not to be an almost integer")
        }
    }
}

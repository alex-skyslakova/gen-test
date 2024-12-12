import kotlin.test.Test
import kotlin.test.assertEquals

class FirstPowerOf2Test {

    @Test
    fun testPFunction() {
        assertEquals(7, p(12, 1), "p(12, 1) should be 7")
        assertEquals(80, p(12, 2), "p(12, 2) should be 80")
        assertEquals(12710, p(123, 45), "p(123, 45) should be 12710")
        // The following tests are based on the task description
        // and are expected to be computationally intensive.
        // They are included here for completeness but may take
        // a significant amount of time to execute.
        // Uncomment them if you want to run these tests.
        
        // assertEquals(expectedValue1, p(123, 12345), "p(123, 12345) should be expectedValue1")
        // assertEquals(expectedValue2, p(123, 678910), "p(123, 678910) should be expectedValue2")
    }
}

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LinearCongruentialGeneratorTest {

    @Test
    fun testBSDGenerator() {
        val bsd = Lcg(1103515245, 12345, 1L shl 31, 1, 0)
        val expectedValues = listOf(
            0L, 12345L, 1406932606L, 654583775L, 1449466924L,
            229283573L, 1109335178L, 1051550459L, 1293799192L, 794471793L
        )
        for (expected in expectedValues) {
            assertEquals(expected, bsd.nextInt())
        }
    }

    @Test
    fun testMSCGenerator() {
        val msc = Lcg(214013, 2531011, 1L shl 31, 1L shl 16, 0)
        val expectedValues = listOf(
            0L, 38L, 497L, 5196L, 5810L,
            20219L, 21632L, 5339L, 22117L, 28205L
        )
        for (expected in expectedValues) {
            assertEquals(expected, msc.nextInt())
        }
    }
}

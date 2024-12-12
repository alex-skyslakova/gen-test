import org.junit.Test
import kotlin.test.assertEquals

class FourBitAdderTest {

    @Test
    fun testFourBitAdder() {
        // Test all combinations of two four-bit numbers (0 to 15)
        for (i in 0..15) {
            for (j in 0..15) {
                val a = i.toNybble()
                val b = j.toNybble()
                val (r, c) = fourBitAdder(a, b)
                val expectedSum = i + j
                val expectedCarry = if (expectedSum > 15) 1 else 0
                val expectedResult = expectedSum % 16

                assertEquals(expectedCarry, c, "Carry mismatch for $i + $j")
                assertEquals(expectedResult.toNybble(), r, "Result mismatch for $i + $j")
            }
        }
    }
}

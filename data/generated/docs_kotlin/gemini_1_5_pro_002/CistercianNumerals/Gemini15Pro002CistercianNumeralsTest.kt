import java.io.StringWriter
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CistercianTest {

    private fun convertToExpectedString(cistercian: Cistercian): String {
        return cistercian.toString()
    }

    private fun generateExpectedOutput(number: Int): String {
        val c = Cistercian(number)
        return convertToExpectedString(c)
    }

    @Test
    fun testZero() {
        assertEquals(generateExpectedOutput(0), convertToExpectedString(Cistercian(0)))
    }
    
    @Test
    fun testOne() {
        assertEquals(generateExpectedOutput(1), convertToExpectedString(Cistercian(1)))

    }

    @Test
    fun testTwenty() {
        assertEquals(generateExpectedOutput(20), convertToExpectedString(Cistercian(20)))
    }

    @Test
    fun testThreeHundred() {
        assertEquals(generateExpectedOutput(300), convertToExpectedString(Cistercian(300)))
    }

    @Test
    fun testFourThousand() {
       assertEquals(generateExpectedOutput(4000), convertToExpectedString(Cistercian(4000)))
    }

    @Test
    fun testFiveThousandFiveHundredFiftyFive() {
        assertEquals(generateExpectedOutput(5555), convertToExpectedString(Cistercian(5555)))
    }


    @Test
    fun testSixThousandSevenHundredEightyNine() {
        assertEquals(generateExpectedOutput(6789), convertToExpectedString(Cistercian(6789)))
    }

    @Test
    fun testNineThousandNineHundredNinetyNine() {
        assertEquals(generateExpectedOutput(9999), convertToExpectedString(Cistercian(9999)))
    }



}
